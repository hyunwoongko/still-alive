package net.glxn.qrgen.core.scheme;

import java.net.MalformedURLException;
import java.net.URL;

public class Url extends Schema {
    private URL url;

    public String getUrl() {
        return this.url != null ? this.url.toString() : null;
    }

    public void setUrl(String str) {
        try {
            this.url = new URL(str);
        } catch (MalformedURLException unused) {
            this.url = null;
        }
    }

    public Schema parseSchema(String str) {
        if (str == null || !(str.trim().toLowerCase().startsWith("http") || str.trim().toLowerCase().startsWith("https"))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a valid url code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        setUrl(str.trim());
        return this;
    }

    public String generateString() {
        return getUrl();
    }

    public String toString() {
        return generateString();
    }

    public static Url parse(String str) {
        Url url = new Url();
        url.parseSchema(str);
        return url;
    }
}
