package com.electron.handle.websocket;

import org.springframework.web.socket.messaging.SessionConnectedEvent;

interface MessageListener {

    void onSessionConnected(SessionConnectedEvent event);
}
