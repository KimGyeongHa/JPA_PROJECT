package jpaShop.shop.domain.order.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
        @NotNull(message = "회원을 선택해주세요.") Long memberId
        ,@NotNull(message = "상품을 선택해주세요.") Long itemId
        ,@NotNull(message = "수량을 입력해주세요.") @Positive(message = "양수만 입력가능합니다.") Integer orderStockQuantity
)
{
}
