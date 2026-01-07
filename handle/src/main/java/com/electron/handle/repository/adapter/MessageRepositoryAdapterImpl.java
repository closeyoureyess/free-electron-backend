package com.electron.handle.repository.adapter;

import com.electron.handle.entity.MessageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class MessageRepositoryAdapterImpl implements MessageRepository {

    private final MessageRepository repository;

    @Override
    public MessageEntity save(MessageEntity entity) {
        return repository.save(entity);
    }

    @Override
    public List<MessageEntity> saveAllAndFlush(Iterable<MessageEntity> entities) {
        return repository.saveAllAndFlush(entities);
    }

    @Override
    public Optional<MessageEntity> findById(UUID id) {
        return repository.findById(id);
    }
}
