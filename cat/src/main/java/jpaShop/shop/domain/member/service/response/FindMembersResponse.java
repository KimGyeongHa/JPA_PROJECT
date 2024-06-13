package jpaShop.shop.domain.member.service.response;

import jpaShop.shop.domain.member.Member;

import java.util.List;
import java.util.stream.Collectors;

public record FindMembersResponse(
        List<FindMemberResponse> findMemberResponseList
)
{
    public static FindMembersResponse of(List<Member> findMemberResponseList)
    {
       return new FindMembersResponse(
            findMemberResponseList.stream()
            .map(FindMemberResponse::from)
            .collect(Collectors.toList())
       );
     }
}
