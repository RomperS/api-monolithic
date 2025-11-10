package com.olo.apimonolithic.feature.categorymanagement.application.impl;

import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.CategoryExists;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.outbounds.CategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryExistsImpl implements CategoryExists {

    private final CategoryRepository categoryRepository;


    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }
}
