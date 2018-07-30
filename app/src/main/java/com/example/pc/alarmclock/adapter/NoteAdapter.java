package com.example.pc.alarmclock.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pc.alarmclock.R;
import com.example.pc.alarmclock.data.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    private ArrayList<Note> listNote;
    public NoteAdapter(ArrayList<Note> listNote){
        this.listNote = listNote;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_note,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Note note = listNote.get(position);
        holder.tvTime.setText(note.getDay()+"/"+note.getMouth()+"/"+note.getYear());
        holder.tvSub.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTime, tvSub;
        public ViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTimeNote);
            tvSub = itemView.findViewById(R.id.tvSubNote);
        }
    }
}
