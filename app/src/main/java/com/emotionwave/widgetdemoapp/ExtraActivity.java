package com.emotionwave.widgetdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class ExtraActivity extends AppCompatActivity {

    String[] arWords = new String[] {
            "가구", "가로수", "가방", "가슴", "가치", "가훈", "나그네", "다리미", "above", "about", "absolute", "access", "activity", "adjust"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);
        ArrayAdapter<String> adWord = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arWords);
        AutoCompleteTextView autoEdit = findViewById(R.id.autoedit);
        autoEdit.setAdapter(adWord);
    }
}