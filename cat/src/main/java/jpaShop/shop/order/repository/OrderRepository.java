package jpaShop.shop.order.repository;

import jakarta.persistence.TypedQuery;
import jakarta.validation.Valid;
import jpaShop.shop.order.Order;
import jakarta.persistence.EntityManager;
import jpaShop.shop.order.controller.request.OrderSearchRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

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
