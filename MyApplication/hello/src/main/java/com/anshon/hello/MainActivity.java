package com.anshon.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import com.anshon.myapplication.Book;
import com.anshon.myapplication.ISendBook;

public class MainActivity extends AppCompatActivity {
    private ISendBook sendBook;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.anshon.myapplication", "com.anshon.myapplication.AppService"));
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                sendBook = ISendBook.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    public void conServer(View view) {
        Book remoteBook = null;
        try {
            remoteBook = sendBook.getBook(1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (remoteBook != null) {
            Toast.makeText(this, remoteBook.name, Toast.LENGTH_LONG).show();
        }
    }
}