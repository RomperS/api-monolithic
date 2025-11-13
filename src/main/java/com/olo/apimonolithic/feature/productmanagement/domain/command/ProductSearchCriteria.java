package com.olo.apimonolithic.feature.productmanagement.domain.command;

import java.math.BigDecimal;

public record ProductSearchCriteria(
        Long id,
        String name,
        Long categoryId,
        ValueRange valueRange,
        int page,
        int pageSize
) {

    public record ValueRange(BigDecimal min, BigDecimal max) {
    }
}
