package jpaShop.shop.domain.member.service.request;

import jpaShop.shop.domain.member.controller.request.MemberJoinRequest;

public record MemberDTO(
        MemberJoinRequest memberJoinRequest)
{
    public static MemberDTO of(MemberJoinRequest memberJoinRequest){
        return new MemberDTO(memberJoinRequest);
    }
}
