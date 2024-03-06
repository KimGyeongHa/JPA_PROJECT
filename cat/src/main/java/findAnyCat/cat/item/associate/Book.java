package findAnyCat.cat.item.associate;

import findAnyCat.cat.item.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;

@DiscriminatorValue("BOOK")
@Entity
public class Book extends Item {
    @Builder(builderMethodName = "bookBuilder")
    public Book(String name, int price, int stcokQuantity, String artist, String etc) {
        super(name, price, stcokQuantity, artist, etc);
    }

    protected Book() {}
}
