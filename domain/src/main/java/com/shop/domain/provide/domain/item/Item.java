package com.shop.domain.provide.domain.item;

import com.shop.domain.provide.domain.category.Category;
import com.shop.domain.provide.domain.orderItem.OrderItem;
import com.shop.domain.provide.domain.item.controller.request.ItemJoinRequest;
import com.shop.domain.provide.domain.item.exception.NoEnoughStcokException;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String itemName;
    private int price;
    private int stockQuantity;
    private String artist;
    private String etc;

    @ManyToMany(mappedBy = "items")
    private List<Category> category = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * 상품수량증가
     * @param stockQuantity
     */
    public void addStcokQuantity(int stockQuantity){
        this.stockQuantity += stockQuantity;
    }

    /**
     * 상품수량감소
     * @param stockQuantity
     */
    public void minusStcokQuantity(int stockQuantity){
        int resetStock = this.stockQuantity - stockQuantity;
        if(resetStock < 0)
            throw new NoEnoughStcokException("등록 된 상품의 수량보다 더 많은 수량을 주문하셨습니다.");

        this.stockQuantity = resetStock;
    }

    /**
     * 상품수정
     * @param itemJoinRequest
     */
    public void updateItem(ItemJoinRequest itemJoinRequest){
        this.itemName = itemJoinRequest.itemName();
        this.stockQuantity = itemJoinRequest.stockQuantity();
        this.artist = itemJoinRequest.artist();
        this.etc = itemJoinRequest.etc();
        this.price = itemJoinRequest.price();
    }

    @Builder
    public Item(String itemName, int price, int stockQuantity, String artist, String etc) {
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.artist = artist;
        this.etc = etc;
    }
}
