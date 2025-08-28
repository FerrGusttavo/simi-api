package br.com.gustavo.simi.Application.Features.Location;

import br.com.gustavo.simi.Application.Features.Location.Dtos.CreateLocationDto;
import br.com.gustavo.simi.Application.Features.Location.Presenters.LocationPresenter;
import br.com.gustavo.simi.Domain.Entities.Location;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Localizações")
@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    @Operation(
            summary = "Cria uma nova localização",
            description = "Permite cadastrar uma localização associada a um coordenador e suas especialidades."
    )
    public ResponseEntity<LocationPresenter> addLocation(@Valid @RequestBody CreateLocationDto dto) {
        Location location = locationService.createLocation(dto);
        return ResponseEntity.ok(LocationPresenter.fromEntity(location));
    }

    @GetMapping
    @Operation(
            summary = "Lista todas as localizações",
            description = "Retorna todas as localizações cadastradas no sistema."
    )
    public ResponseEntity<List<LocationPresenter>> getAllLocations() {
        List<Location> locations = locationService.findAllLocations();
        List<LocationPresenter> presenters = locations.stream()
                .map(LocationPresenter::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(presenters);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consulta uma localização por ID",
            description = "Retorna os detalhes de uma localização específica, incluindo coordenador e especialidades associadas."
    )
    public ResponseEntity<LocationPresenter> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(LocationPresenter.fromEntity(locationService.getLocationById(id)));
    }
}
