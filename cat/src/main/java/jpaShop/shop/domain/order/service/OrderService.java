package jpaShop.shop.domain.order.service;

import jpaShop.shop.domain.delivery.Delivery;
import jpaShop.shop.domain.item.Item;
import jpaShop.shop.domain.item.repository.ItemRepsoitory;
import jpaShop.shop.domain.member.Member;
import jpaShop.shop.domain.member.repository.MemberRepository;
import jpaShop.shop.domain.order.Order;
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

        Member member = memberRepository.findMember(orderDTO.orderRequest().memberId());
        Item item = itemRepsoitory.findOne(orderDTO.orderRequest().memberId());
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), orderDTO.orderRequest().orderStockQuantity());

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
