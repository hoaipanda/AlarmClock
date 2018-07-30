package com.example.pc.alarmclock.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pc.alarmclock.data.Clap;
import com.example.pc.alarmclock.R;

import java.util.ArrayList;

/**
 * Created by dell on 3/8/2018.
 */

public class ClapAdapter extends RecyclerView.Adapter<ClapAdapter.ViewHolder> {

    private ArrayList<Clap> listClap = new ArrayList<>();
    private Context context;


    public ClapAdapter(ArrayList<Clap> listClap, Context context) {
        this.listClap = listClap;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_stop_watch, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Clap clap = listClap.get(position);
        holder.tvItemMenu.setText(clap.getName());
        holder.tvTime.setText(clap.getTime());
    }

    @Override
    public int getItemCount() {
        return listClap.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemMenu;
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvItemMenu = itemView.findViewById(R.id.tvNameCountTime);
            tvTime = itemView.findViewById(R.id.tvTimeCountIndex);
        }
    }
}
