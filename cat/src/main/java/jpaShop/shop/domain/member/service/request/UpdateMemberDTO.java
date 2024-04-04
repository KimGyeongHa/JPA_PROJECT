package jpaShop.shop.domain.member.service.request;

import jakarta.validation.constraints.NotNull;
import jpaShop.shop.domain.member.controller.request.UpdateMemberRequest;

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
