package jpaShop.shop.domain.item.service;


import jpaShop.shop.domain.item.associate.Album;
import jpaShop.shop.domain.item.controller.request.ItemJoinRequest;
import jpaShop.shop.domain.item.repository.ItemRepsoitory;
import jpaShop.shop.domain.item.service.request.ItemDTO;
import jpaShop.shop.domain.item.Item;
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
    public void saveItemToBuilder(ItemDTO itemDTO){

        ItemJoinRequest itemJoinRequest = itemDTO.itemJoinRequest();

        Item item = Album.albumBuilder().itemName(itemJoinRequest.name())
                .stockQuantity(itemJoinRequest.stockQuantity())
                .price(itemJoinRequest.price())
                .artist(itemJoinRequest.artist())
                .etc(itemJoinRequest.etc()).build();
        itemRepsoitory.addItem(item);
    }

    @Transactional
    public void updateItem(Long itemId,ItemJoinRequest itemJoinRequest){
        itemRepsoitory.updateItem(itemId, itemJoinRequest);
    }

    public Item findItem(Long id){
        return itemRepsoitory.findOne(id);
    }

    public List<Item> findAllItem(){
        return itemRepsoitory.findAll();
    }
}
