package com.olo.apimonolithic.feature.categorymanagement.application;

import com.olo.apimonolithic.feature.categorymanagement.application.impl.CreateCategoryImpl;
import com.olo.apimonolithic.feature.categorymanagement.application.impl.DeleteCategoryImpl;
import com.olo.apimonolithic.feature.categorymanagement.application.impl.FindCategoryImpl;
import com.olo.apimonolithic.feature.categorymanagement.application.impl.RenameCategoryImpl;
import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.CreateCategory;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.DeleteCategory;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.FindCategory;
import com.olo.apimonolithic.feature.categorymanagement.domain.ports.inbound.RenameCategory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryService implements CreateCategory, DeleteCategory, FindCategory, RenameCategory {

    private final CreateCategoryImpl createCategoryImpl;
    private final DeleteCategoryImpl deleteCategoryImpl;
    private final FindCategoryImpl findCategoryImpl;
    private final RenameCategoryImpl renameCategoryImpl;

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
}
