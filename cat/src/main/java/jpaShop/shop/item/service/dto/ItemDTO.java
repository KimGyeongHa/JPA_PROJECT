package jpaShop.shop.item.service.dto;

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
