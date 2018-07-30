package com.example.pc.alarmclock.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pc.alarmclock.AppUtil;
import com.example.pc.alarmclock.data.Note;

import java.util.ArrayList;

import static com.example.pc.alarmclock.database.NoteDB.TABLE_NOTE;

/**
 * Created by dell on 3/25/2018.
 */

public class NoteModify {

    private NoteDB noteDB;

    public NoteModify(Context context) {
        noteDB = new NoteDB(context);
    }

    public ArrayList<Note> getAllNote() {
        ArrayList<Note> listNote = new ArrayList<Note>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTE;

        SQLiteDatabase db = noteDB.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Note note = new Note();
                note.setId(cursor.getInt(0));
                note.setDay(cursor.getInt(1));
                note.setMouth(cursor.getInt(2));
                note.setYear(cursor.getInt(3));
                note.setContent(cursor.getString(4));
                listNote.add(note);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return listNote;
    }

    public void addNote(Note Note) {
        SQLiteDatabase db = noteDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        Integer temp = null;
        values.put(AppUtil.ID_NOTE, temp);
        values.put(AppUtil.DAY_NOTE, Note.getDay());
        values.put(AppUtil.MONTH_NOTE, Note.getMouth());
        values.put(AppUtil.YEAR_NOTE, Note.getYear());
        values.put(AppUtil.CONTENT_NOTE, Note.getContent());
        //Neu de null thi khi value bang null thi loi

        db.insert(TABLE_NOTE, null, values);


        db.close();
    }

    public Note getNotebyID(int id) {
        SQLiteDatabase db = noteDB.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NOTE + " WHERE " + AppUtil.ID_NOTE + " ='" + id + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        Note note1 = null;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Note note = new Note();
                note.setId(cursor.getInt(0));
                note.setDay(cursor.getInt(1));
                note.setMouth(cursor.getInt(2));
                note.setYear(cursor.getInt(3));
                note.setContent(cursor.getString(4));
                note1 = note;
                cursor.moveToNext();
            }

        }
        cursor.close();
        db.close();

        return note1;
    }

    public ArrayList<Note> getNotebyMonth(int month,int year) {
        ArrayList<Note> listNote = new ArrayList<>();
        SQLiteDatabase db = noteDB.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NOTE + " WHERE " + AppUtil.MONTH_NOTE + " ='" + month + "'" + " AND " + AppUtil.YEAR_NOTE + " ='" + year +"'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Note note = new Note();
                note.setId(cursor.getInt(0));
                note.setDay(cursor.getInt(1));
                note.setMouth(cursor.getInt(2));
                note.setYear(cursor.getInt(3));
                note.setContent(cursor.getString(4));
                listNote.add(note);
                cursor.moveToNext();
            }

        }
        cursor.close();
        db.close();

        return listNote;
    }


    public void updateNote(Note alarm) {

        SQLiteDatabase db = noteDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppUtil.DAY_NOTE, alarm.getDay());
        values.put(AppUtil.MONTH_NOTE, alarm.getMouth());
        values.put(AppUtil.YEAR_NOTE, alarm.getYear());
        values.put(AppUtil.CONTENT_NOTE, alarm.getContent());
        db.update(NoteDB.TABLE_NOTE, values, AppUtil.ID_NOTE + "=?", new String[]{String.valueOf(alarm.getId())});
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = noteDB.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NOTE);
        db.close();
    }

    public void deleteNote(int  id) {
        SQLiteDatabase db = noteDB.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NOTE + " WHERE " + AppUtil.ID_NOTE + "='" + id + "'");
        db.close();
    }

    public void deleteNote(int day,int mouth,int year) {
        SQLiteDatabase db = noteDB.getWritableDatabase();
        db.execSQL("DELETE  FROM " + TABLE_NOTE+ " WHERE " + AppUtil.DAY_NOTE + " ='" + day +"'"+ " AND " + AppUtil.MONTH_NOTE + " ='" + mouth +"'"+ " AND "+ AppUtil.YEAR_NOTE + " ='" + year + "'");
        db.close();
    }


}
