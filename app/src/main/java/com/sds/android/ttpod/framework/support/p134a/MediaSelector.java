package com.sds.android.ttpod.framework.support.p134a;

import android.content.Context;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.environment.PreferencesID;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/* renamed from: com.sds.android.ttpod.framework.support.a.c */
/* loaded from: classes.dex */
public class MediaSelector {

    /* renamed from: a */
    private List<String> f7075a;

    /* renamed from: c */
    private MediaItem f7077c;

    /* renamed from: f */
    private Context f7080f;

    /* renamed from: b */
    private String f7076b = Preferences.getLocalGroupId();

    /* renamed from: d */
    private ArrayList<String> f7078d = new ArrayList<>();

    /* renamed from: e */
    private Preferences.InterfaceC2031a f7079e = new Preferences.InterfaceC2031a() { // from class: com.sds.android.ttpod.framework.support.a.c.1
        @Override // com.sds.android.ttpod.framework.storage.environment.Preferences.InterfaceC2031a
        /* renamed from: a */
        public void mo2553a(PreferencesID preferencesID) {
            MediaSelector.this.f7078d.clear();
        }
    };

    public MediaSelector(Context context) {
        this.f7080f = null;
        this.f7080f = context;
        m2651f();
    }

    /* renamed from: f */
    private void m2651f() {
        if (!m2650g()) {
            this.f7076b = MediaStorage.GROUP_ID_ALL_LOCAL;
            Preferences.m2894d(MediaStorage.GROUP_ID_ALL_LOCAL);
            Preferences.getMediaId("");
        }
        this.f7075a = MediaStorage.queryMediaIDs(this.f7080f, this.f7076b, Preferences.m2860l(this.f7076b));
        String m2854n = Preferences.getMediaId();
        if (!StringUtils.isEmpty(m2854n)) {
            this.f7077c = MediaStorage.queryMediaItem(this.f7080f, this.f7076b, m2854n);
        }
        Preferences.m3019a(PreferencesID.PLAY_MODE, this.f7079e);
    }

    /* renamed from: g */
    private boolean m2650g() {
        return MediaStorage.isGroupExisted(this.f7080f, this.f7076b) && !MediaStorage.queryMediaIDs(this.f7080f, this.f7076b, Preferences.m2860l(this.f7076b)).isEmpty();
    }

    /* renamed from: a */
    public void m2657a(String str, String str2) {
        if (!StringUtils.equals(this.f7076b, str)) {
            this.f7078d.clear();
        }
        this.f7077c = MediaStorage.queryMediaItem(this.f7080f, str, Preferences.getMediaId());
        this.f7075a = MediaStorage.queryMediaIDs(this.f7080f, m2658a(str) ? MediaStorage.GROUP_ID_FAV_LOCAL : str, Preferences.m2860l(str));
        if (m2658a(str) && !this.f7075a.isEmpty() && !this.f7075a.contains(str2)) {
            str2 = this.f7075a.get(0);
        }
        this.f7075a = MediaStorage.queryMediaIDs(this.f7080f, m2658a(str) ? MediaStorage.GROUP_ID_FAV_LOCAL : str, Preferences.m2860l(str));
        if (m2658a(str) && !this.f7075a.isEmpty() && !this.f7075a.contains(str2)) {
            str2 = this.f7075a.get(0);
        }
        this.f7076b = str;
        Preferences.m2894d(str);
        Preferences.getMediaId(str2);
        if (Preferences.m2862l() == PlayMode.SHUFFLE) {
            if (this.f7078d.size() > 0) {
                Iterator<String> it = this.f7078d.iterator();
                while (it.hasNext()) {
                    if (!this.f7075a.contains(it.next())) {
                        it.remove();
                    }
                }
            }
            this.f7078d.add(str2);
        }
    }

    /* renamed from: a */
    private boolean m2658a(String str) {
        return Preferences.m2954aq() == null && StringUtils.equals(MediaStorage.GROUP_ID_FAV, str);
    }

    /* renamed from: a */
    public int m2662a() {
        if (this.f7077c != null) {
            return this.f7075a.indexOf(this.f7077c.getID());
        }
        return 0;
    }

    /* renamed from: b */
    public MediaItem m2655b() {
        String m2854n = Preferences.getMediaId();
        this.f7076b = Preferences.getLocalGroupId();
        if (this.f7077c != null && (!StringUtils.equals(this.f7077c.getID(), m2854n) || !StringUtils.equals(this.f7077c.getGroupID(), this.f7076b))) {
            this.f7077c = MediaStorage.queryMediaItem(this.f7080f, this.f7076b, m2854n);
        }
        return this.f7077c;
    }

    /* renamed from: a */
    public void m2659a(MediaItem mediaItem) {
        this.f7077c = mediaItem;
    }

    /* renamed from: c */
    public void m2654c() {
        m2656a(false, true);
    }

    /* renamed from: d */
    public void m2653d() {
        m2656a(true, true);
    }

    /* renamed from: e */
    public void m2652e() {
        m2656a(true, false);
    }

    /* renamed from: a */
    private void m2656a(boolean z, boolean z2) {
        String id = this.f7077c == null ? "" : this.f7077c.getID();
        if (this.f7075a == null || this.f7075a.size() <= 0) {
            id = null;
        } else {
            int size = this.f7075a.size();
            int indexOf = this.f7075a.indexOf(id);
            switch (Preferences.m2862l()) {
                case SEQUENCE:
                    int i = indexOf + (z ? 1 : -1);
                    if (z2) {
                        i = (i + size) % size;
                    }
                    if (i >= 0 && i < size) {
                        id = this.f7075a.get(i);
                        break;
                    } else {
                        id = null;
                        break;
                    }
                case SHUFFLE:
                    if (size != 1) {
                        if (z) {
                            ArrayList arrayList = new ArrayList();
                            arrayList.addAll(this.f7075a);
                            int m2661a = m2661a(size);
                            int size2 = this.f7078d.size();
                            if (size2 > m2661a) {
                                arrayList.removeAll(this.f7078d.subList(size2 - m2661a, size2));
                            } else {
                                arrayList.removeAll(this.f7078d);
                            }
                            if (arrayList.size() > 0) {
                                id = (String) arrayList.get(new Random().nextInt(arrayList.size()));
                                this.f7078d.add(id);
                            }
                        } else if (this.f7078d.size() > 0) {
                            this.f7078d.add(0, id);
                            this.f7078d.remove(this.f7078d.size() - 1);
                            id = this.f7078d.get(this.f7078d.size() - 1);
                        } else {
                            id = this.f7075a.get(new Random().nextInt(this.f7075a.size()));
                            this.f7078d.add(id);
                        }
                        if (this.f7078d.size() > this.f7075a.size()) {
                            this.f7078d.remove(0);
                            break;
                        }
                    }
                    break;
                case REPEAT:
                    id = this.f7075a.get((((z ? 1 : -1) + indexOf) + size) % size);
                    break;
                case REPEAT_ONE:
                    if (z2) {
                        id = this.f7075a.get(((indexOf + (z ? 1 : -1)) + size) % size);
                        break;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("illegal play mode!");
            }
        }
        Preferences.getMediaId(id);
        this.f7077c = MediaStorage.queryMediaItem(this.f7080f, this.f7076b, id);
    }

    /* renamed from: a */
    private int m2661a(int i) {
        if (i <= 4) {
            return i - 1;
        }
        if (i <= 10) {
            return i - 2;
        }
        if (i <= 20) {
            return i - 4;
        }
        return i - (i / 3);
    }
}
