package findAnyCat.cat.item.service;


import findAnyCat.cat.item.Item;
import findAnyCat.cat.item.associate.Book;
import findAnyCat.cat.item.service.request.ItemJoinRequest;
import findAnyCat.cat.item.repository.ItemRepsoitory;
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
    public void saveItemToBuilder(ItemJoinRequest itemJoinRequest){
        Item item = Book.bookBuilder().name(itemJoinRequest.name())
                .stcokQuantity(itemJoinRequest.stockQuantity())
                .price(itemJoinRequest.price())
                .artist(itemJoinRequest.author())
                .etc(itemJoinRequest.isbn()).build();
        itemRepsoitory.addItem(item);
    }

    public Item findItem(Long id){
        return itemRepsoitory.findOne(id);
    }

    public List<Item> findAllItem(){
        return itemRepsoitory.findAll();
    }
}
