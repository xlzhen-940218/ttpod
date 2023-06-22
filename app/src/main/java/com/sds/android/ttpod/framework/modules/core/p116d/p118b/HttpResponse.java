package com.sds.android.ttpod.framework.modules.core.p116d.p118b;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/* renamed from: com.sds.android.ttpod.framework.modules.core.d.b.a */
/* loaded from: classes.dex */
class HttpResponse {

    /* renamed from: a */
    private String f6004a;

    /* renamed from: b */
    private String f6005b;

    /* renamed from: c */
    private InputStream f6006c;

    /* renamed from: d */
    private Properties f6007d;

    public HttpResponse() {
        this.f6007d = new Properties();
        this.f6004a = "200 OK";
    }

    public HttpResponse(String str, String str2, InputStream inputStream) {
        this.f6007d = new Properties();
        this.f6004a = str;
        this.f6005b = str2;
        this.f6006c = inputStream;
    }

    public HttpResponse(String str, String str2, String str3) {
        this.f6007d = new Properties();
        this.f6004a = str;
        this.f6005b = str2;
        try {
            this.f6006c = new ByteArrayInputStream(str3.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m4223a(String str, String str2) {
        this.f6007d.put(str, str2);
    }

    /* renamed from: a */
    public String m4224a() {
        return this.f6004a;
    }

    /* renamed from: b */
    public String m4222b() {
        return this.f6005b;
    }

    /* renamed from: c */
    public InputStream m4221c() {
        return this.f6006c;
    }

    /* renamed from: d */
    public Properties m4220d() {
        return this.f6007d;
    }
}
