package com.emotionwave.widgetdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

public class DatePickerAndStopWatchActivity extends AppCompatActivity {

    DatePicker mDate;
    TextView mTxtDate;

    TimePicker mTime;
    TextView mTxtTime;

    int mYear, mMonth, mDay, mHour, mMinute;
    TextView mTxtDate2;
    TextView mTxtTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_and_stop_watch);

        mDate = findViewById(R.id.datepicker);
        mTxtDate = findViewById(R.id.txtdate);
        mDate.init(mDate.getYear(), mDate.getMonth(), mDate.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mTxtDate.setText(String.format("%d/%d/%d", year, monthOfYear + 1, dayOfMonth));
            }
        });
        // 선택기로부터 날짜 조사
        findViewById(R.id.btnnow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = String.format("%d년 %d월 %d일", mDate.getYear(), mDate.getMonth() + 1, mDate.getDayOfMonth());
                Toast.makeText(DatePickerAndStopWatchActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });

        mTime = findViewById(R.id.timepicker);
        mTxtTime = findViewById(R.id.txttime);
        mTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mTxtTime.setText(String.format("%d:%d", hourOfDay, minute));
            }
        });
        findViewById(R.id.btntogle24).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTime.setIs24HourView(!mTime.is24HourView());
            }
        });
        findViewById(R.id.btnnow2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = String.format("%d시 %d분", mTime.getCurrentHour(), mTime.getCurrentMinute());
                Toast.makeText(DatePickerAndStopWatchActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });

        mTxtDate2 = findViewById(R.id.txtdate2);
        mTxtTime2 = findViewById(R.id.txttime2);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Calendar cal = new GregorianCalendar();
            mYear = cal.get(Calendar.YEAR);
            mMonth = cal.get(Calendar.MONTH);
            mDay = cal.get(Calendar.DAY_OF_MONTH);
            mHour = cal.get(Calendar.HOUR_OF_DAY);
            mMinute = cal.get(Calendar.MINUTE);

            UpdateNow();
        }
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btnchangedate:
                new DatePickerDialog(DatePickerAndStopWatchActivity.this, mDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.btnchangetime:
                new TimePickerDialog(DatePickerAndStopWatchActivity.this, mTimeSetListener, mHour, mMinute, false).show();
                break;
            case R.id.next:
                Intent intent = new Intent(DatePickerAndStopWatchActivity.this, ChronometerActivity.class);
                startActivity(intent);
                break;
        }
    }

    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            UpdateNow();
        }
    };

    TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;
            UpdateNow();
        }
    };

    void UpdateNow() {
        mTxtDate2.setText(String.format("%d/%d/%d", mYear, mMonth + 1, mDay));
        mTxtTime2.setText(String.format("%d:%d", mHour, mMinute));
    }


}