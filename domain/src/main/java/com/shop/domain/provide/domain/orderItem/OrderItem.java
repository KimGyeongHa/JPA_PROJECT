package com.shop.domain.provide.domain.orderItem;

import com.shop.domain.provide.domain.item.Item;
import com.shop.domain.provide.domain.order.Order;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "ORDERITEM_ID")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    private int orderPrice;

    private int count;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @Builder
    public OrderItem(Order order, int orderPrice, int count, Item item) {
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
        this.item = item;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * 상품주문
     * @param item
     * @param orderPrice
     * @param count
     * @return OrderItem
     */
    public static OrderItem createOrderItem(Item item,int orderPrice,int count){
        item.minusStcokQuantity(count);

        return OrderItem.builder()
                .item(item)
                .orderPrice(orderPrice)
                .count(count)
                .build();
    }


    /**
     * 주문상품취소
     */
    public void cancle(){
        getItem().addStcokQuantity(count);
    }

}
