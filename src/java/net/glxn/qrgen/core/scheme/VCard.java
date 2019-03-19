package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class VCard extends Schema {
    private static final String ADDRESS = "ADR";
    private static final String BEGIN_VCARD = "BEGIN:VCARD";
    private static final String COMPANY = "ORG";
    private static final String EMAIL = "EMAIL";
    private static final String NAME = "N";
    private static final String NOTE = "NOTE";
    private static final String PHONE = "TEL";
    private static final String TITLE = "TITLE";
    private static final String WEB = "URL";
    private String address;
    private String company;
    private String email;
    private String name;
    private String note;
    private String phoneNumber;
    private String title;
    private String website;

    public VCard(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public VCard setName(String str) {
        this.name = str;
        return this;
    }

    public String getCompany() {
        return this.company;
    }

    public VCard setCompany(String str) {
        this.company = str;
        return this;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public VCard setPhoneNumber(String str) {
        this.phoneNumber = str;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public VCard setTitle(String str) {
        this.title = str;
        return this;
    }

    public String getEmail() {
        return this.email;
    }

    public VCard setEmail(String str) {
        this.email = str;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public VCard setAddress(String str) {
        this.address = str;
        return this;
    }

    public String getWebsite() {
        return this.website;
    }

    public VCard setWebsite(String str) {
        this.website = str;
        return this;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public Schema parseSchema(String str) {
        if (str == null || !str.startsWith(BEGIN_VCARD)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a valid VCARD code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        Map parameters = SchemeUtil.getParameters(str);
        if (parameters.containsKey(NAME)) {
            setName((String) parameters.get(NAME));
        }
        if (parameters.containsKey(TITLE)) {
            setTitle((String) parameters.get(TITLE));
        }
        if (parameters.containsKey(COMPANY)) {
            setCompany((String) parameters.get(COMPANY));
        }
        if (parameters.containsKey(ADDRESS)) {
            setAddress((String) parameters.get(ADDRESS));
        }
        if (parameters.containsKey(EMAIL)) {
            setEmail((String) parameters.get(EMAIL));
        }
        if (parameters.containsKey(WEB)) {
            setWebsite((String) parameters.get(WEB));
        }
        if (parameters.containsKey(PHONE)) {
            setPhoneNumber((String) parameters.get(PHONE));
        }
        if (parameters.containsKey(NOTE)) {
            setNote((String) parameters.get(NOTE));
        }
        return this;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BEGIN_VCARD);
        stringBuilder.append(SchemeUtil.LINE_FEED);
        stringBuilder.append("VERSION:3.0");
        stringBuilder.append(SchemeUtil.LINE_FEED);
        if (this.name != null) {
            stringBuilder.append(NAME);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.name);
        }
        if (this.company != null) {
            stringBuilder.append(SchemeUtil.LINE_FEED);
            stringBuilder.append(COMPANY);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.company);
        }
        if (this.title != null) {
            stringBuilder.append(SchemeUtil.LINE_FEED);
            stringBuilder.append(TITLE);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.title);
        }
        if (this.phoneNumber != null) {
            stringBuilder.append(SchemeUtil.LINE_FEED);
            stringBuilder.append(PHONE);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.phoneNumber);
        }
        if (this.website != null) {
            stringBuilder.append(SchemeUtil.LINE_FEED);
            stringBuilder.append(WEB);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.website);
        }
        if (this.email != null) {
            stringBuilder.append(SchemeUtil.LINE_FEED);
            stringBuilder.append(EMAIL);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.email);
        }
        if (this.address != null) {
            stringBuilder.append(SchemeUtil.LINE_FEED);
            stringBuilder.append(ADDRESS);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.address);
        }
        if (this.note != null) {
            stringBuilder.append(SchemeUtil.LINE_FEED);
            stringBuilder.append(NOTE);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.note);
        }
        stringBuilder.append(SchemeUtil.LINE_FEED);
        stringBuilder.append("END:VCARD");
        return stringBuilder.toString();
    }

    public String toString() {
        return generateString();
    }

    public static VCard parse(String str) {
        VCard vCard = new VCard();
        vCard.parseSchema(str);
        return vCard;
    }
}
