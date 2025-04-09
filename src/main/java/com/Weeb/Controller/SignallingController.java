package com.Weeb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Weeb.Service.SignallingService;

@RestController
public class SignallingController {

    @Autowired
    private SignallingService signallingService;
    
    @GetMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody String message){
        signallingService.sendMessage(message);
        return ResponseEntity.ok("Sent");
    }

    @GetMapping("/sendPrivateMessages/{userId}")
    public ResponseEntity<String> sendPrivateMessage(@PathVariable String userId,@RequestBody String message){
        signallingService.sendPrivateMessage(userId,message);
        return ResponseEntity.ok("Sent");
    }
}
