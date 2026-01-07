package com.electron.handle.mapper;

import com.electron.handle.dto.ChatResponse;
import com.electron.handle.dto.MessageResponseShort;
import com.electron.handle.entity.ChatEntity;
import com.electron.handle.entity.MessageEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ChatMapper {

    @Mapping(target = "messages", source = "messages", qualifiedByName = "messageEntityToMessageResponseShort")
    ChatResponse toChatResponse(ChatEntity chat);

    @Named("messageEntityToMessageResponseShort")
    MessageResponseShort messageEntityToMessageResponseShort(MessageEntity message);
}
