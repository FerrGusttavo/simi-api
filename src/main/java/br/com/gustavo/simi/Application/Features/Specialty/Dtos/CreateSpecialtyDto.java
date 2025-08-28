package br.com.gustavo.simi.Application.Features.Specialty.Dtos;

import jakarta.validation.constraints.NotNull;

public record CreateSpecialtyDto(
        @NotNull(message = "Nome é obrigatório")
        String name
) {
}
