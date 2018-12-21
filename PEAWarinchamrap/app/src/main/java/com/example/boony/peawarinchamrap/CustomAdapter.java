package com.example.boony.peawarinchamrap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context mContext;
    String[] strName;
	String[] strID;
	String[] strPriceVAT;
    int[] resId;
       
    public CustomAdapter(Context context, String[] strName, String[] strID, String[] strPriceVAT) {
        super();
        this.mContext = context;
        this.strName = strName;
        this.strID = strID;
		this.strPriceVAT = strPriceVAT;
        this.resId = resId;
    }

	public int getCount() {
		return strName.length;
	}

	public String[] getStrName() {
		return strName;
	}

	public Object getItem(int arg0) {
		return null;
	}

	public long getItemId(int arg0) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(
	    		Context.LAYOUT_INFLATER_SERVICE);
	    
		View row = mInflater.inflate(R.layout.listview_row, parent, false);
        
        TextView textView = (TextView)row.findViewById(R.id.textView1);
        textView.setText(strName[position]);

		TextView textView100 = (TextView)row.findViewById(R.id.textView100);
		textView100.setText(strID[position]);

		TextView textView102 = (TextView)row.findViewById(R.id.textView102);
		textView102.setText(strPriceVAT[position]);

		EditText editText = (EditText) row.findViewById(R.id.editText222);
		editText.setText("1");


       /*ImageView imageView = (ImageView)row.findViewById(R.id.imageView1);
        imageView.setBackgroundResource(resId[position]);*/
        
        return row;
	}
}

