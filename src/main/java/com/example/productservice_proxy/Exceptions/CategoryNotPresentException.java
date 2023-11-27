package com.example.productservice_proxy.Exceptions;

public class CategoryNotPresentException extends RuntimeException{
    public CategoryNotPresentException(String message) {
        super(message);
    }
}
