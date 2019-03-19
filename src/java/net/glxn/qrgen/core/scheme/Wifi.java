package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class Wifi extends Schema {
    public static final String AUTHENTICATION = "T";
    public static final String HIDDEN = "H";
    public static final String PSK = "P";
    public static final String SSID = "S";
    public static final String WIFI_PROTOCOL_HEADER = "WIFI:";
    private String authentication;
    private boolean hidden = false;
    private String psk;
    private String ssid;

    public enum Authentication {
        WEP,
        WPA,
        nopass
    }

    public String getAuthentication() {
        return this.authentication;
    }

    public void setAuthentication(Authentication authentication) {
        setAuthentication(authentication.toString());
    }

    public void setAuthentication(String str) {
        this.authentication = str;
    }

    public Wifi withAuthentication(Authentication authentication) {
        setAuthentication(authentication);
        return this;
    }

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public Wifi withSsid(String str) {
        setSsid(str);
        return this;
    }

    public String getPsk() {
        return this.psk;
    }

    public void setPsk(String str) {
        this.psk = str;
    }

    public Wifi withPsk(String str) {
        setPsk(str);
        return this;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public void setHidden(String str) {
        setHidden(Boolean.valueOf(str).booleanValue());
    }

    public void setHidden(boolean z) {
        this.hidden = z;
    }

    public Wifi withHidden(boolean z) {
        setHidden(z);
        return this;
    }

    public Schema parseSchema(String str) {
        if (str == null || !str.startsWith(WIFI_PROTOCOL_HEADER)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a valid WIFI code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        Map parameters = SchemeUtil.getParameters(str.substring(WIFI_PROTOCOL_HEADER.length()), "(?<!\\\\);");
        if (parameters.containsKey(SSID)) {
            setSsid(unescape((String) parameters.get(SSID)));
        }
        if (parameters.containsKey(AUTHENTICATION)) {
            setAuthentication((String) parameters.get(AUTHENTICATION));
        }
        if (parameters.containsKey(PSK)) {
            setPsk(unescape((String) parameters.get(PSK)));
        }
        if (parameters.containsKey(HIDDEN)) {
            setHidden((String) parameters.get(HIDDEN));
        }
        return this;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder(WIFI_PROTOCOL_HEADER);
        if (getSsid() != null) {
            stringBuilder.append(SSID);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(escape(getSsid()));
            stringBuilder.append(";");
        }
        if (getAuthentication() != null) {
            stringBuilder.append(AUTHENTICATION);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(getAuthentication());
            stringBuilder.append(";");
        }
        if (getPsk() != null) {
            stringBuilder.append(PSK);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(escape(getPsk()));
            stringBuilder.append(";");
        }
        stringBuilder.append(HIDDEN);
        stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
        stringBuilder.append(isHidden());
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    public String toString() {
        return generateString();
    }

    public static Wifi parse(String str) {
        Wifi wifi = new Wifi();
        wifi.parseSchema(str);
        return wifi;
    }

    public static String escape(String str) {
        return str.replace("\\", "\\\\").replace(",", "\\,").replace(";", "\\;").replace(".", "\\.").replace("\"", "\\\"").replace("'", "\\'");
    }

    public static String unescape(String str) {
        return str.replace("\\\\", "\\").replace("\\,", ",").replace("\\;", ";").replace("\\.", ".").replace("\\\"", "\"").replace("\\'", "'");
    }
}
