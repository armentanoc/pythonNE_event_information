package com.example.pythonNE_event_information;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView titleView, typeView, languageView, startView, durationView, strRoomView, dateView, urlView, abstractView, personNameView, personBiographyView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        //imageView = itemView.findViewById(R.id.imageview);
        titleView = itemView.findViewById(R.id.titleview);
        typeView = itemView.findViewById(R.id.typeview);
        languageView = itemView.findViewById(R.id.languageview);
        startView = itemView.findViewById(R.id.startview);
        durationView = itemView.findViewById(R.id.durationview);
        strRoomView = itemView.findViewById(R.id.roomview);
        dateView = itemView.findViewById(R.id.dateview);
        urlView = itemView.findViewById(R.id.urlview);
        abstractView = itemView.findViewById(R.id.abstractview);
        personNameView = itemView.findViewById(R.id.personnameview);
        //personBiographyView = itemView.findViewById(R.id.personbiographyview);

    }
}
