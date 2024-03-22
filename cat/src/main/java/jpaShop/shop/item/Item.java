package jpaShop.shop.item;

import jpaShop.shop.category.Category;
import jpaShop.shop.exception.NoEnoughStcokException;
import jpaShop.shop.orderItem.OrderItem;
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

    private String name;
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

    @Builder
    public Item(String name, int price, int stockQuantity, String artist, String etc) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.artist = artist;
        this.etc = etc;
    }
}
