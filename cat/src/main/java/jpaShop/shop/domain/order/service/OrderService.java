package jpaShop.shop.domain.order.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpaShop.shop.domain.delivery.Delivery;
import jpaShop.shop.domain.item.Item;
import jpaShop.shop.domain.item.QItem;
import jpaShop.shop.domain.item.exception.NotFoundItemException;
import jpaShop.shop.domain.item.repository.ItemRepsoitory;
import jpaShop.shop.domain.member.Member;
import jpaShop.shop.domain.member.exception.MemberNotFoundException;
import jpaShop.shop.domain.member.repository.MemberRepository;
import jpaShop.shop.domain.order.Order;
import jpaShop.shop.domain.order.exception.NotFoundOrderException;
import jpaShop.shop.domain.order.repository.OrderRepository;
import jpaShop.shop.domain.order.service.reponse.OrderSearchResponse;
import jpaShop.shop.domain.order.service.request.OrderDTO;
import jpaShop.shop.domain.order.service.request.OrderSearchRequest;
import jpaShop.shop.domain.orderItem.OrderItem;
import jpaShop.shop.domain.status.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static jpaShop.shop.domain.item.QItem.*;
import static jpaShop.shop.domain.member.QMember.*;
import static jpaShop.shop.domain.order.QOrder.*;
import static jpaShop.shop.domain.orderItem.QOrderItem.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepsoitory itemRepsoitory;
    private JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager em;



    /**
     * 주문
     * @param orderDTO
     */
    @Transactional
    public Long save(OrderDTO orderDTO){

        Member member =findMemberById(orderDTO.orderRequest().memberId());
        Item item = findItemById(orderDTO.orderRequest().itemId());
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), orderDTO.orderRequest().orderStockQuantity());

        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .status(DeliveryStatus.READY)
                .build();

        Order order = Order.saveOrder(member, delivery, orderItem);

        return orderRepository.save(order).getId();
    }

    @Transactional
    public void orderCancle(Long order_id){
        findOrderById(order_id).cancleOrder();
    }

    public List<OrderSearchResponse> findBySearchOrder(OrderSearchRequest orderSearchRequest){

        jpaQueryFactory = new JPAQueryFactory(em);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (orderSearchRequest.memberName() != null)
            booleanBuilder.and(member.memberName.eq(orderSearchRequest.memberName()));
        if (orderSearchRequest.orderStatus() != null)
            booleanBuilder.and(order.status.eq(orderSearchRequest.orderStatus()));


        // projection 테스트

        List<OrderSearchResponse> searchResponses = jpaQueryFactory
                .select(Projections.constructor(
                        OrderSearchResponse.class,
                        order.id,
                        member.memberName,
                        orderItem.item.itemName,
                        orderItem.orderPrice,
                        orderItem.count,
                        order.status,
                        order.date
                ))
                .from(order)
                .join(order.orderItems, orderItem)
                .join(order.member,member)
                .where(booleanBuilder)
                .fetch();


        List<Order> fetch = jpaQueryFactory
                .select(order)
                .from(order)
                .leftJoin(order.member, member).fetchJoin()
                .fetch();


        return searchResponses;
    }

    public Order findOrderById(Long orderId){
        return orderRepository.findById(orderId).orElseThrow(()-> new NotFoundOrderException("등록 된 상품이 없습니다."));
    }

    public Item findItemById(Long userId){
        return itemRepsoitory.findById(userId).orElseThrow(()-> new NotFoundItemException("등록 된 상품이 없습니다."));
    }

    public Member findMemberById(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberNotFoundException("존재하지 않는 회원입니다."));
    }

}
