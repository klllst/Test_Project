package com.test_project.TestApp.DTO;

import com.test_project.TestApp.Models.Purchase;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PurchaseDTO {
    public final Long id;
    public final String productName;
    public final String materialComponentName;
    public final BigDecimal cost;
    public final LocalDate purchaseDate;


    public PurchaseDTO(Purchase purchase){
        this.id=purchase.getId();
        this.productName=purchase.getProductMC().getProduct().getName();
        this.materialComponentName=purchase.getProduct().getName();
        this.cost=purchase.getCost();
        this.purchaseDate=purchase.getPurchaseDate();
    }
}
