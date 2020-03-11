package com.example.picgame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
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
    public float SCALE;
    public static String mUserName;


    public CountDownTimer countDownTimer;
    private TextView scoreBox, levelBox;
    public ArrayList<MyButton> buttonArray;
    public ArrayList<AnsButton> ansButtonArray;
    private Integer timeRemained = 0;

    private ArrayList<Pair<String, Integer>> rounds = new ArrayList<>();

    public BlurImageView biv;
    private Integer currentImageID;
    public Integer currentRound;
    private Integer score = 100;
    private Integer radius = 8;
    private String currentName;
    public MediaPlayer backgroundSong;
    public MediaPlayer tap;
    public MediaPlayer nextRound;
    public MediaPlayer help;

    public Boolean gameStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundSong = MediaPlayer.create(getApplicationContext(), R.raw.undertale);
        backgroundSong.start();
        tap = MediaPlayer.create(getApplicationContext(), R.raw.tap);
        nextRound = MediaPlayer.create(getApplicationContext(), R.raw.next_round);
        help = MediaPlayer.create(getApplicationContext(), R.raw.help);

        backgroundSong.setLooping(true);

        new ResourceManager(this);
        addRounds();
        initial();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundSong.pause();
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

    public void initial() {

        gameStop = false;
        currentRound = 0;
        scoreBox = findViewById(R.id.score);
        levelBox = findViewById(R.id.level);
        SCALE = getResources().getDisplayMetrics().density;
        display();

         countDownTimer = new CountDownTimer(150000, 1000) {

            public void onTick(long millisUntilFinished) {
                TextView tv = findViewById(R.id.timer);
                tv.setText("remaining: " + millisUntilFinished / 1000);
                timeRemained = (int)millisUntilFinished;
            }

            public void onFinish() {
                gameOver();
            }

        };

        View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameStop) return;
                switch (v.getId()) {
                    case R.id.hint_blur:
                        if (score >= BLUR_COST) {
                            help.start();
                            score -= BLUR_COST;
                            radius = Math.max((radius - 2), 1);
                            biv.setBlur(radius);
                        }
                        break;
                    case R.id.hint_word:
                        if (score >= SHOW_COST) {
                            //TODO now make show one of the answers, better to check show different answers each time.
                            int count = 0;
                            for (int i = 0; i < ansButtonArray.size(); ++i) {
                                if (ansButtonArray.get(i).clickable == false && ansButtonArray.get(i).getText().equals("")) {
                                    count++;
                                }
                            }
                            if (count == 0) break;
                            help.start();
                            score -= SHOW_COST;
                            Random r = new Random();
                            int hintPlace = r.nextInt(count) + 1;
                            Log.d("AmirH", "random: " + hintPlace + " ");
                            count = 0;
                            for (int i = 0; i < ansButtonArray.size(); ++i) {
                                Log.d("AmirH", ansButtonArray.get(i).getText());
                                if (ansButtonArray.get(i).clickable == false && ansButtonArray.get(i).getText().equals("")) {
                                    count++;
                                    if (count == hintPlace) {
                                        hintPlace = i;
                                        break;
                                    }
                                }
                            }
                            for (int i = 1; i < buttonArray.size(); ++i) {
                                if (buttonArray.get(i).clickable == true && buttonArray.get(i).myButton.getText().charAt(0) == currentName.charAt(hintPlace)) {
                                    Log.d("AmirH", currentName.charAt(hintPlace) + " " + hintPlace);
                                    buttonArray.get(i).clickable = false;
                                    ansButtonArray.get(hintPlace).clickable = false;
                                    ansButtonArray.get(hintPlace).setBackground(getDrawable(R.drawable.layer_r));
                                    ansButtonArray.get(hintPlace).setText(buttonArray.get(i).myButton.getText());
                                    ansButtonArray.get(hintPlace).place = i;
                                    buttonArray.get(i).myButton.setText("");
                                    buttonArray.get(i).myButton.setBackground(getDrawable(R.drawable.layer_bw));
                                    break;
                                }
                            }
                            if (check()) {
                                gameStop = true;
                                biv.setBlur(0);
                                nextRound.start();
                                for (int i = 0; i < ansButtonArray.size(); ++i) {
                                    ansButtonArray.get(i).setBackground(getDrawable(R.drawable.layer_g));
                                }

                                final Handler t = new Handler();
                                t.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        gameStop = false;
                                        gun();
                                        currentRound++;
                                        nextRound();
                                    }
                                }, 1000);
                            }
                        }
                        break;
                    default:
                }
                display();
            }
        };

        findViewById(R.id.hint_blur).setOnClickListener(cl);
        findViewById(R.id.hint_word).setOnClickListener(cl);

        StartDialog startDialog = new StartDialog(this);
        startDialog.show();

        rounds = ResourceManager.getImage();
        Collections.shuffle(rounds);
        nextRound();
    }


    public void nextRound () {
        gameStop = false;
        radius = 8;
        score += 100 * (currentRound);
        if (currentRound >= rounds.size()) {
            gameOver();
            return;
        }


        currentName = rounds.get(currentRound).first;
        currentImageID = rounds.get(currentRound).second;


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

        ArrayList<Integer> list = new ArrayList<>();
        for (int i=1; i< 33; i++) {
            list.add(i);
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

        BlurImageView biv = new BlurImageView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dp(250), dp(250));
        lp.gravity = Gravity.CENTER;
        lp.setMargins(36, 36, 36, 36);
        biv.setLayoutParams(lp);
        biv.setScaleType(ImageView.ScaleType.FIT_XY);
        biv.setPadding(8, 8, 8, 8);
        biv.setBackground(getResources().getDrawable(R.drawable.layer));
        biv.setImageResource(currentImageID);
        if (this.biv != null) {
            this.biv.setVisibility(View.GONE);
        }
        LinearLayout board = findViewById(R.id.image_board);
        board.addView(biv);
        biv.setBlur(radius);
        this.biv = biv;


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
        levelBox.setText("Level " + (currentRound+1) + "/" + rounds.size());
    }

    public int dp(float px) {
        return (int)(px * this.SCALE + 0.5f);
    }
}
