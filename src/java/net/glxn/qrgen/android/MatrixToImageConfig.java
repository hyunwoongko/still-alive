package net.glxn.qrgen.android;

import android.graphics.Bitmap.Config;

public class MatrixToImageConfig {
    public static final int BLACK = -16777216;
    public static final int WHITE = -1;
    private final int offColor;
    private final int onColor;

    public MatrixToImageConfig() {
        this(BLACK, -1);
    }

    public MatrixToImageConfig(int i, int i2) {
        this.onColor = i;
        this.offColor = i2;
    }

    public int getPixelOnColor() {
        return this.onColor;
    }

    public int getPixelOffColor() {
        return this.offColor;
    }

    /* Access modifiers changed, original: 0000 */
    public Config getBufferedImageColorModel() {
        return Config.ARGB_8888;
    }
}
