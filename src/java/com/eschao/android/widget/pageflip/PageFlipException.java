package com.eschao.android.widget.pageflip;

public class PageFlipException extends Exception {
    public PageFlipException(String str) {
        super(str);
    }

    public PageFlipException(String str, Throwable th) {
        super(str, th);
    }

    public PageFlipException(Throwable th) {
        super(th);
    }
}
