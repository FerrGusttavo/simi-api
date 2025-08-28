package br.com.gustavo.simi.Application.Features.Preceptor;

import br.com.gustavo.simi.Application.Features.Preceptor.Dtos.CreatePreceptorDto;
import br.com.gustavo.simi.Domain.Entities.Preceptor;
import br.com.gustavo.simi.Domain.Entities.User;
import br.com.gustavo.simi.Domain.Enums.Role;
import br.com.gustavo.simi.Domain.Repositories.PreceptorRepository;
import br.com.gustavo.simi.Domain.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreceptorService {

    private final PreceptorRepository preceptorRepository;
    private final UserRepository userRepository;

    public Preceptor createPreceptor(CreatePreceptorDto dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (user.getRole() != Role.PRECEPTOR) {
            throw new IllegalArgumentException("O usuário não tem o cargo de preceptor");
        }

        User coordinator = userRepository.findById(dto.coordinatorId())
                .orElseThrow(() -> new IllegalArgumentException("Coordenador não encontrado"));

        if (coordinator.getRole() != Role.COORDINATOR) {
            throw new IllegalArgumentException("O usuário selecionado não é um coordenador");
        }

        Preceptor preceptor = Preceptor.builder()
                .coordinator(coordinator)
                .user(user)
                .crm(dto.crm())
                .build();
        return preceptorRepository.save(preceptor);
    }

    public List<Preceptor> getAllPreceptors() {
        return preceptorRepository.findAll();
    }

    public Preceptor findPreceptorById(Long id) {
        return preceptorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Preceptor não encontrado"));
    }
}
