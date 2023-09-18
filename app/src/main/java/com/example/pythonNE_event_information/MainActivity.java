package com.example.pythonNE_event_information;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Item> information = new ArrayList<>();
    RecyclerView recyclerView;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Você pode criar o adaptador vazio aqui e definir no RecyclerView
        adapter = new Adapter(getApplicationContext(), information);
        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {

        String api = "https://pretalx.com/python-nordeste-2023/schedule/export/schedule.json";

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api,

                response -> { try {
                    //Get JSON Object from response
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject day;

                    //Get JSON Array for each day
                    JSONArray days = jsonObject
                            .getJSONObject("schedule")
                            .getJSONObject("conference")
                            .getJSONArray("days");

                    //Initialize rooms information
                    JSONArray rooms = new JSONArray();
                    JSONObject room = new JSONObject();
                    JSONArray events = new JSONArray();
                    JSONObject event;

                    //Get rooms information from each day
                    for (int i_day = 0; i_day < days.length(); i_day++) {
                        day = days.getJSONObject(i_day);
                        rooms.put(day.getJSONObject("rooms"));

                        for (int j_day = 0; j_day < 1; j_day++) { //rooms.length()
                            room = rooms.getJSONObject(j_day);
                        }

                        //Get rooms keys for each day
                        Iterator<String> it_rooms = room.keys();

                        while (it_rooms.hasNext()) {
                            events.put(room.getJSONArray(it_rooms.next()));
                        }
                    }

                    //Get events for each day

                    JSONArray arr_day;
                    String title, type, language, start, duration, strRoom, url,
                            strAbstract, personPublicName, personBiography;
                    Date date;

                    for (int i_day = 0; i_day < events.length(); i_day++) { //events.length()
                        arr_day = events.getJSONArray(i_day);

                        //Get individual event and adds it to
                        for (int i_event = 0; i_event < arr_day.length(); i_event++) {
                            event = arr_day.getJSONObject(i_event);

                            title = event.getString("title");
                            type = event.getString("type");
                            language = event.getString("language");
                            start = event.getString("start");
                            duration = event.getString("duration");
                            strRoom = event.getString("room");
                            url = event.getString("url");
                            strAbstract = event.getString("abstract");

                            //date = event.getString("title");
                            //personPublicName = event.getString("title");
                            //personBiography = event.getString("title");

                            Date date_today = new Date(20230505);
                            Item event_info = new Item(title, type, language, start, duration, strRoom, url, strAbstract, "personName", "personBiography", date_today, R.drawable.placeholder);
                            information.add(event_info);

                        }
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e("api", "onResponse: "+e.getMessage() );
                }
                }, error -> Log.e("api", "onErrorResponse: "+error.getLocalizedMessage()));

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}