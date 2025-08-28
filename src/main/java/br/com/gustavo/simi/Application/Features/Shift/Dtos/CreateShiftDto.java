package br.com.gustavo.simi.Application.Features.Shift.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record CreateShiftDto(
        @Schema(example = "1", description = "ID do semestre ao qual o plantão pertence")
        @NotNull(message = "O semestre é obrigatório")
        Long semesterId,

        @Schema(example = "1", description = "ID do local onde será o plantão")
        @NotNull(message = "O local é obrigatório")
        Long locationId,

        @Schema(example = "1", description = "ID da especialidade do plantão")
        @NotNull(message = "A especialidade é obrigatória")
        Long specialtyId,

        @Schema(example = "1", description = "ID do preceptor responsável pelo plantão")
        @NotNull(message = "O preceptor ID é obrigatório")
        Long preceptorId,

        @Schema(example = "2025-09-01T07:00:00", description = "Data e hora de início do plantão")
        @NotNull(message = "A data de início é obrigatória")
        @Future(message = "A data de início deve ser no futuro")
        LocalDateTime startsAt,

        @Schema(example = "2025-09-01T13:00:00", description = "Data e hora de término do plantão")
        @NotNull(message = "A data de término é obrigatória")
        @Future(message = "A data de término deve ser no futuro")
        LocalDateTime endsAt,

        @Schema(example = "5", description = "Número máximo de vagas para este plantão")
        @NotNull(message = "O número máximo de vagas é obrigatório")
        @Positive(message = "O número de vagas deve ser maior que zero")
        Integer maxSlots
) {
}
