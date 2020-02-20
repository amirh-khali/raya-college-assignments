package com.example.season8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        Button nums = findViewById(R.id.numbers);
        nums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mass = new Intent(MainActivity.this,Numbers.class);
                startActivity(mass);
            }
        });

        Button colors = findViewById(R.id.colors);
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mass = new Intent(MainActivity.this,Colors.class);
                startActivity(mass);
            }
        });

        Button famMem = findViewById(R.id.family_members);
        famMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mass = new Intent(MainActivity.this,FamilyMembers.class);
                startActivity(mass);
            }
        });

        Button phrases = findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mass = new Intent(MainActivity.this,Phrases.class);
                startActivity(mass);
            }
        });
    }
}
