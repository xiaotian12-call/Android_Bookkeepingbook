package com.me.familybookkeepingbook;

import androidx.room.Database;
import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase INSTANCE;
    public abstract UserDao getUserDao();
    static synchronized UserDatabase getUserDataBase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),UserDatabase.class,"user_database")
                    .build();
        }
        return INSTANCE;
    }
}
