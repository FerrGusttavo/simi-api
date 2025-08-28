package br.com.gustavo.simi.Domain.Repositories;

import br.com.gustavo.simi.Domain.Entities.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, Long> { }
