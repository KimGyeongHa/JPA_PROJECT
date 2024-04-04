package jpaShop.shop.domain.member.repository;


import jpaShop.shop.domain.member.service.request.MemberDTO;
import jpaShop.shop.domain.embbed.Address;
import jpaShop.shop.domain.member.Member;
import jakarta.persistence.EntityManager;
import jpaShop.shop.domain.member.controller.request.MemberJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long saveMember(MemberDTO memberDTO) {
        MemberJoinRequest memberJoinRequest = memberDTO.memberJoinRequest();
        Member member =
                Member.builder()
                        .memberName(memberJoinRequest.memberName())
                        .address(new Address(memberJoinRequest.city(),memberJoinRequest.street(),memberJoinRequest.zipcode()))
                        .build();
        em.persist(member);
        return member.getId();
    }

    public Member findMember(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){
        return  em.createQuery("select m from Member m",Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where memberName=:name",Member.class)
                .setParameter("name",name).getResultList();
    }



}
