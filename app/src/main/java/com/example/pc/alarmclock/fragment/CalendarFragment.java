package com.example.pc.alarmclock.fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.pc.alarmclock.AppUtil;
import com.example.pc.alarmclock.EventDecorator;
import com.example.pc.alarmclock.R;
import com.example.pc.alarmclock.activity.CreateNoteActivity;
import com.example.pc.alarmclock.activity.MainActivity;
import com.example.pc.alarmclock.adapter.NoteAdapter;
import com.example.pc.alarmclock.data.Note;
import com.example.pc.alarmclock.database.NoteModify;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {
    private MaterialCalendarView calendarView;
    private RecyclerView rvNote;
    private ArrayList<Note> listInMount = new ArrayList<>();
    private ArrayList<Note> listAll = new ArrayList<>();
    private int mDay, mMount, mYear;
    private NoteModify modify;
    private NoteAdapter noteAdapter;
    private HashSet<CalendarDay> listEvent;

    public CalendarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stop_watch, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        rvNote = view.findViewById(R.id.rvNote);
        modify = new NoteModify(getActivity());
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int day = date.getDay();
                int mount = date.getMonth();
                int year = date.getYear();
                Log.e("hoaiii ======", day + "/" + mount + "/" + year);
                Intent intent = new Intent(getActivity(), CreateNoteActivity.class);
                intent.putExtra(AppUtil.DAY_NOTE, day);
                intent.putExtra(AppUtil.MONTH_NOTE, mount + 1);
                intent.putExtra(AppUtil.YEAR_NOTE, year);
                startActivity(intent);
            }

        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Calendar cl = Calendar.getInstance();
        mMount = cl.get(Calendar.MONTH) + 1;
        mYear = cl.get(Calendar.YEAR);

        listInMount = modify.getNotebyMonth(mMount, mYear);
        listAll = modify.getAllNote();


        noteAdapter = new NoteAdapter(listInMount);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        rvNote.setLayoutManager(gridLayoutManager);
        rvNote.setAdapter(noteAdapter);

        for (Note di : listAll) {
            listEvent = new HashSet<>();
            CalendarDay event = CalendarDay.from(di.getYear(), di.getMouth() - 1, di.getDay());
            listEvent.add(event);
            Drawable drawable = getResources().getDrawable(R.drawable.sunny);
            calendarView.addDecorator(new EventDecorator(drawable, listEvent));
        }

    }
}
