package com.example.boony.peawarinchamrap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapterstp3 extends BaseAdapter {
    Context mContext;
    String[] strName;
	Float[] strID;
    int[] resId;

    public CustomAdapterstp3(Context context, String[] strName, Float[] strID) {
        super();
        this.mContext = context;
        this.strName = strName;
        this.strID = strID;
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
	    
		View row = mInflater.inflate(R.layout.listview_rowstp3, parent, false);
        
        TextView textView = (TextView)row.findViewById(R.id.textView1111);
        textView.setText(strName[position]);

		TextView textView100 = (TextView)row.findViewById(R.id.textView192);
		textView100.setText(strID[position] + " บาท ");


       /*ImageView imageView = (ImageView)row.findViewById(R.id.imageView1);
        imageView.setBackgroundResource(resId[position]);*/
        
        return row;
	}
}

