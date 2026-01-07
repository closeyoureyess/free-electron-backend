package com.electron.handle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WebSocketEnum {

    QUEUE_PATH("/queue/chat/");

    private String value;
}
