package com.test_project.TestApp.Models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;


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


    public MaterialComponent(){
    }

    public MaterialComponent(String name, BigDecimal cost){
        this.name=name;
        this.cost=cost;
    }
}