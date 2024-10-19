package com.shop.domain.provide.global.auth.exception.response;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String code,
        String message,
        int status
) {

}
