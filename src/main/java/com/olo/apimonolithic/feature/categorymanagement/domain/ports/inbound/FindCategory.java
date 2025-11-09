package com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound;

import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;

public interface FindCategory {
    Category findById(Long id);
    Category findByName(String name);
}
