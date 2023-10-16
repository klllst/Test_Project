package com.test_project.TestApp.Models;

import lombok.Data;

import javax.persistence.*;

//Сущность Изделие
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;


    public Product(){
    }

    public Product(String name){
        this.name=name;
    }
}
