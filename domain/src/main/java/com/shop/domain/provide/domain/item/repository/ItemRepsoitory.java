package com.shop.domain.provide.domain.item.repository;


import com.shop.domain.provide.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepsoitory extends JpaRepository<Item,Long> {}
