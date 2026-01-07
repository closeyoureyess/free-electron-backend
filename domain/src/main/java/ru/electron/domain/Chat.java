package ru.electron.domain;

import lombok.Builder;
import lombok.Data;
import ru.electron.domain.valueobject.ChatId;
import ru.electron.domain.valueobject.ChatType;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
public class Chat {

    private ChatId id;

    private ChatType type;

    private Instant createdAt;

    private Instant lastMessageAt;

    private Set<Message> messages;

    public static Chat create(Long id,
                              String type,
                              Instant createdAt,
                              Instant lastMessageAt,
                              Set<Message> messages) {
        Chat.ChatBuilder chatBuilder = Chat.builder();
        if (id != null) chatBuilder = chatBuilder.id(new ChatId(id));
        if (type != null) chatBuilder = chatBuilder.type(new ChatType(type));
        return chatBuilder.createdAt(createdAt)
                .lastMessageAt(lastMessageAt)
                .build();
    }
}
