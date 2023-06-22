package com.sds.android.ttpod.utils;

import android.content.Context;
import com.sds.android.sdk.lib.util.SecurityUtils;
import com.sds.android.sdk.lib.util.StringUtils;

/* renamed from: com.sds.android.ttpod.a.s */
/* loaded from: classes.dex */
public final class SensitiveWordUtils {

    /* renamed from: a */
    private static final Object f2514a = new Object();

    /* renamed from: b */
    private static SensitiveWordUtils f2515b;

    /* renamed from: c */
    private String[] f2516c;

    /* renamed from: a */
    public static synchronized SensitiveWordUtils m8243a(Context context) {
        SensitiveWordUtils sensitiveWordUtils;
        synchronized (SensitiveWordUtils.class) {
            if (f2515b == null) {
                synchronized (f2514a) {
                    if (f2515b == null) {
                        f2515b = new SensitiveWordUtils();
                    }
                }
            }
            sensitiveWordUtils = f2515b;
        }
        return sensitiveWordUtils;
    }

    private SensitiveWordUtils() {
        this.f2516c = new String[0];
        try {
            this.f2516c = m8241b(SecurityUtils.C0609a.m8364a("096ECEE37B47D1CE9D65C4953FD631B751671082A113DC7EFC9EBA070FA124D4207601B90F22A01C986D50FFE3173D45774589695E9869663B90D329D99E4EBFFB2C24F00E596FC0392C3BC2A271FBBA8A702F6CF2CF56FFDED2173F276FD9E87A4FF264DC900817F96E9A7BBD382634157744E19ACFC3D48888221A94432122AB7B6698BA784E6FAB2F8562AA7ED42FB363AC97EA904E8F24FF4D1C058D2D260D87D4CDCE9ED70DF0B490DF2462A01B4B52B2898A74167E1DD7DB3A445704C559CC925DFE0EE0143B4E1BD15209248FFC710262AF86C2AF0BC28CCCFB64F6E74ED19E86009DD93FC59D929F1480AD804DDD394380974FBE288D036E43316C0890BF33DA643A7DB447063AB738A51B5B88B89FE155E5621800AFED2AFE9985DCFA7F87019A39CAEAE90734BD101569BA110D282F7D11062A6124D41A3BAEDB420C60FCFB16EA4A124473D6EEBCFB9B647BB513DDC2C49918F18F0BCF9736D8407E0549A4360A334F9D30AA78877C20DC4C3261C742BA598D3727CF5B1FF76E860EAC6A58413006CC8C9ED826D483F96964FCC3C5A15919CA413FC5CCFA1D9685146877AA31D6B29984C7B727A594BDD3357448CF8BFB7344516C842B7C299EA38F09057720CF1F5A6FD2C0F71BAAAACB1FA184CC823C9DD7BF4569A8F8A9A464034C957CFC3E96CF0AF723AA1F542B0D54FD90D386D551F15E08E533EF2E39ABF5D8245EB054B1FF3CCCAB9A4A058D933E6E638DE7C8D57010692C878B4840B386E11C5EADDC48CC308DD4C4234A4D9F6585151C9EF0202C787E505ACE1342E99A00714E92987F74BC16010342B5292A5DA3E084EBCC1513530FC5E265EB7F2F397F5823539BBAF5B088AAFD3C16EB90D69644BFA9956E1AB519F4530CF2A65E5D62BA934ED7C7F4BF6D485DA4B08D4008128DC0B0628E2200BC5700F42615317796ADFA0CD9B1293D5A592AAE4A1B96F0AD4B63AE245CAFEABBD9A0CE0D06EC47FE99B40BED760A808BE736B2976ECBC17AD9781050A312EDB0E01BA9F30A677EB9F6AD0106FDB83A86F39"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public boolean m8242a(String str) {
        if (StringUtils.m8346a(str)) {
            return false;
        }
        for (String str2 : this.f2516c) {
            if (str.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private String[] m8241b(String str) {
        return str.split(" +|\\r\\n|\\r|\\n");
    }
}
