package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class KddiAu extends Schema {
    private static final String ADD = "ADD";
    private static final String BEGIN = "MEMORY";
    private static final String MAIL1 = "MAIL1";
    private static final String MAIL2 = "MAIL2";
    private static final String MAIL3 = "MAIL3";
    private static final String NAME1 = "NAME1";
    private static final String NAME2 = "NAME2";
    private static final String TEL1 = "TEL1";
    private static final String TEL2 = "TEL2";
    private static final String TEL3 = "TEL3";
    private String address;
    private String email1;
    private String email2;
    private String email3;
    private String name1;
    private String name2;
    private String telephone1;
    private String telephone2;
    private String telephone3;

    public KddiAu(String str) {
        this.name1 = str;
    }

    public String getName1() {
        return this.name1;
    }

    public void setName1(String str) {
        this.name1 = str;
    }

    public String getName2() {
        return this.name2;
    }

    public void setName2(String str) {
        this.name2 = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getTelephone1() {
        return this.telephone1;
    }

    public void setTelephone1(String str) {
        this.telephone1 = str;
    }

    public String getTelephone2() {
        return this.telephone2;
    }

    public void setTelephone2(String str) {
        this.telephone2 = str;
    }

    public String getTelephone3() {
        return this.telephone3;
    }

    public void setTelephone3(String str) {
        this.telephone3 = str;
    }

    public String getEmail1() {
        return this.email1;
    }

    public void setEmail1(String str) {
        this.email1 = str;
    }

    public String getEmail2() {
        return this.email2;
    }

    public void setEmail2(String str) {
        this.email2 = str;
    }

    public String getEmail3() {
        return this.email3;
    }

    public void setEmail3(String str) {
        this.email3 = str;
    }

    public Schema parseSchema(String str) {
        if (str == null || !str.startsWith(BEGIN)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a valid KDDI AU code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        Map parameters = SchemeUtil.getParameters(str);
        if (parameters.containsKey(NAME1)) {
            setName1((String) parameters.get(NAME1));
        }
        if (parameters.containsKey(NAME2)) {
            setName2((String) parameters.get(NAME2));
        }
        if (parameters.containsKey(ADD)) {
            setAddress((String) parameters.get(ADD));
        }
        if (parameters.containsKey(TEL1)) {
            setTelephone1((String) parameters.get(TEL1));
        }
        if (parameters.containsKey(TEL2)) {
            setTelephone1((String) parameters.get(TEL2));
        }
        if (parameters.containsKey(TEL3)) {
            setTelephone1((String) parameters.get(TEL3));
        }
        if (parameters.containsKey(MAIL1)) {
            setEmail1((String) parameters.get(MAIL1));
        }
        if (parameters.containsKey(MAIL2)) {
            setEmail2((String) parameters.get(MAIL2));
        }
        if (parameters.containsKey(MAIL3)) {
            setEmail3((String) parameters.get(MAIL3));
        }
        return this;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BEGIN);
        stringBuilder.append(SchemeUtil.LINE_FEED);
        if (this.name1 != null) {
            stringBuilder.append(NAME1);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.name1);
        }
        if (this.name2 != null) {
            stringBuilder.append(NAME2);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.name2);
        }
        if (this.address != null) {
            stringBuilder.append(ADD);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.address);
        }
        if (this.telephone1 != null) {
            stringBuilder.append(TEL1);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.telephone1);
        }
        if (this.telephone2 != null) {
            stringBuilder.append(TEL2);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.telephone2);
        }
        if (this.telephone3 != null) {
            stringBuilder.append(TEL3);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.telephone3);
        }
        if (this.email1 != null) {
            stringBuilder.append(MAIL1);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.email1);
        }
        if (this.email2 != null) {
            stringBuilder.append(MAIL2);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.email2);
        }
        if (this.email3 != null) {
            stringBuilder.append(MAIL3);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.email3);
        }
        stringBuilder.append(SchemeUtil.LINE_FEED);
        return stringBuilder.toString();
    }

    public String toString() {
        return generateString();
    }

    public static KddiAu parse(String str) {
        KddiAu kddiAu = new KddiAu();
        kddiAu.parseSchema(str);
        return kddiAu;
    }
}
