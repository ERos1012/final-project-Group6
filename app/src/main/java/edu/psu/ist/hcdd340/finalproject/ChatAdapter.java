package edu.psu.ist.hcdd340.finalproject;

// ChatAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatMessage> chatMessages;

    public ChatAdapter(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        // Inflate the appropriate layout based on the view type (sent or received message)
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chat_message_sent, parent, false);
            return new SentMessageViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chat_message_received, parent, false);
            return new ReceivedMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Bind data to the views based on the view type
        if (holder.getItemViewType() == 0) {
            ((SentMessageViewHolder) holder).bind(chatMessages.get(position));
        } else {
            ((ReceivedMessageViewHolder) holder).bind(chatMessages.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Return 0 if the message is sent by the user, 1 otherwise
        return chatMessages.get(position).isSentByUser() ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    // ViewHolder for sent messages
    private static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewProfileSent;
        private TextView textViewNameSent;
        private TextView textViewTimestampSent;
        private TextView textViewMessageSent;

        SentMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProfileSent = itemView.findViewById(R.id.imageViewProfileSent);
            textViewNameSent = itemView.findViewById(R.id.textViewNameSent);
            textViewTimestampSent = itemView.findViewById(R.id.textViewTimestampSent);
            textViewMessageSent = itemView.findViewById(R.id.textViewMessageSent);
        }

        void bind(ChatMessage chatMessage) {
            // Bind data to the views
            imageViewProfileSent.setImageResource(chatMessage.getProfileImage());
            textViewNameSent.setText(chatMessage.getName());
            textViewTimestampSent.setText(chatMessage.getTimestamp());
            textViewMessageSent.setText(chatMessage.getMessage());
        }
    }

    // ViewHolder for received messages
    private static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewProfileReceived;
        private TextView textViewNameReceived;
        private TextView textViewTimestampReceived;
        private TextView textViewMessageReceived;

        ReceivedMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProfileReceived = itemView.findViewById(R.id.imageViewProfileReceived);
            textViewNameReceived = itemView.findViewById(R.id.textViewNameReceived);
            textViewTimestampReceived = itemView.findViewById(R.id.textViewTimestampReceived);
            textViewMessageReceived = itemView.findViewById(R.id.textViewMessageReceived);
        }

        void bind(ChatMessage chatMessage) {
            // Bind data to the views
            imageViewProfileReceived.setImageResource(chatMessage.getProfileImage());
            textViewNameReceived.setText(chatMessage.getName());
            textViewTimestampReceived.setText(chatMessage.getTimestamp());
            textViewMessageReceived.setText(chatMessage.getMessage());
        }
    }
}
