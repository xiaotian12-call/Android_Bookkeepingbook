package com.me.familybookkeepingbook;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDao userDao;
    static User user_0 ;
    public UserRepository(Context context) {
        UserDatabase userDatabase = UserDatabase.getUserDataBase(context.getApplicationContext());
        userDao = userDatabase.getUserDao();
    }


    void insertUser(User ... users){
        new InsertAsyncTask(userDao).execute(users);
    }
    void deleteUser(User ... users){
        new DeleteAsyncTask(userDao).execute(users);
    }
    void updateUser(User ... users){
        new UpdateAsyncTask(userDao).execute(users);
    }
    Boolean queueUser(User user){
        new QueueAsyncTask(userDao).execute(user);
        if(user==null||user_0==null){
            queueUser(user);
            return true;
        }else if(user_0.getUserName().equals(user.getUserName())&&user_0.getPassword().equals(user.getPassword())){
            return true;
        }else {
            return false;
        }
    }
    static class InsertAsyncTask extends AsyncTask<User ,Void,Void> {
        private UserDao userDao;

        public InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users);
            return null;
        }
    }
    static class DeleteAsyncTask extends AsyncTask<User ,Void,Void> {
        private UserDao userDao;

        public DeleteAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.deleteUser(users);
            return null;
        }
    }
    static class UpdateAsyncTask extends AsyncTask<User ,Void,Void> {
        private UserDao userDao;

        public UpdateAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users);
            return null;
        }
    }
    static class QueueAsyncTask extends AsyncTask<User ,Void,Void> {
        private UserDao userDao;
        public QueueAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            User user1 = new User("王正帅","123");
            for (User user:users) {
                user1 = userDao.findUser(user.getUserName(),user.getPassword());
                user_0 = user1;
            }
            return null;
        }
    }


}
