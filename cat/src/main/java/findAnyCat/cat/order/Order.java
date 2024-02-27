package findAnyCat.cat.order;

import findAnyCat.cat.delivery.Delivery;
import findAnyCat.cat.member.Member;
import findAnyCat.cat.orderItem.OrderItem;
import findAnyCat.cat.status.DeliveryStatus;
import findAnyCat.cat.status.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity(name = "ORDERS")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne(fetch = LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void setOrderItems(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    /**
     * 상품주문
     * @param member
     * @param delivery
     * @param orderItems
     * @return Order
     */
    public static Order saveOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            order.setOrderItems(orderItem);
        }
        order.setDate(LocalDate.now());
        order.setStatus(OrderStatus.ORDER);

        return order;
    }

    /**
     * 주문취소
     */
    public void cancleOrder(){
        if(DeliveryStatus.COMP == delivery.getStatus()) throw new IllegalArgumentException("이미 배송된 상품입니다.");

        this.status = OrderStatus.CANCLE;

        for(OrderItem orderItem : orderItems){
            orderItem.cancle();
        }
    }

    /**
     * 전체 주문가격
     * @return
     */
    public int orderPrice(){
        return orderItems.stream()
                .mapToInt(item -> item.getOrderPrice() * item.getCount()).sum();
    }

}
