package com.example.rally;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import java.sql.Statement;
import java.util.ArrayList;

public class Car {

    private RelativeLayout parent;

    private ImageView myImage;

    static int last = 0;

    Integer y = 0;
    Integer x;

    public Car(ArrayList<Car> cars, RelativeLayout playground, Context context, Integer id) {
        parent = playground;
        myImage = new ImageView(context);

        myImage.setId(id);

        myImage.setImageResource(R.drawable.ic_icons8_color_96);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        x = last;
        params.setMargins(y, last, 0, 0);

        last += 200;

        myImage.setLayoutParams(params);

        parent.addView(myImage);
    }

    public boolean move() {
        y += (int)(Math.random() * 10 + 1);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        if (myImage.getWidth() <= 160) {
            return false;
        }

        params.setMargins(y, x, 0, 0);

        myImage.setLayoutParams(params);
        return true;
    }

    public void destroy() {
        myImage.setVisibility(View.GONE);
    }
}
