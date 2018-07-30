package com.example.pc.alarmclock.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.alarmclock.data.Clap;
import com.example.pc.alarmclock.adapter.ClapAdapter;
import com.example.pc.alarmclock.R;

import java.util.ArrayList;

public class StopWatchActivity extends AppCompatActivity {
    private TextView tvCountTime;
    private RecyclerView rvTimeStop;
    private ImageView imAgain, imPlay, imClap;
    private boolean isPlay = false;
    private int mSecond = 0, mMin = 0, mMili = 0, index = 1;
    private ArrayList<Clap> listClap = new ArrayList<>();
    private ClapAdapter adapter;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        tvCountTime = findViewById(R.id.tvCountTime);
        rvTimeStop = findViewById(R.id.rvTimeStop);
        imAgain = findViewById(R.id.imAgain);
        imPlay = findViewById(R.id.imPlay);
        imClap = findViewById(R.id.clap);

        imAgain.setOnClickListener(lsClick);
        imPlay.setOnClickListener(lsClick);
        imClap.setOnClickListener(lsClick);

        imClap.setVisibility(View.GONE);
        imAgain.setVisibility(View.GONE);
    }

    private View.OnClickListener lsClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imAgain:
                    tvCountTime.setText("00:00:00");
                    isPlay = false;
                    handler.removeCallbacks(updateTimerThread);
                    mMili = mMin = mSecond = 0;
                    imPlay.setImageResource(R.drawable.play);
                    listClap.removeAll(listClap);
                    index = 1;
                    imClap.setVisibility(View.GONE);
                    imAgain.setVisibility(View.GONE);
                    break;
                case R.id.imPlay:
                    if (isPlay) {
                        imPlay.setImageResource(R.drawable.play);
                        isPlay = false;
                        handler.removeCallbacks(updateTimerThread);
                    } else {
                        handler.postDelayed(updateTimerThread,10);
                        imClap.setVisibility(View.VISIBLE);
                        imAgain.setVisibility(View.VISIBLE);
                        imPlay.setImageResource(R.drawable.pause);
                        isPlay = true;


                    }
                    break;
                case R.id.clap:
                    listClap.add(new Clap("# " + index,mMin+":"+mSecond+":"+mMili));
                    updateRecyclerview();
                    index++;
                    break;
            }
        }
    };
    private void updateTime() {
        mMili = mMili + 1;
        if (mMili > 99) {
            mMili = 0;
            mSecond++;
            if (mSecond > 59) {
                mSecond = 0;
                mMin++;
            }
        }
        tvCountTime.setText(subtoString(mMin) + ":" + subtoString(mSecond) + ":" + subtoString(mMili));
    }

    private Runnable updateTimerThread = new Runnable() {

        @Override
        public void run() {
            if(isPlay){
                updateTime();
                handler.postDelayed(updateTimerThread, 10);
            }

        }
    };

    private String subtoString(int x) {
        String values;
        if (x < 10) {
            values = "0" + x;
        } else values = String.valueOf(x);
        return values;
    }
    private void updateRecyclerview(){
        adapter = new ClapAdapter(listClap,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        rvTimeStop.setAdapter(adapter);
        rvTimeStop.setLayoutManager(gridLayoutManager);
    }

}
