package com.example.binderclient;

import java.util.List;

public interface IAppleManager extends android.os.IInterface {

    java.lang.String DESCRIPTOR = "com.example.binderclient.IAppleManager";

    int TRANSACTION_getAppleList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    int TRANSACTION_addApple = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);

    List<Apple> getAppleList() throws android.os.RemoteException;

    void addApple(Apple apple) throws android.os.RemoteException;

}
