package edu.psu.ist.hcdd340.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);

        // Set Chat selected
        bottomNavigationView.setSelectedItemId(R.id.chat);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.menu_calendar:
                        startActivity(new Intent(getApplicationContext(), CalendarPanel.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.hot_deals:
                        startActivity(new Intent(getApplicationContext(),HotDeals.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.chat:
                        return true;
                }
                return false;
            }
        });
    }
}