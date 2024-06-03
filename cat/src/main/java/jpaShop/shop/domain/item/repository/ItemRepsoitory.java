package jpaShop.shop.domain.item.repository;


import jpaShop.shop.domain.item.Item;
import jakarta.persistence.EntityManager;
import jpaShop.shop.domain.item.controller.request.ItemJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ItemRepsoitory extends JpaRepository<Item,Long> { }
