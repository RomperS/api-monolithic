package com.olo.apimonolithic.common.infrastructure.config;

import com.olo.apimonolithic.feature.categorymanagement.application.CategoryService;
import com.olo.apimonolithic.feature.categorymanagement.application.impl.CreateCategoryImpl;
import com.olo.apimonolithic.feature.categorymanagement.application.impl.DeleteCategoryImpl;
import com.olo.apimonolithic.feature.categorymanagement.application.impl.FindCategoryImpl;
import com.olo.apimonolithic.feature.categorymanagement.application.impl.RenameCategoryImpl;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.outbounds.CategoryRepository;
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
                new RenameCategoryImpl(categoryRepository)
        );
    }
}
