package com.electron.handle.repository.jpa;

import com.electron.handle.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringChatRepository extends JpaRepository<ChatEntity, Long> {
}
