package com.example.goundbooking;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.button.MaterialButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Bookingform extends AppCompatActivity {

    private Calendar selectedDate;
    EditText bookname,bookmobileno,spname;
    TextView Tv_time1,Tv_time2,setdate;
    MaterialButton bbtn;
    int t1Hour,t1Minute,t2Hour,t2Minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingform);

        Tv_time1 = findViewById(R.id.Tv_time1);
        Tv_time2 = findViewById(R.id.Tv_time2);
        setdate = findViewById(R.id.setdate);
        selectedDate = Calendar.getInstance();

        setdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        Tv_time1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(Bookingform.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                t1Hour = hourOfDay;
                                t1Minute = minute;

                                Calendar calendar = Calendar.getInstance();

                                calendar.set(0,0,0,t1Hour,t1Minute);

                                Tv_time1.setText(DateFormat.format("hh:mm aa",calendar));
                            }
                        },12,0,false
                );

                timePickerDialog.updateTime(t1Hour,t1Minute);

                timePickerDialog.show();
            }
        });

        Tv_time2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Bookingform.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener()
                        {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                            {

                                t2Hour = hourOfDay;
                                t2Minute = minute;

                                String time = t2Hour + ":" + t2Minute;

                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);

                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );

                                    Tv_time2.setText(f12Hours.format(date));

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow()
                        .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                timePickerDialog.updateTime(t2Hour,t2Minute);

                timePickerDialog.show();
            }
        });
    }
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(Bookingform.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Set the selected date
                        selectedDate.set(Calendar.YEAR, year);
                        selectedDate.set(Calendar.MONTH, monthOfYear);
                        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Display the selected date
                        setdate.setText("Selected Date: " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

}