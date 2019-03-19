package net.glxn.qrgen.core.scheme;

public class GooglePlay extends Schema {
    public static final String GPLAY = "{{{market://details?id=%s}}}";
    private String appPackage;

    public String getAppPackage() {
        return this.appPackage;
    }

    public void setAppPackage(String str) {
        this.appPackage = str;
    }

    public Schema parseSchema(String str) {
        if (str == null || !str.trim().toLowerCase().startsWith("{{{market:")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a google play code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        String[] split = str.trim().toLowerCase().replace("}}}", "").split("=");
        if (split != null && split.length > 1) {
            setAppPackage(split[1]);
        }
        return this;
    }

    public String generateString() {
        String str = GPLAY;
        Object[] objArr = new Object[1];
        objArr[0] = this.appPackage != null ? this.appPackage : "";
        return String.format(str, objArr);
    }

    public String toString() {
        return generateString();
    }

    public static GooglePlay parse(String str) {
        GooglePlay googlePlay = new GooglePlay();
        googlePlay.parseSchema(str);
        return googlePlay;
    }
}
