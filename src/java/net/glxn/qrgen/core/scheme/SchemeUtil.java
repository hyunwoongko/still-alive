package net.glxn.qrgen.core.scheme;

import java.util.LinkedHashMap;
import java.util.Map;

public class SchemeUtil {
    public static final String DEFAULT_KEY_VALUE_SEPARATOR = ":";
    public static final String DEFAULT_PARAM_SEPARATOR = "\r?\n";
    public static final String LINE_FEED = "\n";

    public static Map<String, String> getParameters(String str, String str2) {
        return getParameters(str, str2, DEFAULT_KEY_VALUE_SEPARATOR);
    }

    public static Map<String, String> getParameters(String str) {
        return getParameters(str, DEFAULT_PARAM_SEPARATOR, DEFAULT_KEY_VALUE_SEPARATOR);
    }

    public static Map<String, String> getParameters(String str, String str2, String str3) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String[] split = str.split(str2);
        for (String split2 : split) {
            String[] split3 = split2.split(str3);
            if (split3.length > 1) {
                linkedHashMap.put(split3[0], split3[1]);
            }
        }
        return linkedHashMap;
    }
}
