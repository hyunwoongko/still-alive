package net.glxn.qrgen.android;

import java.io.IOException;

public class BmpUtil {
    private static final int BMP_WIDTH_OF_TIMES = 4;
    private static final int BYTE_PER_PIXEL = 3;

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00f4  */
    public static boolean save(android.graphics.Bitmap r22, java.io.OutputStream r23) throws java.io.IOException {
        /*
        r0 = r23;
        r1 = java.lang.System.currentTimeMillis();
        r3 = 0;
        if (r22 != 0) goto L_0x000a;
    L_0x0009:
        return r3;
    L_0x000a:
        if (r0 != 0) goto L_0x000d;
    L_0x000c:
        return r3;
    L_0x000d:
        r12 = r22.getWidth();
        r13 = r22.getHeight();
        r5 = 0;
        r6 = r12 * 3;
        r7 = r6 % 4;
        if (r7 <= 0) goto L_0x002d;
    L_0x001c:
        r5 = 4 - r7;
        r5 = new byte[r5];
        r7 = 0;
    L_0x0021:
        r8 = r5.length;
        if (r7 >= r8) goto L_0x002a;
    L_0x0024:
        r8 = -1;
        r5[r7] = r8;
        r7 = r7 + 1;
        goto L_0x0021;
    L_0x002a:
        r11 = r5;
        r15 = 1;
        goto L_0x002f;
    L_0x002d:
        r11 = r5;
        r15 = 0;
    L_0x002f:
        r10 = r12 * r13;
        r9 = new int[r10];
        if (r15 == 0) goto L_0x0037;
    L_0x0035:
        r5 = r11.length;
        goto L_0x0038;
    L_0x0037:
        r5 = 0;
    L_0x0038:
        r6 = r6 + r5;
        r8 = r6 * r13;
        r7 = 54;
        r6 = r8 + 54;
        r16 = 0;
        r17 = 0;
        r18 = 0;
        r4 = r22;
        r5 = r9;
        r14 = r6;
        r6 = r16;
        r7 = r12;
        r19 = r8;
        r8 = r17;
        r16 = r9;
        r9 = r18;
        r17 = r10;
        r10 = r12;
        r20 = r11;
        r11 = r13;
        r4.getPixels(r5, r6, r7, r8, r9, r10, r11);
        r4 = java.nio.ByteBuffer.allocate(r14);
        r5 = 66;
        r4.put(r5);
        r5 = 77;
        r4.put(r5);
        r5 = writeInt(r14);
        r4.put(r5);
        r5 = writeShort(r3);
        r4.put(r5);
        r5 = writeShort(r3);
        r4.put(r5);
        r5 = 54;
        r5 = writeInt(r5);
        r4.put(r5);
        r5 = 40;
        r5 = writeInt(r5);
        r4.put(r5);
        if (r15 == 0) goto L_0x009c;
    L_0x0094:
        r5 = r20;
        r6 = r5.length;
        r7 = 3;
        if (r6 != r7) goto L_0x009e;
    L_0x009a:
        r6 = 1;
        goto L_0x009f;
    L_0x009c:
        r5 = r20;
    L_0x009e:
        r6 = 0;
    L_0x009f:
        r6 = r6 + r12;
        r6 = writeInt(r6);
        r4.put(r6);
        r6 = writeInt(r13);
        r4.put(r6);
        r6 = 1;
        r7 = writeShort(r6);
        r4.put(r7);
        r6 = 24;
        r6 = writeShort(r6);
        r4.put(r6);
        r6 = writeInt(r3);
        r4.put(r6);
        r6 = r19;
        r6 = writeInt(r6);
        r4.put(r6);
        r6 = writeInt(r3);
        r4.put(r6);
        r6 = writeInt(r3);
        r4.put(r6);
        r6 = writeInt(r3);
        r4.put(r6);
        r3 = writeInt(r3);
        r4.put(r3);
        r3 = r13 + -1;
        r3 = r3 * r12;
        r10 = r3;
        r3 = r17;
    L_0x00f2:
        if (r13 <= 0) goto L_0x0128;
    L_0x00f4:
        r6 = r10;
    L_0x00f5:
        if (r6 >= r3) goto L_0x0119;
    L_0x00f7:
        r7 = r16[r6];
        r7 = r7 & 255;
        r7 = (byte) r7;
        r4.put(r7);
        r7 = r16[r6];
        r8 = 65280; // 0xff00 float:9.1477E-41 double:3.22526E-319;
        r7 = r7 & r8;
        r7 = r7 >> 8;
        r7 = (byte) r7;
        r4.put(r7);
        r7 = r16[r6];
        r8 = 16711680; // 0xff0000 float:2.3418052E-38 double:8.256667E-317;
        r7 = r7 & r8;
        r7 = r7 >> 16;
        r7 = (byte) r7;
        r4.put(r7);
        r6 = r6 + 1;
        goto L_0x00f5;
    L_0x0119:
        if (r15 == 0) goto L_0x011e;
    L_0x011b:
        r4.put(r5);
    L_0x011e:
        r13 = r13 + -1;
        r3 = r10 - r12;
        r21 = r10;
        r10 = r3;
        r3 = r21;
        goto L_0x00f2;
    L_0x0128:
        r3 = r4.array();
        r0.write(r3);
        r23.close();
        r0 = "BmpUtil";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = java.lang.System.currentTimeMillis();
        r4 = r4 - r1;
        r3.append(r4);
        r1 = " ms";
        r3.append(r1);
        r1 = r3.toString();
        android.util.Log.v(r0, r1);
        r0 = 1;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: net.glxn.qrgen.android.BmpUtil.save(android.graphics.Bitmap, java.io.OutputStream):boolean");
    }

    private static byte[] writeInt(int i) throws IOException {
        return new byte[]{(byte) (i & 255), (byte) ((65280 & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((i & MatrixToImageConfig.BLACK) >> 24)};
    }

    private static byte[] writeShort(short s) throws IOException {
        return new byte[]{(byte) (s & 255), (byte) ((s & 65280) >> 8)};
    }
}
