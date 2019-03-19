package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class EMail extends Schema {
    private static final String MAILTO = "mailto";
    private String email;

    public EMail(String str) {
        this.email = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public Schema parseSchema(String str) {
        if (str == null || !str.toLowerCase().startsWith(MAILTO)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a valid email code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        Map parameters = SchemeUtil.getParameters(str.toLowerCase());
        if (parameters.containsKey(MAILTO)) {
            setEmail((String) parameters.get(MAILTO));
        }
        return this;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mailto:");
        stringBuilder.append(this.email);
        return stringBuilder.toString();
    }

    public static EMail parse(String str) {
        EMail eMail = new EMail();
        eMail.parseSchema(str);
        return eMail;
    }

    public String toString() {
        return generateString();
    }
}
