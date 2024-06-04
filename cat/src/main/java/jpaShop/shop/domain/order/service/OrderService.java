package jpaShop.shop.domain.order.service;

import jpaShop.shop.domain.delivery.Delivery;
import jpaShop.shop.domain.item.Item;
import jpaShop.shop.domain.item.exception.NotFoundItemException;
import jpaShop.shop.domain.item.repository.ItemRepsoitory;
import jpaShop.shop.domain.item.service.ItemService;
import jpaShop.shop.domain.member.Member;
import jpaShop.shop.domain.member.exception.MemberNotFoundException;
import jpaShop.shop.domain.member.repository.MemberRepository;
import jpaShop.shop.domain.order.Order;
import jpaShop.shop.domain.order.exception.NotFoundOrderException;
import jpaShop.shop.domain.order.repository.OrderRepository;
import jpaShop.shop.domain.order.service.request.OrderDTO;
import jpaShop.shop.domain.order.service.request.OrderSearchRequest;
import jpaShop.shop.domain.orderItem.OrderItem;
import jpaShop.shop.domain.status.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepsoitory itemRepsoitory;

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

    public List<Order> findBySearchOrder(OrderSearchRequest orderSearchRequest){
        return orderRepository.findBySearchOrder(orderSearchRequest.memberName(), orderSearchRequest.orderStatus());
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
