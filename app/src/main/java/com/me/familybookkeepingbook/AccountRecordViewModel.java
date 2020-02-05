package com.me.familybookkeepingbook;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountRecordViewModel extends AndroidViewModel {
    private AccountRecordRepository accountRecordRepository;
    public AccountRecordViewModel(@NonNull Application application) {
        super(application);
        accountRecordRepository = new AccountRecordRepository(application);
    }

    public LiveData<List<AccountRecord>> getAllAcountRecord() {
        return accountRecordRepository.getAllAcountRecord();
    }
    public LiveData<List<AccountRecord>> getAcountRecordByType(String type) {
        return accountRecordRepository.getAcountRecordByType(type);
    }

    void insertAccountRecord(AccountRecord ... accountRecords){
        accountRecordRepository.insertAccountRecord(accountRecords);
    }
    void deleteAllAccountRecord(){
        accountRecordRepository.deleteAllAccountRecord();
    }
    void deleteAccountRecord(AccountRecord ... accountRecords){
        accountRecordRepository.deleteAccountRecord(accountRecords);
    }

}
