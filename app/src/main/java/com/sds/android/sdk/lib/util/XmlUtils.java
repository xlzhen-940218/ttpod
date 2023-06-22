package com.sds.android.sdk.lib.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* renamed from: com.sds.android.sdk.lib.util.n */
/* loaded from: classes.dex */
public class XmlUtils {
    /* renamed from: a */
    public static Map<String, String> m8332a(InputStream inputStream) throws XmlPullParserException, IOException {
        HashMap hashMap = new HashMap();
        XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            switch (eventType) {
                case 2:
                    if (newPullParser.getName().equals("string")) {
                        String attributeValue = newPullParser.getAttributeValue(0);
                        String nextText = newPullParser.nextText();
                        if (attributeValue != null) {
                            hashMap.put(attributeValue, nextText);
                            break;
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
            }
        }
        return hashMap;
    }
}
