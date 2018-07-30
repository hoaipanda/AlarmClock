package com.example.pc.alarmclock;

import android.graphics.drawable.Drawable;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by dell on 3/27/2018.
 */

public class EventDecorator implements DayViewDecorator {

    private final Drawable drawable1;
    private final HashSet<CalendarDay> dates;

    public EventDecorator(Drawable drawable, Collection<CalendarDay> dates) {
        this.drawable1 = drawable;
        this.dates = new HashSet<>(dates);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return dates.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(drawable1);
    }
}
