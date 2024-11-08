package com.challenge.sistema_notificacoes.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.challenge.sistema_notificacoes.controller.dto.NotificationsDto;
import com.challenge.sistema_notificacoes.controller.dto.NotificationsGetDto;
import com.challenge.sistema_notificacoes.entities.Notifications;
import com.challenge.sistema_notificacoes.services.NotificationService;


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
    public ResponseEntity<List<NotificationsGetDto>> getNotificationByUserId(@PathVariable("userId") 
                                                                        Long userId) {
                                                                            
        List<Notifications> notificationByUserId = notificationService.findByUser_UserId(userId);

       if(notificationByUserId.isEmpty()){
        return ResponseEntity.notFound().build();

       } else {
        List<NotificationsGetDto> dto = notificationByUserId.stream().map( notification ->
            new NotificationsGetDto(
                notification.getTitle(),
                notification.getDescription(),
                notification.GetMarkAsRead()
            )
    ).collect(Collectors.toList());

         return ResponseEntity.ok(dto);
    }
}

    @PutMapping("/{notificationId}/mark-as-read")
    public ResponseEntity<Notifications> notificationRead(@PathVariable("notificationId") 
                                                            Long             notificationId, 
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
    }

}
