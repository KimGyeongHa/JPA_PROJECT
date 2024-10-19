package com.shop.domain.provide.domain.delivery;


import com.shop.domain.provide.domain.status.DeliveryStatus;
import com.shop.domain.provide.domain.embbed.Address;
import com.shop.domain.provide.domain.order.Order;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @Builder
    public Delivery(Address address, DeliveryStatus status) {
        this.address = address;
        this.status = status;
    }
}
