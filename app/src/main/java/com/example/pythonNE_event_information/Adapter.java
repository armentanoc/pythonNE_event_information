package com.example.pythonNE_event_information;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private List<Item> items;
    private SelectListener listener;

    public Adapter(Context context, List<Item> items, SelectListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
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
        String text = "<a href='"+items.get(position).getUrl()+"'>"+items.get(position).getTitle()+"</a>";
        holder.titleView.setText(Html.fromHtml(text));
        holder.typeView.setText(items.get(position).getType());
        holder.startView.setText(items.get(position).getStart());
        holder.durationView.setText(items.get(position).getDuration());
        holder.dateView.setText(items.get(position).getDateStr());
        holder.languageView.setText(items.get(position).getLanguage());
        holder.strRoomView.setText(items.get(position).getStrRoom());
        holder.abstractView.setText(items.get(position).getStrAbstract());
        holder.personNameView.setText(items.get(position).getPersonName());

        //Listener para a URL
        holder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
