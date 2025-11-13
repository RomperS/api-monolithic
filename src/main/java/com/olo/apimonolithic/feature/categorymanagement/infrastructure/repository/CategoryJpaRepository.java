package com.olo.apimonolithic.feature.categorymanagement.infrastructure.repository;

import com.olo.apimonolithic.feature.categorymanagement.infrastructure.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {


    boolean existsByNameContainingIgnoreCase(String name);

    List<CategoryEntity> findByNameContainingIgnoreCase(String name);
}
