package com.shop.domain.provide.domain.order.repository;

import com.shop.domain.provide.domain.order.Order;
import com.shop.domain.provide.domain.status.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("select o from ORDERS o join fetch o.member m where m.memberName = :memberName and o.status = :orderStatus")
    List<Order> findBySearchOrder(@Param("memberName") String memberName, @Param("orderStatus") OrderStatus orderStatus);

}
