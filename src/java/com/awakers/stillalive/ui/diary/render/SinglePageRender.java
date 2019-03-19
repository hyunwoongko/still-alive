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

public class SinglePageRender extends PageRender {
    public SinglePageRender(Context context, PageFlip pageFlip, Handler handler, int i) {
        super(context, pageFlip, handler, i);
    }

    public void onDrawFrame() {
        this.mPageFlip.deleteUnusedTextures();
        Page firstPage = this.mPageFlip.getFirstPage();
        if (this.mDrawCommand == 0 || this.mDrawCommand == 1) {
            if (this.mPageFlip.getFlipState() == PageFlipState.FORWARD_FLIP) {
                if (!firstPage.isSecondTextureSet()) {
                    drawPage(this.mPageNo + 1);
                    firstPage.setSecondTexture(this.mBitmap);
                }
            } else if (!firstPage.isFirstTextureSet()) {
                int i = this.mPageNo - 1;
                this.mPageNo = i;
                drawPage(i);
                firstPage.setFirstTexture(this.mBitmap);
            }
            this.mPageFlip.drawFlipFrame();
        } else if (this.mDrawCommand == 2) {
            if (!firstPage.isFirstTextureSet()) {
                drawPage(this.mPageNo);
                firstPage.setFirstTexture(this.mBitmap);
            }
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
        this.mBitmap = Bitmap.createBitmap((int) firstPage.width(), (int) firstPage.height(), Config.ARGB_8888);
        this.mCanvas.setBitmap(this.mBitmap);
        LoadBitmapTask.get(this.mContext).set(i, i2, 1);
    }

    public boolean onEndedDrawing(int i) {
        if (i != 1) {
            return false;
        }
        if (this.mPageFlip.animating()) {
            this.mDrawCommand = 1;
            return true;
        }
        PageFlipState flipState = this.mPageFlip.getFlipState();
        if (flipState != PageFlipState.END_WITH_BACKWARD && flipState == PageFlipState.END_WITH_FORWARD) {
            this.mPageFlip.getFirstPage().setFirstTextureWithSecond();
            this.mPageNo++;
        }
        this.mDrawCommand = 2;
        return true;
    }

    @SuppressLint({"WrongCall"})
    private void drawPage(int i) {
        int width = this.mCanvas.getWidth();
        int height = this.mCanvas.getHeight();
        new Paint().setFilterBitmap(true);
        Bitmap bitmap = LoadBitmapTask.get(this.mContext).getBitmap();
        int i2 = 0;
        Rect rect = new Rect(0, 0, width, height);
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
        bitmap.recycle();
    }

    public boolean canFlipForward() {
        return this.mPageNo < MAX_PAGES;
    }

    public boolean canFlipBackward() {
        if (this.mPageNo <= 1) {
            return false;
        }
        this.mPageFlip.getFirstPage().setSecondTextureWithFirst();
        return true;
    }
}
