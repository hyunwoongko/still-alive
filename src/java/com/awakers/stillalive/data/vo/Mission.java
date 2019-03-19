package com.awakers.stillalive.data.vo;

public class Mission {
    private static volatile Mission instance;
    private String text;
    private String url;

    private Mission() {
    }

    public static Mission get() {
        if (instance != null) {
            return instance;
        }
        Mission mission;
        synchronized (Mission.class) {
            if (instance == null) {
                instance = new Mission();
            }
            mission = instance;
        }
        return mission;
    }

    public static void set(Mission mission) {
        instance = mission;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
