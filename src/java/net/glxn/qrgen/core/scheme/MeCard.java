package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class MeCard extends Schema {
    private static final String ADDRESS = "ADR";
    private static final String BEGIN_MECARD = "MECARD";
    private static final String EMAIL = "EMAIL";
    private static final String LINE_SEPARATOR = ";";
    private static final String NAME = "N";
    private static final String TEL = "TEL";
    private String address;
    private String email;
    private String name;
    private String telephone;

    public MeCard(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String str) {
        this.telephone = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public Schema parseSchema(String str) {
        if (str == null || !str.startsWith(BEGIN_MECARD)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a valid MeCard code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        Map parameters = SchemeUtil.getParameters(str.replaceFirst("MECARD:", ""), LINE_SEPARATOR, SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
        if (parameters.containsKey(NAME)) {
            setName((String) parameters.get(NAME));
        }
        if (parameters.containsKey(ADDRESS)) {
            setAddress((String) parameters.get(ADDRESS));
        }
        if (parameters.containsKey(TEL)) {
            setTelephone((String) parameters.get(TEL));
        }
        if (parameters.containsKey(EMAIL)) {
            setEmail((String) parameters.get(EMAIL));
        }
        return this;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BEGIN_MECARD);
        stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
        if (this.name != null) {
            stringBuilder.append(NAME);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.name);
            stringBuilder.append(LINE_SEPARATOR);
        }
        if (this.address != null) {
            stringBuilder.append(ADDRESS);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.address);
            stringBuilder.append(LINE_SEPARATOR);
        }
        if (this.telephone != null) {
            stringBuilder.append(TEL);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.telephone);
            stringBuilder.append(LINE_SEPARATOR);
        }
        if (this.email != null) {
            stringBuilder.append(EMAIL);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.email);
            stringBuilder.append(LINE_SEPARATOR);
        }
        stringBuilder.append(LINE_SEPARATOR);
        return stringBuilder.toString();
    }

    public String toString() {
        return generateString();
    }

    public static MeCard parse(String str) {
        MeCard meCard = new MeCard();
        meCard.parseSchema(str);
        return meCard;
    }
}
