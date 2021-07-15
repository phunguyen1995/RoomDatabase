package com.example.myactitivydatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserClassDao {
    @Query("SELECT * FROM UserClass")
    List<UserClass> getAll();
    @Insert
    void insertAll(UserClass... users) ;
    @Update
    void update(UserClass... users);
    @Delete
    void delete(UserClass user);
    @Query("SELECT * FROM userclass ORDER BY id DESC LiMIT 1  ")
    UserClass findLastIdUserClass();
    @Query("SELECT name FROM Userclass  ")
    List<String> getname();
    @Query(" DELETE FROM UserClass")
    void deleteAllUserClass();

}

