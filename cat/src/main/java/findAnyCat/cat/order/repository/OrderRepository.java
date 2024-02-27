package findAnyCat.cat.order.repository;

import findAnyCat.cat.delivery.Delivery;
import findAnyCat.cat.member.Member;
import findAnyCat.cat.order.Order;
import findAnyCat.cat.orderItem.OrderItem;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.stereotype.Repository;

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

}
