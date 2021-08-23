package com.example.myactitivydatabase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAll();
    @Query("SELECT * FROM User")
    List<User> getAll1();
    @Query(" SELECT * FROM User Where idClass = :IdClass ")
    List<User> selectUserByIdClass (int IdClass);
    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    LiveData<User> loadAllByIds(int userIds);

    @Query("SELECT * FROM user WHERE id = :userId")
    Flowable<User> getUserByUserId(int userId);

    @Query("SELECT * FROM user ORDER BY id DESC LiMIT 1  ")
    User findLastIdUser();

    @Query(" DELETE FROM user")
    void deleteAllUser();

    @Query("SELECT * FROM user ORDER BY id ASC LiMIT 5 OFFSET :offset ")
    List<User> loadUser(int offset);

    @Query("SELECT *   FROM user WHERE id = (:userIds)")
    User findByIds(int userIds);

    @Insert
    void insertAll(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... user);

    @Query("SELECT * FROM User ORDER BY id DESC")
    DataSource.Factory<Integer, User> concertsByID();

}