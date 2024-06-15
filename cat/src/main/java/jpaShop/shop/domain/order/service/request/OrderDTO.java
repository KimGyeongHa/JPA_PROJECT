package jpaShop.shop.domain.order.service.request;

import jpaShop.shop.domain.order.controller.request.OrderRequest;

public record OrderDTO(OrderRequest orderRequest) {
    public static OrderDTO of(OrderRequest orderRequest){
        return new OrderDTO(orderRequest);
    }
}
