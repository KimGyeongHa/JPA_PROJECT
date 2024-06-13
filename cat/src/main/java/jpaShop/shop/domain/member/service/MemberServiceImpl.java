package jpaShop.shop.domain.member.service;

import jpaShop.shop.domain.embbed.Address;
import jpaShop.shop.domain.member.controller.request.MemberJoinRequest;
import jpaShop.shop.domain.member.exception.MemberNotFoundException;
import jpaShop.shop.domain.member.exception.MemberOverlappingException;
import jpaShop.shop.domain.member.service.request.FindMemberRequest;
import jpaShop.shop.domain.member.service.request.MemberDTO;
import jpaShop.shop.domain.member.Member;
import jpaShop.shop.domain.member.repository.MemberRepository;
import jpaShop.shop.domain.member.service.request.UpdateMemberDTO;
import jpaShop.shop.domain.member.service.response.FindMemberResponse;
import jpaShop.shop.domain.member.service.response.FindMembersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

        MemberJoinRequest memberJoinRequest = memberDTO.memberJoinRequest();
        Member member =
                Member.builder()
                        .memberName(memberJoinRequest.memberName())
                        .address(
                                new Address(
                                        memberJoinRequest.city()
                                        ,memberJoinRequest.street()
                                        ,memberJoinRequest.zipcode()
                                )
                        ).build();
        return memberRepository.save(member).getId();
    }

    @Override
    @Transactional
    public void updateMember(Long memberId, UpdateMemberDTO updateMemberDTO) {
        findMemberById(memberId).updateMember(updateMemberDTO);
    }

    @Override
    public FindMemberResponse findMember(FindMemberRequest findMemberRequest) {
        return FindMemberResponse.from(
                findMemberById(findMemberRequest.memberId())
        );
    }

    @Override
    public FindMembersResponse findAllMembers() {
        return FindMembersResponse.of(memberRepository.findAll());
    }

    public Member findMemberById(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberNotFoundException("존재하지 않는 회원입니다."));
    }

    public void memberJoinValidation(MemberDTO memberDTO){
        memberRepository.findMemberByMemberName(memberDTO.memberJoinRequest().memberName())
                .ifPresent(member -> {
                    throw new MemberOverlappingException("이미 등록 된 회원입니다.");
                });
    }

}
