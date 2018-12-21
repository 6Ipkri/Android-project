package com.example.boony.peawarinchamrap;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class stp_2 extends AppCompatActivity{

    protected TextView textView35;
    protected ArrayList<String> subnamefromstp1 = new ArrayList<String>();
    protected ArrayList<String> labornamefromstp1 = new ArrayList<String>();
    protected String[] arrnamesub;
    protected String[] arrnameID;
    protected String[] arrPriceVAT;
    protected String peano, customname, customadd;
    Intent myIntent2;
    protected ArrayList<String> subIDfromstp1 = new ArrayList<String>();
    Button savestp2;
    ArrayList<String> testItem = new ArrayList<String>();
    protected ArrayList<String> priceVATfromstp1 = new ArrayList<String>();
    String memberID , fnln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stp_2);


        labornamefromstp1 = getIntent().getStringArrayListExtra("arraysh");
        subnamefromstp1 = getIntent().getStringArrayListExtra("getSubnamedata");
        subIDfromstp1 = getIntent().getStringArrayListExtra("getSubID");
        peano = getIntent().getStringExtra("PEA_NO");
        customname = getIntent().getStringExtra("CUSTOM_NAME");
        customadd = getIntent().getStringExtra("CUSTOM_ADD");
        priceVATfromstp1 = getIntent().getStringArrayListExtra("getPriceVAT");
        memberID = getIntent().getStringExtra("memberID");
        fnln = getIntent().getStringExtra("memberFNLF");

        textView35 = findViewById(R.id.textView35);
        textView35.setText(subnamefromstp1.toString());
        savestp2 = (Button)findViewById(R.id.button3);
        savestp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                testItem.clear();

                ListView lvName = (ListView) findViewById(R.id.listView01);
                View vi;
                EditText et;

                int listLength = lvName.getChildCount();
                String[] valueOfEditText = new String[listLength];
                for (int i = 0; i < listLength; i++)
                {
                    vi = lvName.getChildAt(i);

                    et = (EditText) vi.findViewById(R.id.editText222);
                    testItem.add(et.getText().toString());
                }

                Toast.makeText(getApplicationContext(), "บันทึกแล้ว", Toast.LENGTH_SHORT).show();

            }
        });

        arrnamesub = subnamefromstp1.toArray(new String[subIDfromstp1.size()]);
        arrnameID = subIDfromstp1.toArray(new String[subIDfromstp1.size()]);
        arrPriceVAT = priceVATfromstp1.toArray(new String[subIDfromstp1.size()]);

        ImageView img = (ImageView) findViewById(R.id.imagenextstp2);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(testItem.size()!= 0 || testItem.size() == subnamefromstp1.size()) {

                    myIntent2 = new Intent(stp_2.this, stp_3.class);
                    myIntent2.putStringArrayListExtra("arraysh2", labornamefromstp1);
                    myIntent2.putStringArrayListExtra("getSubnamedata", subnamefromstp1);
                    myIntent2.putStringArrayListExtra("getSubID", subIDfromstp1);
                    myIntent2.putStringArrayListExtra("getCountItem", testItem);
                    myIntent2.putStringArrayListExtra("getPVAT", priceVATfromstp1);
                    myIntent2.putExtra("peano2", peano);
                    myIntent2.putExtra("customname2", customname);
                    myIntent2.putExtra("customadd2", customadd);
                    myIntent2.putExtra("memberID2" , memberID);
                    myIntent2.putExtra("memberFNLN2" , fnln);
                    startActivity(myIntent2);
                }
                else Toast.makeText(getApplicationContext(), "กรุณากดบันทึก", Toast.LENGTH_SHORT).show();

            }
        });

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), arrnamesub,arrnameID,arrPriceVAT);
        ListView listView = (ListView)findViewById(R.id.listView01);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

            }
        });

    }

}