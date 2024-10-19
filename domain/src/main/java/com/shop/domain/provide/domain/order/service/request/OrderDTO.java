package com.shop.domain.provide.domain.order.service.request;

import com.shop.domain.provide.domain.order.controller.request.OrderRequest;

public record OrderDTO(
        OrderRequest orderRequest
)
{
    public static OrderDTO of(OrderRequest orderRequest){
        return new OrderDTO(orderRequest);
    }
}
