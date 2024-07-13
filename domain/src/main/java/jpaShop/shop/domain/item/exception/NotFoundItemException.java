package jpaShop.shop.domain.item.exception;

public class NotFoundItemException extends ItemException{
    public NotFoundItemException(String message) {
        super(message);
    }
}
