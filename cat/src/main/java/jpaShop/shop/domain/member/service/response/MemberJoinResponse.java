package jpaShop.shop.domain.member.service.response;

public record MemberJoinResponse(
        String city
        ,String street
        ,String zipCode
        ,String memberName
)
{
    public static MemberJoinResponse of(
            String city,
            String street,
            String zipCode,
            String memberName
    ){
        return new MemberJoinResponse(city, street, zipCode, memberName);
    }
}
