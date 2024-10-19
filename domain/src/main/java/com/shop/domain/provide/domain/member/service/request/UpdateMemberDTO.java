package com.shop.domain.provide.domain.member.service.request;

import com.shop.domain.provide.domain.member.controller.request.UpdateMemberRequest;

public record UpdateMemberDTO(
        UpdateMemberRequest updateMemberRequest
)
{
    public static UpdateMemberDTO of(
            UpdateMemberRequest updateMemberRequest
    ){
        return new UpdateMemberDTO(updateMemberRequest);
    }
}
