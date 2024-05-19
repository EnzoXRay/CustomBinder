package com.example.binderclient;

public  class AppleManagerImpl implements IAppleManager{
    private android.os.IBinder mRemote;
    AppleManagerImpl(android.os.IBinder remote) {
        mRemote = remote;
    }

    @Override
    public android.os.IBinder asBinder(){
        return mRemote;
    }
    @Override
    public java.util.List<com.example.binderclient.Apple> getAppleList() throws android.os.RemoteException{
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.util.List<com.example.binderclient.Apple> _result;
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            boolean _status = mRemote.transact(TRANSACTION_getAppleList, _data, _reply, 0);
            _reply.readException();
            _result = _reply.createTypedArrayList(com.example.binderclient.Apple.CREATOR);
        }
        finally {
            _reply.recycle();
            _data.recycle();
        }
        return _result;
    }
    @Override
    public void addApple(com.example.binderclient.Apple apple) throws android.os.RemoteException{
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
            _data.writeInterfaceToken(DESCRIPTOR);
            _Parcel.writeTypedObject(_data, apple, 0);
            boolean _status = mRemote.transact(TRANSACTION_addApple, _data, _reply, 0);
            _reply.readException();
        }
        finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    static class _Parcel {
        static private <T> T readTypedObject(
                android.os.Parcel parcel,
                android.os.Parcelable.Creator<T> c) {
            if (parcel.readInt() != 0) {
                return c.createFromParcel(parcel);
            } else {
                return null;
            }
        }
        static private <T extends android.os.Parcelable> void writeTypedObject(
                android.os.Parcel parcel, T value, int parcelableFlags) {
            if (value != null) {
                parcel.writeInt(1);
                value.writeToParcel(parcel, parcelableFlags);
            } else {
                parcel.writeInt(0);
            }
        }
    }
}