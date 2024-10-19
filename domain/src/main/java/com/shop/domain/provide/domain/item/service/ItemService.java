package com.shop.domain.provide.domain.item.service;


import com.shop.domain.provide.domain.item.Item;
import com.shop.domain.provide.domain.item.service.request.ItemDTO;
import com.shop.domain.provide.domain.item.associate.Album;
import com.shop.domain.provide.domain.item.controller.request.ItemJoinRequest;
import com.shop.domain.provide.domain.item.exception.NotFoundItemException;
import com.shop.domain.provide.domain.item.repository.ItemRepsoitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepsoitory itemRepsoitory;

    @Transactional
    public Long saveItem(ItemDTO itemDTO){
        ItemJoinRequest itemJoinRequest = itemDTO.itemJoinRequest();

        Item item = Album.albumBuilder().itemName(itemJoinRequest.itemName())
                .stockQuantity(itemJoinRequest.stockQuantity())
                .price(itemJoinRequest.price())
                .artist(itemJoinRequest.artist())
                .etc(itemJoinRequest.etc()).build();
        return itemRepsoitory.save(item).getId();
    }

    @Transactional
    public void updateItem(Long itemId,ItemJoinRequest itemJoinRequest){
        findItemByItemId(itemId).updateItem(itemJoinRequest);
    }

    public Item findItem(Long itemId){
        return findItemByItemId(itemId);
    }

    public List<Item> findAllItem(){
        return itemRepsoitory.findAll();
    }

    public Item findItemByItemId(Long ItemId){
        return itemRepsoitory.findById(ItemId).orElseThrow(()->new NotFoundItemException("존재하지 않는 상품입니다."));
    }


}
