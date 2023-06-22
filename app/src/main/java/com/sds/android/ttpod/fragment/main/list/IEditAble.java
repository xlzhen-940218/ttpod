package com.sds.android.ttpod.fragment.main.list;

/* renamed from: com.sds.android.ttpod.fragment.main.list.a */
/* loaded from: classes.dex */
public interface IEditAble {

    /* compiled from: IEditAble.java */
    /* renamed from: com.sds.android.ttpod.fragment.main.list.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1677a {
        void onAddToRequested();

        void onRemoveRequested();

        void onSelectedCountChanged();

        void onSendToRequested();

        void onStopEditRequested();
    }

    void addTo();

    boolean isEditing();

    void remove();

    void selectAll();

    void selectNone();

    int selectedCount();

    void sendTo();

    void startEdit();

    void stopEdit();

    int totalCount();
}
