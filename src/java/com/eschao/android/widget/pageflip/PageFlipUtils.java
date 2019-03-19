package com.eschao.android.widget.pageflip;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;

public class PageFlipUtils {
    public static int computeAverageColor(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = width / 3;
        int i3 = height / 3;
        if (i > i2) {
            i = i2;
        }
        if (i > i3) {
            i = i3;
        }
        width -= i;
        height -= i;
        i2 = width / 2;
        i3 = height / 2;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < i; i8++) {
            int pixel = bitmap.getPixel(i8, i8);
            i4 += Color.red(pixel);
            i5 += Color.blue(pixel);
            i6 += Color.green(pixel);
            i7 += Color.alpha(pixel);
            pixel = bitmap.getPixel(i2 + i8, i3 + i8);
            i4 += Color.red(pixel);
            i5 += Color.blue(pixel);
            i6 += Color.green(pixel);
            i7 += Color.alpha(pixel);
            pixel = width + i8;
            int pixel2 = bitmap.getPixel(pixel, i8);
            i4 += Color.red(pixel2);
            i5 += Color.blue(pixel2);
            i6 += Color.green(pixel2);
            i7 += Color.alpha(pixel2);
            pixel2 = height + i8;
            int pixel3 = bitmap.getPixel(i8, pixel2);
            i4 += Color.red(pixel3);
            i5 += Color.blue(pixel3);
            i6 += Color.green(pixel3);
            i7 += Color.alpha(pixel3);
            pixel = bitmap.getPixel(pixel, pixel2);
            i4 += Color.red(pixel);
            i5 += Color.blue(pixel);
            i6 += Color.green(pixel);
            i7 += Color.alpha(pixel);
        }
        i *= 5;
        return Color.argb(i7 / i, i4 / i, i6 / i, i5 / i);
    }

    public static Bitmap createGradientBitmap() {
        Canvas canvas = new Canvas();
        Paint paint = new Paint(1);
        Bitmap createBitmap = Bitmap.createBitmap(256, 1, Config.ARGB_8888);
        canvas.setBitmap(createBitmap);
        paint.setShader(new LinearGradient(0.0f, 0.0f, 256.0f, 0.0f, new int[]{16777215, 603979776, 605032464, 1207959552}, new float[]{0.5f, 0.9f, 0.94f, 1.0f}, TileMode.CLAMP));
        canvas.drawRect(0.0f, 0.0f, 256.0f, 1.0f, paint);
        return createBitmap;
    }
}
