package com.example.picgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Pair;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jgabrielfreitas.core.BlurImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public final static int BLUR_COST = 80;
    public final static int SHOW_COST = 50;


    public static String mUserName;


    public CountDownTimer countDownTimer;
    private TextView scoreBox, levelBox;
    public ArrayList<MyButton> buttonArray;
    public ArrayList<AnsButton> ansButtonArray;
    private Integer timeRemained = 0;

    private ArrayList<Pair<String, Integer>> rounds = new ArrayList<>();

    private Integer currentImageID;
    Integer currentRound;
    private Integer score = 100;
    private Integer radius = 15;
    private String currentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ResourceManager(this);
        addRounds();
        initial();
    }


    public void initial() {
        currentRound = 0;
        scoreBox = findViewById(R.id.score);
        levelBox = findViewById(R.id.level);
        display();

        //*
         countDownTimer = new CountDownTimer(180000, 1000) {

            public void onTick(long millisUntilFinished) {
                TextView tv = findViewById(R.id.chronometer);
                tv.setText("remaining: " + millisUntilFinished / 1000);
                timeRemained = (int)millisUntilFinished;
            }

            public void onFinish() {
                gameOver();
            }

        };
        //*/
        View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.hint_blur:
                        if (score >= BLUR_COST) {
                            score -= BLUR_COST;
                            radius = Math.max((radius - 2), 1);
                            ((BlurImageView)findViewById(R.id.main_image)).setBlur(radius);
                        }
                        break;
                    case R.id.hint_word:
                        if (score >= SHOW_COST) {
                            score -= SHOW_COST;
                            //TODO now make show one of the answers, better to check show different answers each time.
                        }
                        break;
                    default:
                }
                display();
            }
        };

        findViewById(R.id.hint_blur).setOnClickListener(cl);
        //TODO findViewById(R.id.hint_word).setOnClickListener(cl);

        StartDialog startDialog = new StartDialog(this);
        startDialog.show();

        rounds = ResourceManager.getImage();
        Collections.shuffle(rounds);
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
        radius = 15;
        score += 50 * (currentRound + 1);
        if (currentRound >= rounds.size()) {
            gameOver();
            return;
        }

        currentName = rounds.get(currentRound).first;
        currentImageID = rounds.get(currentRound).second;


        BlurImageView imageView = findViewById(R.id.main_image);

        imageView.setImageResource(currentImageID);
//        imageView.setBlur(radius);


        resetAns(currentName);

        resetKeys(currentName);
        display();
    }

    private void gameOver() {
        gun();
        countDownTimer.cancel();
        ResourceManager.addScore(mUserName, score + timeRemained / 10, this);
        CustomDialogClass dialog = new CustomDialogClass(this);
        dialog.show();
        score = 100;
        display();
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

    private void display() {
        scoreBox.setText(String.format("Score: %5s", score));
        levelBox.setText((currentRound+1) + "/" + rounds.size());
    }

}
