package br.com.gustavo.simi.Application.Features.Semester;

import br.com.gustavo.simi.Application.Features.Semester.Dtos.CreateSemesterDto;
import br.com.gustavo.simi.Domain.Entities.Semester;
import br.com.gustavo.simi.Domain.Entities.User;
import br.com.gustavo.simi.Domain.Enums.Role;
import br.com.gustavo.simi.Domain.Repositories.SemesterRepository;
import br.com.gustavo.simi.Domain.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterService {

    private final SemesterRepository semesterRepository;
    private final UserRepository userRepository;

    public Semester createSemester(CreateSemesterDto dto) {

        User coordinator = userRepository.findById(dto.coordinatorId())
                .orElseThrow(() -> new IllegalArgumentException("Coordenador não encontrado"));

        if (coordinator.getRole() != Role.COORDINATOR) {
            throw new IllegalArgumentException("O usuário selecionado não é um coordenador");
        }

        Semester existingSemester = semesterRepository.findByLabel(dto.label());

        if (existingSemester != null) {
            throw new IllegalStateException("Já existe um semestre cadastrado com esse label");
        }

        if (dto.endsAt().isBefore(dto.startsAt())) {
            throw new IllegalStateException("Data de término não pode ser antes da data de início");
        }

        Semester semester = Semester.builder()
                .coordinator(coordinator)
                .label(dto.label())
                .startsAt(dto.startsAt())
                .endsAt(dto.endsAt())
                .build();

        return semesterRepository.save(semester);

    }

    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    public Semester getSemesterById(Long id) {
        return semesterRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Semestre não encontrado"));
    }
}
