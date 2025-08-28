package br.com.gustavo.simi.Application.Features.Student;

import br.com.gustavo.simi.Application.Features.Student.Dtos.CreateStudentDto;
import br.com.gustavo.simi.Application.Features.Student.Presenters.StudentPresenter;
import br.com.gustavo.simi.Domain.Entities.Student;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Estudantes")
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @Operation(
            summary = "Cria um novo estudante",
            description = "Permite criar um estudante vinculado a um coordenador e com registro acadêmico."
    )
    public ResponseEntity<Student> createStudent(@Valid @RequestBody CreateStudentDto dto) {
        Student student = studentService.createStudent(dto);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    @Operation(
            summary = "Lista todos os estudantes",
            description = "Retorna todos os estudantes cadastrados no sistema."
    )
    public ResponseEntity<List<StudentPresenter>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        List<StudentPresenter> response = students.stream()
                .map(StudentPresenter::fromEntity)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Consulta um estudante por ID",
            description = "Retorna os detalhes de um estudante específico."
    )
    public ResponseEntity<StudentPresenter> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(StudentPresenter.fromEntity(student));
    }
}
