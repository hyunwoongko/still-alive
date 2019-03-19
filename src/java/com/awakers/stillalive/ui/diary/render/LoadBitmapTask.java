package com.awakers.stillalive.ui.diary.render;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import com.awakers.stillalive.R;
import java.util.LinkedList;
import java.util.Random;

public final class LoadBitmapTask implements Runnable {
    static final int SMALL_BG = 0;
    private static final String TAG = "LoadBitmapTask";
    private static LoadBitmapTask __object;
    Random mBGRandom = new Random();
    int mBGSizeIndex = 0;
    boolean mIsLandscape = false;
    int[][] mPortraitBGs;
    int mPreRandomNo = 0;
    LinkedList<Bitmap> mQueue = new LinkedList();
    int mQueueMaxSize = 1;
    Resources mResources;
    boolean mStop = false;
    Thread mThread = null;

    public static LoadBitmapTask get(Context context) {
        if (__object == null) {
            __object = new LoadBitmapTask(context);
        }
        return __object;
    }

    private LoadBitmapTask(Context context) {
        this.mResources = context.getResources();
        int[][] iArr = new int[1][];
        iArr[0] = new int[]{R.drawable.background};
        this.mPortraitBGs = iArr;
    }

    public Bitmap getBitmap() {
        Bitmap bitmap;
        synchronized (this) {
            bitmap = this.mQueue.size() > 0 ? (Bitmap) this.mQueue.pop() : null;
            notify();
        }
        if (bitmap != null) {
            return bitmap;
        }
        Log.d(TAG, "Load bitmap instantly!");
        return getRandomBitmap();
    }

    public boolean isRunning() {
        return this.mThread != null && this.mThread.isAlive();
    }

    public synchronized void start() {
        if (this.mThread == null || !this.mThread.isAlive()) {
            this.mStop = false;
            this.mThread = new Thread(this);
            this.mThread.start();
        }
    }

    public void stop() {
        synchronized (this) {
            this.mStop = true;
            notify();
        }
        for (int i = 0; i < 3 && this.mThread.isAlive(); i++) {
            Log.d(TAG, "Waiting thread to stop ...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException unused) {
            }
        }
        if (this.mThread.isAlive()) {
            Log.d(TAG, "Thread is still alive after waited 1.5s!");
        }
    }

    public void set(int i, int i2, int i3) {
        this.mIsLandscape = i > i2;
        if (i3 != this.mQueueMaxSize) {
            this.mQueueMaxSize = i3;
        }
        if (this.mBGSizeIndex != 0) {
            this.mBGSizeIndex = 0;
            synchronized (this) {
                cleanQueue();
                notify();
            }
        }
    }

    private Bitmap getRandomBitmap() {
        Bitmap decodeResource = BitmapFactory.decodeResource(this.mResources, this.mPortraitBGs[this.mBGSizeIndex][0]);
        if (!this.mIsLandscape) {
            return decodeResource;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(90.0f);
        Bitmap createBitmap = Bitmap.createBitmap(decodeResource, 0, 0, decodeResource.getWidth(), decodeResource.getHeight(), matrix, true);
        decodeResource.recycle();
        return createBitmap;
    }

    private void cleanQueue() {
        for (int i = 0; i < this.mQueue.size(); i++) {
            ((Bitmap) this.mQueue.get(i)).recycle();
        }
        this.mQueue.clear();
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0042 */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:7|(2:9|(3:12|13|10))|14|15|16) */
    public void run() {
        /*
        r4 = this;
    L_0x0000:
        monitor-enter(r4);
        r0 = r4.mStop;	 Catch:{ all -> 0x0044 }
        if (r0 == 0) goto L_0x000a;
    L_0x0005:
        r4.cleanQueue();	 Catch:{ all -> 0x0044 }
        monitor-exit(r4);	 Catch:{ all -> 0x0044 }
        return;
    L_0x000a:
        r0 = r4.mQueue;	 Catch:{ all -> 0x0044 }
        r0 = r0.size();	 Catch:{ all -> 0x0044 }
        r1 = 1;
        if (r0 >= r1) goto L_0x003f;
    L_0x0013:
        r0 = 0;
    L_0x0014:
        r1 = r4.mQueueMaxSize;	 Catch:{ all -> 0x0044 }
        if (r0 >= r1) goto L_0x003f;
    L_0x0018:
        r1 = "LoadBitmapTask";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0044 }
        r2.<init>();	 Catch:{ all -> 0x0044 }
        r3 = "Load Queue:";
        r2.append(r3);	 Catch:{ all -> 0x0044 }
        r2.append(r0);	 Catch:{ all -> 0x0044 }
        r3 = " in background!";
        r2.append(r3);	 Catch:{ all -> 0x0044 }
        r2 = r2.toString();	 Catch:{ all -> 0x0044 }
        android.util.Log.d(r1, r2);	 Catch:{ all -> 0x0044 }
        r1 = r4.mQueue;	 Catch:{ all -> 0x0044 }
        r2 = r4.getRandomBitmap();	 Catch:{ all -> 0x0044 }
        r1.push(r2);	 Catch:{ all -> 0x0044 }
        r0 = r0 + 1;
        goto L_0x0014;
    L_0x003f:
        r4.wait();	 Catch:{ InterruptedException -> 0x0042 }
    L_0x0042:
        monitor-exit(r4);	 Catch:{ all -> 0x0044 }
        goto L_0x0000;
    L_0x0044:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0044 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.awakers.stillalive.ui.diary.render.LoadBitmapTask.run():void");
    }
}
