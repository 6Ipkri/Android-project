package com.example.boony.peawarinchamrap;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class stp_3 extends AppCompatActivity {

   protected ListView itemList;
    protected TextView textView40;
    protected ArrayList<String> fromstp2 = new ArrayList<String>();
    protected ArrayList<Float> pricearray = new ArrayList<Float>();
    protected ArrayList<String> laborarrayfromSTP2 = new ArrayList<String>();
    protected ArrayList<String> idSUBrarrayfromSTP2 = new ArrayList<String>();
    protected ArrayList<String> vatSUBarrayfromSTP2 = new ArrayList<String>();
    protected ArrayList<String> listitemTOTAL = new ArrayList<String>();
    protected ArrayList<String> listitemTOTALtostp4 = new ArrayList<String>();
    protected ArrayList<Float> floatvatSUBarrayfromSTP2 = new ArrayList<Float>();
    protected ArrayList<Float> floatitemCountfromSTP2 = new ArrayList<Float>();
    protected ArrayList<Float> floatdividedTotal = new ArrayList<Float>();
    protected ArrayList<String> itemCountfromstp2 = new ArrayList<String>();
    protected ArrayList<String> sentpricetostp4 = new ArrayList<String>();
    protected ArrayList<String> sentCountItemtostp4 = new ArrayList<String>();
    protected EditText timeWork;
    protected TextView textView46;
    protected float payList ,vatcal , paysenttosta4 , payLisyFF;
    protected String objectList;
    protected ImageView staff_sig;
    protected ImageView user_sig;
    protected String[] arrnameITEM;
    protected Float[] arrPRICE;
    protected String peano3, customname3, customadd3,formatP;

    private static final String TAG = "stp_3";
    Intent myIntent3;
    TextView textView49;
    String please , memberID , fnln , vat7percent;
    Float savepaysenttostp4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stp_3);

        textView49 = findViewById(R.id.textView49);
        timeWork = findViewById(R.id.timeWork);
        textView46 = findViewById(R.id.textView46);

        fromstp2 = getIntent().getStringArrayListExtra("arraysh2");
        peano3 = getIntent().getStringExtra("peano2");
        customname3 = getIntent().getStringExtra("customname2");
        customadd3 = getIntent().getStringExtra("customadd2");
        itemCountfromstp2 = getIntent().getStringArrayListExtra("getCountItem");
        laborarrayfromSTP2 = getIntent().getStringArrayListExtra("getSubnamedata");
        idSUBrarrayfromSTP2 = getIntent().getStringArrayListExtra("getSubID");
        vatSUBarrayfromSTP2 = getIntent().getStringArrayListExtra("getPVAT");
        memberID = getIntent().getStringExtra("memberID2");
        fnln = getIntent().getStringExtra("memberFNLN2");
        paysenttosta4 = 0;

        isStoragePermissionGranted();

        float vat;
        for(String selection : vatSUBarrayfromSTP2){
            vat = Float.parseFloat(selection);
            floatvatSUBarrayfromSTP2.add(vat);
        }

        for(String selection : itemCountfromstp2){
            vat = Float.parseFloat(selection);
            floatitemCountfromSTP2.add(vat);
        }

        for (int i = 0; i < vatSUBarrayfromSTP2.size(); i++) {
            floatdividedTotal.add(i, floatvatSUBarrayfromSTP2.get(i)*floatitemCountfromSTP2.get(i));
        }


        totalList();
        priceList();

       arrnameITEM =  listitemTOTAL.toArray(new String[listitemTOTAL.size()]);
       arrPRICE = pricearray.toArray(new Float[pricearray.size()]);

        ImageView img = (ImageView) findViewById(R.id.imagenextstp3);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your code here
                if(paysenttosta4 != 0) {
                    myIntent3 = new Intent(stp_3.this, stp_4.class);
                    myIntent3.putExtra("peano4", peano3);
                    myIntent3.putExtra("customname4", customname3);
                    myIntent3.putExtra("customadd4", customadd3);
                    myIntent3.putStringArrayListExtra("totallist4", listitemTOTALtostp4);
                    myIntent3.putStringArrayListExtra("Pricelist", sentpricetostp4);
                    myIntent3.putStringArrayListExtra("Countlist", sentCountItemtostp4);
                    myIntent3.putExtra("paytofin", please);
                    myIntent3.putExtra("memberID3" , memberID);
                    myIntent3.putExtra("memberFNLN" , fnln);
                    myIntent3.putExtra("vat7percent" , vat7percent);
                    startActivity(myIntent3);
                }
                else Toast.makeText(getApplicationContext(), "กรุณาใส่ระยะเวลาในการทำงาน", Toast.LENGTH_SHORT).show();

            }
        });


        Button calc = (Button) findViewById(R.id.calc);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                payList = payLisyFF;
                paysenttosta4 = savepaysenttostp4;

                int calculator = Integer.parseInt(String.valueOf(timeWork.getText()));
                int calforhigh = 0;
                int calforlow = 0;
                int totalpay = 0;

                for(String pricelist : fromstp2) {

                    if (pricelist.equals("ค่าแรงต้านไฟฟ้าแรงสูง")) {
                        payList = payList-60;
                        calforhigh = calculator;
                        calforhigh = calforhigh * 2;

                    }
                    if (pricelist.equals("ค่าแรงต้านไฟฟ้าแรงต่ำ")) {
                        payList = payList-30;
                        calforlow = calculator;
                    }
                }

                totalpay += payList + calforhigh + calforlow;

                textView46.setText(totalpay + "\t");

                String pattern = "0.00";
                DecimalFormat df = new DecimalFormat(pattern);

                paysenttosta4 = paysenttosta4 + totalpay;

                formatP = df.format(paysenttosta4);
                vat7percent = paysenttosta4 + "";
                savepaysenttostp4 = paysenttosta4 - totalpay;
                please = formatP + "";

            }
        });

        staff_sig = findViewById(R.id.staff_signature);
        staff_sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignature = new Intent(getApplicationContext(),signature_page.class);
                startActivityForResult(intentSignature,1000);
            }
        });

        user_sig = (ImageView) findViewById(R.id.user_signature);
        user_sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignature = new Intent(getApplicationContext(),signature_page.class);
                startActivityForResult(intentSignature,5000);

            }
        });

        if(arrPRICE != null){
        CustomAdapterstp3 adapter = new CustomAdapterstp3(getApplicationContext(), arrnameITEM,arrPRICE);
        ListView listView = (ListView)findViewById(R.id.lisviewItem123);
        listView.setAdapter(adapter);}
        else Toast.makeText(getApplicationContext(), arrPRICE[7].toString(), Toast.LENGTH_SHORT).show();

        String pattern = "0.00";
        DecimalFormat df = new DecimalFormat(pattern);
        String format = df.format(vatcal);
        textView49.setText(format + "");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK) {

                Bitmap bitmapSigpage = BitmapHelper.getInstance().getBitmap();

                Matrix matrix = new Matrix();
                matrix.postScale(5, 1);

                Bitmap bitmapScaled = Bitmap.createBitmap(
                        bitmapSigpage,
                        0, 0,
                        bitmapSigpage.getWidth(), bitmapSigpage.getHeight(),
                        matrix, true);

                saveStaffImage(bitmapScaled);
                staff_sig.setImageBitmap(bitmapScaled);
            }
        }
       else if(requestCode == 5000){
            if(resultCode == Activity.RESULT_OK) {

                Bitmap bitmapSigpage = BitmapHelper.getInstance().getBitmap();

                Matrix matrix = new Matrix();
                matrix.postScale(5, 1);

                Bitmap bitmapScaled = Bitmap.createBitmap(
                        bitmapSigpage,
                        0, 0,
                        bitmapSigpage.getWidth(), bitmapSigpage.getHeight(),
                        matrix, true);

                saveUserImage(bitmapScaled);
                user_sig.setImageBitmap(bitmapScaled);
            }
        }
    }

    private void saveUserImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/Pictures");
        myDir.mkdirs();

        String fname = "users_sig" + ".jpg";

        File file = new File(myDir, fname);
        if (file.exists()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveStaffImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/Pictures");
        myDir.mkdirs();

        String fname = "staffs_sig" + ".jpg";

        File file = new File(myDir, fname);
        if (file.exists()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void totalList() {

         objectList = " ";
         int i = 0;
        for(String selections : fromstp2){
            if(selections.equals("ค่าปลดสับอุปกรณ์ตัดตอนแรงสูง")||selections.equals("ค่าตรวจสอบแก้ไขไฟฟ้าแรงสูง")||selections.equals("ค่าตรวจสอบแก้ไขไฟฟ้าแรงต่ำ")||selections.equals("ค่าแรงต้านไฟฟ้าแรงสูง")||selections.equals("ค่าแรงต้านไฟฟ้าแรงต่ำ")){
                listitemTOTAL.add(selections);
                listitemTOTALtostp4.add(selections);
          // objectList = objectList + selections + "\n";
            }
        }
        //objectList = objectList + "\n";

        for(String selections : laborarrayfromSTP2){
            objectList = " ";
            objectList = objectList + selections + "\n"+"รหัส : " + idSUBrarrayfromSTP2.get(i) +"\n"+ "ราคา(ชิ้น) : "+vatSUBarrayfromSTP2.get(i) + "\n" + "จำนวน : " + itemCountfromstp2.get(i) + "ชิ้น";
            sentpricetostp4.add(floatdividedTotal.get(i) + " ");
            sentCountItemtostp4.add(itemCountfromstp2.get(i)+" ");
            listitemTOTAL.add(objectList);
            listitemTOTALtostp4.add(selections);
            i++;
        }
    }

    public void priceList(){

        for(String pricelist : fromstp2){
            if(pricelist.equals("ค่าปลดสับอุปกรณ์ตัดตอนแรงสูง")){
                pricearray.add((float) 300);
            }
            else if (pricelist.equals("ค่าตรวจสอบแก้ไขไฟฟ้าแรงสูง")){
                pricearray.add((float) 150);

            }
            else if (pricelist.equals("ค่าตรวจสอบแก้ไขไฟฟ้าแรงต่ำ")){
                pricearray.add((float) 10);
            }
            else if (pricelist.equals("ค่าแรงต้านไฟฟ้าแรงสูง")){
                pricearray.add((float) 60);
            }
            else if (pricelist.equals("ค่าแรงต้านไฟฟ้าแรงต่ำ")){
                pricearray.add((float) 30);
            }

        }

        pricearray.addAll(floatdividedTotal);


        payList = 0;
        vatcal = 0;
        String priceshow = "";

        for(Float selections : floatdividedTotal){
            vatcal = vatcal + selections;
        }

        for(Float selections : pricearray){
            if(selections != 0){
                if(selections != 60||selections != 30){
                    payList = payList + selections;
                }
                priceshow = priceshow + selections + "\t บาท"+"\n";}
        }

        String pattern = "0.00";
        DecimalFormat df = new DecimalFormat(pattern);




        payList = payList-vatcal;
        payLisyFF = payList;
        paysenttosta4 = vatcal;
        savepaysenttostp4 = vatcal;

//        textView40.setText(priceshow);
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(stp_3.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);

        }
    }

}
