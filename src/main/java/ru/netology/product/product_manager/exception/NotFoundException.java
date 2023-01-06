package ru.netology.product.product_manager.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String s) {
        super(s);
    }
}
