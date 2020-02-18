package com.example.professionalcaculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display();
        Integer x = Integer.MAX_VALUE;
        Log.d("AmirH", x.toString());
    }

    private static String aqaSajjad = "0";

    private boolean isDigit(char c) {
        if (c >= '0' && c <= '9') return true;
        return false;
    }

    private void display() {
        TextView tv = (TextView) findViewById(R.id.playground);
        tv.setText(aqaSajjad);
    }

    public void dot(View view) {
        for (int i = aqaSajjad.length() - 1; i >= 0; --i) {
            if (!isDigit(aqaSajjad.charAt(i)) && aqaSajjad.charAt(i) != '.') {
                break;
            }
            if (aqaSajjad.charAt(i) == '.') {
                return;
            }
        }
        aqaSajjad += ((TextView)view).getText();
        display();
    }

    public void number(View view) {

        if (aqaSajjad.length() != 0 && aqaSajjad.charAt(aqaSajjad.length() - 1) == '0' &&
                (aqaSajjad.length() == 1 || !isDigit(aqaSajjad.charAt(aqaSajjad.length() - 2))))
            aqaSajjad = aqaSajjad.substring(0, aqaSajjad.length() - 1);
        aqaSajjad += ((TextView)view).getText();
        display();
    }

    public void number_0(View view) {
        if ((aqaSajjad.length() != 0 && aqaSajjad.charAt(aqaSajjad.length() - 1) != '0') ||
                (aqaSajjad.length() > 1 && isDigit(aqaSajjad.charAt(aqaSajjad.length() - 2)) )) {
            aqaSajjad += "0";
        }
        display();
    }
    public void operator(View view) {
        if (aqaSajjad.length() != 0 && isDigit(aqaSajjad.charAt(aqaSajjad.length() - 1))) {
            aqaSajjad += ((TextView)view).getText();
        } else if (aqaSajjad.length() != 0) {
            aqaSajjad = aqaSajjad.substring(0, aqaSajjad.length() - 1);
            aqaSajjad += ((TextView)view).getText();
        }
        display();
    }

    public void delete(View view) {
        if (aqaSajjad.length() != 0) {
            aqaSajjad = aqaSajjad.substring(0, aqaSajjad.length() - 1);
        }
        display();
    }

    public void equals_sign(View view) {
        if(aqaSajjad.length() == 0 || (!isDigit(aqaSajjad.charAt(aqaSajjad.length() - 1)) && aqaSajjad.charAt(aqaSajjad.length() - 1) != '.')) {
            return;
        }
        String[] splitWithOperators = aqaSajjad.split("(\\×|\\÷|\\+|\\-)");
        String[] splitWithNumbers = aqaSajjad.split("([0-9.]+)");
        Double ans = Double.parseDouble(splitWithOperators[0]);
        for (int i = 1; i < splitWithOperators.length; ++i) {
            switch (splitWithNumbers[i]) {
                case "+":
                    ans += Double.parseDouble(splitWithOperators[i]);
                    break;
                case "-":
                    ans -= Double.parseDouble(splitWithOperators[i]);
                    break;
                case "×":
                    ans *= Double.parseDouble(splitWithOperators[i]);
                    break;
                case "÷":
                    if (Double.parseDouble(splitWithOperators[i]) == 0) {
                        return;
                    }
                    ans /= Double.parseDouble(splitWithOperators[i]);
                    break;
            }
        }
        DecimalFormat df = new DecimalFormat("###.#####");;
        aqaSajjad = df.format(ans);
        display();
    }
}
