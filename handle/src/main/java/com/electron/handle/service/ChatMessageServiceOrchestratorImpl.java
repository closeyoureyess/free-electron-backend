package com.electron.handle.service;

import com.electron.handle.dto.ChatMessageRequest;
import com.electron.handle.dto.ChatResponse;
import com.electron.handle.entity.ChatEntity;
import com.electron.handle.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class ChatMessageServiceOrchestratorImpl implements ChatMessageServiceOrchestrator {

    private final ChatService chatService;

    private final MessageService messageService;

    private final UserService userService;

    private final ChatParticipantService chatParticipantService;

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, timeout = 30)
    @Override
    public void sendMessageInChat(Principal recipient, Long targetChatId, ChatMessageRequest payload) {
        // Достать чат из базы
        // Создать сущность сообщение
        // К сообщению привязать чат
        // К сообщению привязать пользователя
        // Сохранить сообщение в базу
        String userId = recipient.getName();
        Optional<ChatEntity> optionalChatResponse = chatService.loadChatByIdAndReturnEntity(targetChatId);
        optionalChatResponse.map(chatEntity -> {
            messageService.saveMessage(userId, payload, chatEntity);
            Optional<UserEntity> optionalUserEntity = userService.findUserByIdAndReturnEntity(UUID.fromString(userId));
            optionalUserEntity.map(userEntity -> {
                chatParticipantService.save(userEntity, chatEntity);
                return null;
            });
            messageService.send(userId, targetChatId, payload);
            return null;
        });
    }

    @Override
    public List<ChatResponse> loadChatsByUserId(String userId, int pageNumber, int pageSize) {
        return chatParticipantService.findAllByUserId(userId, pageNumber, pageSize);
    }
}
