package com.electron.handle.repository.adapter;

import com.electron.handle.entity.ChatEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class ChatRepositoryAdapterImpl implements ChatRepository {

    private final ChatRepository repository;

    @Override
    public ChatEntity save(ChatEntity entity) {
        return repository.save(entity);
    }

    @Override
    public List<ChatEntity> saveAllAndFlush(Iterable<ChatEntity> entities) {
        return repository.saveAllAndFlush(entities);
    }

    @Override
    public Optional<ChatEntity> findById(Long id) {
        return repository.findById(id);
    }
}
