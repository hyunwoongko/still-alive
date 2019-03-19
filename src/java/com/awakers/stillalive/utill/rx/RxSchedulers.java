package com.awakers.stillalive.utill.rx;

import rx.Scheduler;

public interface RxSchedulers {
    Scheduler androidThread();

    Scheduler backgroundThread();

    Scheduler computeThread();

    Scheduler ioThread();

    Scheduler networkThread();
}
