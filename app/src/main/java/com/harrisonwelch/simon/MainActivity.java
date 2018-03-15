package com.harrisonwelch.simon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class MainActivity extends Activity {
    static public final String SCORE_FILENAME = "simon_score.txt";
    static public boolean soundOn = true;
    static public int maxScore;

    static public String GAME_MODE_KEY = "gameMode";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadScore();
        ((TextView) findViewById(R.id.textview_maxScore)).setText("High Score: " + maxScore);

        findViewById(R.id.button_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(1);
            }
        });

        findViewById(R.id.button_playMode2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(2);
            }
        });

        findViewById(R.id.button_playMode3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame(3);
            }
        });

        findViewById(R.id.button_howToPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HowToPlayActivity.class));
            }
        });

        findViewById(R.id.button_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
            }
        });

        CompoundButton soundSwitch = findViewById(R.id.switch_sound);
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                soundOn = b;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadScore();
        ((TextView) findViewById(R.id.textview_maxScore)).setText("High Score: " + maxScore);
    }

    protected void startGame(int gameMode){
        Intent intent = new Intent(new Intent(getApplicationContext(), GameActivity.class));
        intent.putExtra(GAME_MODE_KEY, gameMode);
        startActivity(intent);
    }

    public void loadScore(){
        try{
            BufferedReader br = new BufferedReader((new InputStreamReader(getApplicationContext().openFileInput(SCORE_FILENAME))));
            maxScore = br.read();
        }
        catch (IOException e){
            Log.e("FILES", "Error reading file: " + e);
            maxScore = 0;
        }
    }

    public static void saveScore(Context context, int score){
        try {
            FileOutputStream fos = context.openFileOutput(SCORE_FILENAME, Context.MODE_PRIVATE);
            fos.write(score);
            fos.close();
        }
        catch (IOException e) {
            Log.e("FILES", "Error writing file: " + e);
        }

    }
}
