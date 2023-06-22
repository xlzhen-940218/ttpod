package com.sds.android.ttpod.framework.modules.skin;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.p129b.SBitmap;
import com.sds.android.ttpod.framework.modules.skin.p129b.SFont;
import com.sds.android.ttpod.framework.modules.skin.p129b.SSkinInfo;
import com.sds.android.ttpod.framework.modules.skin.p129b.SerializableSkin;
import com.sds.android.ttpod.framework.modules.skin.p130c.BitmapDrawableCreator;
import com.sds.android.ttpod.framework.modules.skin.p130c.ColorDrawableCreator;
import com.sds.android.ttpod.framework.modules.skin.p130c.DrawableCreator;
import com.sds.android.ttpod.framework.modules.skin.p130c.StateListDrawableCreator;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import com.sds.android.ttpod.framework.modules.skin.p130c.ViewWrapper;
import com.sds.android.ttpod.framework.p106a.C1780b;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.j */
/* loaded from: classes.dex */
public class SkinCache extends SkinReader {

    /* renamed from: a */
    private HashMap<String, Bitmap> f6645a = new HashMap<>();

    /* renamed from: c */
    private HashMap<String, DrawableCreator> f6646c = new HashMap<>();

    /* renamed from: d */
    private HashMap<String, Typeface> f6647d = new HashMap<>();

    /* renamed from: e */
    private String f6648e = null;

    /* renamed from: f */
    private SerializableSkin f6649f = null;

    /* renamed from: g */
    private SSkinInfo f6650g = null;

    /* renamed from: h */
    private long f6651h = 0;

    /* renamed from: i */
    private String f6652i;

    /* renamed from: j */
    private int f6653j;

    public SkinCache(String str) {
        m3592a(str);
    }

    /* renamed from: a */
    public void m3592a(String str) {
        if (!TextUtils.equals(str, this.f6648e)) {
            m3579i();
            this.f6648e = str;
            if (str.startsWith("assets://")) {
                this.f6653j = 1;
                this.f6652i = str.substring("assets://".length());
            } else if (str.startsWith("package://")) {
                this.f6653j = 2;
                this.f6652i = str.substring("package://".length());
            } else if (str.startsWith("file://")) {
                this.f6653j = 0;
                this.f6652i = str.substring("file://".length());
            } else {
                this.f6653j = 0;
                this.f6652i = str;
            }
            m3578m();
        }
    }

    /* renamed from: m */
    private void m3578m() {
        if (this.f6653j == 0) {
            this.f6651h = new File(this.f6652i).lastModified();
        }
    }

    /* renamed from: a */
    public String m3598a() {
        return this.f6648e;
    }

    /* renamed from: b */
    public SerializableSkin m3590b() {
        return this.f6649f;
    }

    /* renamed from: c */
    public long m3587c() {
        return this.f6651h;
    }

    /* renamed from: d */
    public boolean m3585d() {
        return this.f6649f != null;
    }

    /* renamed from: a */
    public Typeface m3593a(SFont sFont) {
        String str;
        if (sFont == null || this.f6647d == null) {
            return null;
        }
        String m3801b = sFont.m3801b();
        int max = Math.max(0, sFont.m3800c());
        if (m3801b == null) {
            str = String.valueOf(max);
        } else {
            str = m3801b + max;
        }
        Typeface typeface = this.f6647d.get(str);
        if (typeface == null) {
            if (m3801b != null) {
                if (m3801b.startsWith("file://")) {
                    typeface = Typeface.createFromFile(m3801b);
                }
            } else if (max > 0) {
                typeface = Typeface.create(m3801b, max);
            }
            if (typeface != null) {
                this.f6647d.put(str, typeface);
                if (max != typeface.getStyle()) {
                    return Typeface.create(typeface, max);
                }
                return typeface;
            }
            return typeface;
        }
        return typeface;
    }

    /* renamed from: a */
    public final DrawableCreator m3594a(SBitmap sBitmap) {
        if (sBitmap != null) {
            if (sBitmap.m3816i()) {
                StateListDrawableCreator stateListDrawableCreator = new StateListDrawableCreator();
                stateListDrawableCreator.m3715a(ViewWrapper.f6593x, m3588b(sBitmap.m3821e()));
                stateListDrawableCreator.m3715a(ViewWrapper.f6571b, m3588b(sBitmap.m3819f()));
                stateListDrawableCreator.m3715a(ViewWrapper.f6573d, m3588b(sBitmap.m3817h()));
                stateListDrawableCreator.m3715a(ViewWrapper.f6572c, m3588b(sBitmap.m3818g()));
                stateListDrawableCreator.m3715a(ViewWrapper.f6570a, m3588b(sBitmap.m3823d()));
                return stateListDrawableCreator;
            }
            return m3588b(sBitmap.m3825c());
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public final DrawableCreator m3588b(String str) {
        String str2;
        String str3;
        DrawableCreator colorDrawableCreator;
        if (str == null || this.f6646c == null) {
            return null;
        }
        DrawableCreator drawableCreator = this.f6646c.get(str);
        if (drawableCreator == null) {
            int lastIndexOf = str.lastIndexOf(124);
            if (lastIndexOf > 0) {
                str3 = str.substring(0, lastIndexOf);
                str2 = str.substring(lastIndexOf + 1);
            } else {
                str2 = null;
                str3 = str;
            }
            Bitmap bitmap = this.f6645a.get(str3);
            if (bitmap == null) {
                bitmap = m3586c(str3);
            }
            if (bitmap != null) {
                this.f6645a.put(str3, bitmap);
                BitmapDrawableCreator bitmapDrawableCreator = new BitmapDrawableCreator(bitmap);
                if (str2 != null) {
                    TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
                    simpleStringSplitter.setString(str2);
                    if (simpleStringSplitter.hasNext()) {
                        bitmapDrawableCreator.m3759a(ValueParser.m3702a(simpleStringSplitter.next(), -1), simpleStringSplitter.hasNext() ? ValueParser.m3702a(simpleStringSplitter.next(), -1) : -1);
                    }
                }
                colorDrawableCreator = bitmapDrawableCreator;
            } else {
                colorDrawableCreator = new ColorDrawableCreator(ValueParser.m3697a(str3, (int[]) null), ValueParser.m3702a(str2, 0));
            }
            this.f6646c.put(str, colorDrawableCreator);
            return colorDrawableCreator;
        }
        return drawableCreator;
    }

    /* renamed from: a */
    public final Drawable m3596a(Resources resources, SBitmap sBitmap) {
        DrawableCreator m3594a = m3594a(sBitmap);
        if (m3594a != null) {
            return m3594a.mo3716a(resources);
        }
        return null;
    }

    /* renamed from: a */
    public final Drawable m3595a(Resources resources, String str) {
        DrawableCreator m3588b = m3588b(str);
        if (m3588b != null) {
            return m3588b.mo3716a(resources);
        }
        return null;
    }

    /* renamed from: c */
    public Bitmap m3586c(String str) {
        String str2;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inInputShareable = true;
        if (str.startsWith("file://")) {
            return BitmapFactory.decodeFile(str.substring("file://".length()), options);
        }
        if (str.startsWith("assets://")) {
            throw new UnsupportedOperationException("not support yet");
        }
        if (this.f6692b == null || !this.f6692b.mo3757a()) {
            return null;
        }
        int indexOf = str.indexOf(File.separatorChar);
        options.inTargetDensity = DisplayUtils.m7222f();
        options.inScaled = true;
        options.inDensity = DisplayUtils.m7220h();
        String m7221g = DisplayUtils.m7221g();
        if (indexOf >= 0) {
            str2 = "/" + m7221g + str;
        } else {
            str2 = str + m7221g;
        }
        Bitmap m3591a = m3591a(str2, options);
        if (m3591a == null) {
            return m3591a(str, options);
        }
        return m3591a;
    }

    /* renamed from: a */
    public Bitmap m3591a(String str, BitmapFactory.Options options) {
        Bitmap bitmap = null;
        try {
            byte[] mo3753b = this.f6692b.mo3753b(str);
            if (mo3753b != null) {
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(mo3753b, 0, mo3753b.length, options);
                if (C1780b.m4782a(options, DisplayUtils.m7225c(), DisplayUtils.m7224d())) {
                    bitmap = BitmapFactory.decodeByteArray(mo3753b, 0, mo3753b.length, options);
                    if (SDKVersionUtils.m8370d()) {
                        bitmap.setHasAlpha(C1780b.m4773b(options) ? false : true);
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return bitmap;
    }

    /* renamed from: d */
    public byte[] m3584d(String str) throws IOException {
        return this.f6692b.mo3753b(str);
    }

    /* renamed from: e */
    public Iterable<String> m3583e() {
        return this.f6692b;
    }

    /* renamed from: f */
    public SSkinInfo m3582f() {
        return this.f6649f == null ? this.f6650g : this.f6649f.m3853a();
    }

    /* renamed from: g */
    public boolean m3581g() {
        if (this.f6652i == null || TextUtils.isEmpty(this.f6652i)) {
            this.f6652i = (String) CommandCenter.m4607a().m4602a(new Command(CommandID.GET_SKIN_PROTOCOL_PATH, new Object[0]), String.class);
        }
        return this.f6653j == 0 ? m3526e(this.f6652i) : m3530a(m3533a(this.f6653j, this.f6652i));
    }

    /* renamed from: h */
    public void m3580h() {
        if (m3581g()) {
            BufferedReader k = m3524k();
            try {
                if (k != null) {
                    try {
                        this.f6649f = SerializableSkin.m3845a(m3598a(), m3587c(), k, 65552);
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            k.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            } finally {
                try {
                    k.close();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    /* renamed from: i */
    public void m3579i() {
        this.f6645a.clear();
        this.f6646c.clear();
        this.f6647d.clear();
        this.f6648e = null;
        this.f6649f = null;
        this.f6650g = null;
        this.f6651h = 0L;
        this.f6652i = null;
        this.f6653j = 0;
        m3525j();
        System.gc();
    }

    /* renamed from: a */
    public Drawable m3597a(Context context) {
        Drawable bitmapDrawable;
        if (!m3581g()) {
            return null;
        }
        Bitmap m3586c = m3586c("/background.jpg");
        if (m3586c == null) {
            bitmapDrawable = m3590b().m3851a(context, this, 0);
        } else {
            bitmapDrawable = new BitmapDrawable(m3586c);
        }
        m3525j();
        return bitmapDrawable;
    }

    /* renamed from: b */
    public Drawable m3589b(Context context) {
        if (!m3581g()) {
            return null;
        }
        Drawable m3851a = m3590b().m3851a(context, this, 0);
        m3525j();
        return m3851a;
    }
}
