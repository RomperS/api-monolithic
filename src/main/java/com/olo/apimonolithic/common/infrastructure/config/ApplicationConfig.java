package com.olo.apimonolithic.common.infrastructure.config;

import com.olo.apimonolithic.feature.categorymanagement.application.CategoryService;
import com.olo.apimonolithic.feature.categorymanagement.application.impl.*;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.CategoryExists;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.FindCategory;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.outbounds.CategoryRepository;
import com.olo.apimonolithic.feature.productmanagement.application.ProductService;
import com.olo.apimonolithic.feature.productmanagement.application.impl.CreateProductImpl;
import com.olo.apimonolithic.feature.productmanagement.application.impl.DeleteProductImpl;
import com.olo.apimonolithic.feature.productmanagement.application.impl.FindProductImpl;
import com.olo.apimonolithic.feature.productmanagement.application.impl.UpdateProductImpl;
import com.olo.apimonolithic.feature.productmanagement.domain.port.outbound.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository) {
        return new CategoryService(
                new CreateCategoryImpl(categoryRepository),
                new DeleteCategoryImpl(categoryRepository),
                new FindCategoryImpl(categoryRepository),
                new RenameCategoryImpl(categoryRepository),
                new CategoryExistsImpl(categoryRepository)
        );
    }

    @Bean
    public ProductService productService(ProductRepository productRepository, CategoryExists categoryExists, FindCategory findCategory) {
        return new ProductService(
                new CreateProductImpl(productRepository, categoryExists, findCategory),
                new DeleteProductImpl(productRepository),
                new FindProductImpl(productRepository, categoryExists),
                new UpdateProductImpl(productRepository, findCategory)
        );
    }
}
