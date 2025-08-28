package br.com.gustavo.simi.Application.Features.Preceptor;

import br.com.gustavo.simi.Application.Features.Preceptor.Dtos.CreatePreceptorDto;
import br.com.gustavo.simi.Application.Features.Preceptor.Presenters.PreceptorPresenter;
import br.com.gustavo.simi.Domain.Entities.Preceptor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Preceptores")
@RestController
@RequestMapping("/api/preceptors")
@RequiredArgsConstructor
public class PreceptorController {
    private final PreceptorService preceptorService;

    @PostMapping
    @Operation(
            summary = "Cria um novo preceptor",
            description = "Permite cadastrar um preceptor, associando ao coordenador e usuário correspondente."
    )
    public ResponseEntity<PreceptorPresenter> createPreceptor(@Valid @RequestBody CreatePreceptorDto dto) {
        Preceptor preceptor = preceptorService.createPreceptor(dto);
        return ResponseEntity.ok(PreceptorPresenter.fromEntity(preceptor));
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os preceptores",
            description = "Retorna todos os preceptores cadastrados no sistema."
    )
    public ResponseEntity<List<PreceptorPresenter>> getAllPreceptors() {
        List<Preceptor> preceptors = preceptorService.getAllPreceptors();
        List<PreceptorPresenter> presenters = preceptors.stream()
                .map(PreceptorPresenter::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(presenters);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consulta um preceptor por ID",
            description = "Retorna os detalhes de um preceptor específico, incluindo usuário e coordenador associados."
    )
    public ResponseEntity<PreceptorPresenter> getPreceptor(@PathVariable Long id) {
        Preceptor preceptor = preceptorService.findPreceptorById(id);
        return ResponseEntity.ok(PreceptorPresenter.fromEntity(preceptor));
    }
}
