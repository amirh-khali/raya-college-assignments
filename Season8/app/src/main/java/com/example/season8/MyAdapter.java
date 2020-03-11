package com.example.season8;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {

    Context mContext;
    ArrayList<String> myTexts;
    String mColor0;
    String mColor1;
    MediaPlayer mediaPlayer;
    ArrayList<Integer> myImgs;

    public MyAdapter(@NonNull Context context, ArrayList<Integer> imgs, ArrayList<String> texts, String color0, String color1, int mediaAdd) {
        super(context,0);
        mContext=context;
        myTexts=texts;
        myImgs = imgs;
        mColor0 = color0;
        mColor1 = color1;
        mediaPlayer = MediaPlayer.create(context, mediaAdd);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rootView = convertView;
        if (convertView == null) {
            rootView = LayoutInflater.from(mContext).inflate(R.layout.template, parent, false);
        }

        String color;
        if (position % 2 == 0) color = mColor0;
        else color = mColor1;

        rootView.findViewById(R.id.my_image).setBackground(getContext().getDrawable(myImgs.get(position)));
        ((Button)rootView.findViewById(R.id.my_button)).setText(myTexts.get(position));
        ((Button)rootView.findViewById(R.id.my_button)).setBackgroundColor(Color.parseColor(color));
        ((ImageButton)rootView.findViewById(R.id.my_sound)).setBackgroundColor(Color.parseColor(color));
        rootView.findViewById(R.id.my_sound).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        return rootView;
    }

    @Override
    public int getCount() {
        return myTexts.size();
    }
}
