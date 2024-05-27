package jpaShop.shop.global.apiException.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String code,
        String message,
        int status
) {

}
