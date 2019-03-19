package net.glxn.qrgen.android;

import android.graphics.Bitmap;
import com.google.zxing.common.BitMatrix;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class MatrixToImageWriter {
    private static final MatrixToImageConfig DEFAULT_CONFIG = new MatrixToImageConfig();

    private MatrixToImageWriter() {
    }

    public static Bitmap toBitmap(BitMatrix bitMatrix) {
        return toBitmap(bitMatrix, DEFAULT_CONFIG);
    }

    public static Bitmap toBitmap(BitMatrix bitMatrix, MatrixToImageConfig matrixToImageConfig) {
        int pixelOnColor = matrixToImageConfig.getPixelOnColor();
        int pixelOffColor = matrixToImageConfig.getPixelOffColor();
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int[] iArr = new int[(width * height)];
        for (int i = 0; i < height; i++) {
            int i2 = i * width;
            for (int i3 = 0; i3 < width; i3++) {
                iArr[i2 + i3] = bitMatrix.get(i3, i) ? pixelOnColor : pixelOffColor;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, matrixToImageConfig.getBufferedImageColorModel());
        createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return createBitmap;
    }

    public static void writeToFile(BitMatrix bitMatrix, String str, File file) throws IOException {
        writeToFile(bitMatrix, str, file, DEFAULT_CONFIG);
    }

    public static void writeToFile(BitMatrix bitMatrix, String str, File file, MatrixToImageConfig matrixToImageConfig) throws IOException {
        if (!BitmapIO.write(toBitmap(bitMatrix, matrixToImageConfig), str, file)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Could not write an image of format ");
            stringBuilder.append(str);
            stringBuilder.append(" to ");
            stringBuilder.append(file);
            throw new IOException(stringBuilder.toString());
        }
    }

    public static void writeToStream(BitMatrix bitMatrix, String str, OutputStream outputStream) throws IOException {
        writeToStream(bitMatrix, str, outputStream, DEFAULT_CONFIG);
    }

    public static void writeToStream(BitMatrix bitMatrix, String str, OutputStream outputStream, MatrixToImageConfig matrixToImageConfig) throws IOException {
        if (!BitmapIO.write(toBitmap(bitMatrix, matrixToImageConfig), str, outputStream)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Could not write an image of format ");
            stringBuilder.append(str);
            throw new IOException(stringBuilder.toString());
        }
    }
}
