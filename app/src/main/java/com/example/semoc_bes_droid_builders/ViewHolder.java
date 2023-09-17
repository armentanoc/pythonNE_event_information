package com.example.semoc_bes_droid_builders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameView, typeView, localView, dateView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        nameView = itemView.findViewById(R.id.name);
        typeView = itemView.findViewById(R.id.type);
        localView = itemView.findViewById(R.id.local);
        dateView = itemView.findViewById(R.id.date);
    }
}
