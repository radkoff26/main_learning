package com.example.speechlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private WordToSpeech tts;
    private TextView ru, us;
    private Button repeat, next;
    public static List<String[]> words;

    static {
        words = Arrays.asList(
                new String[]{"КОШКА", "CAT"},
                new String[]{"СОБАКА", "DOG"},
                new String[]{"ЛИСА", "FOX"},
                new String[]{"СЛОН", "ELEPHANT"},
                new String[]{"МЫШЬ", "MOUSE"}
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ru = (TextView) findViewById(R.id.ru);
        us = (TextView) findViewById(R.id.us);

        repeat = (Button) findViewById(R.id.repeat);
        next = (Button) findViewById(R.id.next);

        Collections.shuffle(words);

        ru.setText(words.get(0)[0]);
        us.setText(words.get(0)[1]);

        tts = new WordToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.run();
                }
            }
        }, words.get(0)[1]);

        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tts.isSpeaking()) {
                    tts.run();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!tts.isSpeaking()) {
            finish();
        }
    }
}