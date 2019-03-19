package com.awakers.stillalive.ui.diary.render;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.awakers.stillalive.app.AwakersApplication;
import com.awakers.stillalive.base.BaseRepository;
import com.awakers.stillalive.data.repo.UserCapsule;
import com.awakers.stillalive.data.vo.Capsule;
import com.awakers.stillalive.data.vo.Mission;
import com.eschao.android.widget.pageflip.OnPageFlipListener;
import com.eschao.android.widget.pageflip.PageFlip;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.text.ParseException;
import java.util.Objects;

public abstract class PageRender implements OnPageFlipListener {
    static final int DRAW_ANIMATING_FRAME = 1;
    static final int DRAW_FULL_PAGE = 2;
    static final int DRAW_MOVING_FRAME = 0;
    static int MAX_PAGES = 0;
    public static final int MSG_ENDED_DRAWING_FRAME = 1;
    private static final String TAG = "PageRender";
    public static String[] dates = new String[31];
    public static ImageView[] imageViews = new ImageView[31];
    public static int missionSize;
    public static String[] missions = new String[31];
    public static String[] texts = new String[31];
    Bitmap mBackgroundBitmap;
    Bitmap mBitmap;
    Canvas mCanvas = new Canvas();
    Context mContext;
    int mDrawCommand = 2;
    Handler mHandler;
    PageFlip mPageFlip;
    int mPageNo;

    public abstract void onDrawFrame();

    public abstract boolean onEndedDrawing(int i);

    public abstract void onSurfaceChanged(int i, int i2);

    public PageRender(Context context, PageFlip pageFlip, Handler handler, int i) {
        this.mContext = context;
        this.mPageFlip = pageFlip;
        this.mPageNo = i;
        this.mPageFlip.setListener(this);
        this.mHandler = handler;
        AwakersApplication.appComponent.api().get("userCapsule", UserCapsule.class, new PageRender$$Lambda$0(this));
    }

    /* Access modifiers changed, original: final|synthetic */
    public final /* synthetic */ void lambda$new$0$PageRender(BaseRepository baseRepository) {
        UserCapsule.set((UserCapsule) baseRepository);
        for (Capsule capsule : UserCapsule.get().getUserCapsule()) {
            if (capsule.getCapsuleName().equals(AwakersApplication.currentCapsuleName)) {
                break;
            }
        }
        Capsule capsule2 = null;
        int i = 0;
        if (capsule2.getMission() == null) {
            missionSize = 0;
        } else {
            missionSize = ((Capsule) Objects.requireNonNull(capsule2)).getMission().size();
        }
        if (capsule2 != null) {
            int size = capsule2.getMission() != null ? capsule2.getMission().size() : 0;
            for (int startDate = capsule2.getStartDate(); startDate < capsule2.getStartDate() + size; startDate++) {
                System.out.println(startDate);
                System.out.println(((Mission) capsule2.getMission().get(String.valueOf(startDate))).getText());
                texts[i] = ((Mission) capsule2.getMission().get(String.valueOf(startDate))).getText();
                try {
                    dates[i] = AwakersApplication.appComponent.app().getDateFromDiff(i, String.valueOf(capsule2.getStartDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                ImageView imageView = new ImageView(this.mContext);
                Picasso.get().load(((Mission) capsule2.getMission().get(String.valueOf(startDate))).getUrl()).into(imageView);
                imageView.setScaleType(ScaleType.CENTER_CROP);
                imageViews[i] = imageView;
                System.out.println(texts[i]);
                i++;
            }
            FirebaseDatabase.getInstance().getReference("Mission").addValueEventListener(new ValueEventListener() {
                public void onCancelled(DatabaseError databaseError) {
                }

                public void onDataChange(DataSnapshot dataSnapshot) {
                    int i = 0;
                    for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                        if (dataSnapshot2.getKey().equals(PageRender.dates[i])) {
                            PageRender.missions[i] = String.valueOf(dataSnapshot2.getValue());
                            i++;
                        }
                    }
                }
            });
        }
    }

    public static int getMaxPages() {
        return MAX_PAGES;
    }

    public static void setMaxPages(int i) {
        MAX_PAGES = i;
    }

    public int getPageNo() {
        return this.mPageNo;
    }

    public void release() {
        if (this.mBitmap != null) {
            this.mBitmap.recycle();
            this.mBitmap = null;
        }
        this.mPageFlip.setListener(null);
        this.mCanvas = null;
        this.mBackgroundBitmap = null;
    }

    public boolean onFingerMove(float f, float f2) {
        this.mDrawCommand = 0;
        return true;
    }

    public boolean onFingerUp(float f, float f2) {
        if (!this.mPageFlip.animating()) {
            return false;
        }
        this.mDrawCommand = 1;
        return true;
    }

    /* Access modifiers changed, original: protected */
    public int calcFontSize(int i) {
        return (int) (((float) i) * this.mContext.getResources().getDisplayMetrics().scaledDensity);
    }
}
