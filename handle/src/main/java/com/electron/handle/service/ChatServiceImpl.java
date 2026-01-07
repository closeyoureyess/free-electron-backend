package com.electron.handle.service;

import com.electron.handle.dto.ChatResponse;
import com.electron.handle.entity.ChatEntity;
import com.electron.handle.mapper.ChatMapper;
import com.electron.handle.repository.adapter.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    private final ChatMapper mapper;

    @Override
    public Optional<ChatResponse> loadChatById(Long id) {
        Optional<ChatEntity> optionalChatEntity = chatRepository.findById(id);
        return optionalChatEntity.map(mapper::toChatResponse);
    }

    @Override
    public Optional<ChatEntity> loadChatByIdAndReturnEntity(Long id) {
        return chatRepository.findById(id);
    }
}
