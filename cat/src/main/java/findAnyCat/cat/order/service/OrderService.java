package findAnyCat.cat.order.service;

import findAnyCat.cat.delivery.Delivery;
import findAnyCat.cat.item.Item;
import findAnyCat.cat.item.repository.ItemRepsoitory;
import findAnyCat.cat.member.Member;
import findAnyCat.cat.member.repository.MemberRepository;
import findAnyCat.cat.order.Order;
import findAnyCat.cat.order.repository.OrderRepository;
import findAnyCat.cat.orderItem.OrderItem;
import findAnyCat.cat.status.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        Order order = Order.saveOrder(member, delivery, orderItem);

        return orderRepository.save(order);
    }

    @Transactional
    public void orderCancle(Long order_id){
        Order order = orderRepository.findOne(order_id);
        order.cancleOrder();
    }


}
