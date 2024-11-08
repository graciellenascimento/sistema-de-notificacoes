package com.challenge.sistema_notificacoes.controller.dto;

import com.challenge.sistema_notificacoes.entities.NotificationChannel;
import com.challenge.sistema_notificacoes.entities.Users;

public record UsersDto(String userName,
                       String userEmail,
                       NotificationChannel notificationChannel) {

    public Users toUsers(){
        return new Users(
            userName,
            userEmail,
            notificationChannel
        );
    }

}
