package com.challenge.sistema_notificacoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.challenge.sistema_notificacoes.controller.dto.NotificationsDto;
import com.challenge.sistema_notificacoes.entities.Notifications;
import com.challenge.sistema_notificacoes.services.NotificationService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {

    private final NotificationService notificationService;

    public NotificationsController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Void> notificationInfo(@RequestBody NotificationsDto dto) {
        notificationService.notificationInfo(dto);

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notifications>> getNotificationByUserId(@PathVariable("userId") Long userId) {
        List<Notifications> notificationByUserId = notificationService.findByUser_UserId(userId);

       if(notificationByUserId.isEmpty()){
        return ResponseEntity.notFound().build();
       } else {
        return ResponseEntity.ok(notificationByUserId);
       }
    }

    @PutMapping("/{notificationId}/mark-as-read")
    public ResponseEntity<Notifications> notificationRead(@PathVariable("notificationId") Long           notificationId, 
                                                                        Notifications    markAsRead,
                                                                        NotificationsDto dto) {

        var notificationIsRead = notificationService.findById(notificationId);
        
        if(notificationIsRead.isPresent()){
            Notifications notification = notificationIsRead.get();

            notification.setMarkAsRead(true);
            notificationService.notificationInfo(dto);
            ResponseEntity.accepted().build();

            return ResponseEntity.ok(notification);
        } else{
            return ResponseEntity.notFound().build();
        }

        // if(notificationIsRead.isPresent()){
        //         Notifications notification = notificationIsRead.get();

        //         notification.setMarkAsRead(true);
        //         notificationService.notificationInfo(dto);

        //         return ResponseEntity.ok(notification);
        // }
        //         return null;


        // return notificationIsRead.map(
        //     notification -> {
        //         notification.setMarkAsRead(true);
        //         notificationService.notificationInfo(dto);

        //         return ResponseEntity.notFound().build();

        //     }
        // ).orElseThrow(() -> new EntityNotFoundException("Notificação não encontrada com ID: " + id));
    }

}
