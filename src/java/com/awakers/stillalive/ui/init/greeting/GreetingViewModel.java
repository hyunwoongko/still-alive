package com.awakers.stillalive.ui.init.greeting;

import android.databinding.ObservableField;
import android.os.Process;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.base.BaseViewModel;
import com.awakers.stillalive.data.repo.UserCapsule;
import com.awakers.stillalive.data.vo.Capsule;
import com.awakers.stillalive.data.vo.Greeting;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class GreetingViewModel extends BaseViewModel {
    public ObservableField<String> date;
    public ObservableField<String> greeting1;
    public ObservableField<String> greeting2;
    private Greeting greetingModel;
    private GreetingNavigator navigator;
    private CompositeSubscription subscription;

    public GreetingViewModel(WeakReference<GreetingNavigator> weakReference, CompositeSubscription compositeSubscription, Greeting greeting) {
        synchronized (this) {
            this.greeting1 = new ObservableField();
            this.greeting2 = new ObservableField();
            this.date = new ObservableField();
            this.navigator = (GreetingNavigator) weakReference.get();
            this.subscription = compositeSubscription;
            this.greetingModel = greeting;
        }
    }

    public void showGreeting() {
        if (UserCapsule.get().getUserCapsule() == null || !((Capsule) UserCapsule.get().getUserCapsule().get(UserCapsule.get().getUserCapsule().size() - 1)).isOnGoing()) {
            this.date.set("생존 0일째");
        } else {
            try {
                ObservableField observableField = this.date;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("생존 ");
                stringBuilder.append(AwakersApplication.appComponent.app().getDate(String.valueOf(((Capsule) UserCapsule.get().getUserCapsule().get(UserCapsule.get().getUserCapsule().size() - 1)).getStartDate()), String.valueOf(AwakersApplication.appComponent.app().getCurrentDate() + 1)));
                stringBuilder.append("일째");
                observableField.set(stringBuilder.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (UserCapsule.get().getUserCapsule() == null || !((Capsule) UserCapsule.get().getUserCapsule().get(UserCapsule.get().getUserCapsule().size() - 1)).isOnGoing()) {
            this.greeting1.set("캡슐을 생성하지 않으셨군요 !");
            this.greeting2.set("캡슐 설정화면으로 이동합니다.");
            moveNextPage();
            return;
        }
        this.greeting1.set("오늘도 살아계신가요?");
        this.greeting2.set(this.greetingModel.getMsg()[new Random().nextInt(this.greetingModel.getMsg().length)]);
        moveNextPage();
    }

    public void showGreetingObservable() {
        this.subscription.add(Observable.just(Boolean.valueOf(app().checkNetwork())).subscribeOn(schedulers().networkThread()).observeOn(schedulers().androidThread()).subscribe(new GreetingViewModel$$Lambda$0(this)));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$showGreetingObservable$0$GreetingViewModel(Boolean bool) {
        if (bool.booleanValue()) {
            showGreeting();
            return;
        }
        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    private void moveNextPage() {
        this.subscription.add(Observable.just(Integer.valueOf(0)).delay(3, TimeUnit.SECONDS).observeOn(schedulers().androidThread()).subscribeOn(schedulers().backgroundThread()).subscribe(new GreetingViewModel$$Lambda$1(this)));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$moveNextPage$1$GreetingViewModel(Integer num) {
        this.navigator.replaceToMain(false);
    }

    public void onCleared() {
        if (this.subscription != null) {
            this.subscription.unsubscribe();
            this.subscription = null;
        }
        this.navigator = null;
    }
}
