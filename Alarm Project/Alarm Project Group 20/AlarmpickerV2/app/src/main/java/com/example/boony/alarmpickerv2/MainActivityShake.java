package com.example.boony.alarmpickerv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivityShake extends Activity implements SensorEventListener, StepListener {
    private TextView textView;
    private SimpleStepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "  Number of Shake: ";
    private int numSteps;
    Intent my_intent;
    Context context;
    int choose_whale_sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainshake);

        this.context = this;

        textView = (TextView) findViewById(R.id.textView2);

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new SimpleStepDetector();
        simpleStepDetector.registerListener(this);

        my_intent = new Intent(this.context, Alarm_Receiver.class);

    }

    @Override
    public void onResume() {
        super.onResume();
        numSteps = 0;
        textView.setText(TEXT_NUM_STEPS + numSteps);
        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        textView.setText(TEXT_NUM_STEPS + numSteps);
        if(numSteps==100){
            my_intent.putExtra("extra", "alarm off");
            my_intent.putExtra("whale_choice", choose_whale_sound);
            sendBroadcast(my_intent);

            startActivity(new Intent(getApplicationContext(), NewsPage.class));
        }
    }

}
