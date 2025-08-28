package br.com.gustavo.simi.Application.Features.Shift.Presenters;

import br.com.gustavo.simi.Application.Features.Location.Presenters.LocationPresenter;
import br.com.gustavo.simi.Application.Features.Preceptor.Presenters.PreceptorPresenter;
import br.com.gustavo.simi.Application.Features.Semester.Presenters.SemesterPresenter;
import br.com.gustavo.simi.Application.Features.Specialty.Presenters.SpecialtyPresenter;
import br.com.gustavo.simi.Domain.Entities.Shift;

import java.time.LocalDateTime;

public record ShiftPresenter(
        Long id,
        SemesterPresenter semester,
        LocationPresenter location,
        SpecialtyPresenter specialty,
        PreceptorPresenter preceptor,
        LocalDateTime startsAt,
        LocalDateTime endsAt,
        Integer maxSlots
) {
    public static ShiftPresenter fromEntity(Shift shift) {
        return new ShiftPresenter(
                shift.getId(),
                SemesterPresenter.fromEntity(shift.getSemester()),
                LocationPresenter.fromEntity(shift.getLocation()),
                SpecialtyPresenter.fromEntity(shift.getSpecialty()),
                PreceptorPresenter.fromEntity(shift.getPreceptor()),
                shift.getStartsAt(),
                shift.getEndsAt(),
                shift.getMaxSlots()
        );
    }
}
