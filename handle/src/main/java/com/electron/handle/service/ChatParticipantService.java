package com.electron.handle.service;

import com.electron.handle.dto.ChatParticipantResponse;
import com.electron.handle.dto.ChatResponse;
import com.electron.handle.entity.ChatEntity;
import com.electron.handle.entity.UserEntity;

import java.util.List;

public interface ChatParticipantService {

    ChatParticipantResponse save(UserEntity user, ChatEntity chat);

    List<ChatResponse> findAllByUserId(String userId, int pageNumber, int pageSize);
}
