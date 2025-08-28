package br.com.gustavo.simi.Application.Features.Student;

import br.com.gustavo.simi.Application.Features.Student.Dtos.CreateStudentDto;
import br.com.gustavo.simi.Domain.Entities.Student;
import br.com.gustavo.simi.Domain.Entities.User;
import br.com.gustavo.simi.Domain.Enums.Period;
import br.com.gustavo.simi.Domain.Enums.Role;
import br.com.gustavo.simi.Domain.Repositories.StudentRepository;
import br.com.gustavo.simi.Domain.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public Student createStudent(CreateStudentDto dto) {
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (user.getRole() != Role.STUDENT) {
            throw new IllegalArgumentException("O usuário não tem o cargo de estudante");
        }

        User coordinator = userRepository.findById(dto.coordinatorId())
                .orElseThrow(() -> new IllegalArgumentException("Coordenador não encontrado"));

        if (coordinator.getRole() != Role.COORDINATOR) {
            throw new IllegalArgumentException("O usuário selecionado não é um coordenador");
        }

        Student student = Student.builder()
                .coordinator(coordinator)
                .user(user)
                .registration(dto.registration())
                .period(Period.valueOf(dto.period()))
                .build();

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Estudante não encontrado"));
    }
}
