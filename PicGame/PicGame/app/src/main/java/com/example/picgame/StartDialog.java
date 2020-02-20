package com.example.picgame;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Activity c;
    private Button confirm;
    private EditText editText;

    public StartDialog(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_start);
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(this);
        editText = findViewById(R.id.edit);

        setCancelable(false);

    }

    @Override
    public void onClick(View v) {
        String name =editText.getText().toString();
        if(isValid(name)) {
            switch (v.getId()) {
                case R.id.confirm:
                    ((MainActivity)c).mUserName = editText.getText().toString();
                    ((MainActivity)c).countDownTimer.start();
                    dismiss();
                    break;
                default:

            }
        }
        else {
            Toast.makeText(c, "Don't set User Name", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean isValid(String s) {
        return s.length() > 0 && 'a' <= (32|s.charAt(0)) && (32|s.charAt(0)) <= 'z';
    }
}
