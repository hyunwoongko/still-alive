package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class Bookmark extends Schema {
    private static final String BEGIN_BOOKMARK = "MEBKM";
    private static final String LINE_SEPARATOR = ";";
    private static final String TITLE = "TITLE";
    private static final String URL = "URL";
    private String titel;
    private String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getTitel() {
        return this.titel;
    }

    public void setTitel(String str) {
        this.titel = str;
    }

    public Schema parseSchema(String str) {
        if (str == null || !str.startsWith(BEGIN_BOOKMARK)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a valid Bookmark code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        Map parameters = SchemeUtil.getParameters(str.replaceFirst("MEBKM:", ""), LINE_SEPARATOR, SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
        if (parameters.containsKey(URL)) {
            setUrl((String) parameters.get(URL));
        }
        if (parameters.containsKey(TITLE)) {
            setTitel((String) parameters.get(TITLE));
        }
        return this;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BEGIN_BOOKMARK);
        stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
        if (this.url != null) {
            stringBuilder.append(URL);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.url);
            stringBuilder.append(LINE_SEPARATOR);
        }
        if (this.titel != null) {
            stringBuilder.append(TITLE);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.titel);
            stringBuilder.append(LINE_SEPARATOR);
        }
        stringBuilder.append(LINE_SEPARATOR);
        return stringBuilder.toString();
    }

    public String toString() {
        return generateString();
    }

    public static Bookmark parse(String str) {
        Bookmark bookmark = new Bookmark();
        bookmark.parseSchema(str);
        return bookmark;
    }
}
