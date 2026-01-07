package com.electron.handle.mapper;

import com.electron.handle.dto.ChatResponseShort;
import com.electron.handle.dto.MessageResponse;
import com.electron.handle.entity.ChatEntity;
import com.electron.handle.entity.MessageEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface MessageMapper {

    @Mapping(target = "chat", source = "chat", qualifiedByName = "toChatResponseShort")
    MessageResponse toMessageResponse(MessageEntity messageEntity);

    @Named("toChatResponseShort")
    ChatResponseShort toChatResponseShort(ChatEntity chatEntity);
}
