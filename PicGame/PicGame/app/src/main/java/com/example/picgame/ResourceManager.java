package com.example.picgame;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ResourceManager{

    private final static String jsonResourceSettingAddress = "resource_settings.json";
    private final static String jsonScoreBoard = "score_board.json";

    private static ArrayList<Pair<String, Integer>> IMAGES = new ArrayList<>(); //name, address
    private static JSONObject images;

    private static ArrayList<Pair<String, Integer>> SCOREBOARD = new ArrayList<>();
    private static JSONObject scoreboard;

    ResourceManager(Context context) {

        initialization(context);

    }

    public static void initialization(Context context){

        try{

            InputStream is = context.getAssets().open(jsonResourceSettingAddress);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            images = (JSONObject) jsonObject.get("images");

            is = context.getAssets().open(jsonScoreBoard);
            size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            jsonObject = new JSONObject(json);
            scoreboard = (JSONObject) jsonObject.get("scores");


        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public static void addScore (String name, Integer score, Context context) {
        try{
            scoreboard.put(name, score);
            String jsonString = scoreboard.toString();
            FileOutputStream fos = context.openFileOutput(jsonScoreBoard,Context.MODE_PRIVATE);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();


            Iterator<String> keys = scoreboard.keys();

            SCOREBOARD = new ArrayList<>();

            while(keys.hasNext()) {
                String key = keys.next();
                SCOREBOARD.add(new Pair<String, Integer>(key, (Integer) scoreboard.get(key)));
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static void addLevel (String name, Integer id, Context context) {
        try{
            if (images.has(name)) return;
            images.put(name, id);
            String jsonString = images.toString();
            FileOutputStream fos = context.openFileOutput(jsonScoreBoard,Context.MODE_PRIVATE);
            if (jsonString != null) {
                fos.write(jsonString.getBytes());
            }
            fos.close();

            Iterator<String> keys = images.keys();

            IMAGES = new ArrayList<>();

            while(keys.hasNext()) {
                String key = keys.next();
                IMAGES.add(new Pair<String, Integer>(key, (Integer) images.get(key)));
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }


    public static ArrayList<Pair<String, Integer>> getImage() {

        return IMAGES;

    }

    public static ArrayList<Pair<String, Integer>> getScore() {

        return SCOREBOARD;
    }
}
