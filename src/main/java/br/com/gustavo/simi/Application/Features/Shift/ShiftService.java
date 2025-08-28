package br.com.gustavo.simi.Application.Features.Shift;

import br.com.gustavo.simi.Application.Features.Shift.Dtos.CreateShiftDto;
import br.com.gustavo.simi.Domain.Entities.*;
import br.com.gustavo.simi.Domain.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final SemesterRepository semesterRepository;
    private final LocationRepository locationRepository;
    private final SpecialtyRepository specialtyRepository;
    private final PreceptorRepository preceptorRepository;
    private final UserRepository userRepository;

    public Shift createShift(CreateShiftDto dto) {
        Semester semester = semesterRepository.findById(dto.semesterId())
                .orElseThrow(() -> new IllegalArgumentException("Semestre não encontrado"));

        if (dto.startsAt().isBefore(semester.getStartsAt().atStartOfDay()) || dto.endsAt().isAfter(semester.getEndsAt().atStartOfDay())) {
            throw new IllegalArgumentException("O plantão deve estar dentro do período do semestre");
        }

        Location location = locationRepository.findById(dto.locationId())
                .orElseThrow(() -> new IllegalArgumentException("Local não encontrado"));

        Specialty specialty = specialtyRepository.findById(dto.specialtyId())
                .orElseThrow(() -> new IllegalArgumentException("Especialidade não encontrada"));

        if (!location.getSpecialties().contains(specialty)) {
            throw new IllegalArgumentException("O local selecionado não oferece esta especialidade");
        }

        Preceptor preceptor = preceptorRepository.findById(dto.preceptorId()).orElseThrow(() -> new IllegalArgumentException("Preceptor não encontrado"));

        Shift shift = Shift.builder()
                .semester(semester)
                .location(location)
                .specialty(specialty)
                .preceptor(preceptor)
                .startsAt(dto.startsAt())
                .endsAt(dto.endsAt())
                .maxSlots(dto.maxSlots())
                .build();

        return shiftRepository.save(shift);
    }

    List<Shift> findAllShifts() {
        return shiftRepository.findAll();
    }

    Shift getShiftById(Long id) {
        return shiftRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Plantão não encontrado"));
    }
}
