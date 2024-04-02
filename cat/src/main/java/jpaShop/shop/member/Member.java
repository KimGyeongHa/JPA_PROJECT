package jpaShop.shop.member;


import jpaShop.shop.embbed.Address;
import jpaShop.shop.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String memberName;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    List<Order> orders = new ArrayList<>();

    public void setOrders(Order order){
        orders.add(order);
        order.setMember(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return memberName;
    }

    public void setName(String memberName) {
        this.memberName = memberName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
