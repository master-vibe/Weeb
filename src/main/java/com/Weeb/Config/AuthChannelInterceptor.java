package com.Weeb.Config;

import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthChannelInterceptor implements ChannelInterceptor{

    @SuppressWarnings("null")
    @Override
    @Nullable
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();

        if (command==null) {
            return message;
        }

        switch (accessor.getCommand()) {
            case SUBSCRIBE:
                Authentication auth = (Authentication) accessor.getUser();
                System.out.println(auth != null && auth.getAuthorities().stream()
                    .anyMatch(granted -> granted.getAuthority().equals("ROLE_ADMIN")));
            default:
                break;
        }

        return message;
    }
}
