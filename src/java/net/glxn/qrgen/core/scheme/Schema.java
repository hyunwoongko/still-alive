package net.glxn.qrgen.core.scheme;

public abstract class Schema {
    public abstract String generateString();

    public abstract Schema parseSchema(String str);

    Schema() {
    }
}
