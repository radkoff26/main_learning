package com.example.speechlearning;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class WordToSpeech extends TextToSpeech implements Runnable {

    private String word_us;

    private boolean isSpeaking = false;

    public WordToSpeech(Context context, OnInitListener listener, String word_us) {
        super(context, listener);
        this.word_us = word_us.toUpperCase();
    }

    @Override
    public void run() {
        isSpeaking = true;

        while (isSpeaking) {
            setLanguage(Locale.US);
            speak(word_us, TextToSpeech.QUEUE_ADD, null);

            stopSpeaking();
        }
    }

    public void stopSpeaking() {
        isSpeaking = false;
    }

    @Override
    public boolean isSpeaking() {
        return isSpeaking;
    }

    @Override
    public int stop() {
        return super.stop();
    }
}
