package jpaShop.shop.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpaShop.shop.domain.member.Member;
import jpaShop.shop.domain.member.projection.MemberClassProjections;
import jpaShop.shop.domain.member.projection.MemberProjections;
import jpaShop.shop.domain.member.controller.request.MemberJoinRequest;
import jpaShop.shop.domain.member.exception.MemberOverlappingException;
import jpaShop.shop.domain.member.repository.MemberRepository;
import jpaShop.shop.domain.member.service.MemberService;
import jpaShop.shop.domain.member.service.request.MemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@SpringBootTest
@Transactional
public class MemberControllerTest {

    @Autowired private MemberService memberService;
    @Autowired private MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager em;

    private JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void init(){
        jpaQueryFactory = new JPAQueryFactory(em);
        getMember(memberService);
    }


    @Test
    void 중복멤버회원가입() throws Exception{
        Assertions.assertThrows(
                MemberOverlappingException.class,()->getMember(memberService)
        );
    }


    @Test
    void 회원프로젝션(){
        List<MemberProjections> memberProjections = memberRepository.findMemberProjectionByMemberName("김경삵");

        Iterator<MemberProjections> iterator = memberProjections.iterator();

        while (iterator.hasNext()){
            MemberProjections projection = iterator.next();
        }
    }

    @Test
    void 회원클래스프로젝션(){
        List<MemberClassProjections> memberClassProjections = memberRepository.findMemberClassProjectionByMemberName("김경삵", MemberClassProjections.class);

    }


    @Test
    void 회원조회(){
        List<Member> all = memberRepository.findAll();

        for(Member member : all){
            System.out.println("member_id : " + member.getId());
        }
    }


    private static Long getMember(MemberService memberService) {
        return memberService.saveMember(
                MemberDTO.of(
                        new MemberJoinRequest("김경삵","경기도","시흥시","888")
                ));
    }
}
