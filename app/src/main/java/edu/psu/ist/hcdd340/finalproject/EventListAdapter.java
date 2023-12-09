package edu.psu.ist.hcdd340.finalproject;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.snackbar.Snackbar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventListAdapter extends ArrayAdapter<Event> {
    private int resource;
    private Set<String> rsvpSet;
    private Context context;

    public EventListAdapter(Context context, int resource, List<Event> events) {
        super(context, resource, events);
        this.resource = resource;
        this.rsvpSet = loadRSVPData(context);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView timeTextView = convertView.findViewById(R.id.timeTextView);
        TextView placeTextView = convertView.findViewById(R.id.placeTextView);
        TextView descriptionTextView = convertView.findViewById(R.id.descriptionTextView);
        Button rsvpButton = convertView.findViewById(R.id.rsvpButton);

        titleTextView.setText(event.getTitle());
        timeTextView.setText(event.getTime());
        placeTextView.setText(event.getPlace());
        descriptionTextView.setText(event.getDescription());

        // Set up RSVP button click listener
        View finalConvertView = convertView;
        rsvpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rsvpSet.contains(event.getTitle())) {
                    showCancelRSVPConfirmationDialog(event, finalConvertView);
                } else {
                    showRSVPConfirmationDialog(event, finalConvertView);
                }
            }
        });

        // Change the RSVP button text based on whether the event is RSVPed
        changeRSVPButtonText(convertView, rsvpSet.contains(event.getTitle()));

        return convertView;
    }

    private void showRSVPConfirmationDialog(final Event event, final View convertView) {
        Log.d("RSVP_DEBUG", "Showing RSVP confirmation dialog for " + event.getTitle());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("RSVP Confirmation");
        builder.setMessage("Are you sure you want to RSVP for " + event.getTitle() + "?");

        builder.setPositiveButton("RSVP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rsvpSet.add(event.getTitle());
                saveRSVPData();
                showRSVPSnackbar(convertView, event.getTitle());
                changeRSVPButtonText(convertView, true);
                Log.d("RSVP_DEBUG", "RSVP added for " + event.getTitle());
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showCancelRSVPConfirmationDialog(final Event event, final View convertView) {
        Log.d("RSVP_DEBUG", "Showing cancel RSVP confirmation dialog for " + event.getTitle());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cancel RSVP Confirmation");
        builder.setMessage("Are you sure you want to cancel your RSVP for " + event.getTitle() + "?");

        builder.setPositiveButton("Cancel RSVP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rsvpSet.remove(event.getTitle());
                saveRSVPData();
                removeRSVPEventFromDatabase(event.getTitle()); // Remove from the database
                showCancelRSVPSnackbar(convertView, event.getTitle());
                changeRSVPButtonText(convertView, false);
                Log.d("RSVP_DEBUG", "RSVP removed for " + event.getTitle());
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showRSVPSnackbar(View view, String eventName) {
        Snackbar.make(view, "RSVP successful for " + eventName, Snackbar.LENGTH_SHORT).show();
    }

    private void showCancelRSVPSnackbar(View view, String eventName) {
        Snackbar.make(view, "Cancelled RSVP for " + eventName, Snackbar.LENGTH_SHORT).show();
    }

    private void changeRSVPButtonText(View convertView, boolean isRSVPed) {
        Button rsvpButton = convertView.findViewById(R.id.rsvpButton);
        rsvpButton.setText(isRSVPed ? "Cancel RSVP" : "RSVP");
    }

    // Save RSVP data to SharedPreferences
    private void saveRSVPData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("RSVP_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> rsvpEventSet = new HashSet<>(rsvpSet);
        editor.putStringSet("RSVP_EVENTS", rsvpEventSet);

        editor.apply();
    }

    // Load RSVP data from SharedPreferences
    private Set<String> loadRSVPData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("RSVP_PREFERENCES", Context.MODE_PRIVATE);
        return sharedPreferences.getStringSet("RSVP_EVENTS", new HashSet<>());
    }

    // Remove RSVP event from SharedPreferences
    private void removeRSVPEventFromDatabase(String eventTitle) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("RSVP_PREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Remove the event from the set
        rsvpSet.remove(eventTitle);

        // Save the updated set to SharedPreferences
        Set<String> rsvpEventSet = new HashSet<>(rsvpSet);
        editor.putStringSet("RSVP_EVENTS", rsvpEventSet);
        editor.apply();
    }
}
