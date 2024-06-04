package jpaShop.shop.domain.item.repository;


import jpaShop.shop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepsoitory extends JpaRepository<Item,Long> {}
