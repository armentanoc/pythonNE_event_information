package com.example.pythonNE_event_information;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SpeakerAdapter extends RecyclerView.Adapter<SpeakerAdapter.SpeakerViewHolder> {

    private List<SpeakerItem> speakerList;

    public SpeakerAdapter(List<SpeakerItem> speakerList) {
        this.speakerList = speakerList;
    }

    @NonNull
    @Override
    public SpeakerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View speakerView = inflater.inflate(R.layout.speaker_item_layout, parent, false); // Create a layout for your speaker item
        return new SpeakerViewHolder(speakerView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeakerViewHolder holder, int position) {
        SpeakerItem speakerItem = speakerList.get(position);
        holder.bind(speakerItem);
    }

    @Override
    public int getItemCount() {
        return speakerList.size();
    }

    public void addAll(List<SpeakerItem> speakers) {
        speakerList.clear();
        speakerList.addAll(speakers);
        notifyDataSetChanged();
    }

    static class SpeakerViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView bioTextView;

        SpeakerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName); // Adjust to your layout
            bioTextView = itemView.findViewById(R.id.textViewBio); // Adjust to your layout
        }

        void bind(SpeakerItem speakerItem) {
            nameTextView.setText(speakerItem.getName());
            bioTextView.setText(speakerItem.getBio());
        }
    }
}
