package com.olo.apimonolithic.feature.categorymanagement.application.impl;

import com.olo.apimonolithic.common.domain.exception.category.CategoryExistsException;
import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.CreateCategory;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.outbounds.CategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCategoryImpl implements CreateCategory {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        if (categoryRepository.existsByName(category.name())){
            throw new CategoryExistsException("Category already exists!");
        }

        Category newCategory = new Category(null, category.name());

        return categoryRepository.create(newCategory);
    }
}
