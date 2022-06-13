package com.example.administrator.podchat.contactlist.ui;

import com.example.administrator.podchat.contactlist.entities.User;

public interface ContactListView {

    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
