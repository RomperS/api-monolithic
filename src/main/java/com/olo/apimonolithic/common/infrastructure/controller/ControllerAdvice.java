package com.olo.apimonolithic.common.infrastructure.controller;

import com.olo.apimonolithic.common.domain.exception.DomainException;
import com.olo.apimonolithic.common.domain.exception.category.CategoryExistsException;
import com.olo.apimonolithic.common.domain.exception.category.CategoryNotFoundException;
import com.olo.apimonolithic.common.domain.exception.product.ProductExistsException;
import com.olo.apimonolithic.common.domain.exception.product.ProductNotFoundException;
import com.olo.apimonolithic.common.infrastructure.utils.ResponseExceptionUtil;
import com.olo.apimonolithic.feature.categorymanagement.infrastructure.dto.CategoryRequestDto;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.dto.ProductRequestDto;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.dto.ProductSearchRequestDTO;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.dto.ProductUpdateRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvice {

    private final ResponseExceptionUtil responseExceptionUtil;

    @ExceptionHandler(DomainException.class)
    public void handleDomainException(DomainException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpStatus status;

        if (ex instanceof CategoryNotFoundException || ex instanceof ProductNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof CategoryExistsException || ex instanceof ProductExistsException) {
            status = HttpStatus.CONFLICT;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        responseExceptionUtil.writeErrorResponse(response, status, ex.getClass().getSimpleName(), ex.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleValidation(MethodArgumentNotValidException ex,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        Object target = ex.getBindingResult().getTarget();
        String errorMessage = "Invalid request: Validation failed.";

        if (target instanceof CategoryRequestDto) {
            errorMessage = "Invalid category data: Missing category name.";
        } else if (target instanceof ProductRequestDto) {
            List<FieldError> errors = ex.getBindingResult().getFieldErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = errors.get(0);
                String field = fieldError.getField();
                switch (field) {
                    case "name" -> errorMessage = "Invalid product data: Missing name.";
                    case "value" -> errorMessage = "Invalid product data: Missing or invalid value.";
                    case "categoriesId" -> errorMessage = "Invalid product data: Must include at least one category.";
                }
            }
        } else if (target instanceof ProductSearchRequestDTO) {
            List<FieldError> errors = ex.getBindingResult().getFieldErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = errors.get(0);
                String field = fieldError.getField();
                switch (field) {
                    case "categoryId" -> errorMessage = "Invalid product data: Missing category id.";
                    case "page" -> errorMessage = "Invalid product data: Missing or invalid page number.";
                    case "pageSize" -> errorMessage = "Invalid product data: Missing or invalid page size number.";
                }
            }
        } else if (target instanceof ProductUpdateRequestDTO) {
            List<FieldError> errors = ex.getBindingResult().getFieldErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = errors.get(0);
                String field = fieldError.getField();
                switch (field) {
                    case "targetId" -> errorMessage = "Invalid product data: Missing or invalid target id.";
                    case "value" -> errorMessage = "Invalid product data: value must be at least 0.0.";
                }
            }
        }

        responseExceptionUtil.writeErrorResponse(
                response,
                status,
                ex.getClass().getSimpleName(),
                errorMessage,
                request.getRequestURI()
        );
    }
}
