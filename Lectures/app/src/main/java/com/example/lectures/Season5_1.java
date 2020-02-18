//package com.example.lectures;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.graphics.drawable.AnimationDrawable;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//public class Season5_1 extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.season5_1);
//        initial();
//    }
//
//    static private Car c1;
//    static private Car c2;
//
//    public void initial() {
//        c1 = new Car(100.0, "Red", 0.0, 100.0);
//        c2 = new Car(100.0, "Red", 0.0, 100.0);
//        display();
//    }
//
//    public void pedal_1(View view) {
//        c1.drive();
//        display();
//    }
//    public void pedal_2(View view) {
//        c2.drive();
//        display();
//    }
//
//    private void display() {
//        TextView gas_1 = findViewById(R.id.gas_1);
//        TextView gas_2 = findViewById(R.id.gas_2);
//        TextView distance_1 = findViewById(R.id.distance_1);
//        TextView distance_2 = findViewById(R.id.distance_2);;
//        gas_1.setText("" + c1.gas);
//        gas_2.setText("" + c2.gas);
//        distance_1.setText("" + c1.distance);
//        distance_2.setText("" + c2.distance);
//    }
//
//}
//
//class Car {
//
//    public Double gas;
//    public String color;
//    public Double distance;
//    public Double topSpeed;
//
//    public Car (double gas, String color, double distance, double topSpeed) {
//        this.gas = gas;
//        this.color = color;
//        this.distance = distance;
//        this.topSpeed = topSpeed;
//    }
//
//    public void drive () {
//        this.distance++;
//        this.gas -= .5;
//    }
//
//
//}