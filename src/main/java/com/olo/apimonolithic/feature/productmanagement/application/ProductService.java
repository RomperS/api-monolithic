package com.olo.apimonolithic.feature.productmanagement.application;

import com.olo.apimonolithic.feature.productmanagement.application.impl.CreateProductImpl;
import com.olo.apimonolithic.feature.productmanagement.application.impl.DeleteProductImpl;
import com.olo.apimonolithic.feature.productmanagement.application.impl.FindProductImpl;
import com.olo.apimonolithic.feature.productmanagement.application.impl.UpdateProductImpl;
import com.olo.apimonolithic.feature.productmanagement.domain.command.ProductCommand;
import com.olo.apimonolithic.feature.productmanagement.domain.command.ProductSearchCriteria;
import com.olo.apimonolithic.feature.productmanagement.domain.command.UpdateProductCommand;
import com.olo.apimonolithic.feature.productmanagement.domain.model.Product;
import com.olo.apimonolithic.feature.productmanagement.domain.port.inbound.CreateProduct;
import com.olo.apimonolithic.feature.productmanagement.domain.port.inbound.DeleteProduct;
import com.olo.apimonolithic.feature.productmanagement.domain.port.inbound.FindProduct;
import com.olo.apimonolithic.feature.productmanagement.domain.port.inbound.UpdateProduct;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductService implements CreateProduct, DeleteProduct, FindProduct, UpdateProduct {

    private final CreateProductImpl  createProductImpl;
    private final DeleteProductImpl deleteProductImpl;
    private final FindProductImpl findProductImpl;
    private final UpdateProductImpl updateProductImpl;

    @Override
    public Product create(ProductCommand product) {
        return createProductImpl.create(product);
    }

    @Override
    public void delete(Long id) {
        deleteProductImpl.delete(id);
    }

    @Override
    public Product findById(Long id) {
        return findProductImpl.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return findProductImpl.findAll();
    }

    @Override
    public List<Product> findByFilter(ProductSearchCriteria criteria) {
        return findProductImpl.findByFilter(criteria);
    }

    @Override
    public Product update(UpdateProductCommand product) {
        return updateProductImpl.update(product);
    }
}
