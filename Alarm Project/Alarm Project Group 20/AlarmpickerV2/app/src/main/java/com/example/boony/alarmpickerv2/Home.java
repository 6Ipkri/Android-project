package com.example.boony.alarmpickerv2;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by boony on 5/7/2017.
 */

public class Home extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //to make our alarm manager
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    TextView update_text , timeset , textmethod;
    Context context;
    PendingIntent pending_intent;
    int choose_whale_sound;
    Calendar calendar;
    Intent my_intent;
    TextView te , te2;
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.context = this;

        // initialize our alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //initialize our timepicker
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        //initialize our text update box
        update_text = (TextView) findViewById(R.id.update_text);

        // create an instance of a calendar
        calendar = Calendar.getInstance();

        // create an intent to the Alarm Receiver class
        my_intent = new Intent(this.context, Alarm_Receiver.class);
        timeset = (TextView)findViewById(R.id.time);

        Spinner meSpinner = (Spinner) findViewById(R.id.method_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.method, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        meSpinner.setAdapter(adapter2);
        // Set an onclick listener to the onItemSelected method
        meSpinner.setOnItemSelectedListener(this);



        // create the spinner in the main UI
        Spinner spinner = (Spinner) findViewById(R.id.richard_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.whale_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        // Set an onclick listener to the onItemSelected method
        spinner.setOnItemSelectedListener(this);


        // initialize start button
        Button alarm_on = (Button) findViewById(R.id.alarm_on);

        // create an onClick listener to start the alarm



        alarm_on.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try {

                    calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                    calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                    int hour = alarm_timepicker.getHour();
                    int minute = alarm_timepicker.getMinute();

                    String hour_string = String.valueOf(hour);
                    String minute_string = String.valueOf(minute);

                    if (hour > 12) {
                        hour_string = String.valueOf(hour - 12);
                    }

                    if (minute < 10) {
                        minute_string = "0" + String.valueOf(minute);
                    }

                    set_alarm_text("Alarm set to: " + hour_string + ":" + minute_string);


                    my_intent.putExtra("extra", "alarm on");
                    my_intent.putExtra("time" , hour_string + ":" + minute_string);

                    my_intent.putExtra("whale_choice", choose_whale_sound);
                    Log.e("The whale id is", String.valueOf(choose_whale_sound));


                    pending_intent = PendingIntent.getBroadcast(Home.this, 0,
                            my_intent, PendingIntent.FLAG_UPDATE_CURRENT);


                    alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            pending_intent);

                }catch (Exception e){

                }
            }

        });



        // initialize the stop button
        Button alarm_off = (Button) findViewById(R.id.alarm_off);
        // create an onClick listener to stop the alarm or undo an alarm set

        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivity(new Intent(Home.this, Home.class));

                }catch (Exception e){

                }
            }
        });

        TextView textcamera = (TextView) findViewById(R.id.textView3);
        textcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Home.this , MainActivityCamera.class));

            }
        });

    }

    protected void set_alarm_text(String output) {
        update_text.setText(output);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        // outputting whatever id the user has selected
        //Toast.makeText(parent.getContext(), "the spinner item is "
        //        + id, Toast.LENGTH_SHORT).show();
        choose_whale_sound = (int) id;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.menu_home){
            startActivity(new Intent(this , Home.class));
            return true;
        }
        else if (id == R.id.menu_news){
            startActivity(new Intent(this, NewsPage.class));
            return true;
        }
        return true;
    }


}
