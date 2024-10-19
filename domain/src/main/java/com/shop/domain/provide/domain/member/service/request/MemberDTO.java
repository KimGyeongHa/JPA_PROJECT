package com.shop.domain.provide.domain.member.service.request;

import com.shop.domain.provide.domain.member.controller.request.MemberJoinRequest;

public record MemberDTO(
        MemberJoinRequest memberJoinRequest)
{
    public static MemberDTO of(MemberJoinRequest memberJoinRequest){
        return new MemberDTO(memberJoinRequest);
    }
}
