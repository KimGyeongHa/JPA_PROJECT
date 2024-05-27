package jpaShop.shop.domain.member.service;

import jpaShop.shop.domain.member.service.request.FindMemberRequest;
import jpaShop.shop.domain.member.service.request.MemberDTO;
import jpaShop.shop.domain.member.Member;
import jpaShop.shop.domain.member.repository.MemberRepository;
import jpaShop.shop.domain.member.service.request.UpdateMemberDTO;
import jpaShop.shop.domain.member.service.response.FindMemberResponse;
import jpaShop.shop.domain.member.service.response.FindMembersResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public FindMembersResponse findMembers() {
        return FindMembersResponse.from(
                memberRepository.findAll()
                .stream()
                .map(item -> FindMemberResponse.of(
                        item.getAddress().getCity(),
                        item.getAddress().getStreet(),
                        item.getAddress().getZipcode(),
                        item.getMemberName()
                )).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public void updateMember(Long memberId, UpdateMemberDTO updateMemberDTO) {
        Member member = memberRepository.findMember(memberId);
        member.updateMember(updateMemberDTO);
    }

    @Override
    @Transactional
    public FindMemberResponse findMember(FindMemberRequest findMemberRequest) {
        Member member = memberRepository.findMember(findMemberRequest.memberId());
        return FindMemberResponse.of(
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
