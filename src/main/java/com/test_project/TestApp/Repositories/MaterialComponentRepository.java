package com.test_project.TestApp.Repositories;

import com.test_project.TestApp.Models.MaterialComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialComponentRepository extends JpaRepository<MaterialComponent,Long> {
}
