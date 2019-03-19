package net.glxn.qrgen.android;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import net.glxn.qrgen.core.image.ImageType;

public class BitmapIO {
    public static boolean write(Bitmap bitmap, String str, OutputStream outputStream) throws IOException {
        if (str.equals(ImageType.PNG)) {
            return bitmap.compress(CompressFormat.PNG, 80, outputStream);
        }
        if (str.equals(ImageType.JPG)) {
            return bitmap.compress(CompressFormat.JPEG, 80, outputStream);
        }
        return BmpUtil.save(bitmap, outputStream);
    }

    public static boolean write(Bitmap bitmap, String str, File file) throws IOException {
        IOException e;
        Throwable th;
        OutputStream outputStream = null;
        try {
            OutputStream fileOutputStream = new FileOutputStream(file);
            try {
                boolean write = write(bitmap, str, fileOutputStream);
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
                return write;
            } catch (IOException e2) {
                e = e2;
                outputStream = fileOutputStream;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                outputStream = fileOutputStream;
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            throw e;
        }
    }
}
