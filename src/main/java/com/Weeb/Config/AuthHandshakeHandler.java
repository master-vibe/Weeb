package com.Weeb.Config;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Component
public class AuthHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    @Nullable
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
            Map<String, Object> attributes) {
        Principal token = (Principal) attributes.get("token");
        if (token != null) {
            return (Principal) attributes.get("token");
        }
        return null;
    }
}
