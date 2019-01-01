package com.example.pratikb.notes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Pratik B on 12/29/2018.
 */

public class notesAdapter extends RecyclerView.Adapter<noteViewHolder>{

    List<noteModel> noteList;

    public  notesAdapter(List<noteModel> noteList){
        this.noteList=noteList;
    }

    @NonNull
    @Override
    public noteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.note_card, parent, false);
        noteViewHolder holder = new noteViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull noteViewHolder holder, int position){
        noteModel noteItem=noteList.get(position);
        holder.populate(noteItem, position);
    }

    @Override
    public int getItemCount(){
        return noteList.size();
    }

}
