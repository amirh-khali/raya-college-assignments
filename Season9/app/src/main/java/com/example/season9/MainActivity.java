package com.example.season9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.frag_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager vv = findViewById(R.id.frags_place);
                TabLayout tt = findViewById(R.id.frags_tabs);
                tt.setupWithViewPager(vv);
                vv.setAdapter(new myAdapter(getSupportFragmentManager()));

            }
        });
    }
}
