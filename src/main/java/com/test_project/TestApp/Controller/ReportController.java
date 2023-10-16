package com.test_project.TestApp.Controller;

import com.test_project.TestApp.DTO.PurchaseDTO;
import com.test_project.TestApp.Service.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
public class ReportController {
    private final PurchaseService purchaseService;

    public ReportController(PurchaseService purchaseService){
        this.purchaseService=purchaseService;
    }
    @GetMapping
    public List<PurchaseDTO> getPurchases(){
        return  purchaseService.getAllPurchases().stream()
                .map(PurchaseDTO::new)
                .toList();
    }

    @GetMapping("/report")
    public List<PurchaseDTO> getReport(@RequestParam("productId") Long productId,
                                       @RequestParam("materialComponentName") String materialComponentName,
                                       @RequestParam("startDate")LocalDate startDate,
                                       @RequestParam("endDate")LocalDate endDate){
        return  purchaseService.getReport(productId,
                        materialComponentName,
                        startDate,
                        endDate).stream()
                .map(PurchaseDTO::new)
                .toList();
    }
}
