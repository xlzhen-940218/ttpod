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
import com.sds.android.ttpod.framework.p106a.BitmapUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.j */
/* loaded from: classes.dex */
public class SkinCache extends SkinReader {

    /* renamed from: a */
    private HashMap<String, Bitmap> imageBitMaps = new HashMap<>();

    /* renamed from: c */
    private HashMap<String, DrawableCreator> imageDrawableCreatorMaps = new HashMap<>();

    /* renamed from: d */
    private HashMap<String, Typeface> typefaceMaps = new HashMap<>();

    /* renamed from: e */
    private String path = null;

    /* renamed from: f */
    private SerializableSkin serializableSkin = null;

    /* renamed from: g */
    private SSkinInfo f6650g = null;

    /* renamed from: h */
    private long lastModified = 0;

    /* renamed from: i */
    private String name;

    /* renamed from: j */
    private int type;

    public SkinCache(String str) {
        m3592a(str);
    }

    /* renamed from: a */
    public void m3592a(String str) {
        if (!TextUtils.equals(str, this.path)) {
            clear();
            this.path = str;
            if (str.startsWith("assets://")) {
                this.type = 1;
                this.name = str.substring("assets://".length());
            } else if (str.startsWith("package://")) {
                this.type = 2;
                this.name = str.substring("package://".length());
            } else if (str.startsWith("file://")) {
                this.type = 0;
                this.name = str.substring("file://".length());
            } else {
                this.type = 0;
                this.name = str;
            }
            m3578m();
        }
    }

    /* renamed from: m */
    private void m3578m() {
        if (this.type == 0) {
            this.lastModified = new File(this.name).lastModified();
        }
    }

    /* renamed from: a */
    public String getPath() {
        return this.path;
    }

    /* renamed from: b */
    public SerializableSkin getSerializableSkin() {
        return this.serializableSkin;
    }

    /* renamed from: c */
    public long getLastModified() {
        return this.lastModified;
    }

    /* renamed from: d */
    public boolean serializableSkinNotNull() {
        return this.serializableSkin != null;
    }

    /* renamed from: a */
    public Typeface getTypeFace(SFont sFont) {
        String str;
        if (sFont == null || this.typefaceMaps == null) {
            return null;
        }
        String m3801b = sFont.getFamilyName();
        int max = Math.max(0, sFont.getStyle());
        if (m3801b == null) {
            str = String.valueOf(max);
        } else {
            str = m3801b + max;
        }
        Typeface typeface = this.typefaceMaps.get(str);
        if (typeface == null) {
            if (m3801b != null) {
                if (m3801b.startsWith("file://")) {
                    typeface = Typeface.createFromFile(m3801b);
                }
            } else if (max > 0) {
                typeface = Typeface.create(m3801b, max);
            }
            if (typeface != null) {
                this.typefaceMaps.put(str, typeface);
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
                stateListDrawableCreator.addStateDrawableCreator(ViewWrapper.PRESSED_ENABLED_STATE_SET, getDrawableCreator(sBitmap.getPressed()));
                stateListDrawableCreator.addStateDrawableCreator(ViewWrapper.ENABLED_STATE_SET, getDrawableCreator(sBitmap.getEnabled()));
                stateListDrawableCreator.addStateDrawableCreator(ViewWrapper.SELECTED_STATE_SET, getDrawableCreator(sBitmap.getChecked()));
                stateListDrawableCreator.addStateDrawableCreator(ViewWrapper.FOCUSED_STATE_SET, getDrawableCreator(sBitmap.getFocused()));
                stateListDrawableCreator.addStateDrawableCreator(ViewWrapper.EMPTY_STATE_SET, getDrawableCreator(sBitmap.getEmpty()));
                return stateListDrawableCreator;
            }
            return getDrawableCreator(sBitmap.getFile());
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public final DrawableCreator getDrawableCreator(String str) {
        String str2;
        String str3;
        DrawableCreator colorDrawableCreator;
        if (str == null || this.imageDrawableCreatorMaps == null) {
            return null;
        }
        DrawableCreator drawableCreator = this.imageDrawableCreatorMaps.get(str);
        if (drawableCreator == null) {
            int lastIndexOf = str.lastIndexOf(124);
            if (lastIndexOf > 0) {
                str3 = str.substring(0, lastIndexOf);
                str2 = str.substring(lastIndexOf + 1);
            } else {
                str2 = null;
                str3 = str;
            }
            Bitmap bitmap = this.imageBitMaps.get(str3);
            if (bitmap == null) {
                bitmap = loadTskBitmap(str3);
            }
            if (bitmap != null) {
                this.imageBitMaps.put(str3, bitmap);
                BitmapDrawableCreator bitmapDrawableCreator = new BitmapDrawableCreator(bitmap);
                if (str2 != null) {
                    TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
                    simpleStringSplitter.setString(str2);
                    if (simpleStringSplitter.hasNext()) {
                        bitmapDrawableCreator.m3759a(ValueParser.parseInt(simpleStringSplitter.next(), -1), simpleStringSplitter.hasNext() ? ValueParser.parseInt(simpleStringSplitter.next(), -1) : -1);
                    }
                }
                colorDrawableCreator = bitmapDrawableCreator;
            } else {
                colorDrawableCreator = new ColorDrawableCreator(ValueParser.stringToIntArray(str3, (int[]) null), ValueParser.parseInt(str2, 0));
            }
            this.imageDrawableCreatorMaps.put(str, colorDrawableCreator);
            return colorDrawableCreator;
        }
        return drawableCreator;
    }

    /* renamed from: a */
    public final Drawable m3596a(Resources resources, SBitmap sBitmap) {
        DrawableCreator m3594a = m3594a(sBitmap);
        if (m3594a != null) {
            return m3594a.getDrawable(resources);
        }
        return null;
    }

    /* renamed from: a */
    public final Drawable m3595a(Resources resources, String str) {
        DrawableCreator m3588b = getDrawableCreator(str);
        if (m3588b != null) {
            return m3588b.getDrawable(resources);
        }
        return null;
    }

    /* renamed from: c */
    public Bitmap loadTskBitmap(String name) {
        String str2;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPurgeable = true;
        options.inInputShareable = true;
        if (name.startsWith("file://")) {
            return BitmapFactory.decodeFile(name.substring("file://".length()), options);
        }
        if (name.startsWith("assets://")) {
            throw new UnsupportedOperationException("not support yet");
        }
        if (this.packHandle == null || !this.packHandle.streamNotNull()) {
            return null;
        }
        int indexOf = name.indexOf(File.separatorChar);
        options.inTargetDensity = DisplayUtils.getDensityDpi();
        options.inScaled = true;
        options.inDensity = DisplayUtils.getDpi();
        String dpiName = DisplayUtils.getDpiName();
        if (indexOf >= 0) {
            str2 = "/" + dpiName + name;
        } else {
            str2 = name + dpiName;
        }
        Bitmap bitmap = loadTskBitmap(str2, options);
        if (bitmap == null) {
            return loadTskBitmap(name, options);
        }
        return bitmap;
    }

    /* renamed from: a */
    public Bitmap loadTskBitmap(String resource, BitmapFactory.Options options) {
        Bitmap bitmap = null;
        try {
            byte[] bytes = this.packHandle.loadTskResource(resource);
            if (bytes != null) {
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
                if (BitmapUtils.scaleBitmap(options, DisplayUtils.getWidth(), DisplayUtils.getHeight())) {
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
                    if (SDKVersionUtils.sdkThan12()) {
                        bitmap.setHasAlpha(!BitmapUtils.isNotPng(options));
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
        return this.packHandle.loadTskResource(str);
    }

    /* renamed from: e */
    public Iterable<String> m3583e() {
        return this.packHandle;
    }

    /* renamed from: f */
    public SSkinInfo m3582f() {
        return this.serializableSkin == null ? this.f6650g : this.serializableSkin.m3853a();
    }

    /* renamed from: g */
    public boolean m3581g() {
        if (this.name == null || TextUtils.isEmpty(this.name)) {
            this.name = (String) CommandCenter.getInstance().m4602a(new Command(CommandID.GET_SKIN_PROTOCOL_PATH, new Object[0]), String.class);
        }
        return this.type == 0 ? m3526e(this.name) : m3530a(m3533a(this.type, this.name));
    }

    /* renamed from: h */
    public void m3580h() {
        if (m3581g()) {
            BufferedReader k = m3524k();
            try {
                if (k != null) {
                    try {
                        this.serializableSkin = SerializableSkin.m3845a(getPath(), getLastModified(), k, 65552);
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
    public void clear() {
        this.imageBitMaps.clear();
        this.imageDrawableCreatorMaps.clear();
        this.typefaceMaps.clear();
        this.path = null;
        this.serializableSkin = null;
        this.f6650g = null;
        this.lastModified = 0L;
        this.name = null;
        this.type = 0;
        handleClose();
        System.gc();
    }

    /* renamed from: a */
    public Drawable m3597a(Context context) {
        Drawable bitmapDrawable;
        if (!m3581g()) {
            return null;
        }
        Bitmap m3586c = loadTskBitmap("/background.jpg");
        if (m3586c == null) {
            bitmapDrawable = getSerializableSkin().m3851a(context, this, 0);
        } else {
            bitmapDrawable = new BitmapDrawable(m3586c);
        }
        handleClose();
        return bitmapDrawable;
    }

    /* renamed from: b */
    public Drawable m3589b(Context context) {
        if (!m3581g()) {
            return null;
        }
        Drawable m3851a = getSerializableSkin().m3851a(context, this, 0);
        handleClose();
        return m3851a;
    }
}
