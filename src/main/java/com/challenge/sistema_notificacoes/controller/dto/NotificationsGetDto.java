package com.challenge.sistema_notificacoes.controller.dto;

import com.challenge.sistema_notificacoes.entities.Notifications;

public record NotificationsGetDto(String title,
                                  String description,
                                  boolean markAsRead) {

    public Notifications displayNotifications(){
        return new Notifications(
            title,
            description,
            markAsRead
        );
    }
}
