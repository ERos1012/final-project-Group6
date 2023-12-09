package edu.psu.ist.hcdd340.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private ListView rsvpEventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        rsvpEventListView = findViewById(R.id.rsvpEventListView); // Initialize the ListView

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
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
                        startActivity(new Intent(getApplicationContext(), Chat.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        // Update TextViews with dummy data for statistics
        updateStatistics();
    }

    private void updateStatistics() {
        // Dummy data for statistics
        int mealsRecovered = 578;
        int totalMealsRecovered = 1000;
        int numVolunteers = 10;
        int hoursVolunteered = 100;

        TextView mealsRecoveredTextView = findViewById(R.id.mealsRecoveredLabelTextView);
        TextView mealsRecoveredNumberTextView = findViewById(R.id.mealsRecoveredNumberTextView);
        mealsRecoveredTextView.setText("Meals Recovered: ");
        mealsRecoveredNumberTextView.setText(mealsRecovered + "");

        TextView totalMealsRecoveredTextView = findViewById(R.id.totalMealsRecoveredLabelTextView);
        TextView totalMealsRecoveredNumberTextView = findViewById(R.id.totalMealsRecoveredNumberTextView);
        totalMealsRecoveredTextView.setText("Total Meals Recovered: ");
        totalMealsRecoveredNumberTextView.setText(totalMealsRecovered + "");

        TextView numVolunteersTextView = findViewById(R.id.numVolunteersLabelTextView);
        TextView numVolunteersNumberTextView = findViewById(R.id.numVolunteersNumberTextView);
        numVolunteersTextView.setText("Number of Volunteers: ");
        numVolunteersNumberTextView.setText(numVolunteers + "");

        TextView hoursVolunteeredTextView = findViewById(R.id.hoursVolunteeredLabelTextView);
        TextView hoursVolunteeredNumberTextView = findViewById(R.id.hoursVolunteeredNumberTextView);
        hoursVolunteeredTextView.setText("Hours Volunteered: ");
        hoursVolunteeredNumberTextView.setText(hoursVolunteered + "");
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Update RSVP events when the activity is resumed
        updateRSVPEvents();
    }

    private void updateRSVPEvents() {
        Set<String> rsvpEvents = loadRSVPData();

        if (rsvpEvents.isEmpty()) {
            // Create an ArrayAdapter to display "No RSVP Events"
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{"No RSVP Events"});

            // Set the adapter to the ListView
            rsvpEventListView.setAdapter(adapter);
        } else {
            // Create an ArrayAdapter to display the RSVP events
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rsvpEvents.toArray(new String[0]));

            // Set the adapter to the ListView
            rsvpEventListView.setAdapter(adapter);
        }
    }


    // Load RSVP data from SharedPreferences
    private Set<String> loadRSVPData() {
        SharedPreferences sharedPreferences = getSharedPreferences("RSVP_PREFERENCES", MODE_PRIVATE);
        return sharedPreferences.getStringSet("RSVP_EVENTS", new HashSet<>());
    }
}
