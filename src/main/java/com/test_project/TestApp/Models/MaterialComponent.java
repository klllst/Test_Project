package com.test_project.TestApp.Models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
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
}