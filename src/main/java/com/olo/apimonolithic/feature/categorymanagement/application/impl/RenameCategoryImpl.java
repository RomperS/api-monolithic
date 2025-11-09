package com.olo.apimonolithic.feature.categorymanagement.application.impl;

import com.olo.apimonolithic.common.domain.exception.category.CategoryExistsException;
import com.olo.apimonolithic.common.domain.exception.category.CategoryNotFoundException;
import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.RenameCategory;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.outbounds.CategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RenameCategoryImpl implements RenameCategory {

    private final CategoryRepository categoryRepository;

    @Override
    public Category rename(Category category) {
        if (categoryRepository.existsByName(category.name())){
            throw new CategoryExistsException("Category already exists!");
        }

        Category targetCategory = categoryRepository.findById(category.id()).orElseThrow(
                () -> new CategoryNotFoundException("Category not found!")
        );

        Category renamedCategory = new Category(targetCategory.id(), category.name());

        return categoryRepository.update(renamedCategory);
    }
}
