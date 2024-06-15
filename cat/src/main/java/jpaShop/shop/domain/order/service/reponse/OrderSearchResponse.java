package jpaShop.shop.domain.order.service.reponse;

import jpaShop.shop.domain.status.OrderStatus;

import java.time.LocalDate;

public record OrderSearchResponse(
        Long itemId
        , String memberName
        , String itemName
        , int orderPrice
        , int orderCount
        , OrderStatus orderStatus
        , LocalDate orderDate
        ) {
}
