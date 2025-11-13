package com.olo.apimonolithic.feature.productmanagement.domain.command;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record UpdateProductCommand(
        Long targetId,
        String name,
        List<Long> additionalCategories,
        List<Long> categoriesToRemove,
        BigDecimal value
) {
    public UpdateProductCommand {
        additionalCategories = additionalCategories == null
                ? new ArrayList<>()
                : new ArrayList<>(additionalCategories);

        categoriesToRemove = categoriesToRemove == null
                ? new ArrayList<>()
                : new ArrayList<>(categoriesToRemove);
    }
}
