package com.Weeb.Service;

import java.security.Principal;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.Weeb.DTO.Message;

@Service
public class MessageService {
    
    private final SimpMessagingTemplate template;

    public MessageService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendMessage(Message message,Principal sender){
        template.convertAndSendToUser(message.getReceiver(), "/topic/privateMessages", message);
    }
}
