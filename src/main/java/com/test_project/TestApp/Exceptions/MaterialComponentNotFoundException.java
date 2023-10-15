package com.test_project.TestApp.Exceptions;

public class MaterialComponentNotFoundException extends RuntimeException {
    public MaterialComponentNotFoundException(Long id){
        super(String.format("MaterialComponent with id: %s hasn't been found", id));
    }
}
