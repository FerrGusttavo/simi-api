package br.com.gustavo.simi.Application.Features.Student.Presenters;

import br.com.gustavo.simi.Domain.Entities.Student;
import br.com.gustavo.simi.Domain.Enums.Period;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record StudentPresenter(
        @Schema(description = "ID do estudante", example = "1")
        Long id,

        @Schema(description = "Nome do estudante", example = "João da Silva")
        String name,

        @Schema(description = "Email do estudante", example = "joao.silva@email.com")
        String email,

        @Schema(description = "Registro do estudante", example = "20250001")
        String registration,

        @Schema(description = "Período do estudante", example = "P9")
        Period period,

        @Schema(description = "Coordenador responsável", example = "Maria Souza")
        String coordinatorName

) {
    public static StudentPresenter fromEntity(Student student) {
        return new StudentPresenter(
                student.getId(),
                student.getUser().getName(),
                student.getUser().getEmail(),
                student.getRegistration(),
                student.getPeriod(),
                student.getCoordinator() != null ? student.getCoordinator().getName() : null
        );
    }
}