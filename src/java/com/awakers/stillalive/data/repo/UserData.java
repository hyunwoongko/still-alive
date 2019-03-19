package com.awakers.stillalive.data.repo;

import com.awakers.stillalive.base.BaseRepository;

public class UserData extends BaseRepository {
    private static volatile UserData instance;
    private String address;
    private String email;
    private String info;
    private String job;
    private String name;
    private String pushToken;
    private String uid;

    private UserData() {
    }

    public static synchronized UserData get() {
        UserData userData;
        synchronized (UserData.class) {
            if (instance == null) {
                instance = new UserData();
            }
            userData = instance;
        }
        return userData;
    }

    public static void set(UserData userData) {
        instance = userData;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String str) {
        this.job = str;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public String getPushToken() {
        return this.pushToken;
    }

    public void setPushToken(String str) {
        this.pushToken = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }
}
