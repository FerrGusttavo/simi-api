package br.com.gustavo.simi.Application.Features.Specialty.Presenters;

import br.com.gustavo.simi.Domain.Entities.Specialty;
import io.swagger.v3.oas.annotations.media.Schema;

public record SpecialtyPresenter(
        @Schema(example = "1")
        Long id,

        @Schema(example = "Ambulatorio")
        String name
) {
    public static SpecialtyPresenter fromEntity(Specialty specialty) {
        return new SpecialtyPresenter(
                specialty.getId(),
                specialty.getName()
        );
    }
}
