package com.olo.apimonolithic.feature.productmanagement.domain.port.outbound;

import com.olo.apimonolithic.feature.productmanagement.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    boolean existsByName(String name);

    Optional<Product> findById(Long id);
    List<Product> findAll();
    List<Product> findByCategoryId(Long id);

    Product create(Product product);

    Product update(Product productUpdated);

    void delete(Long id);
}
