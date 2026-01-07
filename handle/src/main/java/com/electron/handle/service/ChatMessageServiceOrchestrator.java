package com.electron.handle.service;

import com.electron.handle.dto.ChatMessageRequest;
import com.electron.handle.dto.ChatResponse;

import java.security.Principal;
import java.util.List;

public interface ChatMessageServiceOrchestrator {

    void sendMessageInChat(Principal recipient, Long targetChatId, ChatMessageRequest payload);

    List<ChatResponse> loadChatsByUserId(String userId, int pageNumber, int pageSize);
}