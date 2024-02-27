package findAnyCat.cat.item.service;


import findAnyCat.cat.item.Item;
import findAnyCat.cat.item.controller.request.ItemJoinRequest;
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
        itemRepsoitory.addItemToBuilder(itemJoinRequest);
    }

    public Item findItem(Long id){
        return itemRepsoitory.findOne(id);
    }

    public List<Item> findAllItem(){
        return itemRepsoitory.findAll();
    }
}
