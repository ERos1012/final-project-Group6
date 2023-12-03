package edu.psu.ist.hcdd340.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CalendarPanel extends AppCompatActivity {

    private CalendarView calendarView;
    private ListView eventListView;
    private Map<String, List<Event>> eventsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarView = findViewById(R.id.calendarView);
        eventListView = findViewById(R.id.eventListView);
        eventsMap = new HashMap<>();

        // Set up date change listener for the calendar
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                updateEventList(year, month, dayOfMonth);

                // Highlight the selected date with events
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(year, month, dayOfMonth);
                long selectedDateMillis = selectedCalendar.getTimeInMillis();
                calendarView.setDate(selectedDateMillis, true, true);
            }
        });

        // Populate dummy events
        populateDummyEvents();

        // Set up item click listener for the event list
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedEvent = (String) parent.getItemAtPosition(position);
                Toast.makeText(CalendarPanel.this, "Selected Event: " + selectedEvent, Toast.LENGTH_SHORT).show();
            }
        });

        // Set up initial event list for the current date
        Calendar initialCalendar = Calendar.getInstance();
        updateEventList(
                initialCalendar.get(Calendar.YEAR),
                initialCalendar.get(Calendar.MONTH),
                initialCalendar.get(Calendar.DAY_OF_MONTH)
        );

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

    private void updateEventList(int year, int month, int dayOfMonth) {
        Calendar selectedCalendar = Calendar.getInstance();
        selectedCalendar.set(year, month, dayOfMonth);
        String selectedDate = formatDate(selectedCalendar.getTime());

        List<Event> events = eventsMap.get(selectedDate);

        if (events != null && !events.isEmpty()) {
            // Display events in the list
            EventListAdapter adapter = new EventListAdapter(this, R.layout.item_event, events);
            eventListView.setAdapter(adapter);
        } else {
            // Display "No Events"
            List<String> noEvents = new ArrayList<>();
            noEvents.add("No Events");
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_no_events, noEvents);
            eventListView.setAdapter(adapter);
        }
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return sdf.format(date);
    }

    private void populateDummyEvents() {
        // Add dummy events on December 9, 2023
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2023, Calendar.DECEMBER, 9);
        String dateString1 = formatDate(calendar1.getTime());

        List<Event> events1 = new ArrayList<>();
        events1.add(new Event("Event 1A", "10:00 AM", "Location A", "Short description for Event 1A"));
        events1.add(new Event("Event 1B", "2:00 PM", "Location B", "Short description for Event 1B"));
        eventsMap.put(dateString1, events1);

        // Add dummy events on December 3, 2023
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2023, Calendar.DECEMBER, 3);
        String dateString2 = formatDate(calendar2.getTime());

        List<Event> events2 = new ArrayList<>();
        events2.add(new Event("Event 2A", "1:00 PM", "Location C", "Short description for Event 2A"));
        eventsMap.put(dateString2, events2);

        // Add dummy events on December 12, 2023
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2023, Calendar.DECEMBER, 12);
        String dateString3 = formatDate(calendar3.getTime());

        List<Event> events3 = new ArrayList<>();
        events3.add(new Event("Event 3A", "3:00 PM", "Location D", "Short description for Event 3A"));
        events3.add(new Event("Event 3B", "5:00 PM", "Location E", "Short description for Event 3B"));
        eventsMap.put(dateString3, events3);
    }
}
