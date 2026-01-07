package com.electron.handle.service;

import com.electron.handle.dto.ChatMessageRequest;
import com.electron.handle.entity.ChatEntity;
import com.electron.handle.entity.MessageEntity;

import java.util.Optional;

public interface MessageService {

    void send(String userId, Long targetChatId, ChatMessageRequest payload);

    Optional<MessageEntity> saveMessage(String userId, ChatMessageRequest payload, ChatEntity chatEntity);
}
