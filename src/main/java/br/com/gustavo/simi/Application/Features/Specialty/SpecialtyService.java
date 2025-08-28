package br.com.gustavo.simi.Application.Features.Specialty;

import br.com.gustavo.simi.Application.Features.Specialty.Dtos.CreateSpecialtyDto;
import br.com.gustavo.simi.Domain.Entities.Specialty;
import br.com.gustavo.simi.Domain.Repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public Specialty createSpecialty(CreateSpecialtyDto dto) {
        Specialty existingSpecialty = specialtyRepository.findByName(dto.name());

        if (existingSpecialty != null) {
            throw new IllegalArgumentException("Especialidade já existente");
        }

        Specialty specialty = Specialty.builder().name(dto.name()).build();

        return specialtyRepository.save(specialty);
    }

    public List<Specialty> findAllSpecialties() {
        return specialtyRepository.findAll();
    }

    public Specialty findSpecialtyById(Long id) {
        return specialtyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Especialidade não encontrada"));
    }
}
