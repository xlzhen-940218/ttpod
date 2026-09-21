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
        // In Android 14+ (targetSdk >= 34), internal broadcasts sent across processes or components
        // are dropped if registered as RECEIVER_NOT_EXPORTED without explicit components.
        // Using RECEIVER_EXPORTED ensures both internal app broadcasts and system broadcasts are delivered.
        return registerReceiver(context, receiver, filter, RECEIVER_EXPORTED);
    }

    public static void sendBroadcast(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        if (intent.getPackage() == null && intent.getComponent() == null) {
            intent.setPackage(context.getPackageName());
        }
        try {
            context.sendBroadcast(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
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
