package com.challenge.sistema_notificacoes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.challenge.sistema_notificacoes.entities.Notifications;

public interface NotificationsRepository extends JpaRepository<Notifications, Long>{

        List<Notifications> findByUser_UserId(Long userId);
        Optional<Notifications> findById(Long id);

}
