package jpaShop.shop.domain.item.service.request;

import jpaShop.shop.domain.item.controller.request.ItemJoinRequest;

public record ItemDTO(
    ItemJoinRequest itemJoinRequest
) {
    public static ItemDTO of(
            ItemJoinRequest itemJoinRequest
    )
    {
        return new ItemDTO(itemJoinRequest);
    }
}
