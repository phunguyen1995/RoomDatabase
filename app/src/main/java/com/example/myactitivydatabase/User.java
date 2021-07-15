package com.example.myactitivydatabase;
import android.graphics.Bitmap;
import android.view.autofill.AutofillValue;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable  {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int idClass;
    public String name;
    public String age;
    public String adress;
    public String ngaythem;
    public String lop;
    public String giaovien;

    public User (int idClass,String name, String age, String adress, String lop, String giaovien, String ngaythem) {
        this.idClass= idClass;
        this.name = name;
        this.age = age;
        this.adress = adress;
        this.lop = lop;
        this.ngaythem= ngaythem;
        this.giaovien = giaovien;
    }


    @Ignore
    Bitmap bm;
}
