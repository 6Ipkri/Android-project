package com.example.boony.peawarinchamrap;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Fonts extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFont();
    }

    private void initFont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath("font/THSarabun.ttf").setFontAttrId(R.attr.fontPath).build());
    }

}
