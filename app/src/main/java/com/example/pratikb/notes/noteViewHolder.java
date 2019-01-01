package com.example.pratikb.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Pratik B on 12/29/2018.
 */

public class noteViewHolder extends RecyclerView.ViewHolder {
    private TextView noteTitleTV, noteContentTV;
    private Button editNoteB, deleteNoteB, saveNoteB;
    private String title, content;
    private EditText noteTitleET, noteContentET;
    SharedPreferences sharedPreferences;
    Context applicationContext = NotesActivity.getContextOfApplication();

    public ArrayList<String> getDB(ArrayList<String> arrayList, int length, String category)
    {
        sharedPreferences = applicationContext.getSharedPreferences("myPref", applicationContext.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        for(int i=0; i<length; i++)
        {
            String key=category+i;
            String listItem=sharedPreferences.getString(key,null);
            arrayList.add(listItem);
        }
        editor.commit();
        return arrayList;

    }


    public void updateDB(ArrayList<String> arrayList, String category)
    {
        sharedPreferences = applicationContext.getSharedPreferences("myPref", applicationContext.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        String[] array=new String[arrayList.size()];
        arrayList.toArray(array);
        for(int i=0;i<array.length;i++)
        {
            String key=category+i;
            editor.putString(key, array[i]);
            editor.commit();
        }
        editor.commit();
    }

    public noteViewHolder(View itemView){
        super(itemView);
        noteTitleTV=itemView.findViewById(R.id.tv_title);
        noteContentTV=itemView.findViewById(R.id.tv_content);
        editNoteB=itemView.findViewById(R.id.btn_editNote);
        deleteNoteB=itemView.findViewById(R.id.btn_deleteNote);
        noteTitleET=itemView.findViewById(R.id.et_card_title);
        noteContentET=itemView.findViewById(R.id.et_card_content);
        saveNoteB=itemView.findViewById(R.id.btn_saveNote);

    }

    public void populate(noteModel noteItem, final int position){
        title=noteItem.getNoteTitle();
        content=noteItem.getNoteContent();

        sharedPreferences = applicationContext.getSharedPreferences("myPref", applicationContext.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();


        noteTitleTV.setText(title);
        noteContentTV.setText(content);

        noteTitleET.setHint(title);
        noteContentET.setHint(content);

        editNoteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteTitleTV.setVisibility(View.GONE);
                noteTitleET.setVisibility(View.VISIBLE);

                noteContentTV.setVisibility(View.GONE);
                noteContentET.setVisibility(View.VISIBLE);

                editNoteB.setVisibility(View.GONE);
                saveNoteB.setVisibility(View.VISIBLE);


            }
        });

        saveNoteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> editList=new ArrayList<>();
                int length=sharedPreferences.getInt("Length",0);

                editList=getDB(editList,length,"title");
                if(noteTitleET.getText().toString().isEmpty())
                {
                    editList.set(position, noteTitleET.getHint().toString());
                }
                else {editList.set(position, noteTitleET.getText().toString());}
                updateDB(editList,"title");

                editList.clear();

                editList=getDB(editList,length,"content");
                if(noteContentET.getText().toString().isEmpty())
                {
                    editList.set(position, noteContentET.getHint().toString());
                }
                else {editList.set(position, noteContentET.getText().toString());}
                updateDB(editList,"content");


                editor.putInt("Length",editList.size());
                editor.commit();

                NotesActivity.activity.recreate();

                noteTitleTV.setVisibility(View.VISIBLE);
                noteTitleET.setVisibility(View.GONE);

                noteContentTV.setVisibility(View.VISIBLE);
                noteContentET.setVisibility(View.GONE);

                editNoteB.setVisibility(View.VISIBLE);
                saveNoteB.setVisibility(View.GONE);



            }
        });


        deleteNoteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> editList=new ArrayList<>();
                int length=sharedPreferences.getInt("Length",0);

                editList=getDB(editList,length,"title");
                editList.remove(position);
                updateDB(editList,"title");

                editList.clear();
                editList=getDB(editList,length,"content");
                editList.remove(position);
                updateDB(editList,"content");

                editor.putInt("Length",editList.size());
                editor.commit();
                NotesActivity.activity.recreate();
            }
        });


    }






}
