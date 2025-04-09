package com.Weeb.Service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SignallingService {
    
    private final SimpMessagingTemplate template;

    public SignallingService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendMessage(String message){
        template.convertAndSend("/topic/messages",message);
    }

    public void sendPrivateMessage(String userId,String message){
        template.convertAndSendToUser(userId, "/topic/privateMessages", message);
    }
}
