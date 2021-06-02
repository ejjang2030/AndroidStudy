package com.emotionwave.widgetdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class AdvancedWidgetActivity extends AppCompatActivity {


    ProgressBar mProgBar;
    ProgressBar mProgCircle;

    int mProg;

    SeekBar mSeekBar;
    TextView mVolume;

    RatingBar mRating;
    TextView mRateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_widget);


        mProgBar = findViewById(R.id.progress);
        mProgCircle = findViewById(R.id.progcircle);

        mSeekBar = findViewById(R.id.seekbar);
        mVolume = findViewById(R.id.volume);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mVolume.setText("Now Volume : " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mRating = findViewById(R.id.ratingbar);
        mRateText = findViewById(R.id.ratetext);
        mRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mRateText.setText("Now Rate : " + rating);
            }
        });

    }

    public void mOnClick(View v) {
        switch(v.getId()) {
            case R.id.decfirst:
                mProgBar.incrementProgressBy(-2);
                break;
            case R.id.incfirst:
                mProgBar.incrementProgressBy(2);
                break;
            case R.id.decsecond:
                mProgBar.incrementSecondaryProgressBy(-2);
                break;
            case R.id.incsecond:
                mProgBar.incrementSecondaryProgressBy(2);
                break;
            case R.id.start:
                mProgCircle.setVisibility(View.VISIBLE);
                break;
            case R.id.stop:
                mProgCircle.setVisibility(View.INVISIBLE);
                break;
            case R.id.next:
                Intent intent = new Intent(AdvancedWidgetActivity.this, DatesAndClockActivity.class);
                startActivity(intent);
                break;
        }
    }
}