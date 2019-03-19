package com.awakers.stillalive.ui.diary;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import com.awakers.stillalive.base.BaseActivity;
import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.data.repo.UserCapsule;
import com.awakers.stillalive.data.vo.Capsule;
import com.awakers.stillalive.ui.diary.render.LoadBitmapTask;
import com.awakers.stillalive.ui.diary.render.PageFlipView;
import com.awakers.stillalive.ui.diary.render.PageRender;

public class DiaryActivity extends BaseActivity implements OnGestureListener {
    GestureDetector mGestureDetector;
    PageFlipView mPageFlipView;

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPageFlipView = new PageFlipView(this);
        api().get("userCapsule", UserCapsule.class, new DiaryActivity$$Lambda$0(this));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$onCreate$0$DiaryActivity(BaseRepository baseRepository) {
        UserCapsule.set((UserCapsule) baseRepository);
        PageRender.setMaxPages(((Capsule) UserCapsule.get().getUserCapsule().get(UserCapsule.get().getUserCapsule().size() + -1)).getMission() != null ? ((Capsule) UserCapsule.get().getUserCapsule().get(UserCapsule.get().getUserCapsule().size() - 1)).getMission().size() : 0);
        this.mGestureDetector = new GestureDetector(this, this);
        this.mPageFlipView.setSystemUiVisibility(2822);
        this.mPageFlipView.setAnimateDuration(4000);
        setContentView(this.mPageFlipView);
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        LoadBitmapTask.get(this).start();
        this.mPageFlipView.onResume();
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        this.mPageFlipView.onPause();
        LoadBitmapTask.get(this).stop();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return this.mGestureDetector.onTouchEvent(motionEvent);
        }
        this.mPageFlipView.onFingerUp(motionEvent.getX(), motionEvent.getY());
        return true;
    }

    public boolean onDown(MotionEvent motionEvent) {
        this.mPageFlipView.onFingerDown(motionEvent.getX(), motionEvent.getY());
        return true;
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.mPageFlipView.onFingerMove(motionEvent2.getX(), motionEvent2.getY());
        return true;
    }
}
