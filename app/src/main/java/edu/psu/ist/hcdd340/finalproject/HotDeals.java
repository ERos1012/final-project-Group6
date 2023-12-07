package edu.psu.ist.hcdd340.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HotDeals extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private HotDeals_adapter mAdapter;

    private final CampusData[] CAMPUS_LIST = {
            new CampusData("Abba Java", R.drawable.abbajava, " • Closes 5PM", "(814)532-1423", "Open" ),
            new CampusData("Lion Pantry", R.drawable.lionpantry, " • Opens 4PM", "(814)765-3457", "Closed"),
            new CampusData("Waring Commons", R.drawable.waringcommons, " • Closes 8PM", "(814)187-7425", "Open"),
            new CampusData("Redifer Commons", R.drawable.redifer, " • Closes 8PM", "(814)867-6234", "Open"),
            new CampusData("Pollock Commons", R.drawable.pollock, " • Closes 8PM", "(814)432-6132", "Open"),
            new CampusData("Findlay Commons", R.drawable.findlay, " • Closes 8PM", "(814)153-6213", "Open"),
            new CampusData("Warnock Commons", R.drawable.warnock, " • Closes 8PM", "(814)413-523", "Open"),
            new CampusData("Warnock Commons", R.drawable.warnock, " • Closes 8PM", "(814)413-523", "Open")


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
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);

        // Set Hot Deals selected
        bottomNavigationView.setSelectedItemId(R.id.hot_deals);

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
                        return true;
                    case R.id.chat:
                        startActivity(new Intent(getApplicationContext(),Chat.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.use_case:
                        startActivity(new Intent(getApplicationContext(),UseCase.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
}