package br.com.gustavo.simi.Application.Features.ShiftAllocation.Presenters;

import br.com.gustavo.simi.Application.Features.Shift.Presenters.ShiftPresenter;
import br.com.gustavo.simi.Application.Features.Student.Presenters.StudentPresenter;
import br.com.gustavo.simi.Domain.Entities.ShiftAllocation;

import java.time.LocalDateTime;

public record ShiftAllocationPresenter(
        Long id,
        ShiftPresenter shift,
        StudentPresenter student,
        LocalDateTime createdAt
) {
    public static ShiftAllocationPresenter fromEntity(ShiftAllocation allocation) {
        return new ShiftAllocationPresenter(
                allocation.getId(),
                ShiftPresenter.fromEntity(allocation.getShift()),
                StudentPresenter.fromEntity(allocation.getStudent()),
                allocation.getCreatedAt()
        );
    }
}
