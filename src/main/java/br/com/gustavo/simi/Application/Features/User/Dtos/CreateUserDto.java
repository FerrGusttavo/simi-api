package br.com.gustavo.simi.Application.Features.User.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
        @NotNull(message = "O nome é obrigatório")
        String name,

        @NotNull(message = "O email é obrigatório")
        String email,

        @NotNull(message = "O cargo é obrigatório")
        @Schema(description = "Role do usuário", example = "STUDENT", allowableValues = {"STUDENT", "PRECEPTOR"})
        String role
) {
}
