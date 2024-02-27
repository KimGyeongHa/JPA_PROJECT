package findAnyCat.cat.item.controller.request;

import findAnyCat.cat.item.Item;
import findAnyCat.cat.item.associate.Book;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record ItemJoinRequest(
        String name,
        int price,
        int stockQuantity,
        String author,
        String isbn
) {
    public ItemJoinRequest{

    }
}
