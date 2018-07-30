package com.example.pc.alarmclock.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pc.alarmclock.R;
import com.lantouzi.library.pickerview.PickerView;

import java.util.ArrayList;
import java.util.List;

public class TimerActivity extends AppCompatActivity {
    private PickerView pickHour, pickMin, pickSecond;
    private List<String> listHour, listMin, listSecond;
    private int hour = 0, min = 0, second = 0;
    private ProgressBar progress;
    private ImageView imReset, imControl;
    private LinearLayout lyChooseTime, lyCount;
    private boolean isPlay = false;
    private int sumTime = 0,maxprogress = 0;
    private TextView tvHour, tvMin, tvSecond;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        initView();

        setPickTimeHour();
        setPickTimeMin();
        setPickTimeSecond();


    }

    private void setPickTimeHour() {
        listHour = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            listHour.add(subInt(i) + "");
        }
        pickHour.setSelections(listHour, 0);
    }

    private String subInt(int x) {
        String rs = "";
        if (x < 10) {
            rs = "0" + x;
        } else {
            rs = x + "";
        }
        return rs;
    }

    private void setPickTimeMin() {
        listMin = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            listMin.add(subInt(i));
        }
        pickMin.setSelections(listMin, 0);
    }

    private void setPickTimeSecond() {
        listSecond = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            listSecond.add(subInt(i));
        }
        pickSecond.setSelections(listSecond, 0);
    }


    private void initView() {
        lyChooseTime = findViewById(R.id.lyChooseTime);
        lyCount = findViewById(R.id.lyCount);
        pickHour = findViewById(R.id.picker1);
        pickMin = findViewById(R.id.picker2);
        pickSecond = findViewById(R.id.picker3);
        progress = findViewById(R.id.progress);
        imControl = findViewById(R.id.imControl);
        imReset = findViewById(R.id.imReset);
        tvHour = findViewById(R.id.tvHour);
        tvMin = findViewById(R.id.tvMin);
        tvSecond = findViewById(R.id.tvSecond);

        imControl.setOnClickListener(lsClick);
        imReset.setOnClickListener(lsClick);
    }

    private View.OnClickListener lsClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imControl:
                    lyChooseTime.setVisibility(View.GONE);
                    lyCount.setVisibility(View.VISIBLE);
                    maxprogress = sumTime;
                    if (maxprogress > 0) {
                        progress.setMax(maxprogress * 1000);
                        if (isPlay) {
                            isPlay = false;
                            imControl.setImageResource(R.drawable.play);
                            handler.removeCallbacks(updateTimerThread);
                        } else {
                            isPlay = true;
                            imControl.setImageResource(R.drawable.pause);
                            handler.postDelayed(updateTimerThread, 1000);

                        }
                    }

                    break;
                case R.id.imReset:
                    lyChooseTime.setVisibility(View.VISIBLE);
                    lyCount.setVisibility(View.GONE);
                    isPlay = false;
                    imControl.setImageResource(R.drawable.play);
                    handler.removeCallbacks(updateTimerThread);
                    break;
            }
        }
    };


    private Runnable updateTimerThread = new Runnable() {

        @Override
        public void run() {
            if (isPlay) {
                int curentProcess = progress.getProgress();
                progress.setProgress(curentProcess + 1000);
                updateTextByTime();
                if (maxprogress == 0) {
                    imControl.setImageResource(R.drawable.play);
                    isPlay = false;
                }

                handler.postDelayed(updateTimerThread, 1000);
            }

        }
    };

    private void updateTextByTime() {
        maxprogress = maxprogress - 1;
        refreshText();
    }

    private void refreshText() {
        int h = maxprogress / 3600;
        int m = (maxprogress - h * 3600) / 60;
        int s = maxprogress - h * 3600 - m * 60;
        if (maxprogress >= 0) {
            tvHour.setText(subInt(h));
            tvMin.setText(subInt(m));
            tvSecond.setText(subInt(s));
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        pickHour.setOnPickChangeListener(new PickerView.OnPickerListener() {
            @Override
            public void onPicking(int index) {

            }

            @Override
            public void onPicked(int index) {
                hour = index;
                tvHour.setText(subInt(hour));
                Log.e("hoaiii", "hour + " + hour);
                sumTime = hour * 60 * 60 + min * 60 + second;
            }
        });

        pickMin.setOnPickChangeListener(new PickerView.OnPickerListener() {
            @Override
            public void onPicking(int index) {

            }

            @Override
            public void onPicked(int index) {
                min = index;
                tvMin.setText(subInt(min));
                Log.e("hoaiii", "min + " + min);
                sumTime = hour * 60 * 60 + min * 60 + second;
            }
        });

        pickSecond.setOnPickChangeListener(new PickerView.OnPickerListener() {
            @Override
            public void onPicking(int index) {

            }

            @Override
            public void onPicked(int index) {
                second = index;
                tvSecond.setText(subInt(second));
                Log.e("hoaiii", "second + " + second);
                sumTime = hour * 60 * 60 + min * 60 + second;
            }
        });
    }
}
