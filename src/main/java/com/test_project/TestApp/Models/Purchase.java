package com.test_project.TestApp.Models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productMC_fk")
    private ProductMC productMC;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_fk")
    private Product product;

    @Column
    private BigDecimal cost;

    @Column
    private LocalDate purchaseDate;

    public Purchase() {
    }
    public Purchase(LocalDate purchaseDate){
        this.purchaseDate=purchaseDate;
        this.cost=productMC.getMC().getCost().multiply(BigDecimal.valueOf(productMC.getQuantityMC()));
    }
}
