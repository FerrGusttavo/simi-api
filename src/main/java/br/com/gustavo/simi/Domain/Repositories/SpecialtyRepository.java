package br.com.gustavo.simi.Domain.Repositories;

import br.com.gustavo.simi.Domain.Entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Specialty findByName(String name);
}
