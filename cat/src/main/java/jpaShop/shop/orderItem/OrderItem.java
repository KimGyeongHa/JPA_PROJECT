package jpaShop.shop.orderItem;

import jpaShop.shop.item.Item;
import jpaShop.shop.order.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
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

    /**
     * 상품주문
     * @param item
     * @param orderPrice
     * @param count
     * @return OrderItem
     */
    public static OrderItem createOrderItem(Item item,int orderPrice,int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);

        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.minusStcokQuantity(count);

        return orderItem;
    }


    /**
     * 주문상품취소
     */
    public void cancle(){
        getItem().addStcokQuantity(count);
    }

}
