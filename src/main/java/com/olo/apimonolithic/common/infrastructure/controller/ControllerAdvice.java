package com.olo.apimonolithic.common.infrastructure.controller;

import com.olo.apimonolithic.common.domain.exception.DomainException;
import com.olo.apimonolithic.common.domain.exception.category.CategoryExistsException;
import com.olo.apimonolithic.common.domain.exception.category.CategoryNotFoundException;
import com.olo.apimonolithic.common.infrastructure.utils.ResponseExceptionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

        if (ex instanceof CategoryNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof CategoryExistsException) {
            status = HttpStatus.CONFLICT;
        /* } else if (ex instanceof CategoryNotFoundException) {
            status = HttpStatus.BAD_REQUEST; */
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        responseExceptionUtil.writeErrorResponse(response, status, ex.getClass().getSimpleName(), ex.getMessage(), request.getRequestURI());
    }
}
