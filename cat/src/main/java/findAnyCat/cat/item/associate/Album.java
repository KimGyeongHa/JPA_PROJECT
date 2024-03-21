package findAnyCat.cat.item.associate;

import findAnyCat.cat.item.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@DiscriminatorValue("ALBUM")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Album extends Item {
    @Builder(builderMethodName = "albumBuilder")
    public Album(String name, int price, int stockQuantity, String artist, String etc) {
        super(name, price, stockQuantity, artist, etc);
    }

}
