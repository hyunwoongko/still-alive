package net.glxn.qrgen.core.scheme;

import java.io.UnsupportedEncodingException;
import java.util.Set;

public interface QRCodeSchemeParser {
    Set<Class<? extends Schema>> getSupportedSchemes();

    Object parse(String str) throws UnsupportedEncodingException;
}
