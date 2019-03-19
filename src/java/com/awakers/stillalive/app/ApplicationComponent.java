package com.awakers.stillalive.app;

import com.awakers.stillalive.utill.api.FirebaseApi;
import com.awakers.stillalive.utill.pref.Preference;
import com.awakers.stillalive.utill.rx.RxSchedulers;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {ApplicationFactory.class})
public interface ApplicationComponent {
    FirebaseApi api();

    AwakersApplication app();

    Preference preference();

    RxSchedulers schedulers();
}
