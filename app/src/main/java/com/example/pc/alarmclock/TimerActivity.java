package com.example.pc.alarmclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lantouzi.library.pickerview.PickerView;

import java.util.ArrayList;
import java.util.List;

public class TimerActivity extends AppCompatActivity {
    private PickerView pickHour, pickMin,pickSecond;
    private List<String> listHour,listMin,listSecond;
    private int hour,min,second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        initView();

        setPickTimeHour();
        setPickTimeMin();
        setPickTimeSecond();


    }

    private void setPickTimeHour(){
        listHour = new ArrayList<>();
        for (int i = 0;i<12;i++){
            listHour.add(i+"");
        }
        pickHour.setSelections(listHour,3);
    }

    private void setPickTimeMin(){
        listMin = new ArrayList<>();
        for (int i = 0; i<60;i++){
            listMin.add(i+"");
        }
        pickMin.setSelections(listMin,30);
    }

    private void setPickTimeSecond(){
        listSecond = new ArrayList<>();
        for (int i = 0; i<60;i++){
            listMin.add(i+"");
        }
        pickMin.setSelections(listSecond,30);
    }


    private void initView(){
        pickHour = findViewById(R.id.picker1);
        pickMin = findViewById(R.id.picker2);
        pickSecond = findViewById(R.id.picker3);
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
            }
        });

        pickMin.setOnPickChangeListener(new PickerView.OnPickerListener() {
            @Override
            public void onPicking(int index) {

            }

            @Override
            public void onPicked(int index) {
                min = index;
            }
        });
    }
}
