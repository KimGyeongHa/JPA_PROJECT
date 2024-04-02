package jpaShop.shop.order.service;

import jpaShop.shop.delivery.Delivery;
import jpaShop.shop.item.Item;
import jpaShop.shop.item.repository.ItemRepsoitory;
import jpaShop.shop.member.Member;
import jpaShop.shop.member.repository.MemberRepository;
import jpaShop.shop.order.Order;
import jpaShop.shop.order.controller.request.OrderSearchRequest;
import jpaShop.shop.order.repository.OrderRepository;
import jpaShop.shop.orderItem.OrderItem;
import jpaShop.shop.status.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepsoitory itemRepsoitory;

    /**
     * 주문
     * @param member_id
     * @param item_id
     * @param count
     */
    @Transactional
    public Long save(Long member_id,Long item_id,int count){

        Member member = memberRepository.findMember(member_id);
        Item item = itemRepsoitory.findOne(item_id);
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .status(DeliveryStatus.READY)
                .build();

        Order order = Order.saveOrder(member, delivery, orderItem);

        return orderRepository.save(order);
    }

    @Transactional
    public void orderCancle(Long order_id){
        Order order = orderRepository.findOne(order_id);
        order.cancleOrder();
    }
    @Transactional
    public List<Order> getOrderList(OrderSearchRequest orderSearchRequest){
        return orderRepository.orderList(orderSearchRequest);
    }


}
