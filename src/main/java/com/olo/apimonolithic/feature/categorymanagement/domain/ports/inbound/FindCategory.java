package com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound;

import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;

import java.util.List;

public interface FindCategory {
    List<Category> findAll();
    Category findById(Long id);
    Category findByName(String name);
}
