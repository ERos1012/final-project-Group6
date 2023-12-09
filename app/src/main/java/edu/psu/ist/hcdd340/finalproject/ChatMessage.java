package edu.psu.ist.hcdd340.finalproject;

public class ChatMessage {
    private String message;
    private boolean isSentByUser;
    private int profileImage; // Resource ID of the profile image
    private String name;
    private String timestamp;

    public ChatMessage(String message, boolean isSentByUser, int profileImage, String name, String timestamp) {
        this.message = message;
        this.isSentByUser = isSentByUser;
        this.profileImage = profileImage;
        this.name = name;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSentByUser() {
        return isSentByUser;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public String getName() {
        return name;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
