package com.example.listenerpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
        final Handler t = new Handler();
        t.postDelayed(new Runnable() {
            @Override
            public void run() {
                display();
                t.postDelayed(this, 10);
            }
        }, 0);
    }

    static private Car c1;
    static private Car c2;

    public void initial() {

        c1 = new Car(10.0, "Red", 0.0, 100.0, findViewById(R.id.pedal_1));
        c2 = new Car(10.0, "Red", 0.0, 100.0, findViewById(R.id.pedal_2));
        OnLowFuelListener onLowFuelListener = new OnLowFuelListener() {
            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Fuel is low",Toast.LENGTH_SHORT).show();
            }
        };
        c1.setOnLowFuelListener(onLowFuelListener);
        c2.setOnLowFuelListener(onLowFuelListener);
        display();
    }

    private void display() {
        TextView gas_1 = findViewById(R.id.gas_1);
        TextView gas_2 = findViewById(R.id.gas_2);
        TextView distance_1 = findViewById(R.id.distance_1);
        TextView distance_2 = findViewById(R.id.distance_2);;
        gas_1.setText("" + c1.gas);
        gas_2.setText("" + c2.gas);
        distance_1.setText("" + c1.distance);
        distance_2.setText("" + c2.distance);
    }

}
