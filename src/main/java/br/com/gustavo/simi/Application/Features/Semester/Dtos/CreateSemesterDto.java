package br.com.gustavo.simi.Application.Features.Semester.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateSemesterDto(
        @Schema(example = "1")
        @NotNull(message = "Coordenador ID é obrigatório")
        Long coordinatorId,

        @Schema(example = "2025.1")
        @NotNull(message = "Titulo do semestre é obrigatório")
        String label,

        @Schema(description = "Data de início do semestre", example = "2025-03-01")
        @NotNull(message = "Data de início do semestre é obrigatório")
        @FutureOrPresent(message = "A data de início deve ser hoje ou no futuro")
        LocalDate startsAt,

        @Schema(description = "Data de término do semestre", example = "2025-08-31")
        @NotNull(message = "Data de término do semestre é obrigatório")
        @FutureOrPresent(message = "A data de término deve ser hoje ou no futuro")
        LocalDate endsAt
) { }
