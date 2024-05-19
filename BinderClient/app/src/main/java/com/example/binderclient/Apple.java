package com.example.binderclient;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Apple implements Parcelable {
    private String name;
    private String loc;

    public Apple(String name, String loc) {
        this.name = name;
        this.loc = loc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(loc);
    }

    public static final Creator<Apple> CREATOR = new Creator<Apple>() {
        @Override
        public Apple createFromParcel(Parcel in) {
            return new Apple(in);
        }

        @Override
        public Apple[] newArray(int size) {
            return new Apple[size];
        }
    };

    protected Apple(Parcel in) {
        name = in.readString();
        loc = in.readString();
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
