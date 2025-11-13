package com.olo.apimonolithic.feature.productmanagement.infrastructure.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponseDto(
        Long id,
        String name,
        BigDecimal value,
        List<Long> categoriesId
) {
}
