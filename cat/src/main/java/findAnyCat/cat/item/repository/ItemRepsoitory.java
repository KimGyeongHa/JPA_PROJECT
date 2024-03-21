package findAnyCat.cat.item.repository;


import findAnyCat.cat.item.Item;
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
