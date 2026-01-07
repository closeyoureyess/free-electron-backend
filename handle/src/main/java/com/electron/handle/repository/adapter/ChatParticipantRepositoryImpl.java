package com.electron.handle.repository.adapter;

import com.electron.handle.entity.ChatParticipantEntity;
import com.electron.handle.repository.jpa.SpringChatParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class ChatParticipantRepositoryImpl implements ChatParticipantRepository {

    private final SpringChatParticipantRepository repository;

    @Override
    public ChatParticipantEntity save(ChatParticipantEntity entity) {
        return repository.save(entity);
    }

    @Override
    public List<ChatParticipantEntity> saveAllAndFlush(Iterable<ChatParticipantEntity> entities) {
        return repository.saveAllAndFlush(entities);
    }

    @Override
    public Optional<ChatParticipantEntity> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Slice<ChatParticipantEntity> findAllByUser(UUID userId, Pageable pageable) {
        return repository.findAllByUser(userId, pageable);
    }
}
