package br.com.gustavo.simi.Application.Features.Semester.Presenters;

import br.com.gustavo.simi.Domain.Entities.Semester;

import java.time.LocalDate;

public record SemesterPresenter(
        Long id,
        String label,
        LocalDate startsAt,
        LocalDate endsAt,
        String coordinatorName,
        String coordinatorEmail
) {
    public static SemesterPresenter fromEntity(Semester semester) {
        return new SemesterPresenter(
                semester.getId(),
                semester.getLabel(),
                semester.getStartsAt(),
                semester.getEndsAt(),
                semester.getCoordinator() != null ? semester.getCoordinator().getName() : null,
                semester.getCoordinator() != null ? semester.getCoordinator().getEmail() : null
        );
    }
}
