package com.example.myactitivydatabase;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class UserClass implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;

    public UserClass (String name) {
        this.name = name;
    }


    @Ignore
    Bitmap bm;

}
