package com.example.lectures;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;

public class Season4_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season4_1);
        initial();
    }

    private static Integer num = 0;

    public void initial() {
        display();
        priceDisplay();
    }

    public void decrease(View view) {
        if (num > 0) num--;
        display();
        priceDisplay();
    }

    public void increase(View view) {
        num++;
        display();
        priceDisplay();
    }

    public void delete(View view) {
        num = 0;
        display();
        priceDisplay();
    }

    private void display () {
        TextView tv = findViewById(R.id.num);
        tv.setText(num.toString());
    }

    private void priceDisplay () {
        TextView tv = findViewById(R.id.price);
        tv.setText(NumberFormat.getCurrencyInstance().format(num * 2));
        tv = findViewById(R.id.price_with_offer);
        tv.setText(NumberFormat.getCurrencyInstance().format(num * 1.99));
    }
}
