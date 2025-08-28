package br.com.gustavo.simi.Application.Features.Shift;

import br.com.gustavo.simi.Application.Features.Shift.Dtos.CreateShiftDto;
import br.com.gustavo.simi.Application.Features.Shift.Presenters.ShiftPresenter;
import br.com.gustavo.simi.Domain.Entities.Shift;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Plantões")
@RestController
@RequestMapping("/api/shifts")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;

    @PostMapping
    @Operation(
            summary = "Cria um novo plantão",
            description = "Permite criar um plantão com semestre, local, especialidade, preceptor, horários e número máximo de vagas."
    )
    public ResponseEntity<ShiftPresenter> createShift(@Valid @RequestBody CreateShiftDto dto) {
        Shift shift = shiftService.createShift(dto);
        return ResponseEntity.ok(ShiftPresenter.fromEntity(shift));
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os plantões",
            description = "Retorna todos os plantões cadastrados no sistema, com informações detalhadas de semestre, local, especialidade e preceptor."
    )
    public ResponseEntity<List<ShiftPresenter>> getAllShifts() {
        List<Shift> shifts = shiftService.findAllShifts();
        List<ShiftPresenter> presenters = shifts.stream()
                .map(ShiftPresenter::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(presenters);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consulta um plantão por ID",
            description = "Retorna os detalhes de um plantão específico, incluindo semestre, local, especialidade e preceptor."
    )
    public ResponseEntity<ShiftPresenter> getShiftById(@PathVariable Long id) {
        Shift shift = shiftService.getShiftById(id);
        return ResponseEntity.ok(ShiftPresenter.fromEntity(shift));
    }
}
