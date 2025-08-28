package br.com.gustavo.simi.Domain.Repositories;

import br.com.gustavo.simi.Domain.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> { }
