package com.shop.domain.provide.domain.member.service;

import com.shop.domain.provide.domain.member.service.request.FindMemberRequest;
import com.shop.domain.provide.domain.member.service.request.MemberDTO;
import com.shop.domain.provide.domain.member.service.request.UpdateMemberDTO;
import com.shop.domain.provide.domain.member.service.response.FindMemberResponse;
import com.shop.domain.provide.domain.member.service.response.FindMembersResponse;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    Long saveMember(MemberDTO memberDTO);
    FindMemberResponse findMember(FindMemberRequest findMemberRequest);
    FindMembersResponse findAllMembers();
    void updateMember(Long memberId, UpdateMemberDTO updateMemberDTO);

}
