package com.electron.handle.websocket;

import com.electron.handle.dto.ChatMessageRequest;
import com.electron.handle.service.ChatMessageServiceOrchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@RequiredArgsConstructor
class MessageWebSocket {

    private final ChatMessageServiceOrchestrator messageServiceOrchestrator;

    @MessageMapping("/chat/{chatId}/message")
    void sendMessage(@DestinationVariable Long chatId,
                     ChatMessageRequest payload,
                     Principal principal) {
        messageServiceOrchestrator.sendMessageInChat(principal, chatId, payload);
    }
}