package com.challenge.sistema_notificacoes.controller.dto;

import com.challenge.sistema_notificacoes.entities.Notifications;

public record NotificationsSendDto(String title,
                                String description) {

        public Notifications sendNotifications(){
        return new Notifications(
            title,
            description
        );
    }

}
