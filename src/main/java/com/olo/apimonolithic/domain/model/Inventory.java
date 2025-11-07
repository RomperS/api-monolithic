package com.olo.apimonolithic.domain.model;

public record Inventory(
        Long id,
        Long productId,
        Long quantity
) {
}
