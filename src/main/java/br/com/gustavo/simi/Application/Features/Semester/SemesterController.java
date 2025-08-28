package br.com.gustavo.simi.Application.Features.Semester;

import br.com.gustavo.simi.Application.Features.Semester.Dtos.CreateSemesterDto;
import br.com.gustavo.simi.Application.Features.Semester.Presenters.SemesterPresenter;
import br.com.gustavo.simi.Domain.Entities.Semester;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Semestres")
@RestController
@RequestMapping("/api/semesters")
@RequiredArgsConstructor
public class SemesterController {

    private final SemesterService semesterService;

    @PostMapping
    @Operation(
            summary = "Cria um novo semestre",
            description = "Permite cadastrar um semestre com coordenador, datas de início e término e rótulo."
    )
    public ResponseEntity<SemesterPresenter> createSemester(@Valid @RequestBody CreateSemesterDto dto) {
        Semester semester = semesterService.createSemester(dto);
        return ResponseEntity.ok(SemesterPresenter.fromEntity(semester));
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os semestres",
            description = "Retorna todos os semestres cadastrados no sistema."
    )
    public ResponseEntity<List<SemesterPresenter>> getAllSemesters() {
        List<Semester> semesters = semesterService.getAllSemesters();
        List<SemesterPresenter> presenters = semesters.stream()
                .map(SemesterPresenter::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(presenters);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consulta um semestre por ID",
            description = "Retorna os detalhes de um semestre específico, incluindo coordenador e datas."
    )
    public ResponseEntity<SemesterPresenter> getSemester(@PathVariable Long id) {
        Semester semester = semesterService.getSemesterById(id);
        return ResponseEntity.ok(SemesterPresenter.fromEntity(semester));
    }
}
