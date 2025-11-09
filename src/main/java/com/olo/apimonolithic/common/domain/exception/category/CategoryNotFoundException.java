package com.olo.apimonolithic.common.domain.exception.category;

import com.olo.apimonolithic.common.domain.exception.DomainException;

public class CategoryNotFoundException extends DomainException {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
