package com.olo.apimonolithic.feature.categorymanagement.infrastructure.controller;

import com.olo.apimonolithic.feature.categorymanagement.application.CategoryService;
import com.olo.apimonolithic.feature.categorymanagement.domain.model.Category;
import com.olo.apimonolithic.feature.categorymanagement.infrastructure.dto.CategoryRequestDto;
import com.olo.apimonolithic.feature.categorymanagement.infrastructure.dto.CategoryResponseDto;
import com.olo.apimonolithic.feature.categorymanagement.infrastructure.mapper.CategoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto dto) {
        Category category = categoryService.create(CategoryMapper.dtoToModel(dto));
        return ResponseEntity.ok().body(CategoryMapper.modelToDto(category));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> findAll() {
        return ResponseEntity.ok().body(categoryService.findAll().stream().map(CategoryMapper::modelToDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(CategoryMapper.modelToDto(categoryService.findById(id)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CategoryResponseDto>> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(categoryService.findByName(name).stream().map(CategoryMapper::modelToDto).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> renameCategory(@Valid @RequestBody CategoryRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.ok().body(CategoryMapper.modelToDto(categoryService.rename(CategoryMapper.renameToModel(dto, id))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

}
