package br.com.gustavo.simi.Application.Features.User;

import br.com.gustavo.simi.Application.Features.User.Dtos.CreateUserDto;
import br.com.gustavo.simi.Domain.Entities.User;
import br.com.gustavo.simi.Domain.Enums.Role;
import br.com.gustavo.simi.Domain.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(CreateUserDto dto) {
        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .role(Role.valueOf(dto.role()))
                .build();
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }
}
