package com.awakers.stillalive.ui.main.home;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.base.BaseViewModel;
import com.awakers.stillalive.data.repo.UserCapsule;
import com.awakers.stillalive.data.vo.Capsule;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class HomeViewModel extends BaseViewModel {
    public static ObservableBoolean clickable;
    public static int date;
    public static List<String> messeage;
    private HomeNavigator navigator;
    private CompositeSubscription subscription;
    public ObservableField<String> text = new ObservableField("");

    public HomeViewModel(WeakReference<HomeNavigator> weakReference, CompositeSubscription compositeSubscription) {
        this.navigator = (HomeNavigator) weakReference.get();
        this.subscription = compositeSubscription;
        clickable = new ObservableBoolean(true);
        try {
            date = AwakersApplication.appComponent.app().getDate(String.valueOf(((Capsule) UserCapsule.get().getUserCapsule().get(UserCapsule.get().getUserCapsule().size() - 1)).getStartDate()), String.valueOf(AwakersApplication.appComponent.app().getCurrentDate() + 1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public synchronized void loadImage() {
        this.subscription.add(Observable.just(Boolean.valueOf(app().checkNetwork())).filter(HomeViewModel$$Lambda$0.$instance).subscribeOn(schedulers().networkThread()).observeOn(schedulers().androidThread()).subscribe(new HomeViewModel$$Lambda$1(this)));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$loadImage$1$HomeViewModel(Boolean bool) {
        if (bool.booleanValue()) {
            this.navigator.loadImage();
        }
    }

    public void onTextClicked(View view) {
        if (Objects.equals(this.text.get(), "여기를 눌러 미션을 제출해보세요 !")) {
            CompositeSubscription compositeSubscription = this.subscription;
            HomeNavigator homeNavigator = this.navigator;
            homeNavigator.getClass();
            compositeSubscription.add(onButtonClicked(HomeViewModel$$Lambda$2.get$Lambda(homeNavigator), false));
        }
    }

    public void onObjectClicked(View view) {
        api().get("userCapsule", UserCapsule.class, new HomeViewModel$$Lambda$3(this));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$onObjectClicked$3$HomeViewModel(BaseRepository baseRepository) {
        UserCapsule.set((UserCapsule) baseRepository);
        try {
            Capsule capsule = (Capsule) UserCapsule.get().getUserCapsule().get(UserCapsule.get().getUserCapsule().size() - 1);
            int i = -1;
            if (capsule.getMission() != null) {
                i = capsule.getMission().size() - 1;
            }
            boolean equals = String.valueOf(app().getCurrentDate()).equals(app().getDateFromDiff(i, String.valueOf(capsule.getStartDate())));
            if (equals) {
                clickable.set(true);
            }
            String[] strArr;
            StringBuilder stringBuilder;
            StringBuilder stringBuilder2;
            if (!equals) {
                messeage = Arrays.asList(new String[]{"여기를 눌러 미션을 제출해보세요 !", "여기를 눌러 미션을 제출해보세요 !"});
            } else if (date < 6) {
                strArr = new String[7];
                stringBuilder = new StringBuilder();
                stringBuilder.append("제 나이는 ");
                stringBuilder.append(date);
                stringBuilder.append("살이에요.");
                strArr[0] = stringBuilder.toString();
                strArr[1] = "오늘 미션은 확인 하셨나요?";
                strArr[2] = "저는 미션을 먹고 자란답니다.";
                strArr[3] = "어서 무럭무럭 크고 싶어요.";
                strArr[4] = "저를 무럭무럭 키워주세요.";
                strArr[5] = "한달이 지나면 무슨일이 일어날지 몰라요,";
                strArr[6] = "친구들과 함께 하면 더 재미있어요,";
                messeage = Arrays.asList(strArr);
            } else if (date < 12) {
                strArr = new String[10];
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("제 나이는 ");
                stringBuilder2.append(date);
                stringBuilder2.append("살이에요.");
                strArr[0] = stringBuilder2.toString();
                strArr[1] = "오늘 미션은 확인 하셨나요?";
                strArr[2] = "저는 미션을 먹고 자란답니다.";
                strArr[3] = "시간이 지나서 몸이 자랐어요";
                strArr[4] = "나는 어떤 모습이 될까요?";
                strArr[5] = "어서 무럭무럭 크고 싶어요.";
                strArr[6] = "저를 무럭무럭 키워주세요.";
                strArr[7] = "한달이 지나면 무슨일이 일어날지 몰라요,";
                strArr[8] = "친구들과 함께 하면 더 재미있어요,";
                strArr[9] = "오늘의 미션은 무엇일까요?";
                messeage = Arrays.asList(strArr);
            } else if (date < 18) {
                strArr = new String[12];
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("제 나이는 ");
                stringBuilder2.append(date);
                stringBuilder2.append("살이에요.");
                strArr[0] = stringBuilder2.toString();
                strArr[1] = "오늘 미션은 확인 하셨나요?";
                strArr[2] = "저는 미션을 먹고 자란답니다.";
                strArr[3] = "나는 어떤 모습이 될까요?";
                strArr[4] = "한달이 지나면 무슨일이 일어날지 몰라요,";
                strArr[5] = "친구들과 함께 하면 더 재미있어요,";
                strArr[6] = "오늘의 미션은 무엇일까요?";
                strArr[7] = "캡슐에 대해서 아시나요?";
                strArr[8] = "타임 캡슐? 그게 뭘까요?";
                strArr[9] = "still alive에는 여러가지 테마가 있어요 !";
                strArr[10] = "몸이 더 많이 자랐아요 ! ";
                strArr[11] = "쑥쑥 크고 있어요 !";
                messeage = Arrays.asList(strArr);
            } else if (date < 24) {
                strArr = new String[14];
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("제 나이는 ");
                stringBuilder3.append(date);
                stringBuilder3.append("살이에요.");
                strArr[0] = stringBuilder3.toString();
                strArr[1] = "오늘 미션은 확인 하셨나요?";
                strArr[2] = "오늘의 미션은 무엇일까요?";
                strArr[3] = "캡슐에 대해서 아시나요?";
                strArr[4] = "타임 캡슐? 그게 뭘까요?";
                strArr[5] = "still alive에는 여러가지 테마가 있어요 !";
                strArr[6] = "몸이 더 많이 자랐아요 ! ";
                strArr[7] = "쑥쑥 크고 있어요 !";
                strArr[8] = "오늘은 과연 무슨미션일까?";
                strArr[9] = "어서 더 자라고싶어요 !";
                strArr[10] = "미션에 실패하면 무시무시한 벌칙이...";
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append("벌써 ");
                stringBuilder4.append(date);
                stringBuilder4.append("일이나 지났어요 !");
                strArr[11] = stringBuilder4.toString();
                strArr[12] = "한달 뒤에는 무슨 일이 일어날까?";
                strArr[13] = "캡슐이 뭘까요? 너무 궁금해요 !";
                messeage = Arrays.asList(strArr);
            } else if (date < 30) {
                strArr = new String[11];
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("제 나이는 ");
                stringBuilder2.append(date);
                stringBuilder2.append("살이에요.");
                strArr[0] = stringBuilder2.toString();
                strArr[1] = "오늘 미션은 확인 하셨나요?";
                strArr[2] = "오늘의 미션은 무엇일까요?";
                strArr[3] = "오늘은 과연 무슨미션일까?";
                strArr[4] = "미션에 실패하면 무시무시한 벌칙이...";
                StringBuilder stringBuilder5 = new StringBuilder();
                stringBuilder5.append("벌써 ");
                stringBuilder5.append(date);
                stringBuilder5.append("일이나 지났어요 !");
                strArr[5] = stringBuilder5.toString();
                strArr[6] = "얼마 안남았어요 !";
                strArr[7] = "캡슐이 뭘까요? 너무 궁금해요 !";
                strArr[8] = "거의 다왔어요.";
                strArr[9] = "조금만 더 힘내요 !";
                strArr[10] = "캡슐이 뭘까? 너무 궁금해요";
                messeage = Arrays.asList(strArr);
            } else {
                strArr = new String[7];
                stringBuilder = new StringBuilder();
                stringBuilder.append("제 나이는 ");
                stringBuilder.append(date);
                stringBuilder.append("살이에요.");
                strArr[0] = stringBuilder.toString();
                strArr[1] = "모든 미션에 성공했어요 !";
                strArr[2] = "축하합니다. 짝짝짝 !";
                strArr[3] = "어서 캡슐을 확인해보세요.";
                strArr[4] = "한달동안 나는 살아있었어요.";
                strArr[5] = "미션을 성공해줘서 고마워요.";
                strArr[6] = "반짝반짝 캡슐이 너무 예쁘지 않나요?";
                messeage = Arrays.asList(strArr);
            }
            this.text.set(messeage.get(new Random().nextInt(messeage.size() - 1)));
            clickable.set(false);
            Observable.just(Integer.valueOf(0)).delay(4, TimeUnit.SECONDS).subscribeOn(schedulers().backgroundThread()).observeOn(schedulers().androidThread()).subscribe(new HomeViewModel$$Lambda$4(this));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$null$2$HomeViewModel(Integer num) {
        this.text.set("");
        clickable.set(true);
    }

    public void onCleared() {
        if (this.subscription != null) {
            this.subscription.unsubscribe();
            this.subscription = null;
        }
        this.navigator = null;
    }
}
