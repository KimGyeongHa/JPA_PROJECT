package jpaShop.shop.domain.order.repository;

import jakarta.persistence.TypedQuery;
import jpaShop.shop.domain.order.Order;
import jpaShop.shop.domain.order.service.request.OrderSearchRequest;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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

    public List<Order> orderList(OrderSearchRequest orderSearchRequest){

        boolean prefixCheck = false;
        String query = "select o from ORDERS o join o.member m ";

        if(StringUtils.hasText(orderSearchRequest.memberName())) {
            query += "where m.memberName like :name";
            prefixCheck = true;
        }

        if (orderSearchRequest.orderStatus() != null){
            if (prefixCheck) query += " and o.status= :orderStatus";
            else query += "where o.status= :orderStatus";
        }

        TypedQuery<Order> orderTypedQuery =
                em.createQuery(query,Order.class);

        if(StringUtils.hasText(orderSearchRequest.memberName())) {
            orderTypedQuery.setParameter("name",orderSearchRequest.memberName());
        }

        if (orderSearchRequest.orderStatus() != null)  {
            orderTypedQuery.setParameter("orderStatus",orderSearchRequest.orderStatus());
        }


        return orderTypedQuery.getResultList();
    }

}
