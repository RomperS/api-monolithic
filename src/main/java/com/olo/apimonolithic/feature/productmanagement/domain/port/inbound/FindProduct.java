package com.olo.apimonolithic.feature.productmanagement.domain.port.inbound;

import com.olo.apimonolithic.feature.productmanagement.domain.command.ProductSearchCriteria;
import com.olo.apimonolithic.feature.productmanagement.domain.model.Product;

import java.util.List;

public interface FindProduct {
    Product findById(Long id);
    List<Product> findAll();
    List<Product> findByFilter(ProductSearchCriteria criteria);
}
