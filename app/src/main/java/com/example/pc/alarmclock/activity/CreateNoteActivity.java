package com.example.pc.alarmclock.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pc.alarmclock.AppUtil;
import com.example.pc.alarmclock.R;
import com.example.pc.alarmclock.data.Note;
import com.example.pc.alarmclock.database.NoteModify;

import java.util.ArrayList;

public class CreateNoteActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageView imSave;
    private EditText edContent;
    private NoteModify modify;
    private int day, month, year;
    private Note note;
    private boolean isNew;
    private ArrayList<Note> listNote = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        toolbar = findViewById(R.id.toolbar);
        imSave = toolbar.findViewById(R.id.imSave);
        imSave.setOnClickListener(this);
        edContent = findViewById(R.id.edContent);
        modify = new NoteModify(this);
        Intent intent =getIntent();
        day = intent.getIntExtra(AppUtil.DAY_NOTE, -1);
        month = intent.getIntExtra(AppUtil.MONTH_NOTE, -1);
        year = intent.getIntExtra(AppUtil.YEAR_NOTE, -1);

        Log.e("hoaiii",day + "/"+month+"/"+year);
        isNew = checkIsNew(day, month, year);
        if (!isNew) {
            edContent.setText(note.getContent());
        }

    }

    private boolean checkIsNew(int day, int mouth, int year) {
        listNote = modify.getAllNote();
        boolean check = false;
        int count = 0;
        for (Note d : listNote) {
            if (d.getDay() == day && d.getMouth() == mouth && d.getYear() == year) {
                count++;
                note = d;
            }
        }
        if (count == 0) {
            check = true;
        } else check = false;
        return check;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imSave:
                if (edContent.getText().length() > 0) {
                    if (isNew) {
                        modify.addNote(new Note(day, month, year, edContent.getText().toString()));
                        Toast.makeText(this,"Create a note done",Toast.LENGTH_LONG).show();
                    } else {
                        note.setContent(edContent.getText().toString());
                        modify.updateNote(note);
                        Toast.makeText(this,"Update a note done",Toast.LENGTH_LONG).show();
                    }
                }
                finish();

                break;

        }
    }
}
