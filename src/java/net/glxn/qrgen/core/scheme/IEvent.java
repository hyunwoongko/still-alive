package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class IEvent extends SubSchema {
    private static final String BEGIN_EVENT = "BEGIN:VEVENT";
    private static final String END = "DTEND";
    public static final String NAME = "VEVENT";
    private static final String ORGANIZER = "ORGANIZER";
    private static final String STAMP = "DTSTAMP";
    private static final String START = "DTSTART";
    private static final String SUMMARY = "SUMMARY";
    private static final String UID = "UID";
    private String end;
    private String organizer;
    private String stamp;
    private String start;
    private String summary;
    private String uid;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getStamp() {
        return this.stamp;
    }

    public void setStamp(String str) {
        this.stamp = str;
    }

    public String getOrganizer() {
        return this.organizer;
    }

    public void setOrganizer(String str) {
        this.organizer = str;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String str) {
        this.start = str;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String str) {
        this.end = str;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String str) {
        this.summary = str;
    }

    public SubSchema parseSchema(Map<String, String> map, String str) {
        if (map.containsKey(UID)) {
            setUid((String) map.get(UID));
        }
        if (map.containsKey(STAMP)) {
            setStamp((String) map.get(STAMP));
        }
        if (map.containsKey(START)) {
            setStart((String) map.get(START));
        }
        if (map.containsKey(END)) {
            setEnd((String) map.get(END));
        }
        if (map.containsKey(SUMMARY)) {
            setSummary((String) map.get(SUMMARY));
        }
        SchemeUtil.getParameters(str);
        return this;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BEGIN_EVENT);
        stringBuilder.append(SchemeUtil.LINE_FEED);
        if (this.uid != null) {
            stringBuilder.append(UID);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.uid);
            stringBuilder.append(SchemeUtil.LINE_FEED);
        } else if (this.stamp != null) {
            stringBuilder.append(STAMP);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.stamp);
            stringBuilder.append(SchemeUtil.LINE_FEED);
        } else if (this.organizer != null) {
            stringBuilder.append(ORGANIZER);
            stringBuilder.append(";");
            stringBuilder.append(this.organizer);
            stringBuilder.append(SchemeUtil.LINE_FEED);
        } else if (this.start != null) {
            stringBuilder.append(START);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.start);
            stringBuilder.append(SchemeUtil.LINE_FEED);
        } else if (this.end != null) {
            stringBuilder.append(END);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.end);
            stringBuilder.append(SchemeUtil.LINE_FEED);
        } else if (this.summary != null) {
            stringBuilder.append(SUMMARY);
            stringBuilder.append(SchemeUtil.DEFAULT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(this.summary);
            stringBuilder.append(SchemeUtil.LINE_FEED);
        }
        stringBuilder.append(SchemeUtil.LINE_FEED);
        stringBuilder.append("END:VEVENT");
        return stringBuilder.toString();
    }

    public String toString() {
        return generateString();
    }

    public static IEvent parse(Map<String, String> map, String str) {
        IEvent iEvent = new IEvent();
        iEvent.parseSchema(map, str);
        return iEvent;
    }
}
