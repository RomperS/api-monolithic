package com.olo.apimonolithic.feature.productmanagement.infrastructure.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductSearchRequestDTO(
        Long id,
        String name,
        @NotNull(message = "Category ID is mandatory for search.")
        Long categoryId,
        ValueRangeDTO valueRange,

        @Min(value = 1, message = "Page number must be 1 or higher.")
        int page,

        @Min(value = 1, message = "Page size must be 1 or higher.")
        int pageSize
) {
    public record ValueRangeDTO(BigDecimal min, BigDecimal max) {
    }
}