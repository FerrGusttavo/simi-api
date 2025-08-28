package br.com.gustavo.simi.Application.Features.ShiftAllocation.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateShiftAllocationDto(
        @Schema(example = "1")
        @NotNull(message = "O estudante ID é obrigatório")
        Long studentId,

        @Schema(example = "1")
        @NotNull(message = "O plantão ID é obrigatório")
        Long shiftId,

        @NotNull(message = "A data é obrigatória")
        LocalDate date,

        @Schema(example = "4")
        @NotNull(message = "A quantidade de horas é obrigatória")
        @Positive(message = "As horas devem ser maiores que zero")
        Integer hours
) { }
