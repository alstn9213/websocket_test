package com.websocket.websocket.service;


import com.websocket.websocket.dto.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * (로직) 특정 사용자에게 알림을 전송
     * @param username 대상 사용자 (Principal 이름)
     */
    public void sendNotification(String username) {
        // 1. (비즈니스 로직) 알림 메시지 생성
        String message = "서버로부터 온 실시간 알림입니다!";
        Notification notification = new Notification(message);
        // 2. (전송 로직) SimpMessagingTemplate을 사용해 특정 사용자에게 메시지 전송
        messagingTemplate.convertAndSendToUser(
                username,                   // 대상 사용자
                "/queue/notifications",     // 대상 목적지
                notification                // 보낼 메시지
        );
    }
}