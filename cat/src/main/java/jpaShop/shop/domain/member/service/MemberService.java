package jpaShop.shop.domain.member.service;

import jpaShop.shop.domain.member.service.request.FindMemberRequest;
import jpaShop.shop.domain.member.service.request.MemberDTO;
import jpaShop.shop.domain.member.Member;
import jpaShop.shop.domain.member.service.request.UpdateMemberDTO;
import jpaShop.shop.domain.member.service.response.MemberJoinResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    Long saveMember(MemberDTO memberDTO);
    MemberJoinResponse findMember(FindMemberRequest findMemberRequest);
    List<Member> findMembers();
    void updateMember(Long memberId, UpdateMemberDTO updateMemberDTO);

}
