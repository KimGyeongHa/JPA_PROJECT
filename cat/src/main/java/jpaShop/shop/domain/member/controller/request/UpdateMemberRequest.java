package jpaShop.shop.domain.member.controller.request;

import jakarta.validation.constraints.NotNull;

public record UpdateMemberRequest(
        @NotNull(message = "이름을 입력해주세요.") String memberName,
        String city,
        String zipcode,
        String street
) {
}
