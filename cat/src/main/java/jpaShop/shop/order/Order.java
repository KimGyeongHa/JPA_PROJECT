package jpaShop.shop.order;

import jpaShop.shop.delivery.Delivery;
import jpaShop.shop.member.Member;
import jpaShop.shop.orderItem.OrderItem;
import jpaShop.shop.status.DeliveryStatus;
import jpaShop.shop.status.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity(name = "ORDERS")
@Getter
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

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setOrderItems(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    /**
     * 상품주문
     * @param member
     * @param delivery
     * @param requestOrderItems
     * @return Order
     */
    public static Order saveOrder(Member member, Delivery delivery, OrderItem... requestOrderItems){
        List<OrderItem> orderItemList = new ArrayList<>();

        if(orderItemList.size() > 0)
            Arrays.stream(requestOrderItems).toList().forEach(item -> orderItemList.add(item));

        return  Order.builder()
                .member(member)
                .delivery(delivery)
                .date(LocalDate.now())
                .orderItems(orderItemList)
                .status(OrderStatus.ORDER).build();
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

    @Builder
    public Order(LocalDate date, OrderStatus status, Member member, Delivery delivery, List<OrderItem> orderItems) {
        this.date = date;
        this.status = status;
        this.member = member;
        this.delivery = delivery;
        this.orderItems = orderItems;
    }
}
