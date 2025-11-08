package com.olo.apimonolithic.feature.inventorymanagement.domain.model;

public record Inventory(
        Long id,
        Long productId,
        Long quantity
) {
}
