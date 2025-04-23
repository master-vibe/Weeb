package com.Weeb.Exception;

import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

@Component("stompSubProtocolErrorHandler")
public class StompErrorHandler extends StompSubProtocolErrorHandler {

    public StompErrorHandler(){
        super();
    }

    @SuppressWarnings("null")
    @Override
    @Nullable
    public Message<byte[]> handleClientMessageProcessingError(@Nullable Message<byte[]> clientMessage, Throwable ex) {
        String errorMsg = ex.getMessage() != null ? ex.getMessage() : "Unknown Error Occured";
        System.out.println("Error Message : "+errorMsg);
        StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);
        accessor.setMessage(errorMsg);
        accessor.setLeaveMutable(true);

        return MessageBuilder.createMessage(errorMsg.getBytes(), accessor.getMessageHeaders());
    }
}
