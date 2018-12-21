package com.example.boony.alarmpickerv2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class AlarmOffPage extends AppCompatActivity {

    AlarmManager alarm_manager;
    PendingIntent pending_intent;
    Intent my_intent;
    int choose_whale_sound;
    TextView te;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_off_page);

        this.context = this;

        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        my_intent = new Intent(this.context, Alarm_Receiver.class);



        ImageView alarm_Off = (ImageView) findViewById(R.id.image_alarm);
        // create an onClick listener to stop the alarm or undo an alarm set

        alarm_Off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {


                    startActivity(new Intent(AlarmOffPage.this , MethodOffAlarm.class));


                }catch (Exception e){
                    te.setText(e.getMessage());
                }

            }
        });



    }
}