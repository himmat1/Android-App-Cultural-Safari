package com.itp341.singh.himmat.finalprojhimmatsinghitp341;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by himmatsingh on 5/1/17.
 */

public class Messages implements Serializable {
    public static ArrayList<ImageView> images = new ArrayList<>();
    Context context;

    public Messages(Context context){
        this.context = context;
        Drawable drawable = context.getResources().getDrawable(R.drawable.sikhgw);
        ImageView image = new ImageView(context);
        image.setImageResource(R.drawable.sikhgw);

        images.add(image);
    }
}
