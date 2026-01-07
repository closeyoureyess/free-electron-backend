package com.electron.handle.dto;

import com.electron.handle.enums.ChatType;

import java.time.Instant;

public record ChatMessageRequest(String content, ChatType chatType, Instant createdAt) {
}
