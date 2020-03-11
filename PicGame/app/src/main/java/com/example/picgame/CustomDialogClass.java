package com.example.picgame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CustomDialogClass extends Dialog implements
        View.OnClickListener {

    private Context context;
    private Button confirm;
    private LinearLayout linearLayout;
    ArrayList<Pair<String, Integer>> scoreboard;

    public CustomDialogClass(Context context) {
        super(context);

        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_victory);
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(this);
//        ((MainActivity)context).backgroundSong.stop();
        linearLayout = findViewById(R.id.scoreboard);
        scoreboard = ResourceManager.getScore();
        Collections.sort(scoreboard, new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                if (o1.second > o2.second) {
                    return -1;
                } else if (o1.second == o2.second) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        for (int i = 0; i < scoreboard.size(); ++i) {
            TextView tv = new TextView(context);
            tv.setText((i + 1) + ". " + scoreboard.get(i).first + " " + scoreboard.get(i).second);
            tv.setTextColor(context.getColor(R.color.colorPrimary));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(params);
            linearLayout.addView(tv);
        }

        setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        ((MainActivity)context).initial();
        dismiss();
    }

}
