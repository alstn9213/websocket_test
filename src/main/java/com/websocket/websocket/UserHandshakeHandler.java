package com.websocket.websocket;

import com.sun.security.auth.UserPrincipal;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

public class UserHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request,
                                      WebSocketHandler wsHandler,
                                      Map<String, Object> attributes) {
        String username = request.getURI().getQuery();
        if(username != null && username.startsWith("username=")) {
            username = username.substring(9);
            return new UserPrincipal(username);
        }
        return new UserPrincipal(UUID.randomUUID().toString());
    }
}
