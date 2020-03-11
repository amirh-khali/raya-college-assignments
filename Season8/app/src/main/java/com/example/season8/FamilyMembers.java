package com.example.season8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FamilyMembers extends Fragment {

    ArrayList<String> mTexts = new ArrayList<>();

    ArrayList<Integer> mImgs = new ArrayList<>();

    static String title = "FamilyMembers";


    public FamilyMembers() {
        // Required empty public constructor
    }

    public static CharSequence getTitle() {
        return title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.family_members, container, false);

        createList();

        ListView listView = view.findViewById(R.id.family_members_list_view);

        MyAdapter myAdapter = new MyAdapter(getContext(), mImgs, mTexts, "#1B60D8", "#0B40B8", R.raw.screaming);
        listView.setAdapter(myAdapter);

        return view;
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.family_members);
//
//        createList();
//
//        ListView listView=findViewById(R.id.family_members_linear);
//
//        MyAdapter myAdapter = new MyAdapter(this, mTexts, "#1B60D8", "#3B80F8", R.raw.number_15);
//        listView.setAdapter(myAdapter);
//    }

    private void createList() {
        for (int i = 1; i <= 10; ++i) {
            switch (i) {
                case 1:
                    mImgs.add(R.drawable.family_daughter);
                    mTexts.add("daughter");
                    break;
                case 2:
                    mImgs.add(R.drawable.family_father);
                    mTexts.add("father");
                    break;
                case 3:
                    mImgs.add(R.drawable.family_grandfather);
                    mTexts.add("grandma");
                    break;
                case 4:
                    mImgs.add(R.drawable.family_grandfather);
                    mTexts.add("grandfather");
                    break;
                case 5:
                    mImgs.add(R.drawable.family_mother);
                    mTexts.add("mother");
                    break;
                case 6:
                    mImgs.add(R.drawable.family_older_brother);
                    mTexts.add("older brother");
                    break;
                case 7:
                    mImgs.add(R.drawable.family_older_sister);
                    mTexts.add("older sis");
                    break;
                case 8:
                    mImgs.add(R.drawable.family_son);
                    mTexts.add("son");
                    break;
                case 9:
                    mImgs.add(R.drawable.family_younger_brother);
                    mTexts.add("younger brother");
                    break;
                case 10:
                    mImgs.add(R.drawable.family_younger_sister);
                    mTexts.add("younger sis");
                    break;
            }
        }
    }

}
