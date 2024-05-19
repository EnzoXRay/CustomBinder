package com.example.binderserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AppleService extends Service {

    private List<Apple> mAppleList;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppleList = new ArrayList<>();

        Apple apple = new Apple("红富士", "新疆");
        mAppleList.add(apple);

        apple = new Apple("黑卡", "云南");
        mAppleList.add(apple);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final AppleManagerImpl mBinder = new AppleManagerImpl() {
        @Override
        public List<Apple> getAppleList() throws RemoteException {
            return mAppleList;
        }

        @Override
        public void addApple(Apple apple) throws RemoteException {
            if(!mAppleList.contains(apple)){
                mAppleList.add(apple);
            }
        }
    };
}
