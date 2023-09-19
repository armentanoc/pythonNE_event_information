package com.example.pythonNE_event_information;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    List<Item> items;

    public Adapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    //Layout Inflater
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    //Bind the data to the view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleView.setText(items.get(position).getTitle());
        holder.typeView.setText(items.get(position).getType());
        holder.startView.setText(items.get(position).getStart());
        holder.durationView.setText(items.get(position).getDuration());
        holder.dateView.setText(items.get(position).getDateStr());
        holder.languageView.setText(items.get(position).getLanguage());
        holder.strRoomView.setText(items.get(position).getStrRoom());
        holder.urlView.setText(items.get(position).getUrl());
        holder.abstractView.setText(items.get(position).getStrAbstract());
        holder.personNameView.setText(items.get(position).getPersonName());
        holder.imageView.setImageResource(items.get(position).getPlaceholder());
        //holder.personBiographyView.setText(items.get(position).getPersonBiography());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
