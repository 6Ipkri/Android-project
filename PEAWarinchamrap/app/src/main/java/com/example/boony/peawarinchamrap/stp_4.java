package com.example.boony.peawarinchamrap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class stp_4 extends AppCompatActivity {

    Bitmap bitmap;
    private static final String TAG = "stp_4";
    protected String peanofin, customnamefin, customaddfin;
    protected ImageView user_signature;
    protected ImageView staff_signature;
    ArrayList<String> listitemTOTAL04 = new ArrayList<String>();
    protected TextView txtPEA, txtName, txtAdd , txtobjlist , txtprice , txtcount , txt76,txt78 , txt79 ,txt80;
    ImageView demo_image;
    String objlist , priceList;
    ArrayList<String> priceList04 = new ArrayList<String>();
    ArrayList<String> totalCOUNT04 = new ArrayList<String>();
    String getpaytofin, countobj;
    Button saveBtn;
    Intent saveScreen;
    String op79, gettxt79, gettxt80, op80 , memberID , fnln , vat7percentsromstp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stp_4);

        TextView dateText = (TextView) findViewById(R.id.dateText);
        saveBtn = (Button) findViewById(R.id.savebuttonstp4);
        demo_image = (ImageView) findViewById(R.id.imageView26);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd / MM / yyyy");
        String date = simpleDateFormat.format(new Date());
        dateText.setText(date);
        peanofin = getIntent().getStringExtra("peano4");
        customnamefin = getIntent().getStringExtra("customname4");
        customaddfin = getIntent().getStringExtra("customadd4");
        listitemTOTAL04 = getIntent().getStringArrayListExtra("totallist4"); // total items
        priceList04 = getIntent().getStringArrayListExtra("Pricelist"); // price
        totalCOUNT04 = getIntent().getStringArrayListExtra("Countlist"); // count
        getpaytofin = getIntent().getStringExtra("paytofin");
        memberID = getIntent().getStringExtra("memberID3");
        fnln = getIntent().getStringExtra("memberFNLN");
        vat7percentsromstp3 = getIntent().getStringExtra("vat7percent");

        txtPEA = (TextView) findViewById(R.id.textView38);
        txtName = (TextView) findViewById(R.id.textView90);
        txtAdd = (TextView) findViewById(R.id.textView91);
        txtobjlist = (TextView) findViewById(R.id.textView81);
        txtprice = (TextView) findViewById(R.id.textView83);
        txtcount = (TextView)findViewById(R.id.textView82);
        txt76 = (TextView)findViewById(R.id.textView76);
        txt78 = (TextView)findViewById(R.id.textView78);
        txt79 = (TextView)findViewById(R.id.textView79);
        txt80 = (TextView)findViewById(R.id.textView80);

        user_signature = (ImageView) findViewById(R.id.signatureuser);

        String photoUserPath = Environment.getExternalStorageDirectory() + "/Pictures" + "/users_sig.jpg";
        Bitmap bitmapU = BitmapFactory.decodeFile(photoUserPath);
        user_signature.setImageBitmap(bitmapU);

        String pattern = "0.00";
        DecimalFormat df = new DecimalFormat(pattern);


        objlist = "";
        for(String selections : listitemTOTAL04){
            objlist = objlist + selections +"\n" ;
        }
        int k = 0;
        countobj = "";
        for(int i = 0 ; i < listitemTOTAL04.size() ; i++){

            if(listitemTOTAL04.get(i).equals("ค่าปลดสับอุปกรณ์ตัดตอนแรงสูง")){
                countobj = countobj + "\n";
                k--;

            }
            else   if(listitemTOTAL04.get(i).equals("ค่าตรวจสอบแก้ไขไฟฟ้าแรงสูง")){
                countobj = countobj + "\n";
                k--;


            }
            else   if(listitemTOTAL04.get(i).equals("ค่าตรวจสอบแก้ไขไฟฟ้าแรงต่ำ")){
                countobj = countobj + "\n";
                k--;
            }
            else   if(listitemTOTAL04.get(i).equals("ค่าแรงต้านไฟฟ้าแรงสูง")){
                countobj = countobj + "\n";
                k--;
            }
            else   if(listitemTOTAL04.get(i).equals("ค่าแรงต้านไฟฟ้าแรงต่ำ")){
                countobj = countobj + "\n";
                k--;
            }

            else countobj = countobj + totalCOUNT04.get(k) +"\n" ;

            k++;

        }



        priceList = "";
        int j = 0;

        for(int i = 0 ; i < listitemTOTAL04.size() ; i++){

            if(listitemTOTAL04.get(i).equals("ค่าปลดสับอุปกรณ์ตัดตอนแรงสูง")){
                priceList = priceList + 300 + "\n";
                j--;

            }
            else   if(listitemTOTAL04.get(i).equals("ค่าตรวจสอบแก้ไขไฟฟ้าแรงสูง")){
                priceList = priceList + 150 + "\n";
                j--;


            }
            else   if(listitemTOTAL04.get(i).equals("ค่าตรวจสอบแก้ไขไฟฟ้าแรงต่ำ")){
                priceList = priceList + 10 + "\n";
                j--;
            }
            else   if(listitemTOTAL04.get(i).equals("ค่าแรงต้านไฟฟ้าแรงสูง")){
                priceList = priceList + 60 + "\n";
                j--;
            }
            else   if(listitemTOTAL04.get(i).equals("ค่าแรงต้านไฟฟ้าแรงต่ำ")){
                priceList = priceList + 30 + "\n";
                j--;
            }

            else priceList = priceList + priceList04.get(j) +"\n" ;

            j++;

        }

        txtPEA.setText(peanofin);
        txtName.setText(customnamefin);
        txtAdd.setText(customaddfin);
        txtobjlist.setText(objlist);
        txtprice.setText(priceList);
        txtcount.setText(countobj);
        txt76.setText(getpaytofin);
        txt78.setText(getpaytofin);

        Float parsepay, parsepay79, op792, op7980 ,vat7percentparseFloat;
        parsepay = Float.parseFloat(getpaytofin);
        vat7percentparseFloat = Float.parseFloat(vat7percentsromstp3);

        parsepay79 = ((vat7percentparseFloat*7)/100) ;


        op79 = df.format(parsepay79);
        op792 = Float.parseFloat(op79);
        op7980 = op792 + parsepay;
        op80 = df.format(op7980);


        txt79.setText(op79 + "");
        txt80.setText(op80 + " ");

        gettxt79 = txt79.getText().toString();
        gettxt80 = txt80.getText().toString();

        bitmap = ((BitmapDrawable)demo_image.getDrawable()).getBitmap();



        Button saveButton = (Button) findViewById(R.id.savebuttonstp4);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(stp_4.this, ReceiptScreen.class));


            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveScreen = new Intent(stp_4.this, ReceiptScreen.class);
                saveScreen.putExtra("peanofinal", peanofin);
                saveScreen.putExtra("customnamefinal", customnamefin);
                saveScreen.putExtra("customaddfinal", customaddfin);
                saveScreen.putExtra("objlists", objlist);
                saveScreen.putExtra("priceLists", priceList);
                saveScreen.putExtra("countobjs", countobj);
                saveScreen.putExtra("getpaytofins", getpaytofin);
                saveScreen.putExtra("parsepayy79", gettxt79);
                saveScreen.putExtra("parsepayy80", gettxt80);
                saveScreen.putExtra("memberID4" , memberID);
                saveScreen.putExtra("memberFNLN" , fnln);
                startActivity(saveScreen);
            }
        });

    }


}
