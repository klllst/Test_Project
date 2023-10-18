package com.test_project.TestApp.Controller;

import com.test_project.TestApp.DTO.ReportDTO;
import com.test_project.TestApp.Models.Purchase;
import com.test_project.TestApp.Service.ProductService;
import com.test_project.TestApp.Service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class ReportController {
    private final PurchaseService purchaseService;
    private final ProductService productService;
    private static final Logger log = LoggerFactory.getLogger(ReportController.class);
    @Autowired
    public ReportController(PurchaseService purchaseService, ProductService productService){
        this.purchaseService=purchaseService;
        this.productService=productService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }


    @PostMapping("/report")
    public String getReport(@RequestParam("productId") Long productId,
                            @RequestParam("materialComponentName") String materialComponentName,
                            @RequestParam("startDate")String startDate,
                            @RequestParam("endDate")String endDate,
                            Model model){
        LocalDate parsedStartDate = LocalDate.parse(startDate);
        LocalDate parsedEndDate = LocalDate.parse(endDate);
        List<Purchase> purchases = purchaseService.getReport(productId,materialComponentName,parsedStartDate,parsedEndDate);
        log.info("Purchases: {}", purchases);
        if (purchases.isEmpty()) {
            model.addAttribute("errorMessage", "Нет данных для отображения.");
            return "report";
        }

        List<ReportDTO> results = new ArrayList<>();
        for(Purchase purchase : purchases){
            ReportDTO reportDTO = new ReportDTO(
                    purchase.getId(),
                    purchase.getProduct().getName(),
                    purchase.getProductMC().getMC().getName(),
                    purchaseService.getStartCost(purchase.getProduct().getId(),
                            purchase.getProductMC().getMC().getId(),
                            parsedStartDate,parsedEndDate),
                    purchaseService.getEndCost(purchase.getProduct().getId(),
                            purchase.getProductMC().getMC().getId(),
                            parsedStartDate,parsedEndDate)
            );
            results.add(reportDTO);
        }

        model.addAttribute("results", results);
        return "report";
    }
}
