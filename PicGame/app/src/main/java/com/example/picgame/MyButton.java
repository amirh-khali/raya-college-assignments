package com.example.picgame;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class MyButton {

    final public Button myButton;
    final public Integer id;
    private  RelativeLayout parent;

    public Boolean clickable;

    public MyButton(RelativeLayout playerGround, final Context context, final Integer id, final char c, final ArrayList<AnsButton> ansButtonArray){

        clickable = true;
        this.id = id;
        parent = playerGround;
        myButton = new Button(context);
        myButton.setId(id);
        myButton.setText(c + "");
        myButton.setBackground(context.getDrawable(R.drawable.layer));

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(70, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(2, 2, 2, 2);
        myButton.setLayoutParams(params);
        parent.addView(myButton);

        myButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((MainActivity) context).gameStop) return;
                if (!clickable) return;
                clickable = false;
                for (int i = 0; i < ansButtonArray.size(); ++i) {
                    if (ansButtonArray.get(i).clickable == false && ansButtonArray.get(i).getText().equals("")) {
                        ((MainActivity)context).tap.start();
                        ansButtonArray.get(i).clickable = true;
                        ansButtonArray.get(i).setBackground(context.getDrawable(R.drawable.layer));
                        ansButtonArray.get(i).setText(myButton.getText());
                        ansButtonArray.get(i).place = id;
                        myButton.setText("");
                        myButton.setBackground(context.getDrawable(R.drawable.layer_bw));
                        break;
                    }
                }
                if (((MainActivity)context).check()) {
                    ((MainActivity) context).gameStop = true;
                    ((MainActivity) context).biv.setBlur(0);
                    ((MainActivity) context).nextRound.start();
                    for (int i = 0; i < ansButtonArray.size(); ++i) {
                        ansButtonArray.get(i).setBackground(context.getDrawable(R.drawable.layer_g));
                    }

                    final Handler t = new Handler();
                    t.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((MainActivity) context).gameStop = false;
                            ((MainActivity) context).gun();
                            ((MainActivity) context).currentRound++;
                            ((MainActivity) context).nextRound();
                        }
                    }, 1000);

                }
            }
        });

    }

    public void setParams () {
        if (id == 0) return;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) myButton.getLayoutParams();

        if (id <= 8)
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        else
            params.addRule(RelativeLayout.BELOW, id - 8);

        if (id % 8 == 1)
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        else
            params.addRule(RelativeLayout.RIGHT_OF,id - 1);

        myButton.setLayoutParams(params);
    }

    public void setText(String s) {
        myButton.setText(s);
    }

    public void setBackground(android.graphics.drawable.Drawable id) {
        myButton.setBackground(id);
    }

    public void gun() {
        myButton.setVisibility(View.GONE);
    }
}
