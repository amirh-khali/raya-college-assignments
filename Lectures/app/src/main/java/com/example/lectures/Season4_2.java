package com.example.lectures;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.PointerIcon;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;

public class Season4_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season4_2);
        initial();
    }

    static private Pokemon p1;
    static private Pokemon p2;

    static public MediaPlayer fistSound;

    public void initial() {
        p1 = new Pokemon("Pokemon1", "1");
        p2 = new Pokemon("Pokemon2", "2");
        fistSound = MediaPlayer.create(this, R.raw.attack);
        display(false);
    }

    public void pokemon1 (View view) {
        display(p1.attack(p2));
    }

    public void pokemon2 (View view) {
        display(p2.attack(p1));
    }

    private void display (boolean flag) {
        TextView p1Health = findViewById(R.id.pokemon1);
        TextView p2Health = findViewById(R.id.pokemon2);
        TextView youWin = findViewById(R.id.you_win);
        if (p1.getHealth() == 0) {
            youWin.setText("Pokemon2 win");
            youWin.setVisibility(View.VISIBLE);
        }
        if (p2.getHealth() == 0) {
            youWin.setText("Pokemon1 win");
            youWin.setVisibility(View.VISIBLE);
        }
        p1Health.setText("" + p1.getHealth());
        p2Health.setText("" + p2.getHealth());
        if (flag) {
            findViewById(R.id.main_layout).setBackgroundResource(R.drawable.black);
            findViewById(R.id.main_layout).setBackgroundResource(R.drawable.background_animation);
            AnimationDrawable xxx = (AnimationDrawable) findViewById(R.id.main_layout).getBackground();
            xxx.start();
        }
    }
}

class Pokemon {
    private int health;
    private String name;
    private String type;

    Pokemon(String name, String type) {
        this.name = name;
        this.type = type;
        health = 10;
    }

    boolean dodge () {
        return Math.random()>.5;
    }
    boolean attack (Pokemon enemy) {
        if (!enemy.dodge() && enemy.health > 0 && this.health > 0) {
            enemy.health--;
            Season4_2.fistSound.start();
            return true;
        }
        return false;
    }
    int getHealth () {
        return health;
    }
}
