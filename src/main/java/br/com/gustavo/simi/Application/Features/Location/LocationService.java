package br.com.gustavo.simi.Application.Features.Location;

import br.com.gustavo.simi.Application.Features.Location.Dtos.CreateLocationDto;
import br.com.gustavo.simi.Domain.Entities.Location;
import br.com.gustavo.simi.Domain.Entities.Specialty;
import br.com.gustavo.simi.Domain.Entities.User;
import br.com.gustavo.simi.Domain.Enums.Role;
import br.com.gustavo.simi.Domain.Repositories.LocationRepository;
import br.com.gustavo.simi.Domain.Repositories.SpecialtyRepository;
import br.com.gustavo.simi.Domain.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final SpecialtyRepository specialtyRepository;

    public Location createLocation(CreateLocationDto dto) {
        User coordinator = userRepository.findById(dto.coordinatorId())
                .orElseThrow(() -> new IllegalArgumentException("Coordenador não encontrado"));

        if (coordinator.getRole() != Role.COORDINATOR) {
            throw new IllegalArgumentException("O usuário selecionado não é um coordenador");
        }

        Set<Specialty> specialties = new HashSet<>();
        if (dto.specialtyIds() != null) {
            dto.specialtyIds().forEach(id -> {
                Specialty specialty = specialtyRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Especialidade não encontrada: " + id));
                specialties.add(specialty);
            });
        }

        Location location = Location.builder()
                .coordinator(coordinator)
                .specialties(specialties)
                .name(dto.name())
                .state(dto.state())
                .address(dto.address())
                .city(dto.city())
                .zipCode(dto.zipCode())
                .build();

        return locationRepository.save(location);
    }

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(@PathVariable Long id) {
        return locationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Localização não encontrada"));
    }
}
