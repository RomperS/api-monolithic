package com.olo.apimonolithic.feature.categorymanagement.application;

import com.olo.apimonolithic.feature.categorymanagement.application.impl.*;
import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryService implements CreateCategory, DeleteCategory, FindCategory, RenameCategory, CategoryExists {

    private final CreateCategoryImpl createCategoryImpl;
    private final DeleteCategoryImpl deleteCategoryImpl;
    private final FindCategoryImpl findCategoryImpl;
    private final RenameCategoryImpl renameCategoryImpl;
    private final CategoryExistsImpl categoryExistsImpl;

    @Override
    public Category create(Category category) {
        return createCategoryImpl.create(category);
    }

    @Override
    public void delete(Long id) {
        deleteCategoryImpl.delete(id);
    }

    @Override
    public List<Category> findAll() {
        return findCategoryImpl.findAll();
    }

    @Override
    public Category findById(Long id) {
        return findCategoryImpl.findById(id);
    }

    @Override
    public List<Category> findByName(String name) {
        return findCategoryImpl.findByName(name);
    }

    @Override
    public Category rename(Category category) {
        return renameCategoryImpl.rename(category);
    }


    @Override
    public boolean existsById(Long id) {
        return categoryExistsImpl.existsById(id);
    }
}
