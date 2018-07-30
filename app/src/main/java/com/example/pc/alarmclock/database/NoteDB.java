package com.example.pc.alarmclock.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.pc.alarmclock.AppUtil;

/**
 * Created by dell on 3/24/2018.
 */

public class NoteDB extends SQLiteOpenHelper {
    public static final String DATABASE_NOTE ="noteDb";
    public static final String TABLE_NOTE ="note";

    public Context context;
    public NoteDB(Context context) {
        super(context, DATABASE_NOTE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE "+TABLE_NOTE +" (" +
                AppUtil.ID_NOTE +" integer primary key AUTOINCREMENT, "+
                AppUtil.DAY_NOTE + " int, "+
                AppUtil.MONTH_NOTE + " int, "+
                AppUtil.YEAR_NOTE +" TEXT, "+
                AppUtil.CONTENT_NOTE +" TEXT)";

        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NOTE);
        onCreate(db);

        Toast.makeText(context, "Drop successfully", Toast.LENGTH_SHORT).show();
    }
}
