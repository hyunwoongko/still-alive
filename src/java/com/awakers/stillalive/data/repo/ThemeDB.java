package com.awakers.stillalive.data.repo;

import com.awakers.stillalive.base.BaseRepository;
import java.util.List;

public class ThemeDB extends BaseRepository {
    private static volatile ThemeDB instance;
    private String capsule;
    private String name;
    private String special;
    private String thumb;
    private List<String> url;

    private ThemeDB() {
    }

    public static ThemeDB get() {
        if (instance != null) {
            return instance;
        }
        ThemeDB themeDB;
        synchronized (UserCapsule.class) {
            if (instance == null) {
                instance = new ThemeDB();
            }
            themeDB = instance;
        }
        return themeDB;
    }

    public static void set(ThemeDB themeDB) {
        instance = themeDB;
    }

    public String getSpecial() {
        return this.special;
    }

    public void setSpecial(String str) {
        this.special = str;
    }

    public String getCapsule() {
        return this.capsule;
    }

    public void setCapsule(String str) {
        this.capsule = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getThumb() {
        return this.thumb;
    }

    public void setThumb(String str) {
        this.thumb = str;
    }

    public List<String> getUrl() {
        return this.url;
    }

    public void setUrl(List<String> list) {
        this.url = list;
    }
}
