package jpaShop.shop.domain.member.service.response;

import jpaShop.shop.domain.member.Member;

public record FindMemberResponse(
        Long memberId
        ,String city
        ,String street
        ,String zipCode
        ,String memberName
)
{
    public static FindMemberResponse from(Member member){
        return new FindMemberResponse(
                member.getId(),
                member.getAddress().getCity(),
                member.getAddress().getStreet(),
                member.getAddress().getZipcode(),
                member.getMemberName()
        );
    }


}
