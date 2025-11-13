package com.olo.apimonolithic.feature.productmanagement.domain.command;

import java.math.BigDecimal;
import java.util.List;

public record ProductCommand(
        String name,
        BigDecimal value,
        List<Long> categoriesId
) {
}
