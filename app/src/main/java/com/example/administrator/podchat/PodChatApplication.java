package com.example.administrator.podchat;

import android.app.Application;

import com.example.administrator.podchat.lib.GlideImageLoader;
import com.example.administrator.podchat.lib.ImageLoader;
import com.google.firebase.database.FirebaseDatabase;

public class PodChatApplication extends Application {

    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
        setupImageLoader();
    }

    private void setupImageLoader() {
        imageLoader = new GlideImageLoader(this);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    private void setupFirebase(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}