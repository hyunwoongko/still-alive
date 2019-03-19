package com.awakers.stillalive.app;

import android.app.Application;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.ActivityCompat;
import android.widget.ImageView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AwakersApplication extends Application {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static String[] Permission = new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    public static ApplicationComponent appComponent = null;
    public static String currentCapsuleName = "";

    public void onCreate() {
        super.onCreate();
        initAppComponent();
        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
    }

    private void initAppComponent() {
        appComponent = DaggerApplicationComponent.builder().applicationFactory(new ApplicationFactory(this)).build();
    }

    public boolean checkNetwork() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @BindingAdapter({"imgSrc"})
    public static void setImgSrc(ImageView imageView, String str) {
        if (str != null) {
            Picasso.get().load(str).into(imageView);
        }
    }

    public boolean hasPermission(Context context, String... strArr) {
        if (!(context == null || strArr == null)) {
            for (String checkSelfPermission : strArr) {
                if (ActivityCompat.checkSelfPermission(context, checkSelfPermission) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void onTerminate() {
        super.onTerminate();
    }

    public String getDateFromDiff(int i, String str) throws ParseException {
        Date parse = new SimpleDateFormat("yyyyMMdd", Locale.KOREA).parse(str);
        Calendar instance = Calendar.getInstance();
        instance.setTime(parse);
        instance.add(5, i);
        int i2 = instance.get(1);
        int i3 = instance.get(2) + 1;
        i = instance.get(5);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i2);
        if (i3 < 10) {
            stringBuffer.append("0");
        }
        stringBuffer.append(i3);
        if (i < 10) {
            stringBuffer.append("0");
        }
        stringBuffer.append(i);
        return stringBuffer.toString();
    }

    public int getDate(String str, String str2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        return (int) ((simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime()) / 86400000);
    }

    public int getTime(String str, String str2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss", Locale.KOREA);
        return (int) ((simpleDateFormat.parse(str).getTime() - simpleDateFormat.parse(str2).getTime()) / 1000);
    }

    public int getCurrentDate() {
        return Integer.parseInt(new SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(new Date()));
    }

    public int getCurrrentTime() {
        return Integer.parseInt(new SimpleDateFormat("hhmmss", Locale.KOREA).format(new Date(System.currentTimeMillis())));
    }

    public boolean isCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }
}
