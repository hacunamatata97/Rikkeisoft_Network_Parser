package com.example.hacunamatata.rikkeisoft_network_parser.rikkeisoft_network_parser.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private int userId;
    private String title;
    private String body;

    public User(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    protected User(Parcel in) {
        userId = in.readInt();
        title = in.readString();
        body = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeString(title);
        parcel.writeString(body);
    }

    @Override
    public String toString() {
        return "ID: " + userId + "\n" + "TITLE: " + title + "\n" + "BODY: " + body + "\n\n";
    }
}
