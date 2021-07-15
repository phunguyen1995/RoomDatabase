package com.example.myactitivydatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {User.class,UserClass.class}, version = 10118)
public abstract class MyDataBase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract UserClassDao userClassDao();

    public static  MyDataBase instanceDatabase(Context context) {
        return Room.databaseBuilder(context,
                MyDataBase.class, "myDatabase1").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
}
