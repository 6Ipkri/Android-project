package com.example.boony.peawarinchamrap;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiptScreen extends AppCompatActivity {

    View screenshot;
    Bitmap bmScreenshot;
    private static final String TAG = "stp_4";
    ImageView user_ss;
    Handler handler;
    Runnable runnable;
    Long delay_time;
    Long time = 2000L;
    TextView dateText2, txtPeano, txtCusName, txtCusAdd, txt81, txt82, txt83,txt76,txt77,txt78,txt80,txt100;
    protected String peanosave, customnamesave, customaddsave, objlistt, priceListt, countobjt, getpaytofint, parsepayt79, parsepayt80;
    String memberID , fnln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_screen);

        handler = new Handler();
        screenshot = (View)findViewById(R.id.screenShotLayer);
        dateText2 = (TextView)findViewById(R.id.dateText2);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd / MM / yyyy");
        String date = simpleDateFormat.format(new Date());
        dateText2.setText(date);

        peanosave = getIntent().getStringExtra("peanofinal");
        customnamesave = getIntent().getStringExtra("customnamefinal");
        customaddsave = getIntent().getStringExtra("customaddfinal");
        objlistt = getIntent().getStringExtra("objlists");
        priceListt = getIntent().getStringExtra("priceLists");
        countobjt = getIntent().getStringExtra("countobjs");
        getpaytofint = getIntent().getStringExtra("getpaytofins");
        parsepayt79 = getIntent().getStringExtra("parsepayy79");
        parsepayt80 = getIntent().getStringExtra("parsepayy80");
        memberID = getIntent().getStringExtra("memberID4");
        fnln = getIntent().getStringExtra("memberFNLN");

        txtPeano = (TextView) findViewById(R.id.textView38);
        txtCusName = (TextView) findViewById(R.id.textView90);
        txtCusAdd = (TextView) findViewById(R.id.textView91);
        txt81 = (TextView) findViewById(R.id.textView81);
        txt82 = (TextView) findViewById(R.id.textView82);
        txt83 = (TextView) findViewById(R.id.textView83);
        txt76 = (TextView) findViewById(R.id.textView76);
        txt77 = (TextView) findViewById(R.id.textView77);
        txt78 = (TextView) findViewById(R.id.textView78);
        txt80 = (TextView) findViewById(R.id.textView80);
        txt100 = (TextView) findViewById(R.id.textView100);

        String pattern = "0.00";
        DecimalFormat df = new DecimalFormat(pattern);

        txtPeano.setText(peanosave);
        txtCusName.setText(customnamesave);
        txtCusAdd.setText(customaddsave);
        txt81.setText(objlistt);
        txt82.setText(countobjt);
        txt83.setText(priceListt);
        txt76.setText(getpaytofint);
        txt78.setText(getpaytofint);
        txt80.setText(parsepayt79);

        Float pp80 = Float.parseFloat(parsepayt80);
        String formm = df.format(pp80);
        txt100.setText(formm);

        user_ss = (ImageView) findViewById(R.id.user_s);

        String photoUserPath = Environment.getExternalStorageDirectory() + "/Pictures" + "/users_sig.jpg";
        Bitmap bitmapU = BitmapFactory.decodeFile(photoUserPath);
        user_ss.setImageBitmap(bitmapU);

        isStoragePermissionGranted();
        runnable = new Runnable() {
            public void run() {

                screenshot.setDrawingCacheEnabled(false);
                screenshot.setDrawingCacheEnabled(true);
                bmScreenshot = screenshot.getDrawingCache();

                saveImage();

                Intent nmyIntent = new Intent(ReceiptScreen.this, stp_1.class);
                nmyIntent.putExtra("memberIDfinal" , memberID);
                nmyIntent.putExtra("memberFNLNfinal" , fnln);
                startActivity(nmyIntent);
            }
        };

        }

    public void onResume() {
        super.onResume();
        delay_time = time;
        handler.postDelayed(runnable, delay_time);
        time = System.currentTimeMillis();
    }

    public void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
        time = delay_time - (System.currentTimeMillis() - time);
    }

        public void saveImage (){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = simpleDateFormat.format(new Date());

            ContentResolver cr = getContentResolver();
            String title = "IMG" + date;
            String description = "My bitmap created by Android-er";
            String savedURL = MediaStore.Images.Media
                    .insertImage(cr, bmScreenshot, title, description);

            Toast.makeText(ReceiptScreen.this,
                    "บันทึกแล้ว",
                    Toast.LENGTH_LONG).show();
        }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(ReceiptScreen.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);

        }
    }
    }



