package com.test_project.TestApp.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private int materialQuantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "materialComponent_fk")
    private MaterialComponent materialComponent;

    @OneToMany(mappedBy = "product")
    private List<Purchase> purchases;

    public Product(){
        this.purchases=new ArrayList<>();
    }

    public Product(String name, int materialQuantity){
        this.name=name;
        this.materialQuantity=materialQuantity;
        this.purchases=new ArrayList<Purchase>();
    }
}
