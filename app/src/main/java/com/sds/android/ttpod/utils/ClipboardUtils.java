package com.sds.android.ttpod.utils;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.sds.android.sdk.lib.util.SDKVersionUtils;

/* renamed from: com.sds.android.ttpod.a.d */
/* loaded from: classes.dex */
public final class ClipboardUtils {
    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static void m8306a(Context context, CharSequence charSequence) {
        if (SDKVersionUtils.m8371c()) {
            ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("ttpodImei", charSequence));
        } else {
            ((android.text.ClipboardManager) context.getSystemService("clipboard")).setText(charSequence);
        }
    }
}
