package edu.psu.ist.hcdd340.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EventListAdapter extends ArrayAdapter<Event> {
    private int resource;

    public EventListAdapter(Context context, int resource, List<Event> events) {
        super(context, resource, events);
        this.resource = resource;
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

        titleTextView.setText(event.getTitle());
        timeTextView.setText(event.getTime());
        placeTextView.setText(event.getPlace());
        descriptionTextView.setText(event.getDescription());

        return convertView;
    }
}
