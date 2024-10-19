package com.shop.domain.provide.domain.item.associate;

import com.shop.domain.provide.domain.item.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@DiscriminatorValue("MOVIE")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Movie extends Item {
    @Builder(builderMethodName = "movieBuilder")
    public Movie(String itemName, int price, int stockQuantity, String artist, String etc) {
        super(itemName, price, stockQuantity, artist, etc);
    }
}
