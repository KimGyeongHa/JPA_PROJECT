package jpaShop.shop.global.auth.exception.response;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String code,
        String message,
        int status
) {

}
