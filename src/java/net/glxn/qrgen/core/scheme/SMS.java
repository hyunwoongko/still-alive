package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class SMS extends Schema {
    private static final String SMS = "sms";
    private String number;
    private String subject;

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String str) {
        this.subject = str;
    }

    public Schema parseSchema(String str) {
        if (str == null || !str.trim().toLowerCase().startsWith(SMS)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a valid sms code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        Map parameters = SchemeUtil.getParameters(str.trim().toLowerCase());
        if (parameters.containsKey(SMS)) {
            setNumber((String) parameters.get(SMS));
        }
        if (getNumber() != null && parameters.containsKey(getNumber())) {
            setSubject((String) parameters.get(getNumber()));
        }
        return this;
    }

    public String generateString() {
        String stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("sms:");
        stringBuilder2.append(this.number);
        if (this.subject != null) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder3.append(this.subject);
            stringBuilder = stringBuilder3.toString();
        } else {
            stringBuilder = "";
        }
        stringBuilder2.append(stringBuilder);
        return stringBuilder2.toString();
    }

    public String toString() {
        return generateString();
    }

    public static SMS parse(String str) {
        SMS sms = new SMS();
        sms.parseSchema(str);
        return sms;
    }
}
