package edu.psu.ist.hcdd340.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {

    private List<ChatMessage> chatMessages;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set Chat selected
        bottomNavigationView.setSelectedItemId(R.id.chat);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.menu_calendar:
                        startActivity(new Intent(getApplicationContext(), CalendarPanel.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.hot_deals:
                        startActivity(new Intent(getApplicationContext(), HotDeals.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.chat:
                        return true;
                }
                return false;
            }
        });

        // Initialize chatMessages list
        chatMessages = new ArrayList<>();

        // Add sample messages
        ChatMessage sentMessage = new ChatMessage("Hello! I'm reaching out to ask about possible volunteer work with your organization", true, R.drawable.default_profile_image_user, "Volunteer (You)", "12:34 PM");
        ChatMessage receivedMessage = new ChatMessage("Hi there! We're happy to help. Can you tell us more on how we can collaborate?", false, R.drawable.default_profile_image_frn, "Abba Java", "1:45 PM");

        // Add messages to the list
        chatMessages.add(sentMessage);
        chatMessages.add(receivedMessage);

        // Initialize RecyclerView and its adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerViewChat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(chatMessages);
        recyclerView.setAdapter(chatAdapter);
    }
}
