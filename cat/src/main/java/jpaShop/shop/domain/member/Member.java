package jpaShop.shop.domain.member;


import jpaShop.shop.domain.member.controller.request.UpdateMemberRequest;
import jpaShop.shop.domain.member.service.request.MemberDTO;
import jpaShop.shop.domain.member.service.request.UpdateMemberDTO;
import jpaShop.shop.domain.order.Order;
import jpaShop.shop.domain.embbed.Address;
import jakarta.persistence.*;
import jpaShop.shop.global.BaseEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

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
