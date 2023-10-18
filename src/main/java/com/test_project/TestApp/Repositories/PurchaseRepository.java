package com.test_project.TestApp.Repositories;

import com.test_project.TestApp.Models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
    @Query("SELECT p FROM Purchase p " +
            "JOIN p.productMC productMC " +
            "JOIN productMC.MC materialComponent " +
            "WHERE productMC.product.id = :productId " +
            "AND LOWER(materialComponent.name) LIKE LOWER(CONCAT('%', :materialComponentName, '%')) " +
            "AND p.purchaseDate BETWEEN :startDate AND :endDate")
    List<Purchase> getReportForPeriod(
            @Param("productId") Long productId,
            @Param("materialComponentName") String materialComponentName,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
    @Query("SELECT p.cost FROM Purchase p " +
            "JOIN p.productMC productMC " +
            "JOIN productMC.MC materialComponent " +
            "WHERE productMC.product.id = :productId " +
            "AND materialComponent.id = :materialComponentId " +
            "AND p.purchaseDate = (SELECT MIN(p2.purchaseDate) FROM Purchase p2 " +
            "WHERE p2.productMC.product.id = :productId " +
            "AND p2.productMC.MC.id = :materialComponentId " +
            "AND p2.purchaseDate BETWEEN :startDate AND :endDate)")
    BigDecimal getStartCost(@Param("productId") Long productId,
                          @Param("materialComponentId") Long materialComponentId,
                          @Param("startDate") LocalDate startDate,
                          @Param("endDate") LocalDate endDate);
    @Query("SELECT p.cost FROM Purchase p " +
            "JOIN p.productMC productMC " +
            "JOIN productMC.MC materialComponent " +
            "WHERE productMC.product.id = :productId " +
            "AND materialComponent.id = :materialComponentId " +
            "AND p.purchaseDate = (SELECT MAX(p2.purchaseDate) FROM Purchase p2 " +
            "WHERE p2.productMC.product.id = :productId " +
            "AND p2.productMC.MC.id = :materialComponentId " +
            "AND p2.purchaseDate BETWEEN :startDate AND :endDate)")
    BigDecimal getEndCost(@Param("productId") Long productId,
                            @Param("materialComponentId") Long materialComponentId,
                            @Param("startDate") LocalDate startDate,
                            @Param("endDate") LocalDate endDate);
}
