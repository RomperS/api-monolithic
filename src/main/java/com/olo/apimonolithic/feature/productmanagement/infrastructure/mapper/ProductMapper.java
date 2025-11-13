package com.olo.apimonolithic.feature.productmanagement.infrastructure.mapper;

import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;
import com.olo.apimonolithic.feature.categorymanagement.infrastructure.mapper.CategoryMapper;
import com.olo.apimonolithic.feature.productmanagement.domain.command.ProductCommand;
import com.olo.apimonolithic.feature.productmanagement.domain.command.ProductSearchCriteria;
import com.olo.apimonolithic.feature.productmanagement.domain.command.UpdateProductCommand;
import com.olo.apimonolithic.feature.productmanagement.domain.model.Product;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.dto.ProductRequestDto;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.dto.ProductResponseDto;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.dto.ProductSearchRequestDTO;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.dto.ProductUpdateRequestDTO;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductEntity modelToEntity(Product model) {
        if (model == null) {
            return null;
        }

        return new ProductEntity(
                model.id(),
                model.name(),
                model.value(),
                model.categories()
                        .stream()
                        .map(CategoryMapper::modelToEntity)
                        .collect(Collectors.toSet())
        );
    }

    public static Product entityToModel(ProductEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getCategories()
                        .stream()
                        .map(CategoryMapper::entityToModel)
                        .collect(Collectors.toSet()),
                entity.getValue()
        );
    }

    public static ProductResponseDto modelToDto(Product model) {
        if (model == null) {
            return null;
        }

        List<Long> categoriesId = model.categories().stream().map(Category::id).toList();

        return new ProductResponseDto(
                model.id(),
                model.name(),
                model.value(),
                categoriesId
        );
    }

    public static ProductSearchCriteria dtoToSearchCommand(ProductSearchRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return new ProductSearchCriteria(
                dto.id(),
                dto.name(),
                dto.categoryId(),
                new ProductSearchCriteria.ValueRange(dto.valueRange().min(), dto.valueRange().max()),
                dto.page(),
                dto.pageSize()
        );
    }

    public static ProductCommand dtoToCreateCommand(ProductRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return new ProductCommand(
                dto.name(),
                dto.value(),
                dto.categoriesId()
        );
    }

    public static UpdateProductCommand dtoToUpdateCommand(ProductUpdateRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        return new UpdateProductCommand(
                dto.targetId(),
                dto.name(),
                dto.additionalCategories(),
                dto.categoriesToRemove(),
                dto.value()
        );
    }
}
