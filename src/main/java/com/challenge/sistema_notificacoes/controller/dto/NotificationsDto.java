package com.challenge.sistema_notificacoes.controller.dto;

import com.challenge.sistema_notificacoes.entities.Notifications;
import com.challenge.sistema_notificacoes.entities.Users;

public record NotificationsDto(String title,
                               String description,
                               String type,
                               Users user) {

                                
    public Notifications toNotifications() {
        return new Notifications(
            title,
            description,
            type,
            user
        );
    }

}
