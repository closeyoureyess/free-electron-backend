package com.electron.handle.repository.jpa;

import com.electron.handle.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringMessageRepository extends JpaRepository<MessageEntity, UUID> {
}
