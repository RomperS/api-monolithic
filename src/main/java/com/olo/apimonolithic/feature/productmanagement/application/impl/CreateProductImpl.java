package com.olo.apimonolithic.feature.productmanagement.application.impl;

import com.olo.apimonolithic.common.domain.exception.category.CategoryNotFoundException;
import com.olo.apimonolithic.common.domain.exception.product.ProductExistsException;
import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.CategoryExists;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.FindCategory;
import com.olo.apimonolithic.feature.productmanagement.domain.command.ProductCommand;
import com.olo.apimonolithic.feature.productmanagement.domain.model.Product;
import com.olo.apimonolithic.feature.productmanagement.domain.port.inbound.CreateProduct;
import com.olo.apimonolithic.feature.productmanagement.domain.port.outbound.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class CreateProductImpl implements CreateProduct {

    private final ProductRepository productRepository;
    private final CategoryExists categoryExists;
    private final FindCategory findCategory;

    @Override
    public Product create(ProductCommand product) {
        if (productRepository.existsByName(product.name())){
            throw new ProductExistsException("Product already exists!");
        }

        Set<Category> categories = new HashSet<>();

        for (Long categoryId : product.categoriesId()){
            categories.add(findCategory.findById(categoryId));
        }

        categories.stream()
                .filter(c -> !categoryExists.existsById(c.id()))
                .findFirst()
                .ifPresent(c -> {
                    throw new CategoryNotFoundException("Category not found!");
                });

        Product newProduct = new Product(
                null,
                product.name(),
                categories,
                product.value()
        );

        return productRepository.create(newProduct);
    }
}
