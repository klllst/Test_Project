package com.test_project.TestApp.Exceptions;

public class PurchaseNotFoundException extends RuntimeException{
    public PurchaseNotFoundException(Long id) {
        super(String.format("Purchase with id: %s hasn't been found", id));
    }
}
