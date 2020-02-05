package com.me.familybookkeepingbook;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AccountRecordRepository {
    private LiveData<List<AccountRecord>> allAcountRecord;
    private static List<AccountRecord> allAcountRecords;

    private AccountRecordDao accountRecordDao;
    public AccountRecordRepository(Context context) {
        AccountRecordDatabase accountRecordDatabase = AccountRecordDatabase.getAccountRecordDataBase(context.getApplicationContext());
        accountRecordDao = accountRecordDatabase.getAccountRecordDao();
        allAcountRecord = accountRecordDao.getAllAccountRecordLive();
    }

    public LiveData<List<AccountRecord>> getAllAcountRecord() {
        return allAcountRecord;
    }
    public LiveData<List<AccountRecord>> getAcountRecordByType(String type) {
        return accountRecordDao.getAccountRecordLiveByType("%"+type+"%");
    }
    void insertAccountRecord(AccountRecord ... accountRecords){
        new InsertAsyncTask(accountRecordDao).execute(accountRecords);
    }
    void deleteAllAccountRecord(){
        new DeleteAsyncTask(accountRecordDao).execute();
    }
    void deleteAccountRecord(AccountRecord ... accountRecords){
        new DeleteAsyncTask_2(accountRecordDao).execute(accountRecords);
    }
    static class InsertAsyncTask extends AsyncTask<AccountRecord ,Void,Void> {
        private AccountRecordDao accountRecordDao;

        public InsertAsyncTask(AccountRecordDao accountRecordDao) {
            this.accountRecordDao = accountRecordDao;
        }

        @Override
        protected Void doInBackground(AccountRecord... accountRecords) {
            accountRecordDao.insertAccountRecord(accountRecords);
            return null;
        }
    }
    static class DeleteAsyncTask extends AsyncTask<Void ,Void,Void>{
        private AccountRecordDao accountRecordDao;

        public DeleteAsyncTask(AccountRecordDao accountRecordDao) {
            this.accountRecordDao = accountRecordDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            accountRecordDao.deleteAllAccountRecord();
            return null;
        }
    }
    static class DeleteAsyncTask_2 extends AsyncTask<AccountRecord ,Void,Void>{
        private AccountRecordDao accountRecordDao;

        public DeleteAsyncTask_2(AccountRecordDao accountRecordDao) {
            this.accountRecordDao = accountRecordDao;
        }

        @Override
        protected Void doInBackground(AccountRecord... accountRecords ) {
            accountRecordDao.deleteAccountRecord(accountRecords);
            return null;
        }
    }

}
