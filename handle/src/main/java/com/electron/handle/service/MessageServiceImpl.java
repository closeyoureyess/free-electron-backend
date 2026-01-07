package com.electron.handle.service;

import com.electron.handle.dto.ChatMessageRequest;
import com.electron.handle.entity.ChatEntity;
import com.electron.handle.entity.MessageEntity;
import com.electron.handle.mapper.MessageMapper;
import com.electron.handle.repository.adapter.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static com.electron.handle.enums.WebSocketEnum.QUEUE_PATH;

@Component
@RequiredArgsConstructor
class MessageServiceImpl implements MessageService {

    private final MessageMapper mapper;

    private final MessageRepository repository;

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void send(String userId, Long targetChatId, ChatMessageRequest payload) {
        messagingTemplate.convertAndSendToUser(
                userId,
                QUEUE_PATH.getValue() + targetChatId,
                payload
        );
    }

    @Override
    public Optional<MessageEntity> saveMessage(String userId, ChatMessageRequest payload, ChatEntity chatEntity) {
        MessageEntity messageEntity = MessageEntity.builder().content(payload.content())
                .userId(UUID.fromString(userId))
                .createdAt(payload.createdAt())
                .chat(chatEntity)
                .build();
        return Optional.ofNullable(repository.save(messageEntity));
    }
}