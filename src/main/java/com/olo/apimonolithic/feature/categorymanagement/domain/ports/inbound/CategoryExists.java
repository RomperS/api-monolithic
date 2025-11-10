package com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound;

public interface CategoryExists {
    boolean existsById(Long id);
}
