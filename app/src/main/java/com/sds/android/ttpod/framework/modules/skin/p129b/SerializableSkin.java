package com.sds.android.ttpod.framework.modules.skin.p129b;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.aa */
/* loaded from: classes.dex */
public class SerializableSkin implements Container<SBaseView>, Serializable {

    /* renamed from: a */
    private String path;

    /* renamed from: b */
    private long f6402b;

    /* renamed from: c */
    private SSkinInfo sSkinInfo;

    /* renamed from: d */
    private int f6404d;

    /* renamed from: e */
    private SBaseView[] f6405e;

    public SerializableSkin(String str, long j, int i) {
        this.path = str;
        this.f6402b = j;
        this.f6404d = i;
    }

    /* renamed from: a */
    public void m3849a(KXmlParser kXmlParser) {
        this.sSkinInfo = new SSkinInfo(kXmlParser);
    }

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.Container
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo3791a(SBaseView[] sBaseViewArr) {
        this.f6405e = sBaseViewArr;
    }

    /* renamed from: a */
    public SSkinInfo m3853a() {
        if (this.sSkinInfo == null) {
            return null;
        }
        return new SSkinInfo(this.sSkinInfo);
    }

    /* renamed from: a */
    public SBaseView m3846a(String str, int i) {
        SBaseView sBaseView;
        SBaseView sBaseView2;
        if (this.f6405e == null || this.f6405e.length <= 0) {
            return null;
        }
        SBaseView[] sBaseViewArr = this.f6405e;
        int length = sBaseViewArr.length;
        int i2 = 0;
        SBaseView sBaseView3 = null;
        while (true) {
            if (i2 >= length) {
                sBaseView = sBaseView3;
                sBaseView2 = null;
                break;
            }
            sBaseView = sBaseViewArr[i2];
            if (!TextUtils.equals(str, sBaseView.id)) {
                sBaseView = sBaseView3;
            } else if (sBaseView.f6432c == i) {
                sBaseView2 = sBaseView;
                break;
            }
            i2++;
            sBaseView3 = sBaseView;
        }
        return sBaseView2 == null ? sBaseView : sBaseView2;
    }

    /* renamed from: a */
    public SPlayerView m3852a(int i) {
        SBaseView m3846a = m3846a("Player", i);
        if (m3846a instanceof SPlayerView) {
            return (SPlayerView) m3846a;
        }
        return null;
    }

    /* renamed from: b */
    public SPlaylistView m3842b(int i) {
        SBaseView m3846a = m3846a("Playlist", i);
        if (m3846a instanceof SPlaylistView) {
            return (SPlaylistView) m3846a;
        }
        return null;
    }

    /* renamed from: a */
    public Drawable m3851a(Context context, SkinCache skinCache, int i) {
        SPlayerView m3852a;
        Drawable m3595a = this.sSkinInfo != null ? skinCache.m3595a(context.getResources(), this.sSkinInfo.background) : null;
        if (!m3850a(m3595a) && (m3852a = m3852a(i)) != null) {
            m3595a = m3852a.m3810d(context, skinCache);
        }
        if (m3595a != null) {
            return m3595a.getConstantState().newDrawable();
        }
        return null;
    }

    /* renamed from: a */
    private boolean m3850a(Drawable drawable) {
        return (drawable == null || ((drawable instanceof ColorDrawable) && ((ColorDrawable) drawable).getAlpha() == 0)) ? false : true;
    }

    /* renamed from: a */
    public static SSkinInfo m3847a(Reader reader) throws Exception {
        KXmlParser kXmlParser = new KXmlParser();
        kXmlParser.setInput(reader);
        kXmlParser.nextTag();
        kXmlParser.require(2, null, "Theme");
        return new SSkinInfo(kXmlParser);
    }

    /* renamed from: a */
    public static SerializableSkin m3845a(String str, long j, Reader reader, int i) throws Exception {
        int next;
        Object m3844a;
        SerializableSkin serializableSkin = new SerializableSkin(str, j, i);
        KXmlParser kXmlParser = new KXmlParser();
        kXmlParser.setInput(reader);
        kXmlParser.nextTag();
        kXmlParser.require(2, null, "Theme");
        serializableSkin.m3849a(kXmlParser);
        int i2 = serializableSkin.sSkinInfo.loaderVersion;
        ArrayList arrayList = new ArrayList(10);
        HashMap hashMap = new HashMap(10);
        HashMap hashMap2 = new HashMap(10);
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray(10);
        arrayList.add(new C1978a(serializableSkin));
        sparseBooleanArray.put(kXmlParser.getDepth(), true);
        do {
            next = kXmlParser.next();
            switch (next) {
                case 2:
                    String name = kXmlParser.getName();
                    if ("Bitmap".equals(name)) {
                        SBitmap sBitmap = new SBitmap(kXmlParser);
                        hashMap.put(sBitmap.id, sBitmap);
                        break;
                    } else if ("Font".equals(name)) {
                        SFont sFont = new SFont(kXmlParser);
                        hashMap2.put(sFont.familyName, sFont);
                        break;
                    } else {
                        int size = arrayList.size();
                        C1978a c1978a = size > 0 ? (C1978a) arrayList.get(size - 1) : null;
                        if (c1978a != null) {
                            if ("View".equals(name)) {
                                m3844a = m3848a(kXmlParser, hashMap, i2);
                            } else if ("Panel".equals(name)) {
                                m3844a = new SPanel(kXmlParser);
                            } else {
                                m3844a = m3844a(name, kXmlParser, hashMap, hashMap2, i2);
                                if (m3844a == null) {
                                    if ("Event".equals(name)) {
                                        m3844a = new SEvent(kXmlParser);
                                    } else if ("Motion".equals(name)) {
                                        m3844a = new SMotion(kXmlParser);
                                    }
                                }
                            }
                            if (m3844a != null) {
                                c1978a.m3840a(m3844a);
                                if (m3844a instanceof Container) {
                                    arrayList.add(new C1978a((Container) m3844a));
                                    sparseBooleanArray.put(kXmlParser.getDepth(), true);
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case 3:
                    int depth = kXmlParser.getDepth();
                    if (sparseBooleanArray.get(depth)) {
                        int size2 = arrayList.size();
                        C1978a c1978a2 = size2 > 0 ? (C1978a) arrayList.remove(size2 - 1) : null;
                        if (c1978a2 != null) {
                            c1978a2.m3841a();
                        }
                        sparseBooleanArray.put(depth, false);
                        break;
                    }
                    break;
            }
        } while (next != 1);
        return serializableSkin;
    }

    /* renamed from: a */
    private static SBaseView m3848a(KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, int i) {
        return "Playlist".equals(kXmlParser.getAttributeValue(null, "ID")) ? new SPlaylistView(kXmlParser, hashMap, i) : new SPlayerView(kXmlParser, hashMap, i);
    }

    /* renamed from: a */
    private static SComponent m3844a(String str, KXmlParser kXmlParser, HashMap<String, SBitmap> hashMap, HashMap<String, SFont> hashMap2, int i) {
        if ("Button".equals(str)) {
            return new SButton(kXmlParser, hashMap, i);
        }
        if ("Region".equals(str)) {
            return new SRegion(kXmlParser, hashMap, i);
        }
        if ("Text".equals(str)) {
            return new SScrollText(kXmlParser, hashMap, hashMap2, i);
        }
        if ("Icon".equals(str)) {
            return new SIcon(kXmlParser, hashMap, i);
        }
        if ("Slide".equals(str)) {
            return new SSlide(kXmlParser, hashMap, i);
        }
        if ("Animation".equals(str)) {
            return new SAnimation(kXmlParser, hashMap, i);
        }
        if ("LyricShow".equals(str)) {
            return new SLyricShow(kXmlParser, hashMap, hashMap2, i);
        }
        if ("Analyzer".equals(str)) {
            return new SAnalyzer(kXmlParser, hashMap, i);
        }
        if ("Image".equals(str)) {
            return new SAnimTransImage(kXmlParser, hashMap, i);
        }
        if ("ComponentGroup".equals(str)) {
            return new SComponentGroup(kXmlParser, hashMap, i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SerializableSkin.java */
    /* renamed from: com.sds.android.ttpod.framework.modules.skin.b.aa$a */
    /* loaded from: classes.dex */
    public static class C1978a {

        /* renamed from: a */
        private final ArrayList f6406a = new ArrayList(10);

        /* renamed from: b */
        private final ArrayList<SEvent> f6407b = new ArrayList<>(10);

        /* renamed from: c */
        private Container f6408c;

        C1978a(Container container) {
            this.f6408c = container;
        }

        /* renamed from: a */
        void m3840a(Object obj) {
            if (obj instanceof SEvent) {
                this.f6407b.add((SEvent) obj);
            } else {
                this.f6406a.add(obj);
            }
        }

        /* renamed from: a */
        void m3841a() {
            SEvent[] sEventArr;
            int size = this.f6406a.size();
            if (size > 0) {
                if (this.f6408c instanceof SPlayerView) {
                    if (this.f6406a.get(0) instanceof SPanel) {
                        SPanel[] sPanelArr = new SPanel[size];
                        this.f6406a.toArray(sPanelArr);
                        this.f6408c.mo3791a(sPanelArr);
                    }
                } else if ((this.f6408c instanceof SComponentView) || (this.f6408c instanceof SPanel) || (this.f6408c instanceof SComponentGroup)) {
                    SComponent[] sComponentArr = new SComponent[size];
                    this.f6406a.toArray(sComponentArr);
                    this.f6408c.mo3791a(sComponentArr);
                } else if (this.f6408c instanceof SEvent) {
                    SMotion[] sMotionArr = new SMotion[size];
                    this.f6406a.toArray(sMotionArr);
                    this.f6408c.mo3791a(sMotionArr);
                } else if (this.f6408c instanceof SerializableSkin) {
                    SBaseView[] sBaseViewArr = new SBaseView[size];
                    this.f6406a.toArray(sBaseViewArr);
                    this.f6408c.mo3791a(sBaseViewArr);
                } else {
                    this.f6408c.mo3791a(null);
                }
            } else {
                this.f6408c.mo3791a(null);
            }
            if (this.f6408c instanceof HasEvent) {
                int size2 = this.f6407b.size();
                if (size2 > 0) {
                    SEvent[] sEventArr2 = new SEvent[size2];
                    this.f6407b.toArray(sEventArr2);
                    sEventArr = sEventArr2;
                } else {
                    sEventArr = null;
                }
                ((HasEvent) this.f6408c).mo3792a(sEventArr);
            }
        }
    }
}
