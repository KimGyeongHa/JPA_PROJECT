package com.shop.domain.provide.domain.member.service.response;

public record FindMemberResponse(
        String city
        ,String street
        ,String zipCode
        ,String memberName
)
{
    public static FindMemberResponse of(
            String city,
            String street,
            String zipCode,
            String memberName
    ){
        return new FindMemberResponse(city, street, zipCode, memberName);
    }
}
