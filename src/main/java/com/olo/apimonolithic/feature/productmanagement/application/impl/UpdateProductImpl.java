package com.olo.apimonolithic.feature.productmanagement.application.impl;

import com.olo.apimonolithic.common.domain.exception.product.ProductExistsException;
import com.olo.apimonolithic.common.domain.exception.product.ProductNotFoundException;
import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.FindCategory;
import com.olo.apimonolithic.feature.productmanagement.domain.command.UpdateProductCommand;
import com.olo.apimonolithic.feature.productmanagement.domain.model.Product;
import com.olo.apimonolithic.feature.productmanagement.domain.port.inbound.UpdateProduct;
import com.olo.apimonolithic.feature.productmanagement.domain.port.outbound.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UpdateProductImpl implements UpdateProduct {

    private final ProductRepository productRepository;
    private final FindCategory findCategory;

    @Override
    public Product update(UpdateProductCommand product) {

        Product targetProduct = productRepository.findById(product.targetId()).orElseThrow(() -> new ProductNotFoundException("Product not found!"));

        //Categories
        Set<Category> currentCategories = targetProduct.categories();

        if (!product.additionalCategories().isEmpty()) {
            for (Long categoryId : product.additionalCategories()) {
                currentCategories.add(findCategory.findById(categoryId));
            }
        }

        Set<Category> categories = currentCategories
                .stream()
                .filter(c -> !product.categoriesToRemove().contains(c.id()))
                .collect(Collectors.toSet());

        //Name
        String name = targetProduct.name();
        if (product.name() != null && !product.name().equals(name)) {
            if (productRepository.existsByName(product.name())){
                throw new ProductExistsException("Product name already taken!");
            }

            name = product.name();
        }

        //Value
        BigDecimal value = targetProduct.value();
        if (product.value() != null && !product.value().equals(value)) {
            value = product.value();
        }

        Product productUpdated = new Product(
                targetProduct.id(),
                name,
                categories,
                value
        );

        return productRepository.update(productUpdated);
    }
}
