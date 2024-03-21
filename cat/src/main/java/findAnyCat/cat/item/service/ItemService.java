package findAnyCat.cat.item.service;


import findAnyCat.cat.item.Item;
import findAnyCat.cat.item.associate.Book;
import findAnyCat.cat.item.controller.request.ItemJoinRequest;
import findAnyCat.cat.item.repository.ItemRepsoitory;
import findAnyCat.cat.item.service.dto.ItemDTO;
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
    public Long saveItem(Item item){
        itemRepsoitory.addItem(item);
        return item.getId();
    }

    @Transactional
    public void saveItemToBuilder(ItemDTO itemDTO){
        Item item = Book.bookBuilder().name(itemDTO.name())
                .stcokQuantity(itemDTO.stockQuantity())
                .price(itemDTO.price())
                .artist(itemDTO.author())
                .etc(itemDTO.isbn()).build();
        itemRepsoitory.addItem(item);
    }

    public Item findItem(Long id){
        return itemRepsoitory.findOne(id);
    }

    public List<Item> findAllItem(){
        return itemRepsoitory.findAll();
    }
}
