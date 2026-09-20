package com.sds.android.ttpod.framework.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.content.ContextCompat;

public class ReceiverUtils {

    public static final int RECEIVER_EXPORTED = ContextCompat.RECEIVER_EXPORTED;
    public static final int RECEIVER_NOT_EXPORTED = ContextCompat.RECEIVER_NOT_EXPORTED;

    public static Intent registerReceiver(Context context, BroadcastReceiver receiver, IntentFilter filter) {
        if (context == null || receiver == null || filter == null) {
            return null;
        }
        boolean hasSystemAction = false;
        int count = filter.countActions();
        for (int i = 0; i < count; i++) {
            String action = filter.getAction(i);
            if (action != null && (action.startsWith("android.") || action.startsWith("com.android."))) {
                hasSystemAction = true;
                break;
            }
        }
        int flags = hasSystemAction ? RECEIVER_EXPORTED : RECEIVER_NOT_EXPORTED;
        return registerReceiver(context, receiver, filter, flags);
    }

    public static Intent registerReceiver(Context context, BroadcastReceiver receiver, IntentFilter filter, boolean exported) {
        return registerReceiver(context, receiver, filter, exported ? RECEIVER_EXPORTED : RECEIVER_NOT_EXPORTED);
    }

    public static Intent registerReceiver(Context context, BroadcastReceiver receiver, IntentFilter filter, int flags) {
        if (context == null || receiver == null || filter == null) {
            return null;
        }
        try {
            return ContextCompat.registerReceiver(context, receiver, filter, flags);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static void unregisterReceiver(Context context, BroadcastReceiver receiver) {
        if (context == null || receiver == null) {
            return;
        }
        try {
            context.unregisterReceiver(receiver);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
