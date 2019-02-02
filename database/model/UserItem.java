package com.example.deepbeast.androidtest.database.model;

import android.net.Uri;

public class UserItem {

    public static final String TABLE_NAME = "users";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PWD = "password";
    public static final String COLUMN_PIC = "profilepic";

    private String userName;
    private int passcode;
    private String profile_pic;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_PWD + " Integer,"
                    + COLUMN_PIC + " Text"
                    + ")";

    public UserItem() {
    }

    public UserItem(String userName, int passcode, String profile_pic) {
        this.userName = userName;
        this.passcode = passcode;
        this.profile_pic = profile_pic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPasscode() {
        return passcode;
    }

    public void setPasscode(int passcode) {
        this.passcode = passcode;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
