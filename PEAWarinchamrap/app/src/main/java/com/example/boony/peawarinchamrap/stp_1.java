package com.example.boony.peawarinchamrap;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class stp_1 extends AppCompatActivity {

    protected CheckBox checkBox ;
    protected CheckBox checkBox2 ;
    protected CheckBox checkBox3;
    protected CheckBox checkBox5;
    protected CheckBox checkBox6;
    protected CheckBox checkBox7;
    protected CheckBox checkBox8;
    protected CheckBox checkBox9;
    protected ArrayList<String> selection = new ArrayList<String>();
    protected ArrayList<String> address = new ArrayList<String>();
    protected EditText txtID, txtName, txtPEA, txtIDp, txtAdd;
    protected String memberID, memberFName, memberLName;
    protected Button btnSearch;
    protected String peaNo = null;
    protected String customID = null;
    protected String customAdd = null;
    protected String customName = null;
    String addname ,adddress;
    Intent myIntent;
    ArrayList<String> getSubname = new ArrayList<>();
    ArrayList<String> getSubID = new ArrayList<>();
    ArrayList<String> getPriceVAT = new ArrayList<>();
    String fnln;

    private Button mButtonDialog;

    ArrayList<Integer> mMultiSelected;
    final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> MyPriceVat = new ArrayList<HashMap<String, String>>();
    String[] strArr;
    String[] arrSubID;
    String[] arrPriceVAT;

    String nameS,addS;
    ArrayList<HashMap<String, String>> MySubid = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_stp_1);

        mMultiSelected = new ArrayList<Integer>();



        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox5 = findViewById(R.id.checkBox5);
        checkBox6 = findViewById(R.id.checkBox6);
        checkBox7 = findViewById(R.id.checkBox7);
        checkBox8= findViewById(R.id.checkBox8);
        checkBox9= findViewById(R.id.checkBox9);
        txtID = (EditText) findViewById(R.id.editText6);
        txtName = (EditText) findViewById(R.id.editText7);
        txtPEA = (EditText) findViewById(R.id.editText3);
        txtIDp = (EditText) findViewById(R.id.editText4) ;
        txtAdd = (EditText) findViewById(R.id.editText5);
        btnSearch = (Button) findViewById(R.id.button);
        memberID = getIntent().getStringExtra("MemberID");
        memberFName = getIntent().getStringExtra("MemberFName");
        memberLName = getIntent().getStringExtra("MemberLName");


        fnln = memberFName + "\t" + memberLName;

        txtID.setText(memberID);
        txtName.setText(memberFName+ "\t" + memberLName);

        if(memberFName == null){
            memberID = getIntent().getStringExtra("memberIDfinal" );
            memberFName = getIntent().getStringExtra("memberFNLNfinal");
            txtID.setText(memberID);
            txtName.setText(memberFName);
        }

        ImageView img = (ImageView) findViewById(R.id.imagenextbat);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your code here
                myIntent = new Intent(stp_1.this, stp_2.class);
                myIntent.putStringArrayListExtra("arraysh",selection);
                myIntent.putStringArrayListExtra("getSubnamedata" , getSubname);
                myIntent.putStringArrayListExtra("getSubID" , getSubID);
                myIntent.putExtra("PEA_NO", peaNo);
                myIntent.putExtra("CUSTOM_NAME", nameS);
                myIntent.putExtra("CUSTOM_ADD", addS);
                myIntent.putStringArrayListExtra("getPriceVAT", getPriceVAT);
                myIntent.putExtra("memberID" , memberID);
                myIntent.putExtra("memberFNLF" , fnln);
                startActivity(myIntent);

            }
        });

        getJSONdata();
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSubNamedata();
            }
        });
        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSubNamedata2();
            }
        });
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSubNamedata3();
            }
        });

        String url = "http://peawarinubon.000webhostapp.com/get_customer.php";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("strpeano", txtPEA.getText().toString()));

        final ArrayList<String> peaList = new ArrayList<String>();
        final ArrayList<String> idList = new ArrayList<String>();
        final ArrayList<String> nameList = new ArrayList<String>();
        final ArrayList<String> addList = new ArrayList<String>();

        try {

            JSONArray data = new JSONArray(getJSON(url,params));


            for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);
                peaNo = c.getString("pea_no");
                customID = c.getString("cus_cano");
                customName = c.getString("cus_name");
                customAdd = c.getString("cus_address");
                peaList.add(peaNo);
                idList.add(customID);
                nameList.add(customName);
                addList.add(customAdd);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String[] peaStr = new String[peaList.size()];
        String[] idStr = new String[idList.size()];
        String[] nameStr = new String[nameList.size()];
        String[] addStr = new String[addList.size()];
        peaStr = peaList.toArray(peaStr);
        idStr = idList.toArray(idStr);
        nameStr = nameList.toArray(nameStr);
        addStr = addList.toArray(addStr);

        final String[] finalPeaStr = peaStr;
        final String[] finalIdStr = idStr;
        final String[] finalNameStr = nameStr;
        final String[] finalAddStr = addStr;
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < finalPeaStr.length ; i++ ) {

                    if (finalPeaStr[i].equals(txtPEA.getText().toString())) {
                        txtIDp.setText(finalIdStr[i].toString());
                        txtAdd.setText(finalNameStr[i].toString() + ", " + finalAddStr[i].toString());
                        addname = finalNameStr[i].toString();
                        adddress = finalAddStr[i].toString();
                        break;
                    } else {
                        adddress = " ";
                        addname = " ";
                        txtIDp.setText("");
                        txtAdd.setText("");
                    }
                }

                address.add(0,addname);
                address.add(1,adddress);
                nameS = address.get(0).toString();
                addS = address.get(1).toString();

            }
        });

    }

    public  void selecItem (View view){

        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.checkBox:
                if(checked){
                    selection.add(checkBox.getText().toString());}
                else {
                    selection.remove(checkBox.getText().toString());
                }

                break;
            case R.id.checkBox2:
                if(checked){
                    selection.add(checkBox2.getText().toString());}
                else {
                    selection.remove(checkBox2.getText().toString());
                }
                break;
            case R.id.checkBox3:
                if(checked){
                    selection.add(checkBox3.getText().toString());}
                else {
                    selection.remove(checkBox3.getText().toString());
                }
                break;
            case R.id.checkBox5:
                if(checked){
                    selection.add("ค่าปลดสับอุปกรณ์ตัดตอนแรงสูง");
                    AlertDialog.Builder builder = new AlertDialog.Builder(stp_1.this);
                    builder.setMessage("ค่าปลดสับอุปกรณ์ตัดตอนแรงสูง\nราคา 300 บาท");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }
                else {
                    selection.remove("ค่าปลดสับอุปกรณ์ตัดตอนแรงสูง");
                }
                break;
            case R.id.checkBox6:
                if(checked){
                    selection.add("ค่าตรวจสอบแก้ไขไฟฟ้าแรงสูง");
                    AlertDialog.Builder builder = new AlertDialog.Builder(stp_1.this);
                    builder.setMessage("ค่าตรวจสอบแก้ไขไฟฟ้าแรงสูง\nราคา 150 บาท");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }
                else {
                    selection.remove("ค่าตรวจสอบแก้ไขไฟฟ้าแรงสูง");
                }
                break;
            case R.id.checkBox7:
                if(checked){
                    selection.add("ค่าตรวจสอบแก้ไขไฟฟ้าแรงต่ำ");
                    AlertDialog.Builder builder = new AlertDialog.Builder(stp_1.this);
                    builder.setMessage("ค่าตรวจสอบแก้ไขไฟฟ้าแรงต่ำ\nราคา 10 บาท");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }
                else {
                    selection.remove("ค่าตรวจสอบแก้ไขไฟฟ้าแรงต่ำ");
                }
                break;
            case R.id.checkBox8:
                if(checked){
                    selection.add(checkBox8.getText().toString());
                    AlertDialog.Builder builder = new AlertDialog.Builder(stp_1.this);
                    builder.setMessage("ค่าแรงต้านไฟฟ้าแรงสูง\nราคา 60 บาท / 30 นาที");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }
                else {
                    selection.remove(checkBox8.getText().toString());
                }
                break;
            case R.id.checkBox9:
                if(checked){
                    selection.add(checkBox9.getText().toString());
                    AlertDialog.Builder builder = new AlertDialog.Builder(stp_1.this);
                    builder.setMessage("ค่าแรงต้านไฟฟ้าแรงต่ำ\nราคา 30 บาท / 30 นาที");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }
                else {
                    selection.remove(checkBox9.getText().toString());
                }
                break;

        }

    }
    public void getJSONdata (){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String url = "https://peawarinubon.000webhostapp.com/iap_supplies.php";

        List<NameValuePair> params01 = new ArrayList<NameValuePair>();
        try {
            JSONArray data = new JSONArray(getJSON(url,params01));


            HashMap<String, String> map;
            HashMap<String, String> map2;
            HashMap<String, String> map3;



            for(int i = 0; i < data.length(); i++){
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();
                map2 = new HashMap<String, String>();
                map3 = new HashMap<String, String>();

                map3.put("price_vat", c.getString("price_vat"));
                map2.put("sup_id", c.getString("sup_id"));
                map.put("sup_name", c.getString("sup_name"));

                MyArrList.add(map);
                MySubid.add(map2);
                MyPriceVat.add(map3);

            }


            strArr = new String[MyArrList.size()];
            int i = 0;
            for (HashMap<String, String> hash : MyArrList) {
                for (String current : hash.values()) {
                    strArr[i] = current;
                    i++;
                }
            }

            arrSubID = new String[MySubid.size()];
            int j = 0;
            for (HashMap<String, String> hash2 : MySubid) {
                for (String current : hash2.values()) {
                    arrSubID[j] = current;
                    j++;

                }
            }

            arrPriceVAT = new String[MyPriceVat.size()];
            int k = 0;
            for (HashMap<String, String> hash2 : MyPriceVat) {
                for (String current : hash2.values()) {
                    arrPriceVAT[k] = current;
                    k++;

                }
            }


            SimpleAdapter simpleAdapterData;
            simpleAdapterData = new SimpleAdapter(stp_1.this, MyArrList, R.layout.acivity_column,
                    new String[] {"sup_name"}, new int[] {R.id.articleTitle});
            //listViewMovies.setAdapter(simpleAdapterData);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public String getJSON(String url,List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader_buffer = new BufferedReader
                        (new InputStreamReader(content));

                String line;
                while ((line = reader_buffer.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download file..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
    public void getSubNamedata(){
        mMultiSelected = new ArrayList<Integer>();

        AlertDialog.Builder builder =
                new AlertDialog.Builder(stp_1.this);
        builder.setTitle("เลือกรายการ");
        builder.setMultiChoiceItems(strArr, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    mMultiSelected.add(which);
                } else if (mMultiSelected.contains(which)) {
                    mMultiSelected.remove(Integer.valueOf(which));
                }
            }
        });

        builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // เซฟค่าลง database หรือ SharedPreferences.
                String buffer1 = " ";
                for (Integer team : mMultiSelected) {

                    buffer1 =  buffer1 +" " + strArr[team];
                    getSubname.add(strArr[team]);
                    getSubID.add(arrSubID[team]);
                    getPriceVAT.add(arrPriceVAT[team]);
                }
                Toast.makeText(getApplicationContext(), "คุณเลือก" +
                        buffer1, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                checkBox.setChecked(false);
            }
        });


        // builder.setNegativeButton("ไม่ชอบซักทีม"+buffer1, null);
        builder.create();

        // สุดท้ายอย่าลืม show() ด้วย
        builder.show();
    }
    public void getSubNamedata2(){
        mMultiSelected = new ArrayList<Integer>();

        AlertDialog.Builder builder =
                new AlertDialog.Builder(stp_1.this);
        builder.setTitle("เลือกรายการ");
        builder.setMultiChoiceItems(strArr, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    mMultiSelected.add(which);
                } else if (mMultiSelected.contains(which)) {
                    mMultiSelected.remove(Integer.valueOf(which));
                }
            }
        });

        builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // เซฟค่าลง database หรือ SharedPreferences.
                String buffer1 = " ";
                for (Integer team : mMultiSelected) {

                    buffer1 =  buffer1 +" " + strArr[team];
                    getSubname.add(strArr[team]);
                    getSubID.add(arrSubID[team]);
                    getPriceVAT.add(arrPriceVAT[team]);
                }
                Toast.makeText(getApplicationContext(), "คุณเลือก" +
                        buffer1, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                checkBox2.setChecked(false);
            }
        });

        // builder.setNegativeButton("ไม่ชอบซักทีม"+buffer1, null);
        builder.create();

        // สุดท้ายอย่าลืม show() ด้วย
        builder.show();
    }
    public void getSubNamedata3(){
        mMultiSelected = new ArrayList<Integer>();

        AlertDialog.Builder builder =
                new AlertDialog.Builder(stp_1.this);
        builder.setTitle("เลือกรายการ");
        builder.setMultiChoiceItems(strArr, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    mMultiSelected.add(which);
                } else if (mMultiSelected.contains(which)) {
                    mMultiSelected.remove(Integer.valueOf(which));
                }
            }
        });

        builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // เซฟค่าลง database หรือ SharedPreferences.
                String buffer1 = " ";
                for (Integer team : mMultiSelected) {

                    buffer1 =  buffer1 +" " + strArr[team];
                    getSubname.add(strArr[team]);
                    getSubID.add(arrSubID[team]);
                    getPriceVAT.add(arrPriceVAT[team]);
                }
                Toast.makeText(getApplicationContext(), "คุณเลือก" +
                        buffer1, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                checkBox3.setChecked(false);
            }
        });

        // builder.setNegativeButton("ไม่ชอบซักทีม"+buffer1, null);
        builder.create();

        // สุดท้ายอย่าลืม show() ด้วย
        builder.show();
    }

}