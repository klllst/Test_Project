package com.test_project.TestApp.Service;

import com.test_project.TestApp.Exceptions.PurchaseNotFoundException;
import com.test_project.TestApp.Models.Product;
import com.test_project.TestApp.Models.ProductMC;
import com.test_project.TestApp.Models.Purchase;
import com.test_project.TestApp.Repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }
    @Transactional
    public Purchase addPurchase(ProductMC productMC, LocalDate purchaseDate){
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(purchaseDate);
        purchase.setCost(productMC.getMC().getCost().multiply(BigDecimal.valueOf(productMC.getQuantityMC())));
        purchase.setProduct(productMC.getProduct());
        purchase.setProductMC(productMC);
        return purchaseRepository.save(purchase);
    }
    @Transactional()
    public Purchase getPurchase(Long id){
        return purchaseRepository.findById(id).orElseThrow(() -> new PurchaseNotFoundException(id));
    }

    @Transactional
    public List<Purchase> getAllPurchases(){
        return purchaseRepository.findAll();
    }

    @Transactional
    public Purchase updatePurchase(Long id, LocalDate purchaseDate){
        Purchase purchase = getPurchase(id);
        purchase.setPurchaseDate(purchaseDate);
        return purchaseRepository.save(purchase);
    }

    @Transactional
    public Purchase deletePurchase(Long id){
        Purchase purchase = getPurchase(id);
        purchaseRepository.delete(purchase);
        return purchase;
    }
    @Transactional
    public void deleteAllPurchases(){
        purchaseRepository.deleteAll();
    }

    @Transactional
    public List<Purchase> getReport(Long id, String MCname, LocalDate startDate, LocalDate endDate){
        return purchaseRepository.getReportForPeriod(id,MCname,startDate,endDate);
    }

    @Transactional
    public BigDecimal getStartCost(Long id, Long materialComponentId, LocalDate startDate, LocalDate endDate ){
        return purchaseRepository.getStartCost(id,materialComponentId,startDate,endDate);
    }
    @Transactional
    public BigDecimal getEndCost(Long id, Long materialComponentId, LocalDate startDate, LocalDate endDate ){
        return purchaseRepository.getEndCost(id,materialComponentId,startDate,endDate);
    }
}
