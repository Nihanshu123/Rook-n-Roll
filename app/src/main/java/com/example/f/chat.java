package com.example.f;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class chat extends AppCompatActivity {

    private EditText messageEditText;
    private Button sendButton;
    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private ArrayList<ChatMessage> chatMessageList;

    // Firebase reference
    private DatabaseReference chatDatabaseReference;

    // User name variable
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Get the user's name from the Intent
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");

        // Initialize views
        messageEditText = findViewById(R.id.message_edit_text);
        sendButton = findViewById(R.id.send_button);
        chatRecyclerView = findViewById(R.id.chat_recycler_view);

        // Set up RecyclerView
        chatMessageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, chatMessageList);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);

        // Firebase database reference for chat messages
        chatDatabaseReference = FirebaseDatabase.getInstance().getReference("chat");

        // Load chat messages from Firebase
        loadChatMessages();

        // Send button click listener
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void loadChatMessages() {
        chatDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatMessageList.clear();
                for (DataSnapshot messageSnapshot : snapshot.getChildren()) {
                    ChatMessage chatMessage = messageSnapshot.getValue(ChatMessage.class);
                    chatMessageList.add(chatMessage);
                }
                chatAdapter.notifyDataSetChanged();
                chatRecyclerView.scrollToPosition(chatMessageList.size() - 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(chat.this, "Failed to load messages", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendMessage() {
        String message = messageEditText.getText().toString().trim();

        if (TextUtils.isEmpty(message)) {
            Toast.makeText(chat.this, "Message cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a ChatMessage object
        ChatMessage chatMessage = new ChatMessage(userName, message);  // Use the user's actual name

        // Push the message to Firebase
        chatDatabaseReference.push().setValue(chatMessage);

        // Clear the input field
        messageEditText.setText("");
    }

    // ChatMessage class
    public static class ChatMessage {
        public String userName;
        public String message;

        public ChatMessage() {
            // Default constructor required for Firebase
        }

        public ChatMessage(String userName, String message) {
            this.userName = userName;
            this.message = message;
        }
    }
}
