package com.Weeb.Config.Websocket;

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

import com.Weeb.Service.JwtUtils;

import jakarta.servlet.http.Cookie;

@Component
public class AuthHandshakeInterceptor implements HandshakeInterceptor {
    private final JwtUtils jwt;

    AuthHandshakeInterceptor(JwtUtils jwtUtils) {
        this.jwt = jwtUtils;
    }
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
    Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest servletRequest) {
            String authToken =  getCookie(servletRequest.getServletRequest().getCookies(), "token");
            if (jwt.isTokenValid(authToken)) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(jwt.extractUsername(authToken), null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
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

    String getCookie(Cookie[] cookies, String name) {
        if (cookies == null) {
            return null;
        } 
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
