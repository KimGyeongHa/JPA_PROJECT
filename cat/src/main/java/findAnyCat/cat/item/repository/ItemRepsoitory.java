package findAnyCat.cat.item.repository;


import findAnyCat.cat.item.Item;
import findAnyCat.cat.item.associate.Book;
import findAnyCat.cat.item.controller.request.ItemJoinRequest;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepsoitory {

    private final EntityManager em;

    public void addItem(Item item){
        if(item.getId() == null) em.persist(item);
        else em.merge(item);
    }

    public Long addItemToBuilder(ItemJoinRequest itemJoinRequest){
        Book item = Book.bookBuilder().name(itemJoinRequest.name())
                .stcokQuantity(itemJoinRequest.stockQuantity())
                .price(itemJoinRequest.price())
                .artist(itemJoinRequest.author())
                .etc(itemJoinRequest.isbn()).build();
        if(item.getId() == null) em.persist(item);
        else em.merge(item);

        return item.getId();
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }

    public List<Item> findItemListByName(String name){
        return em.createQuery("select i from Item i where name:=itemName")
                .setParameter("itemName",name).getResultList();
    }





}
