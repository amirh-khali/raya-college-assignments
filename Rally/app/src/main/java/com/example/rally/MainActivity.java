package com.example.rally;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    static ArrayList<Car> cars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout playground = findViewById(R.id.playground);
        final Context context = this;

        for (int i = 0; i < 5; ++i) {
            cars.add(new Car(cars, playground, context, cars.size()));
        }

    }

    public void click(View view) {
        final Handler t = new Handler();
        Log.d("AmirH", "1");
        t.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("AmirH", "2");
                for (int i = 0; i < cars.size(); ++i) {
                    if (!cars.get(i).move()) {
                        Toast.makeText(getApplicationContext(),"Car " + (i + 1) + " Win",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                t.postDelayed(this, 50);
            }
        }, 5000);
    }
}
