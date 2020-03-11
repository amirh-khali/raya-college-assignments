package com.example.season8;

import android.os.Bundle;
import android.util.Log;
import android.util.StateSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Colors extends Fragment {

    ArrayList<String> mTexts = new ArrayList<>();

    ArrayList<Integer> mImgs = new ArrayList<>();

    static String title = "Colors";

    public Colors() {
        // Required empty public constructor
    }

    public static CharSequence getTitle() {
        return title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.colors, container, false);

        createList();

        ListView listView=view.findViewById(R.id.colors_list_view);

        MyAdapter myAdapter = new MyAdapter(getContext(), mImgs, mTexts, "#50C80B", "#30A80B", R.raw.screaming);
        listView.setAdapter(myAdapter);

        return view;
    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.colors);
//
//        createList();
//
//        ListView listView=findViewById(R.id.colors_linear);
//
//        MyAdapter myAdapter = new MyAdapter(this, mTexts, "#50C80B", "#60D81B", R.raw.number_15);
//        listView.setAdapter(myAdapter);
//    }

    private void createList() {
        for (int i = 1; i <= 8; ++i) {
            switch (i) {
                case 1:
                    mImgs.add(R.drawable.color_white);
                    mTexts.add("white");
                    break;
                case 2:
                    mImgs.add(R.drawable.color_black);
                    mTexts.add("black");
                    break;
                case 3:
                    mImgs.add(R.drawable.color_brown);
                    mTexts.add("brown");
                    break;
                case 4:
                    mImgs.add(R.drawable.color_dusty_yellow);
                    mTexts.add("yellow");
                    break;
                case 5:
                    mImgs.add(R.drawable.color_gray);
                    mTexts.add("gray");
                    break;
                case 6:
                    mImgs.add(R.drawable.color_green);
                    mTexts.add("green");
                    break;
                case 7:
                    mImgs.add(R.drawable.color_mustard_yellow);
                    mTexts.add("mustard yellow");
                    break;
                case 8:
                    mImgs.add(R.drawable.color_red);
                    mTexts.add("red");
                    break;
            }
        }
    }
}
