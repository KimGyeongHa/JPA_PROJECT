package com.shop.domain.provide.domain.order.service.request;

import com.shop.domain.provide.domain.status.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderSearchRequest(
        @NotNull(message = "검색할 이름을 입력해주세요.") String memberName
        ,@NotNull(message = "주문상태를 선택해주세요.") OrderStatus orderStatus
)
{
    public static OrderSearchRequest of(
            String memberName,
            OrderStatus orderStatus
    ){
        return new OrderSearchRequest(memberName,orderStatus);
    }
}
