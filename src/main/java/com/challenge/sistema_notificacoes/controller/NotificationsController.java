package com.challenge.sistema_notificacoes.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import com.challenge.sistema_notificacoes.controller.dto.NotificationsDto;
import com.challenge.sistema_notificacoes.controller.dto.NotificationsGetDto;
import com.challenge.sistema_notificacoes.controller.dto.NotificationsSendDto;
import com.challenge.sistema_notificacoes.controller.dto.UsersDto;
import com.challenge.sistema_notificacoes.entities.NotificationChannel;
import com.challenge.sistema_notificacoes.entities.Notifications;
import com.challenge.sistema_notificacoes.services.EmailService;
import com.challenge.sistema_notificacoes.services.NotificationService;
import com.challenge.sistema_notificacoes.services.UsersService;


@RestController
@EnableAsync
@RequestMapping("/notifications")
public class NotificationsController {
    
    private NotificationService notificationService;
    private UsersService        usersService;
    private EmailService        emailService;

    public NotificationsController(NotificationService notificationService,
                                   UsersService        usersService,
                                   EmailService        emailService) {
        this.notificationService = notificationService;
        this.usersService        = usersService;
        this.emailService        = emailService;
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

    @GetMapping("/{userId}/send-email")
    public ResponseEntity<List<UsersDto>> sendEmail(@PathVariable("userId") Long userId,
                                                    NotificationsDto dto) {
        
        var getUserEmail = usersService.findById(userId);
        List<Notifications> notificationByUserId = notificationService.findByUser_UserId(userId);
        NotificationChannel channel = null;

        if(getUserEmail.isPresent()){
            List<UsersDto> userDto = getUserEmail.stream().map(
                userEmail -> new UsersDto(userEmail.getUserName(),
                                          userEmail.getUserEmail(),                    
                                          userEmail.getNotificationChannel()
                                        )).collect(Collectors.toList());

            List<NotificationsSendDto> notificationDto = notificationByUserId.stream().map( 
                notification -> new NotificationsSendDto(notification.getTitle(),
                                                         notification.getDescription()
                                                        )).collect(Collectors.toList());

            for(UsersDto usersDto : userDto){
                
                channel = usersDto.notificationChannel();
                channel.getnotificationChannelId();

                for(NotificationsSendDto notificationsDto : notificationDto){
                    
                    emailService.sendEmail(usersDto, notificationsDto);

                }
            }

            return ResponseEntity.ok(userDto);

        } else{
            return ResponseEntity.notFound().build(); 
        }

    }

}
