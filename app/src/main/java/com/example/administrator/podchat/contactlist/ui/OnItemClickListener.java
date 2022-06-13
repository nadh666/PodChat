package com.example.administrator.podchat.contactlist.ui;

import com.example.administrator.podchat.contactlist.entities.User;

public interface OnItemClickListener {

    void onItemClick(User user);
    void onItemLongClick(User user);
}
