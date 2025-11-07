package com.olo.apimonolithic.domain.model;

public record Product(
        Long id,
        String name,
        Category category,
        Long value
) {
}
