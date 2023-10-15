package com.test_project.TestApp.Service;

import com.test_project.TestApp.Exceptions.PurchaseNotFoundException;
import com.test_project.TestApp.Models.Purchase;
import com.test_project.TestApp.Repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    public PurchaseService(PurchaseRepository purchaseRepository){
        this.purchaseRepository = purchaseRepository;
    }
    @Transactional
    public Purchase addPurchase(LocalDate purchaseDate){
        Purchase purchase = new Purchase();
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
        return purchaseRepository.getReport(id,MCname,startDate,endDate);
    }
}
