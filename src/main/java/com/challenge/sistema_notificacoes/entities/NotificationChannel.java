package com.challenge.sistema_notificacoes.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "t_channels")
public class NotificationChannel {

    @Id

    private Long    notificationChannelId;
    private String  chosenService; //canal escolhido para recebimento das notificações

    @OneToMany(mappedBy = "notificationChannel")
    private List<Users> users;

    public NotificationChannel() {
    }

    public NotificationChannel(Long notificationChannelId, String chosenService) {
        this.notificationChannelId = notificationChannelId;
        this.chosenService = chosenService;
    }

    public Long getnotificationChannelId() {
        return notificationChannelId;
    }
    public void setnotificationChannelId(Long notificationChannelId) {
        this.notificationChannelId = notificationChannelId;
    }
    public String getChosenService() {
        return chosenService;
    }
    public void setChosenService(String chosenService) {
        this.chosenService = chosenService;
    }
    public List<Users> getUser() {
        return users;
    }
    public void setUser(List<Users> users) {
        this.users = users;
    }
    public enum notificationServices {
        email((long) 1, "email"),
        push((long) 2, "push");

        private Long   id;
        private String type;

        notificationServices(Long id, String type){
            this.id   = id;
            this.type = type;
        }

        public NotificationChannel toNotificationService() {
            return new NotificationChannel(id, type);
        }
    }
}
