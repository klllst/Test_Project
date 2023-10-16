package com.test_project.TestApp.Repositories;

import com.test_project.TestApp.Models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    @Query("SELECT p FROM Purchase p " +
            "JOIN p.productMC productMC " +
            "JOIN productMC.MC materialComponent " +
            "WHERE productMC.product.id = :productId " +
            "AND LOWER(materialComponent.name) LIKE LOWER(CONCAT('%', :materialComponentName, '%')) " +
            "AND p.purchaseDate BETWEEN :startDate AND :endDate")
    List<Purchase> getReport(
            @Param("productId") Long productId,
            @Param("materialComponentName") String materialComponentName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
