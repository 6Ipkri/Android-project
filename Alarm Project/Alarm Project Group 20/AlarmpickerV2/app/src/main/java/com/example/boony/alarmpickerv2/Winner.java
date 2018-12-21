package com.example.boony.alarmpickerv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;

public class Winner extends AppCompatActivity {
    Layout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

    }

    public void exit (View v){
        Intent intent = new Intent(this , NewsPage.class);
        startActivity(intent);
    }

}
