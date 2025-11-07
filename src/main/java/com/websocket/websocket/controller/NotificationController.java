package com.websocket.websocket.controller;

import com.websocket.websocket.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/api/send-notification/{username}")
    public String sendNotificationToUser(@PathVariable String username) {
        notificationService.sendNotification(username);
        return username + " 님에게 알림을 성공적으로 전송했습니다.";
    }
}