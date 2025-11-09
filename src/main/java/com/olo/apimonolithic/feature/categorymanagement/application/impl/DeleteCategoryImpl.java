package com.olo.apimonolithic.feature.categorymanagement.application.impl;

import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.DeleteCategory;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.outbounds.CategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteCategoryImpl implements DeleteCategory {

    private final CategoryRepository categoryRepository;

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }
}
