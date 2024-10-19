package com.shop.domain.provide.domain.item.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemJoinRequest(
        @NotBlank(message = "상품명을 입력해주세요.") String itemName,
        @NotNull(message = "가격을 입력해주세요.") @Positive(message = "양수를 입력해주세요.") Integer price,
        @NotNull(message = "수량을 입력해주세요.") @Positive(message = "양수를 입력해주세요.") Integer stockQuantity,
        String artist,
        String etc
) {
}
