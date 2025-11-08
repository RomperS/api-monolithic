package com.olo.apimonolithic.feature.productmanagement.domain.model;

import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;

public record Product(
        Long id,
        String name,
        Category category,
        Long value
) {
}
