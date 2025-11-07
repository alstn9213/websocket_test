package com.websocket.websocket.controller;

import com.websocket.websocket.dto.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/api/send-notification/{username}")
    public String sendNotificationToUser(@PathVariable String username) {
        String message = "서버로부터 온 실시간 알림입니다. (by " + username + ")";
        Notification notification = new Notification(message);
        messagingTemplate.convertAndSendToUser(
                username,
                "/queue/notifications",
                notification
        );
        return username + " 님에게 알림을 성공적으로 전송했습니다.";
    }
}
