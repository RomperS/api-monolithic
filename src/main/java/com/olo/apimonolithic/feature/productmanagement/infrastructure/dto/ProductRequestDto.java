package com.olo.apimonolithic.feature.productmanagement.infrastructure.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequestDto(
        @NotEmpty String name,
        @NotNull @DecimalMin(value = "0.1") BigDecimal value,
        @NotEmpty List<Long> categoriesId
) {}
