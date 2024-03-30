package jpaShop.shop.item.repository;


import jpaShop.shop.item.Item;
import jakarta.persistence.EntityManager;
import jpaShop.shop.item.controller.request.ItemJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepsoitory {

    private final EntityManager em;

    public void addItem(Item item){
        em.persist(item);
    }

    public void updateItem(Long itemId,ItemJoinRequest itemJoinRequest){
        Item findItem = em.find(Item.class,itemId);
        findItem.updateItem(itemJoinRequest);
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class).getResultList();
    }

    public List<Item> findItemListById(String id){
        return em.createQuery("select i from Item i where id:=itemId")
                .setParameter("itemId",id).getResultList();
    }





}
