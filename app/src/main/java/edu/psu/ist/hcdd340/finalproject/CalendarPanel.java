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
        // Add dummy events on December 6, 2023
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2023, Calendar.DECEMBER, 6);
        String dateString1 = formatDate(calendar1.getTime());

        List<Event> events1 = new ArrayList<>();
        events1.add(new Event("Community Service Day", "9:00 AM", "Various Locations", "Participate in community service activities"));
        eventsMap.put(dateString1, events1);

        // Add dummy events on December 7, 2023
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2023, Calendar.DECEMBER, 7);
        String dateString2 = formatDate(calendar2.getTime());

        List<Event> events2 = new ArrayList<>();
        events2.add(new Event("Tech Workshop", "2:00 PM", "Innovation Center", "Hands-on workshop on emerging technologies"));
        events2.add(new Event("Art Class", "6:00 PM", "Art Studio", "Learn painting and drawing techniques"));
        eventsMap.put(dateString2, events2);

        // Add dummy events on December 8, 2023
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2023, Calendar.DECEMBER, 8);
        String dateString3 = formatDate(calendar3.getTime());

        List<Event> events3 = new ArrayList<>();
        events3.add(new Event("Fitness Challenge", "7:30 AM", "City Park", "Join us for a morning fitness challenge"));
        events3.add(new Event("Networking Event", "5:30 PM", "Business Center", "Connect with professionals from various industries"));
        eventsMap.put(dateString3, events3);

        // Add dummy events on December 9, 2023
        Calendar calendar11 = Calendar.getInstance();
        calendar11.set(2023, Calendar.DECEMBER, 9);
        String dateString11 = formatDate(calendar11.getTime());

        List<Event> events11 = new ArrayList<>();
        events11.add(new Event("Community Gathering", "6:00 PM", "Community Center", "Join us for a festive community gathering"));
        eventsMap.put(dateString11, events11);


        // Add dummy events on December 10, 2023
        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(2023, Calendar.DECEMBER, 10);
        String dateString4 = formatDate(calendar4.getTime());

        List<Event> events4 = new ArrayList<>();
        events4.add(new Event("Community Cleanup", "9:00 AM", "Local Park", "Join us for a community cleanup event"));
        eventsMap.put(dateString4, events4);

        // Add dummy events on December 11, 2023
        Calendar calendar5 = Calendar.getInstance();
        calendar5.set(2023, Calendar.DECEMBER, 11);
        String dateString5 = formatDate(calendar5.getTime());

        List<Event> events5 = new ArrayList<>();
        events5.add(new Event("Holiday Parade", "2:00 PM", "Downtown State College", "Annual holiday parade celebration"));
        eventsMap.put(dateString5, events5);

        // Add dummy events on December 12, 2023
        Calendar calendar6 = Calendar.getInstance();
        calendar6.set(2023, Calendar.DECEMBER, 12);
        String dateString6 = formatDate(calendar6.getTime());

        List<Event> events6 = new ArrayList<>();
        events6.add(new Event("Hunger and Homelessness Week", "3:00 PM", "Zoom", "Virtual hour of volunteer service on Earth Day"));
        events6.add(new Event("MLK Act of Service", "5:00 PM", "Zoom", "Virtual hour of volunteer service to honor the legacy of Dr. Martin Luther King"));
        eventsMap.put(dateString6, events6);

        // Add dummy events on December 13, 2023
        Calendar calendar7 = Calendar.getInstance();
        calendar7.set(2023, Calendar.DECEMBER, 13);
        String dateString7 = formatDate(calendar7.getTime());

        List<Event> events7 = new ArrayList<>();
        events7.add(new Event("Team Building Workshop", "11:00 AM", "Company Office", "Interactive workshop to build team collaboration"));
        events7.add(new Event("Charity Auction", "7:00 PM", "Convention Center", "Auction event to support local charities"));
        eventsMap.put(dateString7, events7);

        // Add dummy events on December 14, 2023
        Calendar calendar8 = Calendar.getInstance();
        calendar8.set(2023, Calendar.DECEMBER, 14);
        String dateString8 = formatDate(calendar8.getTime());

        List<Event> events8 = new ArrayList<>();
        events8.add(new Event("Tech Conference", "9:30 AM", "Conference Center", "Conference on the latest technology trends"));
        events8.add(new Event("Networking Mixer", "6:00 PM", "City Lounge", "Social networking event for professionals"));
        eventsMap.put(dateString8, events8);

        // Add dummy events on December 15, 2023
        Calendar calendar9 = Calendar.getInstance();
        calendar9.set(2023, Calendar.DECEMBER, 15);
        String dateString9 = formatDate(calendar9.getTime());

        List<Event> events9 = new ArrayList<>();
        events9.add(new Event("Art Exhibition", "10:00 AM", "Art Gallery", "Showcasing local artists' work"));
        events9.add(new Event("Music Festival", "4:00 PM", "Outdoor Venue", "Live music performances and food stalls"));
        eventsMap.put(dateString9, events9);

        // Add dummy events on December 16, 2023
        Calendar calendar10 = Calendar.getInstance();
        calendar10.set(2023, Calendar.DECEMBER, 16);
        String dateString10 = formatDate(calendar10.getTime());

        List<Event> events10 = new ArrayList<>();
        events10.add(new Event("Book Club Meeting", "6:30 PM", "Public Library", "Discussion on the latest book selection"));
        events10.add(new Event("Movie Night", "8:00 PM", "Community Center", "Outdoor movie screening for the community"));
        eventsMap.put(dateString10, events10);
    }
}
