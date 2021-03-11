package com.anshon.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

public class AppService extends Service {
    public AppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends ISendBook.Stub {

        @Override
        public Book getBook(int index) throws RemoteException {
            if (index == 0) {
                return new Book("乌合之众", 12);
            } else {
                return new Book("月亮与六便士", 36);
            }
        }
    }
}