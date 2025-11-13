package com.olo.apimonolithic.common.infrastructure.controller;

import com.olo.apimonolithic.common.domain.exception.DomainException;
import com.olo.apimonolithic.common.domain.exception.category.CategoryExistsException;
import com.olo.apimonolithic.common.domain.exception.category.CategoryNotFoundException;
import com.olo.apimonolithic.common.domain.exception.product.ProductExistsException;
import com.olo.apimonolithic.common.domain.exception.product.ProductNotFoundException;
import com.olo.apimonolithic.common.infrastructure.utils.ResponseExceptionUtil;
import com.olo.apimonolithic.feature.categorymanagement.infrastructure.dto.CategoryRequestDto;
import com.olo.apimonolithic.feature.productmanagement.infrastructure.dto.ProductRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

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
    public void handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String errorMessage;
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Object target = ex.getBindingResult().getTarget();

        if (target instanceof CategoryRequestDto) {
            errorMessage = "Invalid category data: Missing category name.";
        } else if (target instanceof ProductRequestDto) {
            errorMessage = "Invalid product data: Missing target category.";
        } else {
            errorMessage = "Invalid request: Validation failed.";
        }

        responseExceptionUtil.writeErrorResponse(response, status, ex.getClass().getSimpleName(), errorMessage, request.getRequestURI());
    }
}
