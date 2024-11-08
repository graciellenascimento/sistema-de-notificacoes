package com.challenge.sistema_notificacoes.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.challenge.sistema_notificacoes.controller.dto.NotificationsDto;
import com.challenge.sistema_notificacoes.entities.Notifications;
import com.challenge.sistema_notificacoes.repository.NotificationsRepository;

@Service
public class NotificationService {

    private final NotificationsRepository notificationsRepository;

    public NotificationService(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    public void notificationInfo(NotificationsDto dto) {
        notificationsRepository.save(dto.toNotifications());
    }

    public List<Notifications> findByUser_UserId(Long userId) {
        return notificationsRepository.findByUser_UserId(userId);
    }

    public Optional<Notifications> findById(Long notificationId){
        return notificationsRepository.findById(notificationId);
    }
    
}
