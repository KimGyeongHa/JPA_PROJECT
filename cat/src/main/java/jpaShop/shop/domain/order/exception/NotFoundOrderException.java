package jpaShop.shop.domain.order.exception;

public class NotFoundOrderException extends OrderException{
    public NotFoundOrderException(String message) {
        super(message);
    }
}
