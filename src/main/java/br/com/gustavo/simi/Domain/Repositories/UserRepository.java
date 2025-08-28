package br.com.gustavo.simi.Domain.Repositories;

import br.com.gustavo.simi.Domain.Entities.User;
import br.com.gustavo.simi.Domain.Enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);
}
