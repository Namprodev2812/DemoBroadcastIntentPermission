package com.asterisk.nam.demobroadcastintentpermission;

import android.os.Parcel;
import android.os.Parcelable;

public class Cat implements Parcelable {
    public static final Creator<Cat> CREATOR = new Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel in) {
            return new Cat(in);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };
    private String mName;
    private int mAge;
    private String mHo;

    public Cat(String name, int age, String ho) {
        mName = name;
        mAge = age;
        mHo = ho;
    }

    protected Cat(Parcel in) {
        mName = in.readString();
        mAge = in.readInt();
        mHo = in.readString();
    }

    public String getName() {
        return mName;
    }

    public int getAge() {
        return mAge;
    }

    public String getHo() {
        return mHo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mAge);
        dest.writeString(mHo);
    }

    public String getInfor() {
        return mName + "*" + mAge + "*" + mHo;
    }
}
