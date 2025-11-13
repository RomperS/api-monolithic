package com.olo.apimonolithic.feature.productmanagement.infrastructure.controller;

import com.olo.apimonolithic.feature.productmanagement.application.ProductService;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.dto.ProductRequestDto;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.dto.ProductResponseDto;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.dto.ProductSearchRequestDTO;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.dto.ProductUpdateRequestDTO;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductRestController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductRequestDto dto){
        return ResponseEntity.ok().body(ProductMapper.modelToDto(productService.create(ProductMapper.dtoToCreateCommand(dto))));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        return ResponseEntity.ok().body(productService.findAll().stream().map(ProductMapper::modelToDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(ProductMapper.modelToDto(productService.findById(id)));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDto>> findByCriteria(@Valid @RequestBody ProductSearchRequestDTO dto) {
        return ResponseEntity.ok().body(
                productService.findByFilter(
                        ProductMapper.dtoToSearchCommand(dto))
                        .stream()
                        .map(ProductMapper::modelToDto)
                        .toList()
        );
    }

    @PatchMapping
    public ResponseEntity<ProductResponseDto> update(@Valid @RequestBody ProductUpdateRequestDTO dto) {
        return ResponseEntity.ok().body(ProductMapper.modelToDto(productService.update(ProductMapper.dtoToUpdateCommand(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }
}
