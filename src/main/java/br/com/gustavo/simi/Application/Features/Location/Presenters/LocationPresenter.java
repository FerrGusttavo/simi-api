package br.com.gustavo.simi.Application.Features.Location.Presenters;

import br.com.gustavo.simi.Application.Features.Specialty.Presenters.SpecialtyPresenter;

import br.com.gustavo.simi.Domain.Entities.Location;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;
import java.util.stream.Collectors;

public record LocationPresenter(
        @Schema(example = "1")
        Long id,

        @Schema(example = "Hospital Regional de João Pessoa")
        String name,

        @Schema(example = "PB")
        String state,

        @Schema(example = "Av. Epitácio Pessoa, 1000")
        String address,

        @Schema(example = "João Pessoa")
        String city,

        @Schema(example = "58000-000")
        String zipCode,

        Set<SpecialtyPresenter> specialties
) {
    public static LocationPresenter fromEntity(Location location) {
        return new LocationPresenter(
                location.getId(),
                location.getName(),
                location.getState(),
                location.getAddress(),
                location.getCity(),
                location.getZipCode(),
                location.getSpecialties().stream()
                        .map(SpecialtyPresenter::fromEntity)
                        .collect(Collectors.toSet())
        );
    }
}
