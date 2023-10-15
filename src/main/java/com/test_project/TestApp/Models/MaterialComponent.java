package com.test_project.TestApp.Models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class MaterialComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal cost;

    @OneToMany(mappedBy = "materialComponent")
    private List<Product> products;

    @OneToMany(mappedBy = "materialComponent")
    private List<Purchase> purchases;

    public MaterialComponent(){
        this.products=new ArrayList<>();
        this.purchases=new ArrayList<>();
    }

    public MaterialComponent(String name, BigDecimal cost){
        this.name=name;
        this.cost=cost;
        this.products=new ArrayList<Product>();
        this.purchases=new ArrayList<Purchase>();
    }
}