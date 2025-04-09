package com.Weeb.Config;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Component
public class AuthHandshakeInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
    Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest servletRequest) {
            String authToken = servletRequest.getServletRequest().getParameter("auth");
            if (authToken.equals("Ashique")) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authToken, null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
                attributes.put("token", token);
                return true;
            } else {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return false;
            }

        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2,
            @Nullable Exception arg3) {
    }
}
