package com.Weeb.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import com.Weeb.DTO.Message;
import com.Weeb.Service.MessageService;

@Controller
public class MessageController {
    
    @Autowired
    private MessageService messageService;
    
    @MessageMapping("/privateMessages")
    public ResponseEntity<String> sendMessage(@Payload Message message,Principal sender){
        messageService.sendMessage(message, sender);
        return ResponseEntity.ok("Sent");
    }

    @MessageMapping("/signalling")
    public ResponseEntity<String> sendSignal(@Payload Message message,Principal sender){
        messageService.sendMessage(message, sender);
        return ResponseEntity.ok("Sent");
    }
}