package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class IJournal extends SubSchema {
    private static final String BEGIN_TODO = "BEGIN:VJOURNAL";
    public static final String NAME = "VJOURNAL";

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
        stringBuilder.append("END:VJOURNAL");
        return stringBuilder.toString();
    }

    public String toString() {
        return generateString();
    }
}
