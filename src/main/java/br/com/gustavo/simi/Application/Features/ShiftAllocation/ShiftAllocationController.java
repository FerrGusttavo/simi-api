package br.com.gustavo.simi.Application.Features.ShiftAllocation;

import br.com.gustavo.simi.Application.Features.ShiftAllocation.Dtos.CreateShiftAllocationDto;
import br.com.gustavo.simi.Application.Features.ShiftAllocation.Presenters.ShiftAllocationPresenter;
import br.com.gustavo.simi.Domain.Entities.ShiftAllocation;
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
@RequestMapping("/api/shifts/allocations")
@RequiredArgsConstructor
public class ShiftAllocationController {

    private final ShiftAllocationService shiftAllocationService;

    @PostMapping()
    @Operation(
            summary = "Cria uma nova alocação de plantão",
            description = "Este endpoint permite criar uma alocação de plantão para um estudante em uma data e hora específicas."
    )
    public ResponseEntity<ShiftAllocationPresenter> createShiftAllocation(@Valid @RequestBody CreateShiftAllocationDto dto) {
        ShiftAllocation result = shiftAllocationService.createShiftAllocation(dto);
        return ResponseEntity.ok(ShiftAllocationPresenter.fromEntity(result));
    }

    @GetMapping
    @Operation(
            summary = "Lista todas as alocações de plantões",
            description = "Retorna todas as alocações de plantões cadastradas no sistema com detalhes do aluno e do plantão."
    )
    public ResponseEntity<List<ShiftAllocationPresenter>> getAllShiftAllocations() {
        List<ShiftAllocation> allocations = shiftAllocationService.getAllShiftAllocations();
        List<ShiftAllocationPresenter> presenters = allocations.stream()
                .map(ShiftAllocationPresenter::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(presenters);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consulta uma alocação de plantão por ID",
            description = "Retorna os detalhes de uma alocação de plantão específica, incluindo informações do aluno e do plantão."
    )
    public ResponseEntity<ShiftAllocationPresenter> getShiftAllocation(@PathVariable Long id) {
        ShiftAllocation allocation = shiftAllocationService.getShiftAllocationById(id);
        return ResponseEntity.ok(ShiftAllocationPresenter.fromEntity(allocation));
    }
}
