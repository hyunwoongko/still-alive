package net.glxn.qrgen.core.scheme;

import java.util.Map;

public abstract class SubSchema {
    public abstract String generateString();

    public abstract SubSchema parseSchema(Map<String, String> map, String str);

    SubSchema() {
    }
}
