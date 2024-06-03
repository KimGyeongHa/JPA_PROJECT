package jpaShop.shop.member;

import jpaShop.shop.domain.member.controller.request.MemberJoinRequest;
import jpaShop.shop.domain.member.service.MemberService;
import jpaShop.shop.domain.member.service.request.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.fail;


@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired private MemberService memberService;

    @Test
    void 중복멤버회원가입(){
        memberService.saveMember(MemberDTO.of(new MemberJoinRequest("김경하","","","")));

        try{
            memberService.saveMember(MemberDTO.of(new MemberJoinRequest("김경하","","","")));
        }catch (IllegalArgumentException e){
            fail(e.getMessage());
        }
    }

}
