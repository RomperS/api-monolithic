package com.olo.apimonolithic.common.domain.exception.product;

import com.olo.apimonolithic.common.domain.exception.DomainException;

public class ProductNotFoundException extends DomainException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
