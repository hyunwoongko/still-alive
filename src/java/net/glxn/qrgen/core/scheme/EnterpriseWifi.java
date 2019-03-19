package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class EnterpriseWifi extends Wifi {
    public static final String EAP = "E";
    public static final String PHASE = "PH";
    public static final String USER = "U";
    private String eap;
    private String phase;
    private String user;

    public EnterpriseWifi withUser(String str) {
        this.user = str;
        return this;
    }

    public void setUser(String str) {
        withUser(str);
    }

    public String getUser() {
        return this.user;
    }

    public EnterpriseWifi withEap(String str) {
        this.eap = str;
        return this;
    }

    public void setEap(String str) {
        withEap(str);
    }

    public String getEap() {
        return this.eap;
    }

    public EnterpriseWifi withPhase(String str) {
        this.phase = str;
        return this;
    }

    public void setPhase(String str) {
        withPhase(str);
    }

    public String getPhase() {
        return this.phase;
    }

    public Schema parseSchema(String str) {
        if (str == null || !str.startsWith(Wifi.WIFI_PROTOCOL_HEADER)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a valid WIFI code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        Map parameters = SchemeUtil.getParameters(str.substring(Wifi.WIFI_PROTOCOL_HEADER.length()), "(?<!\\\\);");
        if (parameters.containsKey(Wifi.SSID)) {
            setSsid(unescape((String) parameters.get(Wifi.SSID)));
        }
        if (parameters.containsKey(Wifi.PSK)) {
            setPsk(unescape((String) parameters.get(Wifi.PSK)));
        }
        if (parameters.containsKey(USER)) {
            setUser(unescape((String) parameters.get(USER)));
        }
        if (parameters.containsKey(EAP)) {
            setEap(unescape((String) parameters.get(EAP)));
        }
        if (parameters.containsKey(PHASE)) {
            setPhase(unescape((String) parameters.get(PHASE)));
        }
        if (parameters.containsKey(Wifi.HIDDEN)) {
            setHidden((String) parameters.get(Wifi.HIDDEN));
        }
        return this;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder(Wifi.WIFI_PROTOCOL_HEADER);
        if (getSsid() != null) {
            stringBuilder.append(Wifi.SSID);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(escape(getSsid()));
            stringBuilder.append(";");
        }
        if (getUser() != null) {
            stringBuilder.append(USER);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(escape(getUser()));
            stringBuilder.append(";");
        }
        if (getPsk() != null) {
            stringBuilder.append(Wifi.PSK);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(escape(getPsk()));
            stringBuilder.append(";");
        }
        if (getEap() != null) {
            stringBuilder.append(EAP);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(escape(getEap()));
            stringBuilder.append(";");
        }
        if (getPhase() != null) {
            stringBuilder.append(PHASE);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(escape(getPhase()));
            stringBuilder.append(";");
        }
        stringBuilder.append(Wifi.HIDDEN);
        stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
        stringBuilder.append(isHidden());
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    public String toString() {
        return generateString();
    }

    public static EnterpriseWifi parse(String str) {
        EnterpriseWifi enterpriseWifi = new EnterpriseWifi();
        enterpriseWifi.parseSchema(str);
        return enterpriseWifi;
    }

    public static String escape(String str) {
        return str.replace("\\", "\\\\").replace(",", "\\,").replace(";", "\\;").replace(".", "\\.").replace("\"", "\\\"").replace("'", "\\'");
    }

    public static String unescape(String str) {
        return str.replace("\\\\", "\\").replace("\\,", ",").replace("\\;", ";").replace("\\.", ".").replace("\\\"", "\"").replace("\\'", "'");
    }
}
