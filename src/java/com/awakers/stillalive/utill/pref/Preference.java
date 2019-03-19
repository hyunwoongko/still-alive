package com.awakers.stillalive.utill.pref;

import java.util.List;
import java.util.Set;

public interface Preference {
    boolean getBoolean(String str, boolean z);

    double getDouble(String str);

    float getFloat(String str, float f);

    int getInt(String str);

    List<String> getList(String str);

    long getLong(String str, long j);

    String getString(String str, String str2);

    Set<? extends String> getStringSet(String str);

    void remove(String str);

    boolean setBoolean(String str, boolean z);

    boolean setDouble(String str, double d);

    boolean setFloat(String str, float f);

    boolean setInt(String str, int i);

    void setList(String str, List<String> list);

    boolean setLong(String str, long j);

    boolean setString(String str, String str2);

    boolean setStringSet(String str, Set<String> set);
}
