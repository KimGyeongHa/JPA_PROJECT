package findAnyCat.cat.item.service.dto;

import findAnyCat.cat.item.controller.request.ItemJoinRequest;

public record ItemDTO(
        String name,
        int price,
        int stockQuantity,
        String author,
        String isbn
) {
    public static ItemDTO of(
            String name,
            int price,
            int stockQuantity,
            String author,
            String isbn
    ){
        return new ItemDTO(name, price, stockQuantity, author, isbn);
    }
}
