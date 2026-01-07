package com.electron.handle.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "chat_message")
@NamedEntityGraph(
        name = "MessageEntity.graph",
        attributeNodes = @NamedAttributeNode(value = "chats")
)
public class MessageEntity {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "content")
    private String content;

    @Column(name = "author_user_id")
    private UUID userId;

    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private ChatEntity chat;

}
