package com.example.to_do_list_app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "my_notes")
public class Note {
    private String title;
    private String dis;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Note(String title, String dis) {
        this.title = title;
        this.dis = dis;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }
}






