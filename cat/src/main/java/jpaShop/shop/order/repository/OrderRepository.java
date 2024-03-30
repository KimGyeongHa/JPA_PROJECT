package jpaShop.shop.order.repository;

import jpaShop.shop.order.Order;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public Long save(Order order){
        em.persist(order);
        return order.getId();
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }

    public List<Order> orderList(){
        return em.createQuery("select o from Order o").getResultList();
    }

}
