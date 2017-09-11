package com.itp341.singh.himmat.finalprojhimmatsinghitp341.LazyList;

/**
 * Created by himmatsingh on 5/5/17.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itp341.singh.himmat.finalprojhimmatsinghitp341.R;

import java.util.ArrayList;

public class LazyAdapter extends BaseAdapter {

    private Activity activity;
    //private String[] data;
    private ArrayList<String>data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

    public LazyAdapter(Activity a, ArrayList<String> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.message_board_card, null);

        TextView text=(TextView)vi.findViewById(R.id.templeName);;
        ImageView image=(ImageView)vi.findViewById(R.id.imageTemple);
        text.setText("San Jose Sikh Temple");
        imageLoader.DisplayImage(data.get(position), image);
        return vi;
    }
}