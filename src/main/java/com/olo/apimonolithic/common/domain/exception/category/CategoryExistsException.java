package com.olo.apimonolithic.common.domain.exception.category;

import com.olo.apimonolithic.common.domain.exception.DomainException;

public class CategoryExistsException extends DomainException {
    public CategoryExistsException(String message) {
        super(message);
    }

    public CategoryExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
