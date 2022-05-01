package com.example.notes.models;

import com.example.notes.adapters.NotesAdapter;

public class NotesList {
    private static NotesAdapter n_adapter = new NotesAdapter();

    public NotesList(NotesAdapter n_adapter) {
        this.n_adapter = n_adapter;
    }

    public static NotesAdapter getN_adapter() {
        return n_adapter;
    }

    public static void setN_adapter(Note note) {
        n_adapter.setItem(note);
    }
}
