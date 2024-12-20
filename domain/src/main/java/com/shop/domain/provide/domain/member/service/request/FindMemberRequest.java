package com.shop.domain.provide.domain.member.service.request;

public record FindMemberRequest(
        Long memberId
)
{
    public static FindMemberRequest of(Long memberId){
        return new FindMemberRequest(memberId);
    }
}
