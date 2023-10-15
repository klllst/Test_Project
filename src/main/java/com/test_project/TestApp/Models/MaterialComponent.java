package com.test_project.TestApp.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class MaterialComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "materialComponent")
    private List<Product> products;

    @OneToMany(mappedBy = "materialComponent")
    private List<Purchase> purchases;
}