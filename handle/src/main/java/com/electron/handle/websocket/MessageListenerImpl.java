package com.electron.handle.websocket;

import com.electron.handle.dto.ChatResponse;
import com.electron.handle.service.ChatMessageServiceOrchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.security.Principal;
import java.util.List;

import static com.electron.handle.enums.ChatEnum.PAGE_NUMBER_DEFAULT;
import static com.electron.handle.enums.ChatEnum.PAGE_SIZE_DEFAULT;

@Component
@RequiredArgsConstructor
class MessageListenerImpl implements MessageListener {

    private final SimpMessagingTemplate messagingTemplate;

    private final ChatMessageServiceOrchestrator chatMessageServiceOrchestrator;

    @Async
    @EventListener
    @Override
    public void onSessionConnected(SessionConnectedEvent event) {
        Principal principal = event.getUser();
        if (principal == null) {
            return;
        }
        String userId = principal.getName();
        List<ChatResponse> list = chatMessageServiceOrchestrator.loadChatsByUserId(
                userId,
                PAGE_NUMBER_DEFAULT.getValue(),
                PAGE_SIZE_DEFAULT.getValue()
        );
        messagingTemplate.convertAndSendToUser(
                userId,
                "/queue/chats",
                list
        );
    }
}
