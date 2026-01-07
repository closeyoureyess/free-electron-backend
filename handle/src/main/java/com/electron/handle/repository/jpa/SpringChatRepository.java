package com.electron.handle.repository.jpa;

import com.electron.handle.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringChatRepository extends JpaRepository<ChatEntity, UUID> {
}
