package net.glxn.qrgen.core.scheme;

import java.util.ArrayList;
import java.util.List;

public class GeoInfo extends Schema {
    public static final String GEO = "geo";
    private List<String> points = new ArrayList();

    public List<String> getPoints() {
        return this.points;
    }

    public void setPoints(List<String> list) {
        this.points = list;
    }

    public Schema parseSchema(String str) {
        if (str == null || !str.trim().toLowerCase().startsWith(GEO)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a geo info code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        String[] split = str.trim().toLowerCase().replaceAll("geo:", "").split(",");
        if (split != null && split.length > 0) {
            for (Object add : split) {
                this.points.add(add);
            }
        }
        return this;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.points != null) {
            int size = this.points.size();
            for (int i = 0; i < size; i++) {
                stringBuilder.append((String) this.points.get(i));
                if (i < size - 1) {
                    stringBuilder.append(",");
                }
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("geo:");
        stringBuilder2.append(stringBuilder.toString());
        return stringBuilder2.toString();
    }

    public String toString() {
        return generateString();
    }

    public static GeoInfo parse(String str) {
        GeoInfo geoInfo = new GeoInfo();
        geoInfo.parseSchema(str);
        return geoInfo;
    }
}
