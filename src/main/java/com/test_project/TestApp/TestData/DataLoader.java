package com.test_project.TestApp.TestData;

import com.test_project.TestApp.Models.MaterialComponent;
import com.test_project.TestApp.Models.Product;
import com.test_project.TestApp.Models.ProductMC;
import com.test_project.TestApp.Service.MaterialComponentService;
import com.test_project.TestApp.Service.ProductMCService;
import com.test_project.TestApp.Service.ProductService;
import com.test_project.TestApp.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductService productService;
    private final MaterialComponentService materialComponentService;
    private final ProductMCService productMCService;
    private final PurchaseService purchaseService;

    @Autowired
    public DataLoader(ProductService productService, MaterialComponentService materialComponentService,
                      ProductMCService productMCService, PurchaseService purchaseService) {
        this.productService = productService;
        this.materialComponentService = materialComponentService;
        this.productMCService = productMCService;
        this.purchaseService = purchaseService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Создание изделий
        Product product1 = productService.addProduct("Изделие 1");
        Product product2 = productService.addProduct("Изделие 2");
        Product product3 = productService.addProduct("Изделие 3");

        // Создание МиКов
        MaterialComponent material1 = materialComponentService.addMaterialComponent("Материал 1", BigDecimal.valueOf(10));
        MaterialComponent material2 = materialComponentService.addMaterialComponent("Материал 2", BigDecimal.valueOf(8));
        MaterialComponent material3 = materialComponentService.addMaterialComponent("Материал 3", BigDecimal.valueOf(12));
        MaterialComponent material4 = materialComponentService.addMaterialComponent("Материал 4", BigDecimal.valueOf(15));
        MaterialComponent material5 = materialComponentService.addMaterialComponent("Материал 5", BigDecimal.valueOf(20));
        MaterialComponent material6 = materialComponentService.addMaterialComponent("Материал 6", BigDecimal.valueOf(18));
        MaterialComponent material7 = materialComponentService.addMaterialComponent("Материал 7", BigDecimal.valueOf(25));

        // Создание применений МиКов в изделиях
        ProductMC productMC1 = productMCService.addProductMC(product1, material1, 5);
        ProductMC productMC2 = productMCService.addProductMC(product1, material1, 13);
        ProductMC productMC3 = productMCService.addProductMC(product1, material2, 3);
        ProductMC productMC4 = productMCService.addProductMC(product2, material3, 2);
        ProductMC productMC5 = productMCService.addProductMC(product2, material4, 4);
        ProductMC productMC6 = productMCService.addProductMC(product2, material5, 1);
        ProductMC productMC7 = productMCService.addProductMC(product3, material6, 6);
        ProductMC productMC8 = productMCService.addProductMC(product3, material7, 7);

        // Создание закупок
        purchaseService.addPurchase(productMC1, LocalDate.of(2020, 11, 11));
        purchaseService.addPurchase(productMC2, LocalDate.of(2020, 10, 20) );
        purchaseService.addPurchase(productMC1, LocalDate.of(2020, 12, 20));
        purchaseService.addPurchase(productMC4, LocalDate.of(2021, 1, 10) );
        purchaseService.addPurchase(productMC3, LocalDate.of(2021, 3, 8) );
        purchaseService.addPurchase(productMC4, LocalDate.of(2021, 5, 21) );
        purchaseService.addPurchase(productMC5, LocalDate.of(2022, 2, 3) );
        purchaseService.addPurchase(productMC6, LocalDate.of(2022, 7, 17) );
        purchaseService.addPurchase(productMC7,LocalDate.of(2023, 6, 12)  );
        purchaseService.addPurchase(productMC8,LocalDate.of(2023, 6, 12)  );
    }
}






