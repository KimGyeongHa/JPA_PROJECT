package findAnyCat.cat.item;

import findAnyCat.cat.category.Category;
import findAnyCat.cat.exception.NoEnoughStcokException;
import findAnyCat.cat.orderItem.OrderItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter @Setter
public class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stcokQuantity;
    private String artist;
    private String etc;

    @ManyToMany(mappedBy = "items")
    private List<Category> category = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * 상품수량증가
     * @param stcokQuantity
     */
    public void addStcokQuantity(int stcokQuantity){
        this.stcokQuantity += stcokQuantity;
    }

    /**
     * 상품수량감소
     * @param stcokQuantity
     */
    public void minusStcokQuantity(int stcokQuantity){
        int resetStock = this.stcokQuantity - stcokQuantity;
        if(resetStock < 0)
            throw new NoEnoughStcokException("등록 된 상품의 수량보다 더 많은 수량을 주문하셨습니다.");

        this.stcokQuantity = resetStock;
    }

    @Builder
    public Item(String name, int price, int stcokQuantity, String artist, String etc) {
        this.name = name;
        this.price = price;
        this.stcokQuantity = stcokQuantity;
        this.artist = artist;
        this.etc = etc;
    }
}
