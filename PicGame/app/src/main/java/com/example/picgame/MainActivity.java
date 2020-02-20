package com.example.picgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jgabrielfreitas.core.BlurImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static String mUserName;

    Integer currentRound;
    private ArrayList<Pair<String, Integer>> rounds = new ArrayList<>();
    public ArrayList<MyButton> buttonArray;
    public ArrayList<AnsButton> ansButtonArray;
    private Integer currentImageID;
    private String currentName;

    public CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ResourceManager(this);
        addRounds();
        initial();
    }

    public void initial() {

        countDownTimer = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                TextView tv = findViewById(R.id.chronometer);
                tv.setText("remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                gameOver();
            }

        };

        StartDialog startDialog = new StartDialog(this);
        startDialog.show();

        rounds = ResourceManager.getImage();
        Collections.shuffle(rounds);
        currentRound = 0;
        nextRound();
    }

    private void addRounds() {
        ResourceManager.addLevel("bicycle", R.drawable.bicycle, this);
        ResourceManager.addLevel("calculator", R.drawable.calculator, this);
        ResourceManager.addLevel("car", R.drawable.car, this);
        ResourceManager.addLevel("cat", R.drawable.cat, this);
        ResourceManager.addLevel("dog", R.drawable.dog, this);
        ResourceManager.addLevel("fish", R.drawable.fish, this);
        ResourceManager.addLevel("gun", R.drawable.gun, this);
        ResourceManager.addLevel("sun", R.drawable.sun, this);
    }

    public void nextRound () {
        if (currentRound >= rounds.size()) {
            gameOver();
            return;
        }
        TextView level = findViewById(R.id.level);
        level.setText((currentRound + 1) + "");

        currentName = rounds.get(currentRound).first;
        currentImageID = rounds.get(currentRound).second;


        BlurImageView imageView = findViewById(R.id.main_image);
        imageView.setImageResource(currentImageID);

        resetAns(currentName);

        resetKeys(currentName);

    }

    private void gameOver() {
        gun();
        countDownTimer.cancel();
        ResourceManager.addScore(mUserName, currentRound * 8, this);
        CustomDialogClass dialog = new CustomDialogClass(this);
        dialog.show();
    }

    private void resetKeys(String name) {

        RelativeLayout relativeLayout = findViewById(R.id.keyboard);

        buttonArray = new ArrayList<>();

        Random r = new Random();
        String alphabet = "qwertyuioplkjhgfdsazxcvbnm";
        for (int i = 0; i < 33; i++) {

            char c = alphabet.charAt(r.nextInt(alphabet.length()));
            buttonArray.add(new MyButton(relativeLayout , this, i, c, ansButtonArray));
        }

        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i< 33; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<name.length(); i++) {
            buttonArray.get(list.get(i)).setText(name.charAt(i) + "");
        }

        buttonArray.get(0).myButton.setVisibility(View.GONE);
        for (int i = 0; i < 33; ++i) {
            buttonArray.get(i).setParams();
        }
    }

    public void resetAns(String input) {

        LinearLayout linearLayout = findViewById(R.id.ans);

        ansButtonArray = new ArrayList<>();

        for (int i = 0; i < input.length(); ++i) {
            ansButtonArray.add(new AnsButton(linearLayout, this, i));
        }
    }

    public boolean check() {
        for (int i = 0; i < currentName.length(); ++i) {
            if (ansButtonArray.get(i).getText().length() == 0 ||
                    currentName.charAt(i) != ansButtonArray.get(i).getText().charAt(0)) {
                return false;
            }
        }
        return true;
    }

    public void gun() {
        for (int i = 0; i < ansButtonArray.size(); ++i) {
            ansButtonArray.get(i).gun();
        }
        for (int i = 0; i < buttonArray.size(); ++i) {
            buttonArray.get(i).gun();
        }
    }
}
