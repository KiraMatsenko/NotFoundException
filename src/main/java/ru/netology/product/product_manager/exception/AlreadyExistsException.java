package ru.netology.product.product_manager.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String msg) {
        super(msg);
    }
}
