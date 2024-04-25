package com.example.goundbooking;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.goundbooking.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class calender extends AppCompatActivity {

    CalendarView cal;
    TextView date_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

//        cal = findViewById(R.id.calendar);
//        date_view = findViewById(R.id.date_view);
        cal = findViewById(R.id.calendar1);
        date_view=findViewById(R.id.date_view1);


        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "-" + (month + 1) + "-" + year;

                // Check booking status based on your logic
                boolean isBooked = isDateBooked(selectedDate);

                // Set the background color based on booking status
                int color = Integer.parseInt(String.valueOf(isBooked ? Color.RED : Color.GREEN));
                view.setDateTextAppearance(color);
//                used here parseInt to convert ot integer value

                // set this date in TextView for Display
                date_view.setText(selectedDate);

                // Allow booking if the date is available
                if (!isBooked) {
                    startActivity(new Intent(calender.this,Bookingform.class));
                    Toast.makeText(calender.this, "Ground is available. You can book!", Toast.LENGTH_SHORT).show();
                    // Add your booking logic here
                } else {
                    Toast.makeText(calender.this, "Ground is already booked on this date.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Example method to check booking status (replace it with your logic)
    private boolean isDateBooked(String selectedDate) {
        // Example: Check if the date is already booked in your data source
        // Replace this with your actual logic or data source check
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = sdf.parse(selectedDate);
            // Example: Check if the date is already booked
            Date bookedDate = sdf.parse("10-01-2024");
            return date != null && date.equals(bookedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}