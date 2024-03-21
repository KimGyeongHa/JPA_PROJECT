package findAnyCat.cat.item.associate;

import findAnyCat.cat.item.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@DiscriminatorValue("BOOK")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Book extends Item {
    @Builder(builderMethodName = "bookBuilder")
    public Book(String name, int price, int stockQuantity, String artist, String etc) {
        super(name, price, stockQuantity, artist, etc);
    }

}
