package jpaShop.shop;

import jpaShop.shop.member.Member;
import jpaShop.shop.member.service.MemberService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberJoin {

    @Autowired private MemberService memberService;
    @Autowired private EntityManager em;

    @Test
    void memberJoin(){
        Member member = new Member();
        member.setName("김경하");
        Long id = memberService.saveMember(member);

        em.flush();
        assertThat(member.getClass()).isEqualTo(memberService.findMember(id).getClass());
    }

    @Test
    void 중복멤버회원가입(){
        Member member = new Member();
        member.setName("김경하");

        Member member1 = new Member();
        member1.setName("하경김");

        memberService.saveMember(member);

        try{
            memberService.saveMember(member1);
        }catch (IllegalArgumentException e){
           fail(e.getMessage());
        }


    }


}
