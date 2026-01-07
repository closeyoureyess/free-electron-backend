package com.electron.handle.service.command;

import com.electron.handle.dto.ChatMessageRequest;
import ru.electron.domain.Chat;
import ru.electron.domain.Message;

public interface MessageCommand {

    Message createMessageDomain(String userId, ChatMessageRequest payload);

    void assignChatToMessage(Message message, Chat chat);
}
