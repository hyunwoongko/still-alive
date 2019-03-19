package net.glxn.qrgen.android;

import android.graphics.Bitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import net.glxn.qrgen.core.AbstractQRCode;
import net.glxn.qrgen.core.exception.QRGenerationException;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.core.scheme.Schema;

public class QRCode extends AbstractQRCode {
    private MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();
    protected final String text;

    protected QRCode(String str) {
        this.text = str;
        this.qrWriter = new QRCodeWriter();
    }

    public static QRCode from(String str) {
        return new QRCode(str);
    }

    public static QRCode from(Schema schema) {
        return new QRCode(schema.generateString());
    }

    public QRCode to(ImageType imageType) {
        this.imageType = imageType;
        return this;
    }

    public QRCode withColor(int i, int i2) {
        this.matrixToImageConfig = new MatrixToImageConfig(i, i2);
        return this;
    }

    public QRCode withSize(int i, int i2) {
        this.width = i;
        this.height = i2;
        return this;
    }

    public QRCode withCharset(String str) {
        return withHint(EncodeHintType.CHARACTER_SET, str);
    }

    public QRCode withErrorCorrection(ErrorCorrectionLevel errorCorrectionLevel) {
        return withHint(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
    }

    public QRCode withHint(EncodeHintType encodeHintType, Object obj) {
        this.hints.put(encodeHintType, obj);
        return this;
    }

    public Bitmap bitmap() {
        try {
            return MatrixToImageWriter.toBitmap(createMatrix(this.text), this.matrixToImageConfig);
        } catch (WriterException e) {
            throw new QRGenerationException("Failed to create QR image from text due to underlying exception", e);
        }
    }

    public File file() {
        try {
            File createTempFile = createTempFile();
            MatrixToImageWriter.writeToFile(createMatrix(this.text), this.imageType.toString(), createTempFile, this.matrixToImageConfig);
            return createTempFile;
        } catch (Exception e) {
            throw new QRGenerationException("Failed to create QR image from text due to underlying exception", e);
        }
    }

    public File file(String str) {
        try {
            File createTempFile = createTempFile(str);
            MatrixToImageWriter.writeToFile(createMatrix(this.text), this.imageType.toString(), createTempFile, this.matrixToImageConfig);
            return createTempFile;
        } catch (Exception e) {
            throw new QRGenerationException("Failed to create QR image from text due to underlying exception", e);
        }
    }

    /* Access modifiers changed, original: protected */
    public void writeToStream(OutputStream outputStream) throws IOException, WriterException {
        MatrixToImageWriter.writeToStream(createMatrix(this.text), this.imageType.toString(), outputStream, this.matrixToImageConfig);
    }
}
