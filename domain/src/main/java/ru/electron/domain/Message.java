package ru.electron.domain;

import lombok.Builder;
import lombok.Data;
import ru.electron.domain.valueobject.MessageContent;
import ru.electron.domain.valueobject.MessageId;
import ru.electron.domain.valueobject.MessageUserId;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class Message {

    private MessageId id;

    private MessageContent content;

    private MessageUserId userId;

    private Instant createdAt;

    private Chat chat;

    public static Message create(String content, UUID userId, Instant createdAt) {
        Message.MessageBuilder messageBuilder = Message.builder();
        if (content != null) messageBuilder = messageBuilder.content(new MessageContent(content));
        if (userId != null) messageBuilder = messageBuilder.userId(new MessageUserId(userId));
        return messageBuilder.createdAt(createdAt).build();
    }

    public Instant fixCreatedAt() {
        this.createdAt = Instant.now();
        return this.createdAt;
    }

    public void assignChatToMessage(Chat chat) {
        this.chat = chat;
    }
}
