package com.example.dhani.kholas.page;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dhani.kholas.R;
import com.example.dhani.kholas.adapter.JuzAdapter;
import com.example.dhani.kholas.model.BaseJuz;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JuzActivity extends AppCompatActivity {
    private final static String JSON_LIST_JUZ = "data-juz.json";

    private JuzAdapter juzAdapter;
    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juz);

        List<BaseJuz> list = new ArrayList<BaseJuz>();
        String json = getAssetsJSON(JSON_LIST_JUZ);

        JSONArray array = null;
        try {
            array = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (array.length() > 0) {
            Gson gson = new Gson();
            int i = 0;
            while (i < array.length()) {
                try {
                    list.add(gson.fromJson(array.getJSONObject(i).toString(), BaseJuz.class));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
        } else {
            Toast.makeText(this, "No Objects", Toast.LENGTH_LONG).show();
        }


        listView = findViewById(R.id.lv_daftar_juz);
        juzAdapter = new JuzAdapter(JuzActivity.this, list);
        listView.setAdapter(juzAdapter);


    }

    public String getAssetsJSON(String fileName) {
        String json = null;
        try {
            InputStream inputStream = this.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

}
