package com.sds.android.ttpod.framework.modules.skin.serialskin;

import android.text.TextUtils;
import com.sds.android.ttpod.framework.modules.search.p127a.KXmlParser;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.b.h */
/* loaded from: classes.dex */
public class SBitmap extends SBase {

    /* renamed from: c */
    protected String file;

    /* renamed from: d */
    private String empty;

    /* renamed from: e */
    private String pressed;

    /* renamed from: f */
    private String enabled;

    /* renamed from: g */
    private String checked;

    /* renamed from: h */
    private String focused;

    /* renamed from: i */
    private boolean dataAllNull;

    @Override // com.sds.android.ttpod.framework.modules.skin.p129b.SBase
    /* renamed from: a */
    public /* bridge */ /* synthetic */ String getId() {
        return super.getId();
    }

    SBitmap() {
        this.dataAllNull = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SBitmap(KXmlParser kXmlParser) {
        super(kXmlParser);
        this.dataAllNull = false;
        this.file = kXmlParser.getAttributeValue(null, "File");
        this.empty = kXmlParser.getAttributeValue(null, "Empty");
        this.pressed = kXmlParser.getAttributeValue(null, "Pressed");
        this.enabled = kXmlParser.getAttributeValue(null, "Enabled");
        this.checked = kXmlParser.getAttributeValue(null, "Checked");
        this.focused = kXmlParser.getAttributeValue(null, "Focused");
        m3827b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SBitmap(String str) {
        this.dataAllNull = false;
        this.file = str;
    }

    /* renamed from: b */
    public void m3827b() {
        this.dataAllNull = !TextUtils.isEmpty(this.empty)
                || !TextUtils.isEmpty(this.pressed)
                || !TextUtils.isEmpty(this.enabled)
                || !TextUtils.isEmpty(this.checked)
                || !TextUtils.isEmpty(this.focused);
    }

    /* renamed from: c */
    public String getFile() {
        return this.file;
    }

    /* renamed from: a */
    public void setEmpty(String str) {
        this.empty = str;
    }

    /* renamed from: d */
    public String getEmpty() {
        return this.empty;
    }

    /* renamed from: b */
    public void setPoressed(String str) {
        this.pressed = str;
    }

    /* renamed from: e */
    public String getPressed() {
        return this.pressed;
    }

    /* renamed from: c */
    public void setEnabled(String str) {
        this.enabled = str;
    }

    /* renamed from: f */
    public String getEnabled() {
        return this.enabled;
    }

    /* renamed from: d */
    public void setFocused(String str) {
        this.focused = str;
    }

    /* renamed from: g */
    public String getFocused() {
        return this.focused;
    }

    /* renamed from: e */
    public void setChecked(String str) {
        this.checked = str;
    }

    /* renamed from: h */
    public String getChecked() {
        return this.checked;
    }

    /* renamed from: i */
    public boolean m3816i() {
        return this.dataAllNull;
    }
}
