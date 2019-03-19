package com.awakers.stillalive.data.repo;

import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.data.vo.Capsule;
import java.util.List;

public class UserCapsule extends BaseRepository {
    private static volatile UserCapsule instance;
    private List<Capsule> userCapsule;

    private UserCapsule() {
    }

    public static UserCapsule get() {
        if (instance != null) {
            return instance;
        }
        UserCapsule userCapsule;
        synchronized (UserCapsule.class) {
            if (instance == null) {
                instance = new UserCapsule();
            }
            userCapsule = instance;
        }
        return userCapsule;
    }

    public static void set(UserCapsule userCapsule) {
        instance = userCapsule;
    }

    public List<Capsule> getUserCapsule() {
        return this.userCapsule;
    }

    public void setUserCapsule(List<Capsule> list) {
        this.userCapsule = list;
    }
}
