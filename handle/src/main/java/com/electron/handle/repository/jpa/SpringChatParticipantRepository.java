package com.electron.handle.repository.jpa;

import com.electron.handle.entity.ChatParticipantEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringChatParticipantRepository extends JpaRepository<ChatParticipantEntity, UUID> {

    Slice<ChatParticipantEntity> findAllByUser(UUID userId, Pageable pageable);
}
