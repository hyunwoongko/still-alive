package com.awakers.stillalive.utill.rx;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxSchedulersImpl implements RxSchedulers {
    public static Scheduler BACKGROUND_SCHEDULERS = Schedulers.from(backgroundExecutor);
    public static Scheduler NETWORK_SCHEDULERS = Schedulers.from(networkExecutor);
    public static Executor backgroundExecutor = Executors.newCachedThreadPool();
    public static Executor networkExecutor = Executors.newCachedThreadPool();

    public Scheduler backgroundThread() {
        return BACKGROUND_SCHEDULERS;
    }

    public Scheduler ioThread() {
        return Schedulers.io();
    }

    public Scheduler computeThread() {
        return Schedulers.computation();
    }

    public Scheduler androidThread() {
        return AndroidSchedulers.mainThread();
    }

    public Scheduler networkThread() {
        return NETWORK_SCHEDULERS;
    }
}
