package jpaShop.shop.member.repository;


import jpaShop.shop.member.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long saveMember(Member member) {
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
        return em.createQuery("select m from Member m where name=:memberName",Member.class)
                .setParameter("memberName",name).getResultList();
    }



}
