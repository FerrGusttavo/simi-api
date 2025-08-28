package br.com.gustavo.simi.Application.Features.User;

import br.com.gustavo.simi.Application.Features.User.Dtos.CreateUserDto;
import br.com.gustavo.simi.Domain.Entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuários")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(
            summary = "Cria um novo usuário",
            description = "Permite criar um usuário com nome, e-mail, senha e função (role) no sistema."
    )
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserDto dto) {
        User user = userService.createUser(dto);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os usuários",
            description = "Retorna todos os usuários cadastrados no sistema."
    )
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consulta um usuário por ID",
            description = "Retorna os detalhes de um usuário específico."
    )
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
