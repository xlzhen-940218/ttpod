package com.sds.android.ttpod.component.p096h;

import android.util.Xml;
import com.sds.android.sdk.lib.util.FileUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Vector;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

/* renamed from: com.sds.android.ttpod.component.h.b */
/* loaded from: classes.dex */
public final class RegisterData {

    /* renamed from: c */
    private static RegisterData f4305c;

    /* renamed from: d */
    private static String f4306d = "user_data";

    /* renamed from: e */
    private static String f4307e;

    /* renamed from: a */
    private HashMap<String, String> f4308a = new HashMap<>();

    /* renamed from: b */
    private Vector<String> f4309b = new Vector<>();

    private RegisterData(String str) {
        m6392b(str);
    }

    /* renamed from: a */
    public static RegisterData m6396a(String str) {
        f4307e = str;
        if (f4305c == null) {
            f4305c = new RegisterData(str);
        }
        return f4305c;
    }

    /* renamed from: b */
    private void m6392b(String str) {
        f4307e = str;
        XmlPullParser newPullParser = Xml.newPullParser();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            try {
                newPullParser.setInput(fileInputStream, "UTF-8");
                for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                    switch (eventType) {
                        case 2:
                            String name = newPullParser.getName();
                            if (!name.equals(f4306d) && !name.equals("")) {
                                m6391b(name, newPullParser.nextText());
                                break;
                            }
                            break;
                    }
                }
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m6397a() {
        FileOutputStream fileOutputStream;
        FileUtils.createFolder(FileUtils.m8400l(f4307e));
        XmlSerializer newSerializer = Xml.newSerializer();
        try {
            fileOutputStream = new FileOutputStream(new File(f4307e));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
        try {
            newSerializer.setOutput(fileOutputStream, "UTF-8");
            newSerializer.startDocument(null, true);
            newSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            newSerializer.startTag(null, f4306d);
            m6394a(newSerializer);
            newSerializer.endTag(null, f4306d);
            newSerializer.endDocument();
            newSerializer.flush();
            fileOutputStream.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m6393a(XmlSerializer xmlSerializer, String str, String str2) {
        try {
            xmlSerializer.startTag(null, str);
            xmlSerializer.text(str2);
            xmlSerializer.endTag(null, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m6394a(XmlSerializer xmlSerializer) {
        for (int size = this.f4309b.size() - 1; size >= 0; size--) {
            String str = this.f4309b.get(size);
            m6393a(xmlSerializer, str, this.f4308a.get(str));
        }
    }

    /* renamed from: a */
    public String m6395a(String str, String str2) {
        return this.f4308a.get(str) == null ? str2 : this.f4308a.get(str);
    }

    /* renamed from: b */
    public void m6391b(String str, String str2) {
        this.f4308a.put(str, str2);
        if (!this.f4309b.contains(str)) {
            this.f4309b.add(str);
        }
    }
}
