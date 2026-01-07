package com.electron.handle.repository.adapter;

import com.electron.handle.entity.ChatParticipantEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.UUID;

public interface ChatParticipantRepository extends Repository<UUID, ChatParticipantEntity> {

    Slice<ChatParticipantEntity> findAllByUser(UUID userId, Pageable pageable);
}
