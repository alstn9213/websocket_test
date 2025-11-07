package com.websocket.websocket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    // DTO
    private  String sender;
    private String content;
    private MessageType type;

    public enum MessageType {
        CHAT, // 일반 채팅
        JOIN, // 입장
        LEAVE // 퇴장
    }
}
