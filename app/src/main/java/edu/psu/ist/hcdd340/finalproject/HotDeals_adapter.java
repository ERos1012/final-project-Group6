package edu.psu.ist.hcdd340.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class HotDeals_adapter extends
        RecyclerView.Adapter<HotDeals_adapter.CampusViewHolder> {
    private final CampusData[] mCampusList;
    private final LayoutInflater mInflater;
    public HotDeals_adapter(Context context, CampusData[] mCampusList) {
        this.mCampusList = mCampusList;
        mInflater = LayoutInflater.from(context);
    }

    public class CampusListActivity extends AppCompatActivity {
        private RecyclerView mRecyclerView;
        private HotDeals_adapter mAdapter;


    }


    public HotDeals_adapter(CampusData[] mCampusList, LayoutInflater mInflater) {
        this.mCampusList = mCampusList;
        this.mInflater = mInflater;
    }




    class CampusViewHolder extends RecyclerView.ViewHolder {
        public final TextView campusItemView;
        final HotDeals_adapter mAdapter;
        public final TextView campusEmailView;
        public final TextView campusPhoneView;
        public final ImageView campusImageView;

        public final TextView status;

        public

        CampusViewHolder(View campusItemView1, HotDeals_adapter mAdapter) {
            super(campusItemView1);


            campusItemView = campusItemView1.findViewById(R.id.campus_list_title_id);
            // this.campusItemView = campusItemView;
            this.mAdapter = mAdapter;
            campusImageView = itemView.findViewById(R.id.campus_list_image_id);
            campusEmailView = itemView.findViewById(R.id.campus_list_email);
            campusPhoneView = itemView.findViewById(R.id.campus_list_phone);
            status = itemView.findViewById(R.id.status);
        }
    }

    @NonNull
    @Override
    public HotDeals_adapter.CampusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.hot_deals_list_item,
                parent, false);
        return new CampusViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HotDeals_adapter.CampusViewHolder holder, int position) {


        CampusData campus = mCampusList[position];
        holder.campusItemView.setText(campus.getCampusName());

        // display phone ->
        holder.campusPhoneView.setText(campus.getPhone());

        // display email ->
        holder.campusEmailView.setText(campus.getEmail());

        // display profile ->
        int image = campus.getProfileImage();
        holder.campusImageView.setImageResource(image);

        // display hours  ->
        String status = campus.getHours();
        holder.status.setText(status);

        // set text color based on status
        campus.setRedTextColor(holder.status);
        campus.setGreenTextColor(holder.status);
    }

    @Override
    public int getItemCount() {
        return mCampusList.length;
    }
}







