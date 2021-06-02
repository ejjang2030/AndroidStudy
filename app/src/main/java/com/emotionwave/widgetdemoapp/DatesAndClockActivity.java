package com.emotionwave.widgetdemoapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class DatesAndClockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates_and_clock);
        Refresh();
    }

    public void mOnClick(View v) {
        switch(v.getId()) {
            case R.id.btnrefresh:
                Refresh();
                break;
            case R.id.btnrun:
                int i;
                int a, b = 123, c = 456;
                long start, end;
                start = System.currentTimeMillis();
                for(i = 0; i < 500000000; i++) {
                    a = b + c;
                }
                end = System.currentTimeMillis();
                TextView result2 = findViewById(R.id.result2);
                String sres = "덧셈 5억번에 총 " + (end - start)/1000.0 + " 초가 걸렸습니다.";
                result2.setText(sres);
                break;
            case R.id.next:
                Intent intent = new Intent(DatesAndClockActivity.this, DatePickerAndStopWatchActivity.class);
                startActivity(intent);
                break;
        }
    }

    void Refresh() {
        StringBuilder time = new StringBuilder();
        long epoch = System.currentTimeMillis();
        time.append("epoch = " + epoch + "\n");
        // time.append("now = " + DateUtils.formatDateTime(this, epoch, 0)+ "\n");

        GregorianCalendar cal = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cal = new GregorianCalendar();
            time.append("now = " + String.format("%d년 %d월 %d일 %d시 %d분\n", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE)));
            Date now = new Date();
            SimpleDateFormat sdf = null;
            sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
            time.append("Now = " + sdf.format(now) + "\n");
            Calendar tom = null;
            tom = new GregorianCalendar();
            tom.add(Calendar.DAY_OF_MONTH, 1);
            Date tomdate = tom.getTime();
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");
            time.append("tomorrow = " + sdf2.format(tomdate) + "\n");

            time.append("boot = " + UpTime(SystemClock.elapsedRealtime()));
            time.append("run = " + UpTime(SystemClock.uptimeMillis()));
            time.append("thread = " + UpTime(SystemClock.currentThreadTimeMillis()));
            TextView result = findViewById(R.id.result);
            result.setText(time.toString());
        }
    }

    String UpTime(long msec) {
        long sec = msec / 1000;
        String result;
        result = String.format("%d일 %d시 %d분 %d초\n", sec / 86400, sec / 3600 % 24, sec / 60 % 60, sec % 60);
        return result;
    }
}