package jpaShop.shop.item.associate;

import jpaShop.shop.item.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@DiscriminatorValue("BOOK")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Book extends Item {
    @Builder(builderMethodName = "bookBuilder")
    public Book(String itemName, int price, int stockQuantity, String artist, String etc) {
        super(itemName, price, stockQuantity, artist, etc);
    }
}
