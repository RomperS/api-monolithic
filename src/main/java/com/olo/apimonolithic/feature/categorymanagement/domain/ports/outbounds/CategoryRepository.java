package com.olo.apimonolithic.feature.categorymanagement.domain.ports.outbounds;

import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    boolean existsByName(String name);

    List<Category> findAll();
    Optional<Category> findById(Long id);
    List<Category>  findByName(String name);

    Category create(Category category);

    Category update(Category category);

    void delete(Long id);
}
