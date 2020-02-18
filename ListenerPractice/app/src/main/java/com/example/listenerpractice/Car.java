package com.example.listenerpractice;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


class Car {

    public Double gas;
    public String color;
    public Double distance;
    public Double topSpeed;
    public Button mButton;
    final Car mCar = this;

    private OnLowFuelListener mOnLowFuelListener;

    public Car (double gas, String color, double distance, double topSpeed, View view) {
        this.gas = gas;
        this.color = color;
        this.distance = distance;
        this.topSpeed = topSpeed;
        mButton = (Button) view;
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCar.drive();
            }
        });
    }

    public void setOnLowFuelListener (OnLowFuelListener onLowFuelListener) {
        mOnLowFuelListener = onLowFuelListener;
    }

    public void drive () {
        if (gas - .5 < 0)
            mOnLowFuelListener.onFinish();
        else {
            this.distance++;
            this.gas -= .5;
        }
    }
}

