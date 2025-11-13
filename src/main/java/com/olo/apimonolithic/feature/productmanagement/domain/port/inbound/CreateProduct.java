package com.olo.apimonolithic.feature.productmanagement.domain.port.inbound;

import com.olo.apimonolithic.feature.productmanagement.domain.command.ProductCommand;
import com.olo.apimonolithic.feature.productmanagement.domain.model.Product;

public interface CreateProduct {
    Product create(ProductCommand product);
}
