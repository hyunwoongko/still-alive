package net.glxn.qrgen.core.scheme;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

public class ExtendableQRCodeSchemeParser implements QRCodeSchemeParser {
    private Set<QRCodeSchemeParser> parser;

    static class QRCodeSchemeParserImpl implements QRCodeSchemeParser {
        QRCodeSchemeParserImpl() {
        }

        public Object parse(String str) throws UnsupportedEncodingException {
            for (Class createInstance : getSupportedSchemes()) {
                Object createInstance2 = createInstance(str, createInstance);
                if (createInstance2 != null) {
                    return createInstance2;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("unkonwn QR code scheme: ");
            stringBuilder.append(str);
            throw new UnsupportedEncodingException(stringBuilder.toString());
        }

        /* Access modifiers changed, original: protected */
        public Object createInstance(String str, Class<? extends Schema> cls) {
            try {
                return ((Schema) cls.getConstructor(null).newInstance(null)).parseSchema(str);
            } catch (Exception unused) {
                return null;
            }
        }

        public Set<Class<? extends Schema>> getSupportedSchemes() {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            linkedHashSet.add(Girocode.class);
            linkedHashSet.add(VCard.class);
            linkedHashSet.add(Wifi.class);
            linkedHashSet.add(BizCard.class);
            linkedHashSet.add(EMail.class);
            linkedHashSet.add(EnterpriseWifi.class);
            linkedHashSet.add(GeoInfo.class);
            linkedHashSet.add(GooglePlay.class);
            linkedHashSet.add(ICal.class);
            linkedHashSet.add(KddiAu.class);
            linkedHashSet.add(MeCard.class);
            linkedHashSet.add(MMS.class);
            linkedHashSet.add(SMS.class);
            linkedHashSet.add(Telephone.class);
            linkedHashSet.add(Url.class);
            return linkedHashSet;
        }
    }

    public Set<Class<? extends Schema>> getSupportedSchemes() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (QRCodeSchemeParser supportedSchemes : getParser()) {
            linkedHashSet.addAll(supportedSchemes.getSupportedSchemes());
        }
        return linkedHashSet;
    }

    public Object parse(String str) throws UnsupportedEncodingException {
        for (QRCodeSchemeParser parse : getParser()) {
            try {
                return parse.parse(str);
            } catch (UnsupportedEncodingException unused) {
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("unkonwn QR code scheme: ");
        stringBuilder.append(str);
        throw new UnsupportedEncodingException(stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public Set<QRCodeSchemeParser> getParser() {
        if (this.parser == null) {
            this.parser = loadParser();
        }
        return this.parser;
    }

    /* Access modifiers changed, original: protected */
    public Set<QRCodeSchemeParser> loadParser() {
        Throwable th;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        InputStream openStream;
        try {
            Iterator it = Collections.list(getClass().getClassLoader().getResources("META-INF/qrcode.meta")).iterator();
            while (it.hasNext()) {
                URL url = (URL) it.next();
                Properties properties = new Properties();
                openStream = url.openStream();
                properties.load(openStream);
                for (String createParserInstance : properties.getProperty(QRCodeSchemeParser.class.getName()).split(",")) {
                    linkedHashSet.add(createParserInstance(createParserInstance));
                }
                if (openStream != null) {
                    openStream.close();
                }
            }
            linkedHashSet.add(new QRCodeSchemeParserImpl());
            return linkedHashSet;
        } catch (Exception e) {
            throw new RuntimeException("failed to load schemes", e);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }

    /* Access modifiers changed, original: protected */
    public QRCodeSchemeParser createParserInstance(String str) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (QRCodeSchemeParser) Class.forName(str.trim()).newInstance();
    }
}
