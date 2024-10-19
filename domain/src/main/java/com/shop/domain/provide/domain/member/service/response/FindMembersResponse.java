package com.shop.domain.provide.domain.member.service.response;

import java.util.List;

public record FindMembersResponse(
        List<FindMemberResponse> findMemberResponseList
)
{
    public static FindMembersResponse from(
            List<FindMemberResponse> findMemberResponseList
    )
    {
       return new FindMembersResponse(findMemberResponseList);
     }
}
