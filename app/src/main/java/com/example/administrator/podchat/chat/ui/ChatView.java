package com.example.administrator.podchat.chat.ui;

import com.example.administrator.podchat.chat.entities.ChatMessage;

public interface ChatView {
    void sendMessage();
    void onMessageReceived(ChatMessage msg);
}
