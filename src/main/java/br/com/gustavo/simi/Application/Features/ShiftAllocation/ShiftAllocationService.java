package br.com.gustavo.simi.Application.Features.ShiftAllocation;

import br.com.gustavo.simi.Application.Features.ShiftAllocation.Dtos.CreateShiftAllocationDto;
import br.com.gustavo.simi.Domain.Entities.Shift;
import br.com.gustavo.simi.Domain.Entities.ShiftAllocation;
import br.com.gustavo.simi.Domain.Entities.Student;
import br.com.gustavo.simi.Domain.Repositories.ShiftAllocationRepository;
import br.com.gustavo.simi.Domain.Repositories.ShiftRepository;
import br.com.gustavo.simi.Domain.Repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftAllocationService {

    private final ShiftRepository shiftRepository;
    private final ShiftAllocationRepository shiftAllocationRepository;
    private final StudentRepository studentRepository;

    public ShiftAllocation createShiftAllocation(CreateShiftAllocationDto dto) {
        Shift shift = shiftRepository.findById(dto.shiftId()).orElseThrow(() -> new IllegalArgumentException("Plantão não encontrado"));
        Student student = studentRepository.findById(dto.studentId()).orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado"));

        if (shiftAllocationRepository.existsByShiftIdAndStudentId(shift.getId(), student.getId())) {
            throw new IllegalArgumentException("Aluno já alocado nesse plantão");
        }

        long shiftCapacity = shiftAllocationRepository.countByShiftId(shift.getId());
        if (shiftCapacity >= shift.getMaxSlots()) {
            throw new IllegalStateException("Plantão lotado");
        }

        if (shift.getStartsAt().toLocalDate().isBefore(shift.getSemester().getStartsAt())
                || shift.getEndsAt().toLocalDate().isAfter(shift.getSemester().getEndsAt())) {
            throw new IllegalArgumentException("Plantão fora do intervalo do semestre");
        }

        List<ShiftAllocation> existing = shiftAllocationRepository.findByStudentId(student.getId());
        for  (ShiftAllocation a : existing) {
            Shift other = a.getShift();
            boolean overlap = shift.getStartsAt().isBefore(other.getEndsAt()) &&
                    shift.getEndsAt().isAfter(other.getStartsAt());
            if (overlap) {
                throw  new IllegalStateException("Sobreposição com outro plantão já alocado (id=" + other.getId() + ")");
            }
        }

        ShiftAllocation shiftAllocation = ShiftAllocation.builder()
                .shift(shift)
                .student(student)
                .build();

        return  shiftAllocationRepository.save(shiftAllocation);
    }

    public List<ShiftAllocation> getAllShiftAllocations() {
        return shiftAllocationRepository.findAll();
    }

    public ShiftAllocation getShiftAllocationById(Long id) {
        return shiftAllocationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Alocação de turno não encontrado"));
    }
}
