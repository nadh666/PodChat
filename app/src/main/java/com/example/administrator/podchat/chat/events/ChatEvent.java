package com.example.administrator.podchat.chat.events;

import com.example.administrator.podchat.chat.entities.ChatMessage;

public class ChatEvent {

    ChatMessage msg;

    public ChatEvent(ChatMessage msg) {
        this.msg = msg;
    }

    public ChatMessage getMessage() {
        return msg;
    }
}
