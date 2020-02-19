package com.example.picgame;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

public class ResourceManager{

    private final static String jsonResourceSettingAddress = "resource_settings.json";

    private static HashMap<String, String> IMAGES = new HashMap<>(); //name, address

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
            JSONObject RESOURCES;


            // Images

            RESOURCES = (JSONObject) jsonObject.get("images");

            Iterator<String> keys = RESOURCES.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                IMAGES.put( key, (String) RESOURCES.get(key));
            }

        } catch (Exception e) {

            Log.d("AmirH", "Hiii");
            e.printStackTrace();

        }

    }


    public static HashMap<String, String> getImage() {

        return IMAGES;

    }

}
