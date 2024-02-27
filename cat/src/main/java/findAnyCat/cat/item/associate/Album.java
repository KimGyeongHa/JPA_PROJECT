package findAnyCat.cat.item.associate;

import findAnyCat.cat.item.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;

@DiscriminatorValue("ALBUM")
@Entity
public class Album extends Item {
    @Builder(builderMethodName = "albumBuilder")
    public Album(String name, int price, int stcokQuantity, String artist, String etc) {
        super(name, price, stcokQuantity, artist, etc);
    }
}
