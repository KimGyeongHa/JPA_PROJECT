package jpaShop.shop.delivery;


import jpaShop.shop.embbed.Address;
import jpaShop.shop.order.Order;
import jpaShop.shop.status.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.ast.Or;

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
