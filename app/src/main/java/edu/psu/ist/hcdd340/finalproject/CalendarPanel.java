package edu.psu.ist.hcdd340.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarPanel extends AppCompatActivity {

    private CalendarView calendarView;
    private RecyclerView recyclerViewEvents;
    private EventAdapter eventAdapter;

    private Map<Long, List<Event>> eventsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        CalendarView calendarView = findViewById(R.id.calendarView);
        RecyclerView recyclerViewEvents = findViewById(R.id.recyclerViewEvents);


        // Set the current date as the selected date in the CalendarView
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        long currentDateInMillis = calendar.getTimeInMillis();
        calendarView.setDate(currentDateInMillis);

        // Logic for displaying events
        EventAdapter eventAdapter = new EventAdapter(new ArrayList<>());
        recyclerViewEvents.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEvents.setAdapter(eventAdapter);

        // Dummy events
        long today = System.currentTimeMillis();
        long tomorrow = today + (24 * 60 * 60 * 1000);
        long nextWeek = today + (7 * 24 * 60 * 60 * 1000);

        eventsMap.put(today, createEventList("Meeting", "Discuss project status"));
        eventsMap.put(tomorrow, createEventList("Lunch", "Team lunch at a local restaurant"));
        eventsMap.put(nextWeek, createEventList("Presentation", "Prepare for client presentation",
                "Training", "Attend training session"));

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                long selectedDate = getDateInMillis(year, month, dayOfMonth);
                updateEvents(eventsMap.get(selectedDate));
            }
        });

        // **NAVIGATION BAR LOGIC**
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set Calendar selected
        bottomNavigationView.setSelectedItemId(R.id.menu_calendar);

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
                        return true;
                    case R.id.hot_deals:
                        startActivity(new Intent(getApplicationContext(), HotDeals.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.chat:
                        startActivity(new Intent(getApplicationContext(), Chat.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.use_case:
                        startActivity(new Intent(getApplicationContext(), UseCase.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    private long getDateInMillis(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }


    private List<Event> createEventList(String... eventDetails) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < eventDetails.length; i += 2) {
            String title = eventDetails[i];
            String description = eventDetails[i + 1];
            events.add(new Event(title, description));
        }
        return events;
    }

    private void updateEvents(List<Event> events) {
        eventAdapter.updateEvents(events);
    }
}
