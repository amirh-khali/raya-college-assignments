package com.example.season8;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Numbers extends Fragment {

    ArrayList<String> mTexts = new ArrayList<>();
    ArrayList<Integer> mImgs = new ArrayList<>();


    static String title = "Numbers";


    public Numbers() {
        // Required empty public constructor
    }

    public static CharSequence getTitle() {
        return title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.numbers, container, false);

        createList();

        ListView listView = view.findViewById(R.id.numbers_list_view);

        MyAdapter myAdapter = new MyAdapter(getContext(), mImgs, mTexts, "#D81B60", "#B80B40", R.raw.screaming);
        listView.setAdapter(myAdapter);

        return view;
    }

    //    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.numbers);
//
//        createList();
//
//        ListView listView=findViewById(R.id.numbers_linear);
//
//        MyAdapter myAdapter = new MyAdapter(this, mTexts, "#D81B60", "#F83B80", R.raw.number_15);
//        listView.setAdapter(myAdapter);
//    }
//
    private void createList() {
        for (int i = 1; i <= 10; ++i) {
            mTexts.add("Number " + i);
            switch (i) {
                case 1:
                    mImgs.add(R.drawable.number_one);
                    break;
                case 2:
                    mImgs.add(R.drawable.number_two);
                    break;
                case 3:
                    mImgs.add(R.drawable.number_three);
                    break;
                case 4:
                    mImgs.add(R.drawable.number_four);
                    break;
                case 5:
                    mImgs.add(R.drawable.number_five);
                    break;
                case 6:
                    mImgs.add(R.drawable.number_six);
                    break;
                case 7:
                    mImgs.add(R.drawable.number_seven);
                    break;
                case 8:
                    mImgs.add(R.drawable.number_eight);
                    break;
                case 9:
                    mImgs.add(R.drawable.number_nine);
                    break;
                case 10:
                    mImgs.add(R.drawable.number_ten);
                    break;
            }
        }
    }
}
