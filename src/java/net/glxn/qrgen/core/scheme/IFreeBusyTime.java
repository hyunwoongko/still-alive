package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class IFreeBusyTime extends SubSchema {
    private static final String BEGIN_TODO = "BEGIN:VFREEBUSY";
    public static final String NAME = "VFREEBUSY";

    public static SubSchema parse(Map<String, String> map, String str) {
        return null;
    }

    public SubSchema parseSchema(Map<String, String> map, String str) {
        return null;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BEGIN_TODO);
        stringBuilder.append(SchemeUtil.LINE_FEED);
        stringBuilder.append(SchemeUtil.LINE_FEED);
        stringBuilder.append("END:VFREEBUSY");
        return stringBuilder.toString();
    }

    public String toString() {
        return generateString();
    }
}
