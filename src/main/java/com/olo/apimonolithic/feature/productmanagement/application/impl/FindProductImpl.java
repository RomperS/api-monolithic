package com.olo.apimonolithic.feature.productmanagement.application.impl;

import com.olo.apimonolithic.common.domain.exception.category.CategoryNotFoundException;
import com.olo.apimonolithic.common.domain.exception.product.ProductNotFoundException;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.CategoryExists;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.FindCategory;
import com.olo.apimonolithic.feature.productmanagement.domain.command.ProductSearchCriteria;
import com.olo.apimonolithic.feature.productmanagement.domain.model.Product;
import com.olo.apimonolithic.feature.productmanagement.domain.port.inbound.FindProduct;
import com.olo.apimonolithic.feature.productmanagement.domain.port.outbound.ProductRepository;
import com.olo.apimonolithic.feature.productmanagement.domain.command.ProductSearchCriteria.*;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class FindProductImpl implements FindProduct {

    private final ProductRepository productRepository;
    private final CategoryExists categoryExists;

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByFilter(ProductSearchCriteria criteria) {
        if (!categoryExists.existsById(criteria.categoryId())){
            throw new CategoryNotFoundException("Category not found!");
        }

        List<Product> products = productRepository.findByCategoryId(criteria.categoryId()).stream()
                .filter(p -> idFilter(p.id(), criteria.id()))
                .filter(p -> nameFilter(p.name(), criteria.name()))
                .filter(p -> valueFilter(p.value(), criteria.valueRange()))
                .toList();

        long offset = (long) (criteria.page() - 1) * criteria.pageSize();

        return products.stream()
                .skip(offset)
                .limit(criteria.pageSize())
                .toList();
    }

    private boolean idFilter(Long targetId, Long referenceId){
        if (referenceId == null){
            return true;
        }

        return targetId.equals(referenceId);
    }

    private boolean nameFilter(String targetName, String referenceName){
        if (referenceName == null){
            return true;
        }

        return targetName.toUpperCase().contains(referenceName.toUpperCase());
    }

    private boolean valueFilter(BigDecimal targetValue, ValueRange range){
        if (range == null){
            return true;
        }

        boolean greaterThanOrEqualMin = (range.min() == null) ||
                (targetValue.compareTo(range.min()) >= 0);

        boolean lessThanOrEqualMax = (range.max() == null) ||
                (targetValue.compareTo(range.max()) <= 0);

        return greaterThanOrEqualMin && lessThanOrEqualMax;
    }
}
