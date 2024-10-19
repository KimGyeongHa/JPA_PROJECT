package com.shop.domain.provide.domain.item.service.request;

import com.shop.domain.provide.domain.item.controller.request.ItemJoinRequest;

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
