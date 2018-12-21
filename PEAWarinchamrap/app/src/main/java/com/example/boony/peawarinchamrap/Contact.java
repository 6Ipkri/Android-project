package com.example.boony.peawarinchamrap;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Contact extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        WebView myWebView;

        String mapPath = "https://www.google.com/maps/@15.1621334,104.8547794,195m/data=!3m1!1e3";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        myWebView = (WebView)findViewById(R.id.web_view);

        myWebView.setWebViewClient(new CustomWebViewClient());
        WebSettings webSeting = myWebView.getSettings();

        webSeting.setJavaScriptEnabled(true);
        myWebView.getSettings().setJavaScriptEnabled(true);


        myWebView.loadUrl(mapPath);
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
}
