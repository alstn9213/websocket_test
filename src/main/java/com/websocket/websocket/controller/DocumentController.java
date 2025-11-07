package com.websocket.websocket.controller;


import com.websocket.websocket.dto.DocumentState;
import com.websocket.websocket.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class DocumentController {

    // 1. Service 계층 주입
    @Autowired
    private DocumentService documentService;

    /**
     * (브로드캐스트)
     * 클라이언트가 문서 내용을 변경할 때 호출됨
     * @SendTo가 제거되고, 반환 타입이 void로 변경됨
     */
    @MessageMapping("/document.update")
    public void updateDocument(@Payload DocumentState documentState, SimpMessageHeaderAccessor headerAccessor) {

        String sessionId = headerAccessor.getSessionId();

        // 2. 모든 로직을 Service에 위임
        documentService.updateDocument(documentState, sessionId);
    }

    /**
     * (1:1 전송)
     * 새로운 클라이언트가 접속했을 때, 현재 문서 상태를 요청
     * 반환 타입이 void로 변경됨
     */
    @MessageMapping("/document.requestCurrent")
    public void requestCurrentDocument(SimpMessageHeaderAccessor headerAccessor) {

        // 2. 모든 로직을 Service에 위임
        documentService.requestCurrentDocument(headerAccessor.getSessionId());
    }
}