package com.example.pratikb.notes;

/**
 * Created by Pratik B on 12/29/2018.
 */

public class noteModel {
    private String noteTitle;
    private String noteContent;

    public noteModel(String noteTitle, String noteContent) {
        this.noteTitle=noteTitle;
        this.noteContent=noteContent;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }


}

