package com.emotionwave.widgetdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.health.TimerStat;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Timer;

public class ChronometerActivity extends AppCompatActivity {

    Chronometer mChrono;

    TextView mEllapse;
    TextView mSplit;
    Button mBtnStart;
    Button mBtnSplit;
    final static int IDLE = 0;
    final static int RUNNING = 1;
    final static int PAUSE = 2;
    int mStatus = IDLE;
    long mBaseTime;
    long mPauseTime;
    int mSplitCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);
        mChrono = findViewById(R.id.chrono);

        mEllapse = findViewById(R.id.ellapse);
        mSplit = findViewById(R.id.split);
        mBtnStart = findViewById(R.id.btnstart2);
        mBtnSplit = findViewById(R.id.btnsplit);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mChrono.stop();
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnstart:
                mChrono.start();
                break;
            case R.id.btnstop:
                mChrono.stop();
                break;
            case R.id.btnreset:
                mChrono.setBase(SystemClock.elapsedRealtime());
                break;
            case R.id.next:
                Intent intent = new Intent(ChronometerActivity.this, ExtraActivity.class);
                startActivity(intent);
                break;
        }
    }

}