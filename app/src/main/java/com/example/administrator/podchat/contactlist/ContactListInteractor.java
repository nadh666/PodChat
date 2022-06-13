package com.example.administrator.podchat.contactlist;

public interface ContactListInteractor {

    void subscribeForContactEvents();
    void unSubscribeForContactEvents();
    void destroyContactListListener();
    void removeContact(String email);
}
