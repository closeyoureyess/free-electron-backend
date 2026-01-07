package com.electron.handle.mapper;

import com.electron.handle.dto.ChatParticipantResponse;
import com.electron.handle.dto.ChatResponse;
import com.electron.handle.dto.MessageResponseShort;
import com.electron.handle.entity.ChatEntity;
import com.electron.handle.entity.ChatParticipantEntity;
import com.electron.handle.entity.MessageEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = UserMapper.class
)
public interface ChatParticipantMapper {

    @Mapping(target = "chat", source = "chat", qualifiedByName = "chatEntityToChatResponse")
    ChatParticipantResponse toChatParticipantResponse(ChatParticipantEntity chatParticipantEntity);

    @Named("chatEntityToChatResponse")
    @Mapping(target = "messages", source = "messages", qualifiedByName = "messageEntityToMessageResponseShort")
    ChatResponse toChatResponse(ChatEntity chat);

    @Named("messageEntityToMessageResponseShort")
    MessageResponseShort messageEntityToMessageResponseShort(MessageEntity message);
}
