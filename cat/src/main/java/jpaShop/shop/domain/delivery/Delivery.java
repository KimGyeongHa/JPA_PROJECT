package jpaShop.shop.domain.delivery;


import jpaShop.shop.domain.embbed.Address;
import jpaShop.shop.domain.order.Order;
import jpaShop.shop.domain.status.DeliveryStatus;
import jakarta.persistence.*;
import jpaShop.shop.global.BaseEntity;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseEntity {

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
    @Builder
    public Delivery(Address address, DeliveryStatus status) {
        this.address = address;
        this.status = status;
    }
}
