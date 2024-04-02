package jpaShop.shop.item.service;


import jpaShop.shop.item.Item;
import jpaShop.shop.item.associate.Album;
import jpaShop.shop.item.controller.request.ItemJoinRequest;
import jpaShop.shop.item.repository.ItemRepsoitory;
import jpaShop.shop.item.service.dto.ItemDTO;
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
    public void saveItemToBuilder(ItemJoinRequest itemJoinRequest){

        ItemDTO itemDTO = ItemDTO.of(
                itemJoinRequest.name()
                ,itemJoinRequest.price()
                ,itemJoinRequest.stockQuantity()
                ,itemJoinRequest.artist()
                ,itemJoinRequest.etc()
        );

        Item item = Album.albumBuilder().itemName(itemDTO.name())
                .stockQuantity(itemDTO.stockQuantity())
                .price(itemDTO.price())
                .artist(itemDTO.author())
                .etc(itemDTO.isbn()).build();
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
