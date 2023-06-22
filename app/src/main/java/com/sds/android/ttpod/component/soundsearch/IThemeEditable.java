package com.sds.android.ttpod.component.soundsearch;

/* renamed from: com.sds.android.ttpod.component.soundsearch.b */
/* loaded from: classes.dex */
public interface IThemeEditable {

    /* compiled from: IThemeEditable.java */
    /* renamed from: com.sds.android.ttpod.component.soundsearch.b$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1333a {
        void onRemoveRequested();

        void onSelectedCountChanged();

        void onStopEditRequested();
    }

    void selectAll();

    void selectNone();

    int selectedCount();

    int totalCount();
}
