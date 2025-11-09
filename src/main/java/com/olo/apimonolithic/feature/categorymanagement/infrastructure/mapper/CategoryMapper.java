package com.olo.apimonolithic.feature.categorymanagement.infrastructure.mapper;

import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;
import com.olo.apimonolithic.feature.categorymanagement.infrastructure.dto.CategoryRequestDto;
import com.olo.apimonolithic.feature.categorymanagement.infrastructure.dto.CategoryResponseDto;
import com.olo.apimonolithic.feature.categorymanagement.infrastructure.entity.CategoryEntity;

public class CategoryMapper {

    public static Category entityToModel(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Category(
                entity.getId(),
                entity.getName()
        );
    }

    public static CategoryEntity modelToEntity(Category model) {
        if (model == null) {
            return null;
        }

        return new CategoryEntity(
                model.id(),
                model.name()
        );
    }

    public static Category dtoToModel(CategoryRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return new Category(
                null,
                dto.name()
        );
    }

    public static CategoryResponseDto modelToDto(Category model) {
        if (model == null) {
            return null;
        }

        return new CategoryResponseDto(
                model.id(),
                model.name()
        );
    }

    public static Category renameToModel(CategoryRequestDto dto, Long id) {
        if (dto == null || id == null) {
            return null;
        }

        return new Category(
                id,
                dto.name()
        );
    }
}
