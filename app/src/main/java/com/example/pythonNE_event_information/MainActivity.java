package com.example.pythonNE_event_information;

import androidx.appcompat.app.AppCompatActivity;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Item> information = new ArrayList<>();
    RecyclerView recyclerView;
    Adapter adapter;
    Date parsedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(getApplicationContext(), information);
        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {

        //Python NE API
        String api = "https://pretalx.com/python-nordeste-2023/schedule/export/schedule.json";

        //Initializing date formatter
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api,

                response -> { try {

                    String title, type, language, start, duration, strRoom, url, slug, strAbstract, strDate;
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray days = jsonObject.getJSONObject("schedule")
                            .getJSONObject("conference").getJSONArray("days");

                    for (int i = 0; i < days.length(); i++) {
                        JSONObject day = days.getJSONObject(i);
                        JSONObject roomData = day.getJSONObject("rooms");

                        Iterator<String> roomKeys = roomData.keys();
                        while (roomKeys.hasNext()) {
                            String roomName = roomKeys.next();
                            JSONArray events = roomData.getJSONArray(roomName);

                            for (int k = 0; k < events.length(); k++) {
                                JSONObject event = events.getJSONObject(k);

                                title = event.getString("title");
                                type = event.getString("type");
                                language = event.getString("language");
                                start = event.getString("start");
                                duration = event.getString("duration");
                                strRoom = event.getString("room");
                                url = event.getString("url");
                                strAbstract = event.getString("abstract");

                                //Date Information
                                strDate = event.getString("date");

                                try {
                                    // Parse the JSON string into a Date object
                                    parsedDate = dateFormat.parse(strDate);

                                    // Output the parsed date
                                    System.out.println("Parsed Date: " + parsedDate.toString());

                                } catch (ParseException e) {
                                    Log.e("date", "getData: "+e.getLocalizedMessage());
                                }

                                //Person Information
                                JSONArray persons = event.getJSONArray("persons");
                                List<String> personNames = new ArrayList<>();

                                for (int p = 0; p < persons.length(); p++) {
                                    JSONObject person = persons.getJSONObject(p);
                                    String personName = person.getString("public_name");
                                    personNames.add(personName);
                                }

                                //Creating Item object
                                Item event_info = new Item(title, type, language, start, duration, strRoom, url, strAbstract, personNames.toString().replaceAll("[\\[\\]]", ""), "personBiography", parsedDate);

                                //Adding object to List of Item objects
                                information.add(event_info);
                            }
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