package com.awakers.stillalive.data.repo;

import com.awakers.stillalive.base.BaseRepository;
import java.util.List;

public class UserTheme extends BaseRepository {
    private static volatile UserTheme instance;
    private List<String> userTheme;

    private UserTheme() {
    }

    public static UserTheme get() {
        if (instance != null) {
            return instance;
        }
        UserTheme userTheme;
        synchronized (UserTheme.class) {
            if (instance == null) {
                instance = new UserTheme();
            }
            userTheme = instance;
        }
        return userTheme;
    }

    public static void set(UserTheme userTheme) {
        instance = userTheme;
    }

    public List<String> getUserTheme() {
        return this.userTheme;
    }

    public void setUserTheme(List<String> list) {
        this.userTheme = list;
    }
}
