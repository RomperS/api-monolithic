package com.olo.apimonolithic.feature.productmanagement.infrastructure.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ProductUpdateRequestDTO(
        @NotNull
        Long targetId,
        String name,
        List<Long> additionalCategories,
        List<Long> categoriesToRemove,
        @DecimalMin(value = "0.1") BigDecimal value
) {
}