package jpaShop.shop.domain.member.service;

import jpaShop.shop.domain.member.service.request.FindMemberRequest;
import jpaShop.shop.domain.member.service.request.MemberDTO;
import jpaShop.shop.domain.member.Member;
import jpaShop.shop.domain.member.repository.MemberRepository;
import jpaShop.shop.domain.member.service.request.UpdateMemberDTO;
import jpaShop.shop.domain.member.service.response.MemberJoinResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long saveMember(MemberDTO memberDTO) {
        memberJoinValidation(memberDTO);
        return memberRepository.saveMember(memberDTO);
    }

    @Override
    @Transactional
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Override
    @Transactional
    public void updateMember(Long memberId, UpdateMemberDTO updateMemberDTO) {
        Member member = memberRepository.findMember(memberId);
        member.updateMember(updateMemberDTO);
    }

    @Override
    @Transactional
    public MemberJoinResponse findMember(FindMemberRequest findMemberRequest) {
        Member member = memberRepository.findMember(findMemberRequest.memberId());
        return MemberJoinResponse.of(
                member.getAddress().getCity(),
                member.getAddress().getStreet(),
                member.getAddress().getZipcode(),
                member.getMemberName()
        );
    }

    public void memberJoinValidation(MemberDTO memberDTO){
        List<Member> findMember = memberRepository.findByName(memberDTO.memberJoinRequest().memberName());
        if(!findMember.isEmpty()) throw new IllegalArgumentException("이미 등록 된 회원입니다.");
    }
}
