package com.example.binderserver;

import android.os.Binder;
import android.os.IBinder;

public abstract class AppleManagerImpl extends Binder implements IAppleManager {

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
        java.lang.String descriptor = DESCRIPTOR;
        if (code >= android.os.IBinder.FIRST_CALL_TRANSACTION && code <= android.os.IBinder.LAST_CALL_TRANSACTION) {
            data.enforceInterface(descriptor);
        }
        switch (code)
        {
            case INTERFACE_TRANSACTION:
            {
                reply.writeString(descriptor);
                return true;
            }
        }
        switch (code)
        {
            case TRANSACTION_getAppleList:
            {
                java.util.List<com.example.binderserver.Apple> _result = this.getAppleList();
                reply.writeNoException();
                reply.writeTypedList(_result);
                break;
            }
            case TRANSACTION_addApple:
            {
                com.example.binderserver.Apple _arg0;
                _arg0 = _Parcel.readTypedObject(data, com.example.binderserver.Apple.CREATOR);
                this.addApple(_arg0);
                reply.writeNoException();
                break;
            }
            default:
            {
                return super.onTransact(code, data, reply, flags);
            }
        }
        return true;
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
