package com.olo.apimonolithic.feature.categorymanagement.infrastructure.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CategoryRequestDto(
        @NotNull @NotEmpty String name
) {
}
