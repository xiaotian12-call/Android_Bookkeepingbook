package com.me.familybookkeepingbook;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {AccountRecord.class },version = 1,exportSchema = false)
public abstract class AccountRecordDatabase extends RoomDatabase {
    private static AccountRecordDatabase INSTANCE;
    public abstract AccountRecordDao getAccountRecordDao();
    static synchronized AccountRecordDatabase getAccountRecordDataBase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AccountRecordDatabase.class,"accountrecord_database")
                    .build();
        }
        return INSTANCE;
    }

}
