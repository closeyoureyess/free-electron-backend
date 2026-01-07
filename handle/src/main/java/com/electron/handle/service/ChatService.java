package com.electron.handle.service;

import com.electron.handle.dto.ChatResponse;
import com.electron.handle.entity.ChatEntity;

import java.util.Optional;

public interface ChatService {

    Optional<ChatResponse> loadChatById(Long id);

    Optional<ChatEntity> loadChatByIdAndReturnEntity(Long id);
}
