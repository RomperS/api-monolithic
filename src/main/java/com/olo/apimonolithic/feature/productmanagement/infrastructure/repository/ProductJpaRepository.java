package com.olo.apimonolithic.feature.productmanagement.infrastructure.repository;

import com.olo.apimonolithic.feature.productmanagement.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT p FROM ProductEntity p JOIN p.categories c WHERE c.id = :categoryId")
    List<ProductEntity> findProductsByCategoryId(@Param("categoryId") Long categoryId);

    boolean existsByNameIgnoreCase(String name);
}