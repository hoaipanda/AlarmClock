package com.example.pc.alarmclock.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pc.alarmclock.R;
import com.tomerrosenfeld.customanalogclockview.CustomAnalogClock;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeFragment extends Fragment {

    private CustomAnalogClock analogClock;


    public TimeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_time, container, false);
        analogClock = view.findViewById(R.id.analog_clock);
        analogClock.setAutoUpdate(true);
        analogClock.setScale(0.4f);
        analogClock.init(getActivity(),R.drawable.bgclock,R.drawable.hand_hour,R.drawable.hand_min,0,false,true);
        return view;
    }

}
