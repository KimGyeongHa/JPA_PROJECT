package jpaShop.shop.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpaShop.shop.domain.member.Member;
import jpaShop.shop.domain.member.QMember;
import jpaShop.shop.domain.member.controller.request.MemberJoinRequest;
import jpaShop.shop.domain.member.exception.MemberOverlappingException;
import jpaShop.shop.domain.member.service.MemberService;
import jpaShop.shop.domain.member.service.request.MemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class MemberControllerTest {

    @Autowired private MemberService memberService;

    @BeforeEach
    void init(){
        getMember(memberService);
    }

    @Test
    void 중복멤버회원가입() throws Exception{
        Assertions.assertThrows(MemberOverlappingException.class,()->getMember(memberService));
    }

    private static Long getMember(MemberService memberService) {
        return memberService.saveMember(
                MemberDTO.of(
                        new MemberJoinRequest("김경하","경기도","시흥시","888")
                ));
    }
}
