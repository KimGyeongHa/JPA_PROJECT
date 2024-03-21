package findAnyCat.cat.item.associate;

import findAnyCat.cat.item.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@DiscriminatorValue("MOVIE")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Movie extends Item {
    @Builder(builderMethodName = "movieBuilder")
    public Movie(String name, int price, int stockQuantity, String author, String etc) {
        super(name, price, stockQuantity, author, etc);
    }
}
