package com.challenge.sistema_notificacoes.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_notifications")

public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long    notificationId;
    private String  title; //título da notificação
    private String  description; //corpo da notificação
    private String  type; //tipo de notificação
    private  boolean markAsRead = false; //se foi lido ou não pelo usuário

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user; //pessoa que receberá a notificação

    public Notifications() {
    }

    public Notifications(String title, String description, String type, Users user) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.user = user;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Users getUserId() {
        return user;
    }

    public void setUserId(Users user) {
        this.user = user;
    }

    public boolean isMarkAsRead() {
        return markAsRead;
    }

    public void setMarkAsRead(boolean markAsRead) {
        this.markAsRead = markAsRead;
    }

}
