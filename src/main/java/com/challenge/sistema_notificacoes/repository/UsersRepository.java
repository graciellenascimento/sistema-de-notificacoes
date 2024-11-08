package com.challenge.sistema_notificacoes.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.challenge.sistema_notificacoes.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
    Optional<Users> findById(Long id);
}
