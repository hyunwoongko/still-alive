package net.glxn.qrgen.core.exception;

public class QRGenerationException extends RuntimeException {
    public QRGenerationException(String str, Throwable th) {
        super(str, th);
    }
}
