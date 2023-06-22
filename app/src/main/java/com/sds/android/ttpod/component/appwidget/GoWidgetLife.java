package com.sds.android.ttpod.component.appwidget;

import android.os.Bundle;

/* loaded from: classes.dex */
public interface GoWidgetLife {
    boolean onApplyTheme(Bundle bundle);

    void onDelete(int i);

    void onPause(int i);

    void onRemove(int i);

    void onResume(int i);

    void onStart(Bundle bundle);
}
