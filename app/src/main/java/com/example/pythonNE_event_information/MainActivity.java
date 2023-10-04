package com.example.pythonNE_event_information;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.util.Calendar;

import android.widget.ImageView;
import android.widget.Toast;

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

import android.widget.Button;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements SelectListener {
    List<Item> information = new ArrayList<>();
    RecyclerView recyclerView;
    Adapter adapter;
    Button datePicker;
    ImageView removeFilterButton;
    private Date selectedDate = null;
    //Python NE API
    String api = "https://pretalx.com/python-nordeste-2023/schedule/export/schedule.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(this, information, this);
        recyclerView.setAdapter(adapter);

        // Initialize data with all events
        getData();

        datePicker = findViewById(R.id.datePicker);
        // Setting click listener for the date picker button
        datePicker.setOnClickListener(view -> showDatePicker());

        // Initialize the "Remove Filter" button
        removeFilterButton = findViewById(R.id.removeFilterButton);

        // Set a click listener for the "Remove Filter" button
        removeFilterButton.setOnClickListener(view -> {
            selectedDate = null;
            getData();
            Button datePickerButton = findViewById(R.id.datePicker);
            datePickerButton.setText("Selecionar Data");
        });
    }

    //collects data from Python NE API

    private void getData() {
        // Clear the existing information list
        information.clear();

        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api, response -> {
            try {
                String title, type, language, start, duration, strRoom, url, strAbstract, strDate;
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

                            // Date Information
                            strDate = event.getString("date");

                            try {
                                // Parse the JSON string into a Date object
                                SimpleDateFormat dateFormatJSON = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                                Date parsedDate = dateFormatJSON.parse(strDate);

                                // Check if the event matches the selected date or if no date is selected
                                if (selectedDate == null || isSameDay(parsedDate, selectedDate)) {
                                    // Person Information
                                    JSONArray persons = event.getJSONArray("persons");
                                    List<String> personNames = new ArrayList<>();

                                    for (int p = 0; p < persons.length(); p++) {
                                        JSONObject person = persons.getJSONObject(p);
                                        String personName = person.getString("public_name");
                                        personNames.add(personName);
                                    }

                                    // Creating Item object
                                    Item event_info = new Item(title, type, language, start, duration, strRoom, url, strAbstract, personNames.toString().replaceAll("[\\[\\]]", ""), "personBiography", parsedDate);
                                    information.add(event_info);
                                }
                            } catch (ParseException e) {
                                Log.e("date", "getData: " + e.getLocalizedMessage());
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                Log.e("api", "onResponse: " + e.getMessage());
            }
        }, error -> Log.e("api", "onErrorResponse: " + error.getLocalizedMessage()));

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    // Function to check if two Dates are the same day
    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void onItemClicked(Item item) {
        String url = item.getUrl();
        Uri uri = Uri.parse(url);
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();

        try {
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (Exception e) {
            Toast.makeText(this, "Não foi possível abrir o link.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    private void showDatePicker() {
        CalendarConstraints.Builder constraintsBuilderRange = new CalendarConstraints.Builder();

        // Parsing min and max dates from string to Date
        Date min, max;
        SimpleDateFormat dateFormatCalendar = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            min = dateFormatCalendar.parse("2023-09-21");
            max = dateFormatCalendar.parse("2023-09-24");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Max and min date
        CalendarConstraints.DateValidator dateValidatorMin = DateValidatorPointForward.from(min.getTime());
        CalendarConstraints.DateValidator dateValidatorMax = DateValidatorPointBackward.before(max.getTime());

        // Organizing list validators
        ArrayList<CalendarConstraints.DateValidator> listValidators = new ArrayList<>();
        listValidators.add(dateValidatorMin);
        listValidators.add(dateValidatorMax);
        CalendarConstraints.DateValidator validators = CompositeDateValidator.allOf(listValidators);

        // Adding date range constraints
        constraintsBuilderRange.setValidator(validators);
        constraintsBuilderRange.setStart(min.getTime());
        constraintsBuilderRange.setEnd(max.getTime());

        // Creating a MaterialDatePicker builder for selecting a date range
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();

        builder.setTheme(R.style.MaterialCalendarTheme);

        // Setting text
        builder.setTitleText("Selecione a data:");

        // Setting calendar constraints
        builder.setCalendarConstraints(constraintsBuilderRange.build());

        // Building the Material Date Picker itself
        MaterialDatePicker<Long> picker = builder.build();

        // Building the date picker dialog and handling date selection
        picker.addOnPositiveButtonClickListener(choosenDate -> {
            // Parse Long to Date
            selectedDate = new Date(choosenDate);

            // Formatting the selected date as string
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            String choosenDateString = sdf.format(new Date(choosenDate));

            // Set the selected date as the text of the date picker button
            datePicker.setText(choosenDateString); // Set the selected date as the button text

            // Call getData with the selected date to filter events
            getData();
        });

        // Showing the date picker dialog
        picker.show(getSupportFragmentManager(), "DATE_PICKER");
    }

}