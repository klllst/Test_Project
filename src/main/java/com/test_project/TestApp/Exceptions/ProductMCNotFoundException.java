package com.test_project.TestApp.Exceptions;

public class ProductMCNotFoundException extends RuntimeException{
    public ProductMCNotFoundException(Long id) {
        super(String.format("ProductMC with id: %s hasn't been found", id));
    }
}
