package jpaShop.shop.item.exception;

public class NoEnoughStcokException extends RuntimeException{
    public NoEnoughStcokException() {
        super();
    }

    public NoEnoughStcokException(String message) {
        super(message);
    }

    public NoEnoughStcokException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEnoughStcokException(Throwable cause) {
        super(cause);
    }

    protected NoEnoughStcokException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
