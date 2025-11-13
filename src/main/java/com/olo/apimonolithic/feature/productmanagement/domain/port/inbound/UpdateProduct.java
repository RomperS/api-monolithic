package com.olo.apimonolithic.feature.productmanagement.domain.port.inbound;

import com.olo.apimonolithic.feature.productmanagement.domain.command.UpdateProductCommand;
import com.olo.apimonolithic.feature.productmanagement.domain.model.Product;

public interface UpdateProduct {
    Product update(UpdateProductCommand product);
}
