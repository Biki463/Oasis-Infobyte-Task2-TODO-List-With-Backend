package com.example.to_do_list_app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    public void insert(Note note);
    @Delete
    public void delete(Note note);
    @Update
    public void update(Note note);

    @Query("SELECT * FROM my_notes")
    public LiveData<List<Note>> getAllData();
}