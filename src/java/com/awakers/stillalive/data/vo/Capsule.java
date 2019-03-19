package com.awakers.stillalive.data.vo;

import java.util.Map;

public class Capsule {
    private static volatile Capsule instance;
    private String capsuleName;
    private Map<String, Mission> mission;
    private boolean onGoing = false;
    private int startDate;
    private String theme;

    private Capsule() {
    }

    public static Capsule get() {
        if (instance != null) {
            return instance;
        }
        Capsule capsule;
        synchronized (Capsule.class) {
            if (instance == null) {
                instance = new Capsule();
            }
            capsule = instance;
        }
        return capsule;
    }

    public static void set(Capsule capsule) {
        instance = capsule;
    }

    public boolean isOnGoing() {
        return this.onGoing;
    }

    public void setOnGoing(boolean z) {
        this.onGoing = z;
    }

    public String getCapsuleName() {
        return this.capsuleName;
    }

    public void setCapsuleName(String str) {
        this.capsuleName = str;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String str) {
        this.theme = str;
    }

    public int getStartDate() {
        return this.startDate;
    }

    public void setStartDate(int i) {
        this.startDate = i;
    }

    public Map<String, Mission> getMission() {
        return this.mission;
    }

    public void setMission(Map<String, Mission> map) {
        this.mission = map;
    }
}
