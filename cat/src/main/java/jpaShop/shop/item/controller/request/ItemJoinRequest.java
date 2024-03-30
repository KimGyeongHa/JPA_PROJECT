package jpaShop.shop.item.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemJoinRequest(
        @NotBlank(message = "상품명을 입력해주세요.") String name,
        @NotNull(message = "가격을 입력해주세요.") @Positive(message = "양수를 입력해주세요.") int price,
        @NotNull(message = "수량을 입력해주세요.") @Positive(message = "양수를 입력해주세요.") int stockQuantity,
        String artist,
        String etc
) {
    public static ItemJoinRequest of(
            String name,
            int price,
            int stockQuantity,
            String artist,
            String etc
    ){
        return new ItemJoinRequest(name, price, stockQuantity, artist, etc);
    }
}
