package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class Telephone extends Schema {
    private static final String TEL = "tel";
    private String telephone;

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String str) {
        this.telephone = str;
    }

    public Schema parseSchema(String str) {
        if (str == null || !str.trim().toLowerCase().startsWith(TEL)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a valid telephone code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        Map parameters = SchemeUtil.getParameters(str.trim().toLowerCase());
        if (parameters.containsKey(TEL)) {
            setTelephone((String) parameters.get(TEL));
        }
        return this;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("tel:");
        stringBuilder.append(this.telephone);
        return stringBuilder.toString();
    }

    public String toString() {
        return generateString();
    }

    public static Telephone parse(String str) {
        Telephone telephone = new Telephone();
        telephone.parseSchema(str);
        return telephone;
    }
}
