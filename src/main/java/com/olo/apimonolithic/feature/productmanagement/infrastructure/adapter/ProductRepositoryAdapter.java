package com.olo.apimonolithic.feature.productmanagement.infrastructure.adapter;

import com.olo.apimonolithic.feature.productmanagement.domain.model.Product;
import com.olo.apimonolithic.feature.productmanagement.domain.port.outbound.ProductRepository;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.mapper.ProductMapper;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public boolean existsByName(String name) {
        return productJpaRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productJpaRepository.findById(id).map(ProductMapper::entityToModel);
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll()
                .stream()
                .map(ProductMapper::entityToModel)
                .toList();
    }

    @Override
    public List<Product> findByCategoryId(Long id) {
        return productJpaRepository.findProductsByCategoryId(id)
                .stream()
                .map(ProductMapper::entityToModel)
                .toList();
    }

    @Override
    @Transactional(readOnly = false)
    public Product create(Product product) {
        var entity = ProductMapper.modelToEntity(product);
        var saved = productJpaRepository.save(entity);
        return ProductMapper.entityToModel(saved);
    }

    @Override
    @Transactional
    public Product update(Product productUpdated) {
        var entity = ProductMapper.modelToEntity(productUpdated);
        var saved = productJpaRepository.save(entity);
        return ProductMapper.entityToModel(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productJpaRepository.deleteById(id);
    }
}

