package com.challenge.sistema_notificacoes.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/notifications")
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

    @Operation(description = "Cadastro de notificações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cadastro concluído com sucesso"),
        @ApiResponse(responseCode = "400", description = "Falha no cadastro"),
        @ApiResponse(responseCode = "500", description = "Parâmetros errados")
    })
    @PostMapping
    public ResponseEntity<Void> notificationInfo(@RequestBody @Valid NotificationsDto dto) {
        notificationService.notificationInfo(dto);

        return ResponseEntity.accepted().build();
    }

    @Operation(description = "Busca todas as notificações atreladas a um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Busca concluída com sucesso"),
        @ApiResponse(responseCode = "400", description = "Usuário inexistente")
    })
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
                notification.getCreationDate(),
                notification.GetMarkAsRead()
            )
    ).collect(Collectors.toList());

         return ResponseEntity.ok(dto);
    }
}

    @Operation(description = "Marcar como lido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Notificação lida"),
        @ApiResponse(responseCode = "400", description = "Não há nenhuma notificação com este ID")
    })
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

    @Operation(description = "Envio de e-mail")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "E-mail enviado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro no envio")
    })
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

            return ResponseEntity.ok().build();

        } else{
            return ResponseEntity.notFound().build(); 
        }

    }

}
