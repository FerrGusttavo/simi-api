package br.com.gustavo.simi.Application.Features.Preceptor.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CreatePreceptorDto(
        @Schema(example = "1")
        @NotNull(message = "Coordenador ID é obrigatório")
        Long coordinatorId,

        @Schema(example = "1")
        @NotNull(message = "User ID é obrigatório")
        Long userId,

        @NotNull(message = "CRM é obrigatório")
        String crm
) {
}
