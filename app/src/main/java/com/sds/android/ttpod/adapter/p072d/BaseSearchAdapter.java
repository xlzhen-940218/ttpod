package com.sds.android.ttpod.adapter.p072d;

import android.app.Activity;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.BaseAdapter;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.PinyinUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* renamed from: com.sds.android.ttpod.adapter.d.a */
/* loaded from: classes.dex */
public abstract class BaseSearchAdapter extends BaseAdapter {

    /* renamed from: c */
    protected static String f3225c;

    /* renamed from: d */
    protected static String f3226d;

    /* renamed from: e */
    protected static ForegroundColorSpan f3227e;

    /* renamed from: b */
    protected Activity f3228b;

    /* renamed from: f */
    protected List<InterfaceC0967a> f3229f = new ArrayList();

    /* renamed from: g */
    protected List<InterfaceC0967a> f3230g = new ArrayList();

    /* renamed from: h */
    protected List<InterfaceC0967a> f3231h = new ArrayList();

    /* compiled from: BaseSearchAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.d.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0967a {
        /* renamed from: c */
        int[] mo7583c();
    }

    public BaseSearchAdapter(Activity activity) {
        this.f3228b = activity;
        f3227e = new ForegroundColorSpan(this.f3228b.getResources().getColor(R.color.text_search_highlight_color));
    }

    /* renamed from: a */
    public void m7591a(InterfaceC0967a interfaceC0967a) {
        this.f3229f.remove(interfaceC0967a);
        this.f3231h.remove(interfaceC0967a);
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public void mo7590a(MediaItem mediaItem) {
    }

    /* renamed from: e */
    public void m7584e() {
        f3225c = "";
        f3226d = "";
        this.f3231h = this.f3229f;
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public int m7588a(String str) {
        if (this.f3229f == null) {
            return 0;
        }
        f3225c = f3226d;
        f3226d = str;
        if (TextUtils.isEmpty(str)) {
            this.f3231h.clear();
        } else if (f3226d.length() > (f3225c != null ? f3225c.length() : 0) && this.f3231h == this.f3230g) {
            for (int size = this.f3231h.size() - 1; size >= 0; size--) {
                if (this.f3231h.get(size).mo7583c() == null) {
                    this.f3231h.remove(size);
                }
            }
        } else {
            this.f3231h = this.f3230g;
            this.f3231h.clear();
            for (InterfaceC0967a interfaceC0967a : this.f3229f) {
                if (interfaceC0967a.mo7583c() != null) {
                    this.f3231h.add(interfaceC0967a);
                }
            }
        }
        notifyDataSetChanged();
        return this.f3231h.size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BaseSearchAdapter.java */
    /* renamed from: com.sds.android.ttpod.adapter.d.a$b */
    /* loaded from: classes.dex */
    public class C0968b {

        /* renamed from: b */
        private int f3233b;

        /* renamed from: c */
        private int f3234c;

        /* renamed from: d */
        private int f3235d;

        public C0968b(int i, int i2, int i3) {
            this.f3233b = i;
            this.f3234c = i2;
            this.f3235d = i3;
        }
    }

    /* renamed from: a */
    private boolean m7586a(char[] cArr, char[] cArr2, int i) {
        int i2 = 1;
        for (int i3 = i + 1; i2 < cArr.length && i3 < cArr2.length; i3++) {
            if (cArr[i2] == cArr2[i3]) {
                i2++;
            } else {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private int m7585a(char[] cArr, char[][] cArr2, int i, int[] iArr) {
        boolean z;
        int length = cArr.length;
        Stack stack = new Stack();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i >= cArr2.length) {
                z = false;
                break;
            }
            char[] cArr3 = cArr2[i];
            if (cArr3 == null) {
                i2 += iArr[i];
            } else if (cArr3[0] != cArr[i3]) {
                if (stack.empty()) {
                    z = false;
                    break;
                }
                C0968b c0968b = (C0968b) stack.pop();
                i = c0968b.f3233b;
                i3 = c0968b.f3234c + 1;
                i2 = c0968b.f3235d;
            } else if (m7586a(cArr3, cArr, i3)) {
                if (cArr3.length + i3 >= length) {
                    if (iArr[i] > 1) {
                        i2 += length - i3;
                        z = true;
                    } else {
                        i2++;
                        z = true;
                    }
                } else {
                    i2 += iArr[i];
                    stack.push(new C0968b(i, i3, i2));
                    i3 += cArr3.length;
                }
            } else {
                i3++;
                i2 += iArr[i];
            }
            i++;
        }
        if (z) {
            return i2;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m7589a(PinyinUtils.Pinyin pinyin, int[] iArr) {
        int m7585a;
        if (TextUtils.isEmpty(f3226d) || pinyin == null) {
            return false;
        }
        char[] charArray = f3226d.toCharArray();
        char[][] t9Key = pinyin.getT9Key();
        int[] placeHolder = pinyin.getPlaceHolder();
        int i = 0;
        for (int i2 = 0; i2 < t9Key.length; i2++) {
            if (t9Key[i2] != null && (m7585a = m7585a(charArray, t9Key, i2, placeHolder)) > 0) {
                iArr[1] = i;
                iArr[2] = m7585a;
                return true;
            }
            i += placeHolder[i2];
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m7587a(String str, int[] iArr) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(f3226d)) {
            return false;
        }
        int indexOf = str.indexOf(f3226d);
        if (indexOf >= 0) {
            iArr[1] = indexOf;
            iArr[2] = f3226d.length();
        }
        return indexOf >= 0;
    }
}
