package com.test_project.TestApp.Models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "materialComponent_fk")
    private MaterialComponent materialComponent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_fk")
    private Product product;

    @Column
    private BigDecimal cost;

    @Column
    private LocalDate purchaseDate;

    public Purchase() {
    }
    public Purchase(BigDecimal cost, LocalDate purchaseDate){
        this.cost=cost;
        this.purchaseDate=purchaseDate;
    }
}
