package com.example.recyleviewproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ExampleItemList implements Parcelable
{
    //properties
    ArrayList<ExampleItem> myList;

    //constructors
    public ExampleItemList()
    {
        myList = new ArrayList<ExampleItem>();
    }

    protected ExampleItemList(Parcel in) {
        myList = in.createTypedArrayList(ExampleItem.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(myList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExampleItemList> CREATOR = new Creator<ExampleItemList>() {
        @Override
        public ExampleItemList createFromParcel(Parcel in) {
            return new ExampleItemList(in);
        }

        @Override
        public ExampleItemList[] newArray(int size) {
            return new ExampleItemList[size];
        }
    };
}
