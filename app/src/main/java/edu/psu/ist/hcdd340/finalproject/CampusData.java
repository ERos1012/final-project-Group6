package edu.psu.ist.hcdd340.finalproject;

import android.graphics.Color;
import android.widget.TextView;

class CampusData {
    final private String campusName;
    final private int profileImage;
    final private String email;
    final private String phone;

    final private String hours;

    CampusData(String campusName, int profileImage, String email, String phone, String hours) {
        this.campusName = campusName;
        this.profileImage = profileImage;
        this.email = email;
        this.phone = phone;
        this.hours = hours;
    }


    public String getCampusName() {
        return campusName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public String getHours() {
        return hours;
    }

    public void setRedTextColor(TextView textView) {
        if (hours.equals("Closed")) {
            textView.setTextColor(Color.RED);
        }
    }

    public void setGreenTextColor(TextView textView) {
        if (hours.equals("Open")) {
            textView.setTextColor(Color.rgb(0, 128, 0));
        }
    }
}





