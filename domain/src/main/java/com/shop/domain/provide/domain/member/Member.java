package com.shop.domain.provide.domain.member;


import com.shop.domain.provide.domain.embbed.Address;
import com.shop.domain.provide.domain.member.controller.request.UpdateMemberRequest;
import com.shop.domain.provide.domain.member.service.request.UpdateMemberDTO;
import com.shop.domain.provide.domain.order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @Builder
    public Member(String memberName, Address address) {
        this.memberName = memberName;
        this.address = address;
    }

    public void updateMember(UpdateMemberDTO updateMemberDTO){
        UpdateMemberRequest updateMemberRequest = updateMemberDTO.updateMemberRequest();
        this.memberName = updateMemberRequest.memberName();
        this.address = new Address(updateMemberRequest.city(),updateMemberRequest.street(),updateMemberRequest.zipcode());
    }
}
