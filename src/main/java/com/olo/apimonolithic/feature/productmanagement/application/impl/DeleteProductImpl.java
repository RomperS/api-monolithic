package com.olo.apimonolithic.feature.productmanagement.application.impl;

import com.olo.apimonolithic.feature.productmanagement.domain.port.inbound.DeleteProduct;
import com.olo.apimonolithic.feature.productmanagement.domain.port.outbound.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteProductImpl implements DeleteProduct {

    private final ProductRepository productRepository;

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }
}
