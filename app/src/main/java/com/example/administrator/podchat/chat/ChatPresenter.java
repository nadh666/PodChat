package com.example.administrator.podchat.chat;

import com.example.administrator.podchat.chat.events.ChatEvent;

public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);

    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
}
