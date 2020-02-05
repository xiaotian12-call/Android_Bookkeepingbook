package com.me.familybookkeepingbook;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AccountRecordDao {
    @Insert
    void insertAccountRecord (AccountRecord ... AccountRecords);
    @Update
    void updateAccountRecord (AccountRecord ... AccountRecords);
    @Delete
    void deleteAccountRecord (AccountRecord ... AccountRecords);
    @Query("DELETE From ACCOUNTRECORD")
    void deleteAllAccountRecord ();
    @Query("SELECT * From ACCOUNTRECORD ORDER BY ID DESC")
    LiveData<List<AccountRecord>> getAllAccountRecordLive ();
    @Query("SELECT * From ACCOUNTRECORD WHERE cost_type LIKE :type ORDER BY ID DESC")
    LiveData<List<AccountRecord>> getAccountRecordLiveByType (String type);
}
