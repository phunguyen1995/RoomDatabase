package com.example.myactitivydatabase;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface UserPaging2Dao {
        // The Integer type parameter tells Room to use a
        // PositionalDataSource object.
        @Query("SELECT * FROM User ORDER BY id DESC")
        DataSource.Factory<Integer, User> concertsByID();

}
