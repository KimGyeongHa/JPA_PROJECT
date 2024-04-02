package jpaShop.shop.member.service.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MemberJoinRequest(
        @NotNull(message = "이름을 입력해주세요.") String memberName,
        String city,
        String zipcode,
        String street
) {
    public MemberJoinRequest{

    }
}
