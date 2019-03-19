package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class ICal extends Schema {
    private static final String BEGIN_VCALENDAR = "BEGIN:VCALENDAR";
    private SubSchema subSchema;

    private ICal() {
    }

    public ICal(IEvent iEvent) {
        this();
        this.subSchema = iEvent;
    }

    public ICal(IToDo iToDo) {
        this();
        this.subSchema = iToDo;
    }

    public ICal(IJournal iJournal) {
        this();
        this.subSchema = iJournal;
    }

    public ICal(IFreeBusyTime iFreeBusyTime) {
        this();
        this.subSchema = iFreeBusyTime;
    }

    public SubSchema getSubSchema() {
        return this.subSchema;
    }

    public Schema parseSchema(String str) {
        if (str == null || !str.startsWith(BEGIN_VCALENDAR)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a valid ICal code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        Map parameters = SchemeUtil.getParameters(str);
        if (parameters.containsKey(IEvent.NAME)) {
            this.subSchema = IEvent.parse(parameters, str);
        }
        if (parameters.containsKey(IToDo.NAME)) {
            this.subSchema = IToDo.parse(parameters, str);
        }
        if (parameters.containsKey(IJournal.NAME)) {
            this.subSchema = IJournal.parse(parameters, str);
        }
        if (parameters.containsKey(IFreeBusyTime.NAME)) {
            this.subSchema = IFreeBusyTime.parse(parameters, str);
        }
        return this;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BEGIN_VCALENDAR);
        stringBuilder.append(SchemeUtil.LINE_FEED);
        stringBuilder.append("VERSION:2.0");
        stringBuilder.append(SchemeUtil.LINE_FEED);
        stringBuilder.append("PRODID:-//hacksw/handcal//NONSGML v1.0//EN");
        stringBuilder.append(SchemeUtil.LINE_FEED);
        if (this.subSchema != null) {
            stringBuilder.append(this.subSchema.generateString());
        }
        stringBuilder.append(SchemeUtil.LINE_FEED);
        stringBuilder.append("END:VCALENDAR");
        return stringBuilder.toString();
    }

    public String toString() {
        return generateString();
    }

    public static ICal parse(String str) {
        ICal iCal = new ICal();
        iCal.parseSchema(str);
        return iCal;
    }
}
