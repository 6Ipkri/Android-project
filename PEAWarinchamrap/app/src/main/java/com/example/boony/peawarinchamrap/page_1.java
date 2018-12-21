package com.example.boony.peawarinchamrap;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class page_1 extends AppCompatActivity {
    protected TextView txtContact;

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_page_1);

        //  ReplaceFont.replaceDefultFont(this,"MONOSPACE","THSarabun.ttf");

        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        // txtUsername & txtPassword
        final EditText txtUser = (EditText) findViewById(R.id.txtuser);
        final EditText txtPass = (EditText) findViewById(R.id.txtpass);
        txtContact = (TextView) findViewById(R.id.textView9);

        txtContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity = new Intent(page_1.this,Contact.class);
                startActivity(activity);
            }
        });

        // btnLogin
        final Button btnLogin = (Button) findViewById(R.id.btn_login);
        // Perform action on click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String url = "https://peawarinubon.000webhostapp.com/select.php";
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("strUser", txtUser.getText().toString()));
                params.add(new BasicNameValuePair("strPass", txtPass.getText().toString()));


                String resultServer  = getHttpPost(url,params);

                /*** Default Value ***/
                String strStatusID = "0";
                String strMemberID = "0";
                String strFName = null;
                String strLName = null;
                String strError = "Unknow Status!";

                JSONObject c;
                try {
                    c = new JSONObject(resultServer);
                    strStatusID = c.getString("StatusID");
                    strMemberID = c.getString("user_id");
                    strError = c.getString("Error");
                    strFName = c.getString("user_fname");
                    strLName = c.getString("user_lname");

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // Prepare Login
                if(strStatusID.equals("0"))
                {
                    // Dialog
                    ad.setTitle("Error! ");
                    ad.setIcon(android.R.drawable.btn_star_big_on);
                    ad.setPositiveButton("Close", null);
                    ad.setMessage(strError);
                    ad.show();
                    txtUser.setText("");
                    txtPass.setText("");
                }
                else
                {
                    Toast.makeText(page_1.this, "Login OK", Toast.LENGTH_SHORT).show();
                    Intent newActivity = new Intent(page_1.this,stp_1.class);
                    newActivity.putExtra("MemberID", strMemberID);
                    newActivity.putExtra("MemberFName", strFName);
                    newActivity.putExtra("MemberLName", strLName);
                    startActivity(newActivity);
                }
            }
        });

    }


    public String getHttpPost(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Status OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }


}
