package jpaShop.shop.domain.item.service;


import jpaShop.shop.domain.item.associate.Album;
import jpaShop.shop.domain.item.controller.request.ItemJoinRequest;
import jpaShop.shop.domain.item.exception.NotFoundItemException;
import jpaShop.shop.domain.item.repository.ItemRepsoitory;
import jpaShop.shop.domain.item.service.request.ItemDTO;
import jpaShop.shop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepsoitory itemRepsoitory;

    @Transactional
    public void saveItemToBuilder(ItemDTO itemDTO){
        ItemJoinRequest itemJoinRequest = itemDTO.itemJoinRequest();

        Item item = Album.albumBuilder().itemName(itemJoinRequest.itemName())
                .stockQuantity(itemJoinRequest.stockQuantity())
                .price(itemJoinRequest.price())
                .artist(itemJoinRequest.artist())
                .etc(itemJoinRequest.etc()).build();
        itemRepsoitory.save(item);
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
