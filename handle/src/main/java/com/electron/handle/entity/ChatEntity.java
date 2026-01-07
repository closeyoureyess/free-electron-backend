package com.electron.handle.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "chat")
@NamedEntityGraph(
        name = "ChatEntity.graph",
        attributeNodes = @NamedAttributeNode(value = "messages")
)
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "last_message_at")
    private Instant lastMessageAt;

    @OneToMany(mappedBy = "chats")
    private Set<MessageEntity> messages;
}
