package com.example.picgame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AnsButton {

    public Boolean clickable;
    Integer place;
    Button myButton;
    Integer id;
    LinearLayout parent;
    Context context;

    AnsButton (LinearLayout playerGround, final Context context, Integer id) {

        this.context = context;
        clickable = false;
        place = -1;
        this.id = id;
        parent = playerGround;
        myButton = new Button(context);
        myButton.setBackground(context.getDrawable(R.drawable.layer_bw));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        myButton.setLayoutParams(params);
        parent.addView(myButton);

        myButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clickable) return;
                clickable = false;
                myButton.setBackground(context.getDrawable(R.drawable.layer_bw));
                ((MainActivity)context).buttonArray.get(place).setText((String) myButton.getText());
                myButton.setText("");
                ((MainActivity)context).buttonArray.get(place).setBackground(context.getDrawable(R.drawable.layer));
                ((MainActivity)context).buttonArray.get(place).clickable = true;
                place = -1;

            }
        });
    }

    public String getText() {
        return (String) myButton.getText();
    }

    public void setBackground(Drawable drawable) {
        myButton.setBackground(drawable);
    }

    public void setText(CharSequence text) {
        myButton.setText(text);
    }

    public void gun() {
        myButton.setVisibility(View.GONE);
    }
}
