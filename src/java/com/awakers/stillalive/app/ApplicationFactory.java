package com.awakers.stillalive.app;

import com.awakers.stillalive.utill.api.FirebaseApi;
import com.awakers.stillalive.utill.pref.Preference;
import com.awakers.stillalive.utill.pref.PreferenceImpl;
import com.awakers.stillalive.utill.rx.RxSchedulers;
import com.awakers.stillalive.utill.rx.RxSchedulersImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class ApplicationFactory {
    private AwakersApplication awakersApp;

    public ApplicationFactory(AwakersApplication awakersApplication) {
        this.awakersApp = awakersApplication;
    }

    /* Access modifiers changed, original: 0000 */
    @Singleton
    @Provides
    public AwakersApplication provideAppContext() {
        return this.awakersApp;
    }

    /* Access modifiers changed, original: 0000 */
    @Singleton
    @Provides
    public RxSchedulers provideRxSchedulers() {
        return new RxSchedulersImpl();
    }

    /* Access modifiers changed, original: 0000 */
    @Singleton
    @Provides
    public Preference providePreference() {
        return new PreferenceImpl(this.awakersApp);
    }

    /* Access modifiers changed, original: 0000 */
    @Singleton
    @Provides
    public FirebaseApi api() {
        return new FirebaseApi();
    }
}
