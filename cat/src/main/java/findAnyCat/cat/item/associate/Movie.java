package findAnyCat.cat.item.associate;

import findAnyCat.cat.item.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;

@DiscriminatorValue("MOVIE")
@Entity
public class Movie extends Item {
    @Builder(builderMethodName = "movieBuilder")
    public Movie(String name, int price, int stcokQuantity, String artist, String etc) {
        super(name, price, stcokQuantity, artist, etc);
    }
}
