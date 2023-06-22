package com.sds.android.ttpod.framework.modules.core.p116d.p117a;

import android.text.TextUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.framework.p106a.CodeIdentifyInputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

/* renamed from: com.sds.android.ttpod.framework.modules.core.d.a.b */
/* loaded from: classes.dex */
public class CDRWinSheetParser implements Iterator<CDRWinSheetEntry> {

    /* renamed from: a */
    private BufferedReader f5975a;

    /* renamed from: b */
    private CDRWinSheetEntry f5976b;

    /* renamed from: c */
    private String f5977c;

    /* renamed from: d */
    private String f5978d;

    /* renamed from: e */
    private String f5979e;

    /* renamed from: f */
    private String f5980f;

    /* renamed from: g */
    private String f5981g;

    public CDRWinSheetParser(String str) throws IOException {
        this.f5975a = new BufferedReader(new CodeIdentifyInputStreamReader(new FileInputStream(str)));
        try {
            m4247a(str);
            this.f5976b = m4241f();
        } catch (IOException e) {
            m4242e();
            throw e;
        }
    }

    /* renamed from: a */
    public String m4248a() {
        return this.f5979e;
    }

    /* renamed from: b */
    public String m4246b() {
        return this.f5978d;
    }

    /* renamed from: c */
    public String m4244c() {
        return this.f5980f;
    }

    /* renamed from: a */
    private void m4247a(String str) throws IOException {
        String m4245b = "";
        while (true) {
            String readLine = this.f5975a.readLine();
            this.f5977c = readLine;
            if (readLine != null) {
                this.f5977c = this.f5977c.trim();
                String upperCase = this.f5977c.toUpperCase(Locale.US);
                if (!upperCase.startsWith("TRACK ")) {
                    if (upperCase.startsWith("FILE ")) {
                        String str2 = FileUtils.m8400l(str) + File.separatorChar;
                        this.f5978d = str2 + m4245b(this.f5977c);
                        File file = new File(this.f5978d);
                        if (!file.isFile() || !file.exists()) {
                            this.f5978d = str2 + FileUtils.m8401k(str) + '.' + FileUtils.m8399m(m4245b);
                            file = new File(this.f5978d);
                        }
                        if (!file.isFile() || !file.exists()) {
                            break;
                        }
                    } else if (upperCase.startsWith("TITLE ")) {
                        this.f5979e = m4245b(this.f5977c);
                    } else if (upperCase.startsWith("PERFORMER ")) {
                        this.f5980f = m4245b(this.f5977c);
                    } else if (upperCase.startsWith("SONGWRITER ")) {
                        this.f5981g = m4245b(this.f5977c);
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        throw new FileNotFoundException("File " + this.f5978d + " not found!");
    }

    /* renamed from: b */
    private String m4245b(String str) {
        int i;
        int indexOf = str.indexOf(34);
        int lastIndexOf = str.lastIndexOf(34);
        if (indexOf >= 0 && lastIndexOf > (i = indexOf + 1)) {
            return str.substring(i, lastIndexOf);
        }
        return str;
    }

    /* renamed from: f */
    private CDRWinSheetEntry m4241f() throws IOException {
        if (this.f5977c == null) {
            return null;
        }
        String upperCase = this.f5977c.toUpperCase(Locale.US);
        CDRWinSheetEntry cDRWinSheetEntry = new CDRWinSheetEntry();
        TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
        do {
            if (upperCase.startsWith("TRACK ")) {
                String substring = this.f5977c.substring("TRACK ".length());
                int indexOf = substring.indexOf(32);
                if (indexOf > 0) {
                    try {
                        cDRWinSheetEntry.m4263a(Integer.parseInt(substring.substring(0, indexOf)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (upperCase.startsWith("INDEX ")) {
                this.f5977c = this.f5977c.substring("INDEX ".length());
                int indexOf2 = this.f5977c.indexOf(32);
                if (indexOf2 > 0) {
                    try {
                        int i = indexOf2 + 1;
                        int parseInt = Integer.parseInt(this.f5977c.substring(0, indexOf2));
                        simpleStringSplitter.setString(this.f5977c.substring(i).trim());
                        if (simpleStringSplitter.hasNext()) {
                            int parseInt2 = Integer.parseInt(simpleStringSplitter.next()) * 60;
                            if (simpleStringSplitter.hasNext()) {
                                int parseInt3 = parseInt2 + Integer.parseInt(simpleStringSplitter.next());
                                if (simpleStringSplitter.hasNext()) {
                                    int parseInt4 = Integer.parseInt(simpleStringSplitter.next());
                                    if (parseInt == 0) {
                                        cDRWinSheetEntry.m4254d(parseInt3);
                                        cDRWinSheetEntry.m4252e(parseInt4);
                                    } else if (parseInt == 1) {
                                        cDRWinSheetEntry.m4259b(parseInt3);
                                        cDRWinSheetEntry.m4256c(parseInt4);
                                    } else {
                                        cDRWinSheetEntry.m4261a(new int[]{parseInt3, parseInt4});
                                    }
                                }
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } else if (upperCase.startsWith("TITLE ")) {
                cDRWinSheetEntry.m4262a(m4245b(this.f5977c));
            } else if (upperCase.startsWith("PERFORMER ")) {
                cDRWinSheetEntry.m4258b(m4245b(this.f5977c));
            }
            this.f5977c = this.f5975a.readLine();
            if (this.f5977c == null) {
                break;
            }
            this.f5977c = this.f5977c.trim();
            upperCase = this.f5977c.toUpperCase(Locale.US);
        } while (!upperCase.startsWith("TRACK "));
        if (cDRWinSheetEntry.m4251f() == cDRWinSheetEntry.m4250g() && cDRWinSheetEntry.m4251f() < 0) {
            cDRWinSheetEntry.m4254d(cDRWinSheetEntry.m4264a());
            cDRWinSheetEntry.m4252e(cDRWinSheetEntry.m4260b());
        }
        return cDRWinSheetEntry;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f5976b != null;
    }

    @Override // java.util.Iterator
    /* renamed from: d */
    public CDRWinSheetEntry next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException("has no more entry.");
        }
        CDRWinSheetEntry cDRWinSheetEntry = this.f5976b;
        try {
            this.f5976b = m4241f();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cDRWinSheetEntry;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("cannot support remove operation.");
    }

    /* renamed from: e */
    public void m4242e() {
        try {
            this.f5975a.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f5975a = null;
    }
}
