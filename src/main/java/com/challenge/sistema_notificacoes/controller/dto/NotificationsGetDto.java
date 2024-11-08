package com.challenge.sistema_notificacoes.controller.dto;

import java.time.LocalDate;

import com.challenge.sistema_notificacoes.entities.Notifications;

public record NotificationsGetDto(String title,
                                  String description,
                                  LocalDate creationDate,
                                  boolean markAsRead) {

    public Notifications displayNotifications(){
        return new Notifications(
            title,
            description,
            creationDate,
            markAsRead
        );
    }
}
