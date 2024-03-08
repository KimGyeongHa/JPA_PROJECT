package findAnyCat.cat.image;

import findAnyCat.cat.item.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id @GeneratedValue
    @Column(name = "search_id")
    private Long id;

    private Date getDate;
    private String imageName;

    public Image(Item item) {
        this.id = item.getId();
        this.imageName = item.getName();
        this.getDate = new Date();
    }
}
