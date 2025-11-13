package com.olo.apimonolithic.common.domain.exception.product;

import com.olo.apimonolithic.common.domain.exception.DomainException;

public class ProductExistsException extends DomainException {
    public ProductExistsException(String message) {
        super(message);
    }

    public ProductExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
