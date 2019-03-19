package net.glxn.qrgen.core.scheme;

import java.util.Map;

public class YouTube extends Schema {
    public static final String YOUTUBE = "youtube";
    private String videoId;

    public YouTube(String str) {
        this.videoId = str;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public void setVideoId(String str) {
        this.videoId = str;
    }

    public Schema parseSchema(String str) {
        if (str == null || !str.toLowerCase().startsWith(YOUTUBE)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("this is not a valid you tube code: ");
            stringBuilder.append(str);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
        Map parameters = SchemeUtil.getParameters(str);
        if (parameters.containsKey(YOUTUBE)) {
            setVideoId((String) parameters.get(YOUTUBE));
        }
        return this;
    }

    public String generateString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("youtube:");
        stringBuilder.append(this.videoId);
        return stringBuilder.toString();
    }

    public String toString() {
        return generateString();
    }

    public static YouTube parse(String str) {
        YouTube youTube = new YouTube();
        youTube.parseSchema(str);
        return youTube;
    }
}
