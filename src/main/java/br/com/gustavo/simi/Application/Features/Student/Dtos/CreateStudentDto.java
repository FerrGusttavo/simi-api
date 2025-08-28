package br.com.gustavo.simi.Application.Features.Student.Dtos;

import br.com.gustavo.simi.Domain.Enums.Period;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CreateStudentDto(
        @Schema(example = "1")
        @NotNull(message = "Coordenador ID é obrigatório")
        Long coordinatorId,

        @Schema(example = "1")
        @NotNull(message = "User ID é obrigatório")
        Long userId,

        @NotNull(message = "Matrícula é obrigatório")
        String registration,

        @Schema(example = "P10")
        @NotNull(message = "Período é obrigatório")
        String period
) { }
