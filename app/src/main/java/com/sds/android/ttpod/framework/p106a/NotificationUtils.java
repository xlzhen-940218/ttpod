package com.sds.android.ttpod.framework.p106a;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RemoteViews;
import com.sds.android.sdk.lib.util.SDKVersionUtils;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.player.PlayStatus;

/* renamed from: com.sds.android.ttpod.framework.a.k */
/* loaded from: classes.dex */
public class NotificationUtils {

    /* renamed from: a */
    private static long f5678a = 0;

    /* renamed from: b */
    private static final SparseIntArray f5679b = new SparseIntArray();

    static {
        f5679b.put(0, 0);
        f5679b.put(1, 1);
        f5679b.put(-1, -1);
        f5679b.put(2, 2);
        f5679b.put(-2, -2);
    }

    /* renamed from: a */
    public static Notification m4693a(Context context, int i, CharSequence charSequence, CharSequence charSequence2, Bitmap bitmap, PendingIntent pendingIntent) {
        return m4694a(context, i, charSequence, charSequence2, bitmap, 0L, pendingIntent);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0041  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Notification m4694a(Context context, int i, CharSequence charSequence, CharSequence charSequence2, Bitmap bitmap, long j, PendingIntent pendingIntent) {
        Bitmap bitmap2 = null;
        NotificationBuilder notificationBuilder = new NotificationBuilder(context);
        if (bitmap != null) {
            try {
                bitmap2 = BitmapUtils.m4787a(bitmap, DisplayUtils.dp2px(64));
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (bitmap2 == null && i != 0) {
                bitmap2 = BitmapFactory.decodeResource(context.getResources(), i);
            }
            if (!SDKVersionUtils.sdkThan11()) {
                notificationBuilder.m4702a(charSequence);
                notificationBuilder.m4700b(charSequence2);
                notificationBuilder.m4705a(bitmap2);
            } else {
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_default);
                remoteViews.setTextViewText(R.id.textview_title, charSequence);
                remoteViews.setTextColor(R.id.textview_title, NotificationBuilder.m4699c());
                remoteViews.setTextViewText(R.id.textview_sub_title, charSequence2);
                remoteViews.setTextColor(R.id.textview_sub_title, NotificationBuilder.m4699c());
                remoteViews.setImageViewBitmap(R.id.imgview_largeicon, bitmap2);
                notificationBuilder.m4703a(remoteViews);
            }
            notificationBuilder.m4709a(i);
            notificationBuilder.m4707a(pendingIntent);
            notificationBuilder.m4708a(j);
            return notificationBuilder.m4710a();
        }
        bitmap2 = bitmap;
        if (bitmap2 == null) {
            bitmap2 = BitmapFactory.decodeResource(context.getResources(), i);
        }
        if (!SDKVersionUtils.sdkThan11()) {
        }
        notificationBuilder.m4709a(i);
        notificationBuilder.m4707a(pendingIntent);
        notificationBuilder.m4708a(j);
        return notificationBuilder.m4710a();
    }

    /* renamed from: a */
    public static Notification m4692a(Context context, PlayStatus playStatus, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, PendingIntent pendingIntent, PendingIntent pendingIntent2, PendingIntent pendingIntent3, PendingIntent pendingIntent4, PendingIntent pendingIntent5) {
        NotificationBuilder notificationBuilder = new NotificationBuilder(context);
        notificationBuilder.m4709a(R.drawable.img_notification_tickericon);
        notificationBuilder.m4707a(pendingIntent);
        if (f5678a <= 0) {
            f5678a = System.currentTimeMillis();
        }
        notificationBuilder.m4708a(f5678a);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_play);
        remoteViews.setTextViewText(R.id.title, charSequence);
        remoteViews.setTextColor(R.id.title, NotificationBuilder.m4701b());
        remoteViews.setTextViewText(R.id.text, charSequence2);
        remoteViews.setTextColor(R.id.text, NotificationBuilder.m4699c());
        if (bitmap != null) {
            remoteViews.setImageViewBitmap(R.id.imageview_notification_play, bitmap);
        }
        notificationBuilder.m4703a(remoteViews);
        if (SDKVersionUtils.sdkThan14()) {
            remoteViews.setViewVisibility(R.id.control_bar, View.VISIBLE);
            remoteViews.setViewVisibility(R.id.button_previous_notification_play, Preferences.m2988aS() ? View.VISIBLE : View.GONE);
            remoteViews.setViewVisibility(R.id.button_exit_notification_play, Preferences.m2987aT() ? View.VISIBLE : View.GONE);
            if (playStatus == PlayStatus.STATUS_PLAYING) {
                remoteViews.setViewVisibility(R.id.button_play_notification_play, View.GONE);
                remoteViews.setViewVisibility(R.id.button_pause_notification_play, View.VISIBLE);
                remoteViews.setOnClickPendingIntent(R.id.button_pause_notification_play, pendingIntent3);
            } else {
                remoteViews.setViewVisibility(R.id.button_pause_notification_play, View.GONE);
                remoteViews.setViewVisibility(R.id.button_play_notification_play, View.VISIBLE);
                remoteViews.setOnClickPendingIntent(R.id.button_play_notification_play, pendingIntent3);
            }
            remoteViews.setOnClickPendingIntent(R.id.button_previous_notification_play, pendingIntent2);
            remoteViews.setOnClickPendingIntent(R.id.button_next_notification_play, pendingIntent4);
            remoteViews.setOnClickPendingIntent(R.id.button_exit_notification_play, pendingIntent5);
        }
        Notification m4710a = notificationBuilder.m4710a();
        if (SDKVersionUtils.sdkThan16()) {
            m4710a.priority = f5679b.get(Preferences.m2989aR());
            RemoteViews remoteViews2 = new RemoteViews(context.getPackageName(), R.layout.notification_play_hasjellybean);
            if (charSequence != null) {
                remoteViews2.setTextViewText(R.id.title, charSequence);
                remoteViews2.setTextColor(R.id.title, NotificationBuilder.m4701b());
                remoteViews2.setTextViewText(R.id.text, charSequence2);
                remoteViews2.setTextColor(R.id.text, NotificationBuilder.m4699c());
                remoteViews2.setTextViewText(R.id.text2, charSequence3);
                remoteViews2.setTextColor(R.id.text2, NotificationBuilder.m4699c());
            }
            if (bitmap != null) {
                remoteViews2.setImageViewBitmap(R.id.imageview_notification_play_hasjellybean, bitmap);
            }
            m4710a.bigContentView = remoteViews2;
            if (playStatus == PlayStatus.STATUS_PLAYING) {
                remoteViews2.setViewVisibility(R.id.button_play_notification_play_hasjellybean, View.GONE);
                remoteViews2.setViewVisibility(R.id.button_pause_notification_play_hasjellybean, View.VISIBLE);
                remoteViews2.setOnClickPendingIntent(R.id.button_pause_notification_play_hasjellybean, pendingIntent3);
            } else {
                remoteViews2.setViewVisibility(R.id.button_pause_notification_play_hasjellybean, View.GONE);
                remoteViews2.setViewVisibility(R.id.button_play_notification_play_hasjellybean, View.VISIBLE);
                remoteViews2.setOnClickPendingIntent(R.id.button_play_notification_play_hasjellybean, pendingIntent3);
            }
            remoteViews2.setOnClickPendingIntent(R.id.button_previous_notification_play_hasjellybean, pendingIntent2);
            remoteViews2.setOnClickPendingIntent(R.id.button_next_notification_play_hasjellybean, pendingIntent4);
            remoteViews2.setOnClickPendingIntent(R.id.button_exit_notification_play_hasjellybean, pendingIntent5);
            remoteViews2.setViewVisibility(R.id.button_exit_notification_play_hasjellybean, Preferences.m2988aS() ? View.VISIBLE : View.GONE);
            remoteViews2.setViewVisibility(R.id.button_exit_notification_play_hasjellybean, Preferences.m2987aT() ? View.VISIBLE : View.GONE);
        }
        return m4710a;
    }

    /* renamed from: a */
    public static void m4695a(int i, Notification notification) {
        ((NotificationManager) BaseApplication.getApplication().getSystemService(Context.NOTIFICATION_SERVICE)).notify(i, notification);
    }

    /* renamed from: a */
    public static void m4696a(int i) {
        try {
            ((NotificationManager) BaseApplication.getApplication().getSystemService(Context.NOTIFICATION_SERVICE)).cancel(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m4697a() {
        m4696a(15121750);
        m4696a(15121730);
        m4696a(15121740);
    }
}
