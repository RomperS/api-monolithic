package com.olo.apimonolithic.feature.categorymanagement.infrastructure.adapter;

import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.outbounds.CategoryRepository;
import com.olo.apimonolithic.feature.categorymanagement.infrastructure.entity.CategoryEntity;
import com.olo.apimonolithic.feature.categorymanagement.infrastructure.mapper.CategoryMapper;
import com.olo.apimonolithic.feature.categorymanagement.infrastructure.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CategoryRepositoryAdapter implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;


    @Override
    public boolean existsByName(String name) {
        return categoryJpaRepository.existsByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryJpaRepository.findAll().stream().map(CategoryMapper::entityToModel).toList();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryJpaRepository.findById(id).map(CategoryMapper::entityToModel);
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryJpaRepository.findByNameContainingIgnoreCase(name).stream().map(CategoryMapper::entityToModel).toList();
    }

    @Override
    public Category create(Category category) {
        CategoryEntity entity = categoryJpaRepository.save(CategoryMapper.modelToEntity(category));

        return CategoryMapper.entityToModel(entity);
    }

    @Override
    public Category update(Category category) {
        CategoryEntity entity = categoryJpaRepository.save(CategoryMapper.modelToEntity(category));

        return CategoryMapper.entityToModel(entity);
    }

    @Override
    public void delete(Long id) {
        categoryJpaRepository.deleteById(id);
    }
}
