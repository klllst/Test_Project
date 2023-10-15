package com.test_project.TestApp.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProductMC {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int quantityMC;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_fk")
    Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="mc_fk")
    MaterialComponent MC;

    public ProductMC(){}

    public ProductMC(int quantityMC){
        this.quantityMC=quantityMC;
    }

}
