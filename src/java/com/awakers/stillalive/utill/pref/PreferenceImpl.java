package com.awakers.stillalive.utill.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PreferenceImpl implements Preference {
    private SharedPreferences mSharedPreference;

    public PreferenceImpl(Context context) {
        this.mSharedPreference = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void remove(String str) {
        this.mSharedPreference.edit().remove(str).apply();
    }

    public void setList(String str, List<String> list) {
        this.mSharedPreference.edit().putString(str, new Gson().toJson(list)).apply();
    }

    public List<String> getList(String str) {
        return (List) new Gson().fromJson(this.mSharedPreference.getString(str, ""), new TypeToken<List<String>>() {
        }.getType());
    }

    public boolean getBoolean(String str, boolean z) {
        return this.mSharedPreference.getBoolean(str, z);
    }

    public double getDouble(String str) {
        return Double.longBitsToDouble(this.mSharedPreference.getLong(str, Double.doubleToRawLongBits(-1.0d)));
    }

    public float getFloat(String str, float f) {
        return this.mSharedPreference.getFloat(str, f);
    }

    public int getInt(String str) {
        return this.mSharedPreference.getInt(str, -1);
    }

    public long getLong(String str, long j) {
        try {
            return this.mSharedPreference.getLong(str, j);
        } catch (ClassCastException unused) {
            return 0;
        }
    }

    public String getString(String str, String str2) {
        return this.mSharedPreference.getString(str, str2);
    }

    public Set<? extends String> getStringSet(String str) {
        return this.mSharedPreference.getStringSet(str, new HashSet());
    }

    public boolean setBoolean(String str, boolean z) {
        return this.mSharedPreference.edit().putBoolean(str, z).commit();
    }

    public boolean setDouble(String str, double d) {
        return this.mSharedPreference.edit().putLong(str, Double.doubleToRawLongBits(d)).commit();
    }

    public boolean setFloat(String str, float f) {
        return this.mSharedPreference.edit().putFloat(str, f).commit();
    }

    public boolean setInt(String str, int i) {
        return this.mSharedPreference.edit().putInt(str, i).commit();
    }

    public boolean setLong(String str, long j) {
        return this.mSharedPreference.edit().putLong(str, j).commit();
    }

    public boolean setString(String str, String str2) {
        return this.mSharedPreference.edit().putString(str, str2).commit();
    }

    public boolean setStringSet(String str, Set<String> set) {
        return this.mSharedPreference.edit().putStringSet(str, set).commit();
    }
}
