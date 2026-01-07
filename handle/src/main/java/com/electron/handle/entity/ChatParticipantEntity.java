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
@Table(name = "chat_participant")
@NamedEntityGraph(
        name = "ChatParticipantEntity.graph",
        attributeNodes = {
                @NamedAttributeNode(value = "chat"),
                @NamedAttributeNode(value = "user")
        }
)
public class ChatParticipantEntity {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private ChatEntity chat;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    private Instant joinedAt;
}
