package jpaShop.shop.delivery;


import jpaShop.shop.embbed.Address;
import jpaShop.shop.order.Order;
import jpaShop.shop.status.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    public void setOrders(Order order){
        order.setDelivery(this);
        this.order = order;
    }

}
