package edu.psu.ist.hcdd340.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HotDeals extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private HotDeals_adapter mAdapter;

    private final CampusData[] CAMPUS_LIST = {
            // Your campus data here...
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_deals_locations);

        mRecyclerView = findViewById(R.id.recycler_view_campus);
        mAdapter = new HotDeals_adapter(this, CAMPUS_LIST);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set Hot Deals selected
        bottomNavigationView.setSelectedItemId(R.id.hot_deals);

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
                        return true;
                    case R.id.chat:
                        startActivity(new Intent(getApplicationContext(), Chat.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

//        // Assuming you've inflated the layout somewhere in your activity
//        View itemView = getLayoutInflater().inflate(R.layout.hot_deals_list_item, null);
//        TextView chatIcon = itemView.findViewById(R.id.chatIcon);
//
//        chatIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Start a new activity for chat (replace Chat.class with the actual class for your chat page)
//                startActivity(new Intent(getApplicationContext(), Chat.class));
//            }
//        });
    }
}
