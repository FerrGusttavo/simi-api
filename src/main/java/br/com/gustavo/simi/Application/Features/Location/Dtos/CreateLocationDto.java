package br.com.gustavo.simi.Application.Features.Location.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record CreateLocationDto(
        @Schema(example = "1")
        @NotNull(message = "Coordenador ID é obrigatório")
        Long coordinatorId,

        @Schema(example = "[1, 2, 3]")
        Set<Long> specialtyIds,

        @Schema(example = "Hospital Regional de João Pessoa")
        @NotNull(message = "Nome é obrigatório")
        String name,

        @Schema(example = "PB")
        @NotNull(message = "Estado é obrigatório")
        String state,

        @Schema(example = "Av. Epitácio Pessoa, 1000")
        @NotNull(message = "Endereço é obrigatório")
        String address,

        @Schema(example = "João Pessoa")
        @NotNull(message = "Cidade é obrigatória")
        String city,

        @Schema(example = "58000-000")
        @NotNull(message = "CEP é obrigatório")
        String zipCode
) {
}
