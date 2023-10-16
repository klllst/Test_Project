package com.test_project.TestApp.Repositories;

import com.test_project.TestApp.Models.ProductMC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMCRepository extends JpaRepository<ProductMC,Long> {
}
