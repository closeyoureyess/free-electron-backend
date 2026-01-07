package com.electron.handle.service;

import com.electron.handle.dto.ChatParticipantResponse;
import com.electron.handle.dto.ChatResponse;
import com.electron.handle.entity.ChatEntity;
import com.electron.handle.entity.ChatParticipantEntity;
import com.electron.handle.entity.UserEntity;
import com.electron.handle.mapper.ChatParticipantMapper;
import com.electron.handle.repository.adapter.ChatParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class ChatParticipantServiceImpl implements ChatParticipantService {

    private final ChatParticipantMapper mapper;

    private final ChatParticipantRepository repository;

    @Override
    public ChatParticipantResponse save(UserEntity user, ChatEntity chat) {
        ChatParticipantEntity chatParticipantEntity = ChatParticipantEntity.builder().chat(chat).user(user)
                .joinedAt(Instant.now()).build();
        repository.save(chatParticipantEntity);
        return mapper.toChatParticipantResponse(chatParticipantEntity);
    }

    @Override
    public List<ChatResponse> findAllByUserId(String userId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Slice<ChatParticipantEntity> chatsPagination =
                repository.findAllByUser(UUID.fromString(userId), pageable);
        List<ChatParticipantEntity> list = chatsPagination.getContent();
        return list.stream().map(e -> {
                    ChatEntity chatEntity = e.getChat();
                    return mapper.toChatResponse(chatEntity);
                })
                .toList();
    }
}
