package com.olo.apimonolithic.feature.productmanagement.infrastructure.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductSearchRequestDTO(
        Long id,
        String name,
        @NotNull
        Long categoryId,
        ValueRangeDTO valueRange,

        @Min(value = 1)
        int page,

        @Min(value = 1)
        int pageSize
) {
    public record ValueRangeDTO(BigDecimal min, BigDecimal max) {
    }
}