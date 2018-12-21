package com.example.boony.alarmpickerv2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MethodOffAlarm extends AppCompatActivity {

    TextView texterr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_off_alarm);

        final ImageView shake = (ImageView) findViewById(R.id.shake);
        texterr = (TextView) findViewById(R.id.texterr);

        shake.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                try {

                    startActivity(new Intent(MethodOffAlarm.this, MainActivityShake.class));

                }catch (Exception e){
                    texterr.setText(e.getMessage());
                }

            }
        });

        ImageView bunny = (ImageView) findViewById(R.id.rabbit);
        bunny.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(MethodOffAlarm.this, MainActivityBunny.class));
                }catch (Exception e){
                    texterr.setText(e.getMessage());
                }

            }
        });

        ImageView camera = (ImageView) findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                try {

                    startActivity(new Intent(MethodOffAlarm.this, MainActivityCamera.class));
                }catch (Exception e){
                    texterr.setText(e.getMessage());
                }

            }
        });



            }
}
