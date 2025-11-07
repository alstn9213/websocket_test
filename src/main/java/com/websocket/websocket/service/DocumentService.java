package com.websocket.websocket.service;

import com.websocket.websocket.dto.DocumentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    // 1. 메시지 전송을 위한 템플릿
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    // 2. 비즈니스 로직: 서버 메모리에 현재 문서 내용 저장
    private String contentCache = "";
    /**
     * (로직 1) 문서 업데이트 및 브로드캐스트
     */
    public void updateDocument(DocumentState documentState, String editorSessionId) {
        // 1. 서버 캐시(메모리) 내용 업데이트
        this.contentCache = documentState.getContent();
        // 2. 메시지를 보낸 사람의 세션 ID를 editorId에 저장
        documentState.setEditorId(editorSessionId);
        //    /topic/document 를 구독 중인 모든 클라이언트에게 문서 상태 전송
        messagingTemplate.convertAndSend("/topic/document", documentState);
    }

    /**
     * (로직 2) 신규 접속자에게 현재 문서 상태 전송
     */
    public void requestCurrentDocument(String userSessionId) {
        if (userSessionId == null) return;
        // 1. 현재 캐시된 문서 내용을 DTO에 담기
        DocumentState currentState = new DocumentState(this.contentCache, "server");
        // 2. (중요) 특정 사용자에게만 1:1로 메시지 전송
        messagingTemplate.convertAndSendToUser(
                userSessionId,
                "/queue/document",
                currentState
        );
    }
}