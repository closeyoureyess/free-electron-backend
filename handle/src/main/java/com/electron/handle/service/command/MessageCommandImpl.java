package com.electron.handle.service.command;

import com.electron.handle.dto.ChatMessageRequest;
import org.springframework.stereotype.Component;
import ru.electron.domain.Chat;
import ru.electron.domain.Message;

import java.util.UUID;

@Component
class MessageCommandImpl implements MessageCommand {
    @Override
    public Message createMessageDomain(String userId, ChatMessageRequest payload) {
        return Message.create(payload.content(), UUID.fromString(userId), payload.createdAt());
    }

    @Override
    public void assignChatToMessage(Message message, Chat chat) {
        message.assignChatToMessage(chat);
    }
}
