package com.awakers.stillalive.utill.qr;

import android.widget.ImageView;
import net.glxn.qrgen.android.QRCode;

public class QRGenerator {
    static void generate(ImageView imageView, String str) {
        imageView.setImageBitmap(QRCode.from(str).bitmap());
    }
}
