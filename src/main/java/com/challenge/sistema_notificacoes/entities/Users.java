package com.challenge.sistema_notificacoes.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "t_users")
public class Users {

    @Id

    private Long    userId;
    private String  userName; //nome do usuário
    private String  userEmail; //email do usuário

    @OneToMany(mappedBy = "user")
    private List<Notifications> notifications;

    @ManyToOne
    @JoinColumn(name = "notificationChannelId")
    private NotificationChannel notificationChannel; //canal escolhido para receber as notificações 

    public Users() {
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Notifications> getNotifications() {
        return notifications;
    }
    public void setNotifications(List<Notifications> notifications) {
        this.notifications = notifications;
    } 

    public NotificationChannel getNotificationChannel() {
        return notificationChannel;
    }
    public void setNotificationChannel(NotificationChannel notificationChannel) {
        this.notificationChannel = notificationChannel;
    }


    

}
