package com.olo.apimonolithic.feature.productmanagement.domain.model;

import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;

import java.math.BigDecimal;
import java.util.Set;

public record Product(
        Long id,
        String name,
        Set<Category> categories,
        BigDecimal value
) {
}
