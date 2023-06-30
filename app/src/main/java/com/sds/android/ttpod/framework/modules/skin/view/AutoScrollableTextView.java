package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.collection.SparseArrayCompat;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.SparseIntArray;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.skin.p130c.ValueParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class AutoScrollableTextView extends androidx.appcompat.widget.AppCompatCheckedTextView {

    /* renamed from: a */
    private boolean autoScrollEnable;

    /* renamed from: b */
    private String formatString;

    /* renamed from: c */
    private SpannableStringBuilder spannableStringBuilder;

    /* renamed from: d */
    private Map<String, int[]> f6726d;

    /* renamed from: e */
    private ArrayList<Object> spanMaps;

    /* renamed from: f */
    private SparseIntArray spanHashCodeLengthArray;

    /* renamed from: g */
    private SparseArrayCompat<ArrayList<Object>> formatDataArray;

    public AutoScrollableTextView(Context context) {
        super(context);
        this.autoScrollEnable = false;
        this.formatString = null;
        this.spannableStringBuilder = new SpannableStringBuilder();
        this.f6726d = new HashMap();
        this.spanMaps = new ArrayList<>();
        this.spanHashCodeLengthArray = new SparseIntArray();
        this.formatDataArray = new SparseArrayCompat<>();
    }

    public AutoScrollableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.autoScrollEnable = false;
        this.formatString = null;
        this.spannableStringBuilder = new SpannableStringBuilder();
        this.f6726d = new HashMap();
        this.spanMaps = new ArrayList<>();
        this.spanHashCodeLengthArray = new SparseIntArray();
        this.formatDataArray = new SparseArrayCompat<>();
        m3492a(context, attributeSet);
    }

    public AutoScrollableTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.autoScrollEnable = false;
        this.formatString = null;
        this.spannableStringBuilder = new SpannableStringBuilder();
        this.f6726d = new HashMap();
        this.spanMaps = new ArrayList<>();
        this.spanHashCodeLengthArray = new SparseIntArray();
        this.formatDataArray = new SparseArrayCompat<>();
        m3492a(context, attributeSet);
    }

    /* renamed from: a */
    private void m3492a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AutoScrollableTextView);
        this.autoScrollEnable = obtainStyledAttributes.getBoolean(R.styleable.AutoScrollableTextView_autoScrollEnable, this.autoScrollEnable);
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    public void m3490a(CharSequence... charSequenceArr) {
        if (charSequenceArr != null && charSequenceArr.length != 0) {
            int i = 0;
            while (i < charSequenceArr.length - 1) {
                int i2 = i + 1;
                int[] iArr = this.f6726d.get(String.valueOf(charSequenceArr[i]));
                if (iArr != null) {
                    CharSequence charSequence = charSequenceArr[i2];
                    if (charSequence == null) {
                        charSequence = "";
                    }
                    this.spannableStringBuilder.replace(iArr[0], iArr[1], charSequence);
                    int length = charSequence.length() + iArr[0];
                    int i3 = length - iArr[1];
                    iArr[1] = length;
                    for (int[] iArr2 : this.f6726d.values()) {
                        if (iArr2[0] > iArr[0]) {
                            iArr2[0] = iArr2[0] + i3;
                            iArr2[1] = iArr2[1] + i3;
                        }
                    }
                }
                i = i2 + 1;
            }
            setText(this.spannableStringBuilder);
        }
    }

    /* renamed from: a */
    public boolean m3491a(String str) {
        return this.f6726d.containsKey(str);
    }

    public void setFormatString(String textContent) {
        int indexOf;
        String str2;
        Object standard;
        if (!TextUtils.equals(textContent, this.formatString)) {
            this.f6726d.clear();
            this.spanMaps.clear();
            this.spanHashCodeLengthArray.clear();
            this.formatDataArray.clear();
            this.formatString = textContent;
            SpannableStringBuilder spannableStringBuilder = this.spannableStringBuilder;
            spannableStringBuilder.clearSpans();
            spannableStringBuilder.clear();
            if (textContent != null) {
                int length = "$[".length();
                int i = 0;
                int i2 = 0;
                while (i2 < textContent.length() && (indexOf = textContent.indexOf("$[", i2)) >= 0) {
                    spannableStringBuilder.append((CharSequence) textContent.substring(i, indexOf));
                    int i3 = indexOf + length;
                    int indexOf2 = textContent.indexOf("]", i3);
                    if (indexOf2 <= i3) {
                        break;
                    }
                    int i4 = indexOf2 + 1;
                    String trim = textContent.substring(i3, indexOf2).trim();
                    int indexOf3 = trim.indexOf(58);
                    if (indexOf3 > 0) {
                        str2 = trim.substring(indexOf3 + 1).trim();
                        trim = trim.substring(0, indexOf3).trim();
                    } else {
                        str2 = null;
                    }
                    int length2 = spannableStringBuilder.length();
                    if (str2 != null) {
                        if ("Color".equals(trim)) {
                            standard = new ForegroundColorSpan(ValueParser.parseColor(str2, getCurrentTextColor()));
                        } else if ("Size".equals(trim)) {
                            int textSize = (int) getTextSize();
                            standard = new AbsoluteSizeSpan(ValueParser.getSize(ValueParser.parseCommon(str2, textSize), textSize));
                        } else {
                            standard = "Align".equals(trim) ? new AlignmentSpan.Standard(ValueParser.stringToAlign(str2, Layout.Alignment.ALIGN_NORMAL)) : null;
                        }
                        if (standard != null) {
                            this.spanMaps.add(standard);
                            this.spanHashCodeLengthArray.put(standard.hashCode(), length2);
                            ArrayList<Object> arrayList = this.formatDataArray.get(length2);
                            if (arrayList == null) {
                                arrayList = new ArrayList<>();
                                this.formatDataArray.put(length2, arrayList);
                            }
                            arrayList.add(standard);
                        }
                    } else {
                        int[] iArr = this.f6726d.get(trim);
                        if (iArr == null) {
                            iArr = new int[2];
                            this.f6726d.put(trim, iArr);
                        }
                        iArr[0] = length2;
                        iArr[1] = length2;
                    }
                    i = i4;
                    i2 = i3;
                }
            }
            m3493a();
            setText(this.spannableStringBuilder);
        }
    }

    /* renamed from: a */
    private void m3493a() {
        Object obj;
        this.spannableStringBuilder.clearSpans();
        SparseIntArray sparseIntArray = new SparseIntArray();
        Iterator<Object> it = this.spanMaps.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            int i = this.spanHashCodeLengthArray.get(next.hashCode());
            Class<?> cls = next.getClass();
            int hashCode = cls.hashCode();
            int i2 = sparseIntArray.get(hashCode, -1);
            if (i2 >= 0) {
                sparseIntArray.delete(i2);
                ArrayList<Object> arrayList = this.formatDataArray.get(i2);
                if (arrayList != null) {
                    Iterator<Object> it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        obj = it2.next();
                        if (obj.getClass() == cls) {
                            break;
                        }
                    }
                }
                obj = null;
                if (obj != null) {
                    this.spannableStringBuilder.setSpan(obj, i2, i, 18);
                }
            }
            sparseIntArray.put(hashCode, i);
        }
        int size = sparseIntArray.size();
        for (int i3 = 0; i3 < size; i3++) {
            int valueAt = sparseIntArray.valueAt(i3);
            Iterator<Object> it3 = this.formatDataArray.get(valueAt).iterator();
            while (it3.hasNext()) {
                Object next2 = it3.next();
                if (next2 != null) {
                    this.spannableStringBuilder.setSpan(next2, valueAt, this.spannableStringBuilder.length(), 18);
                }
            }
        }
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.autoScrollEnable || super.isFocused();
    }

    public void setAutoScrollable(boolean autoScrollEnable) {
        this.autoScrollEnable = autoScrollEnable;
    }
}
