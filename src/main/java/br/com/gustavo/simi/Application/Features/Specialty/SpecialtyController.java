package br.com.gustavo.simi.Application.Features.Specialty;

import br.com.gustavo.simi.Application.Features.Specialty.Dtos.CreateSpecialtyDto;
import br.com.gustavo.simi.Application.Features.Specialty.Presenters.SpecialtyPresenter;
import br.com.gustavo.simi.Domain.Entities.Specialty;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Especialidades")
@RestController
@RequestMapping("/api/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @PostMapping
    @Operation(
            summary = "Cria uma nova especialidade",
            description = "Permite cadastrar uma nova especialidade médica ou acadêmica no sistema."
    )
    public ResponseEntity<SpecialtyPresenter> createSpecialty(@Valid @RequestBody CreateSpecialtyDto dto) {
        Specialty specialty = specialtyService.createSpecialty(dto);
        return ResponseEntity.ok(SpecialtyPresenter.fromEntity(specialty));
    }

    @GetMapping
    @Operation(
            summary = "Lista todas as especialidades",
            description = "Retorna todas as especialidades cadastradas no sistema."
    )
    public ResponseEntity<List<SpecialtyPresenter>> findAllSpecialties() {
        List<Specialty> specialties = specialtyService.findAllSpecialties();
        List<SpecialtyPresenter> presenters = specialties.stream()
                .map(SpecialtyPresenter::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(presenters);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consulta uma especialidade por ID",
            description = "Retorna os detalhes de uma especialidade específica."
    )
    public ResponseEntity<SpecialtyPresenter> findSpecialtyById(@PathVariable Long id) {
        Specialty specialty = specialtyService.findSpecialtyById(id);
        return ResponseEntity.ok(SpecialtyPresenter.fromEntity(specialty));
    }
}
