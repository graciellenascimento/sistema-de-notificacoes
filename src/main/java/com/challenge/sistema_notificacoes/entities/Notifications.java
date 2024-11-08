package com.challenge.sistema_notificacoes.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Entity
@Valid
@Table(name = "t_notifications")

public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long    notificationId;

    @NotBlank(message = "Insira um título")
    private String  title; //título da notificação

    @NotBlank(message = "Insira uma descrição")
    private String  description; //corpo da notificação

    @NotBlank(message = "Insira um tipo")
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

    public Notifications(String title, String description, boolean markAsRead) {
        this.title = title;
        this.description = description;
        this.markAsRead = markAsRead;
    }

    public Notifications(String title, String description) {
        this.title = title;
        this.description = description;
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

    public boolean GetMarkAsRead() {
        return markAsRead;
    }

    public void setMarkAsRead(boolean markAsRead) {
        this.markAsRead = markAsRead;
    }

}
