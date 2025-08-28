package br.com.gustavo.simi.Domain.Repositories;

import br.com.gustavo.simi.Domain.Entities.ShiftAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftAllocationRepository extends JpaRepository<ShiftAllocation, Long> {
    long countByShiftId(Long shiftId);
    List<ShiftAllocation> findByStudentId(Long studentId);
    boolean existsByShiftIdAndStudentId(Long shiftId, Long studentId);
}
