package com.example.administrator.podchat.chat.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.podchat.PodChatApplication;
import com.example.administrator.podchat.chat.ChatPresenter;
import com.example.administrator.podchat.chat.ChatPresenterImpl;
import com.example.administrator.podchat.chat.adapters.ChatAdapter;
import com.example.administrator.podchat.chat.entities.ChatMessage;
import com.example.administrator.podchat.domain.AvatarHelper;
import com.example.administrator.podchat.lib.ImageLoader;
import com.example.administrator.podchat.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity
        implements ChatView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtUser)
    TextView txtUser;
    @BindView(R.id.txtStatus)
    TextView txtStatus;
    @BindView(R.id.editTxtMessage)
    EditText inputMessage;
    @BindView(R.id.messageRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;

    public final static String EMAIL_KEY = "email";
    public final static String ONLINE_KEY = "online";

    private ChatAdapter adapter;
    private ChatPresenter chatPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        chatPresenter = new ChatPresenterImpl(this);
        chatPresenter.onCreate();

        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        setToolbarData(intent);

        setupAdapter();
        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        chatPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        chatPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        chatPresenter.onDestroy();
        super.onDestroy();
    }

    private void setToolbarData(Intent i) {
        String recipient = i.getStringExtra(EMAIL_KEY);
        chatPresenter.setChatRecipient(recipient);

        boolean online = i.getBooleanExtra(ONLINE_KEY, false);
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);

        PodChatApplication app = (PodChatApplication) getApplication();
        ImageLoader imageLoader = app.getImageLoader();
        imageLoader.load(imgAvatar, AvatarHelper.getAvatarUrl(recipient));
    }

    private void setupAdapter() {
        adapter = new ChatAdapter(this, new ArrayList<ChatMessage>());
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.invalidate();
    }

    @Override
    @OnClick(R.id.btnSendMessage)
    public void sendMessage() {
        chatPresenter.sendMessage(inputMessage.getText().toString());
        inputMessage.setText("");
        adapter.notifyDataSetChanged();
        recyclerView.invalidate();
    }

    @Override
    public void onMessageReceived(ChatMessage msg) {
        adapter.add(msg);
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        adapter.notifyDataSetChanged();
        recyclerView.invalidate();
    }
}