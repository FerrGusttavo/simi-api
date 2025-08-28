package br.com.gustavo.simi.Domain.Repositories;

import br.com.gustavo.simi.Domain.Entities.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
    Semester findByLabel(String label);
}
