package com.me.familybookkeepingbook;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User...users);
    @Delete
    void deleteUser(User...users);
    @Update
    void updateUser(User...users);
    @Query("SELECT * From USER WHERE user_name like:userName_y AND password like :password_y")
    User findUser(String userName_y ,String password_y);
}
