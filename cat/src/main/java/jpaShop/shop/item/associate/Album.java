package jpaShop.shop.item.associate;

import jpaShop.shop.item.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@DiscriminatorValue("ALBUM")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Album extends Item {
    @Builder(builderMethodName = "albumBuilder")
    public Album(String itemName, int price, int stockQuantity, String artist, String etc) {
        super(itemName, price, stockQuantity, artist, etc);
    }
}
