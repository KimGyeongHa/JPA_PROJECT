package findAnyCat.cat.item.service.dto;

public record ItemDto(
        String name,
        int price,
        int stockQuantity,
        String author,
        String isbn
) {
    public ItemDto{

    }
}
