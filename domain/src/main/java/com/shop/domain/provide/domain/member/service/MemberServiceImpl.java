package com.shop.domain.provide.domain.member.service;

import com.shop.domain.provide.domain.embbed.Address;
import com.shop.domain.provide.domain.member.Member;
import com.shop.domain.provide.domain.member.controller.request.MemberJoinRequest;
import com.shop.domain.provide.domain.member.exception.MemberNotFoundException;
import com.shop.domain.provide.domain.member.exception.MemberOverlappingException;
import com.shop.domain.provide.domain.member.repository.MemberRepository;
import com.shop.domain.provide.domain.member.service.request.FindMemberRequest;
import com.shop.domain.provide.domain.member.service.request.MemberDTO;
import com.shop.domain.provide.domain.member.service.request.UpdateMemberDTO;
import com.shop.domain.provide.domain.member.service.response.FindMemberResponse;
import com.shop.domain.provide.domain.member.service.response.FindMembersResponse;
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
        Member member = findMemberById(findMemberRequest.memberId());
        return FindMemberResponse.of(
                member.getAddress().getCity(),
                member.getAddress().getStreet(),
                member.getAddress().getZipcode(),
                member.getMemberName()
        );
    }

    @Override
    public FindMembersResponse findAllMembers() {
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
