package com.supriadi.diki.codelabs_sharefre.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements  Parcelable{
    private int id;
    private String name;
    private String nim;

    public UserModel(){

    }

    public UserModel(String name, String nim){
        this.name = name;
        this.nim = nim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.nim);
    }

    protected  UserModel(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
        this.nim = in.readString();
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }
        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
