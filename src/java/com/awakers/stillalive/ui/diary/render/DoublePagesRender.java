package com.awakers.stillalive.ui.diary.render;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import com.awakers.stillalive.utill.canvas.CanvasHelper;
import com.eschao.android.widget.pageflip.Page;
import com.eschao.android.widget.pageflip.PageFlip;
import com.eschao.android.widget.pageflip.PageFlipState;

public class DoublePagesRender extends PageRender {
    public boolean canFlipBackward() {
        return false;
    }

    public DoublePagesRender(Context context, PageFlip pageFlip, Handler handler, int i) {
        super(context, pageFlip, handler, i);
    }

    public void onDrawFrame() {
        this.mPageFlip.deleteUnusedTextures();
        Page firstPage = this.mPageFlip.getFirstPage();
        Page secondPage = this.mPageFlip.getSecondPage();
        if (!firstPage.isFirstTextureSet()) {
            drawPage(firstPage.isLeftPage() ? this.mPageNo : this.mPageNo + 1);
            firstPage.setFirstTexture(this.mBitmap);
        }
        if (!secondPage.isFirstTextureSet()) {
            drawPage(secondPage.isLeftPage() ? this.mPageNo : this.mPageNo + 1);
            secondPage.setFirstTexture(this.mBitmap);
        }
        if (this.mDrawCommand == 0 || this.mDrawCommand == 1) {
            if (!firstPage.isBackTextureSet()) {
                drawPage(firstPage.isLeftPage() ? this.mPageNo - 1 : this.mPageNo + 2);
                firstPage.setBackTexture(this.mBitmap);
            }
            if (!firstPage.isSecondTextureSet()) {
                drawPage(firstPage.isLeftPage() ? this.mPageNo - 2 : this.mPageNo + 3);
                firstPage.setSecondTexture(this.mBitmap);
            }
            this.mPageFlip.drawFlipFrame();
        } else if (this.mDrawCommand == 2) {
            this.mPageFlip.drawPageFrame();
        }
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.arg1 = this.mDrawCommand;
        this.mHandler.sendMessage(obtain);
    }

    public void onSurfaceChanged(int i, int i2) {
        if (this.mBackgroundBitmap != null) {
            this.mBackgroundBitmap.recycle();
        }
        if (this.mBitmap != null) {
            this.mBitmap.recycle();
        }
        Page firstPage = this.mPageFlip.getFirstPage();
        i2 = (int) firstPage.width();
        i = (int) firstPage.height();
        this.mBitmap = Bitmap.createBitmap(i2, i, Config.ARGB_8888);
        this.mCanvas.setBitmap(this.mBitmap);
        LoadBitmapTask.get(this.mContext).set(i2, i, 2);
    }

    public boolean onEndedDrawing(int i) {
        if (i != 1) {
            return false;
        }
        if (this.mPageFlip.animating()) {
            this.mDrawCommand = 1;
            return true;
        }
        if (this.mPageFlip.getFlipState() == PageFlipState.END_WITH_FORWARD) {
            Page firstPage = this.mPageFlip.getFirstPage();
            this.mPageFlip.getSecondPage().swapTexturesWithPage(firstPage);
            if (firstPage.isLeftPage()) {
                this.mPageNo -= 2;
            } else {
                this.mPageNo += 2;
            }
        }
        this.mDrawCommand = 2;
        return true;
    }

    @SuppressLint({"WrongCall"})
    private void drawPage(int i) {
        int width = this.mCanvas.getWidth();
        int height = this.mCanvas.getHeight();
        Paint paint = new Paint();
        paint.setFilterBitmap(true);
        Bitmap bitmap = LoadBitmapTask.get(this.mContext).getBitmap();
        int i2 = 0;
        Rect rect = new Rect(0, 0, width, height);
        if (width > height) {
            this.mCanvas.rotate(90.0f);
            this.mCanvas.drawBitmap(bitmap, null, rect, paint);
            this.mCanvas.rotate(-90.0f);
        } else {
            this.mCanvas.drawBitmap(bitmap, null, rect, paint);
        }
        bitmap.recycle();
        CanvasHelper canvasHelper = new CanvasHelper(this.mContext, this.mCanvas);
        while (i2 < missionSize + 1) {
            if (i == i2) {
                int i3 = i2 - 1;
                canvasHelper.setText(texts[i3]);
                canvasHelper.setMission(missions[i3]);
                canvasHelper.setImage(imageViews[i3]);
                canvasHelper.onDraw(this.mCanvas);
                canvasHelper.image.invalidate();
            }
            i2++;
        }
    }

    public boolean canFlipForward() {
        boolean z = false;
        if (this.mPageFlip.getFirstPage().isLeftPage()) {
            if (this.mPageNo > 1) {
                z = true;
            }
            return z;
        }
        if (this.mPageNo + 2 <= MAX_PAGES) {
            z = true;
        }
        return z;
    }
}
