package com.sds.android.ttpod.framework.p106a;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.SDKVersionUtils;

@SuppressLint({"NewApi"})
/* renamed from: com.sds.android.ttpod.framework.a.j */
/* loaded from: classes.dex */
public class NotificationBuilder {

    /* renamed from: a */
    private static boolean f5651a = false;

    /* renamed from: b */
    private static int f5652b;

    /* renamed from: c */
    private static int f5653c;

    /* renamed from: A */
    private final Object f5654A;

    /* renamed from: d */
    private Context f5655d;

    /* renamed from: f */
    private int f5657f;

    /* renamed from: g */
    private int f5658g;

    /* renamed from: h */
    private int f5659h;

    /* renamed from: i */
    private int f5660i;

    /* renamed from: j */
    private int f5661j;

    /* renamed from: k */
    private CharSequence f5662k;

    /* renamed from: l */
    private CharSequence f5663l;

    /* renamed from: m */
    private PendingIntent f5664m;

    /* renamed from: n */
    private RemoteViews f5665n;

    /* renamed from: o */
    private PendingIntent f5666o;

    /* renamed from: p */
    private PendingIntent f5667p;

    /* renamed from: q */
    private CharSequence f5668q;

    /* renamed from: r */
    private RemoteViews f5669r;

    /* renamed from: s */
    private Uri f5670s;

    /* renamed from: u */
    private long[] f5672u;

    /* renamed from: v */
    private int f5673v;

    /* renamed from: w */
    private int f5674w;

    /* renamed from: x */
    private int f5675x;

    /* renamed from: y */
    private int f5676y;

    /* renamed from: z */
    private int f5677z;

    /* renamed from: e */
    private long f5656e = System.currentTimeMillis();

    /* renamed from: t */
    private int f5671t = -1;

    public NotificationBuilder(Context context) {
        this.f5655d = context;
        if (SDKVersionUtils.sdkThan11()) {
            this.f5654A = new Notification.Builder(context);
        } else {
            this.f5654A = null;
        }
        m4706a(context);
    }

    /* renamed from: a */
    public NotificationBuilder m4708a(long j) {
        this.f5656e = j;
        if (this.f5654A != null) {
            ((Notification.Builder) this.f5654A).setWhen(j);
        }
        return this;
    }

    /* renamed from: a */
    public NotificationBuilder m4709a(int i) {
        this.f5657f = i;
        return this;
    }

    /* renamed from: a */
    public NotificationBuilder m4702a(CharSequence charSequence) {
        this.f5662k = charSequence;
        if (this.f5654A != null) {
            ((Notification.Builder) this.f5654A).setContentTitle(charSequence);
        }
        return this;
    }

    /* renamed from: b */
    public NotificationBuilder m4700b(CharSequence charSequence) {
        this.f5663l = charSequence;
        if (this.f5654A != null) {
            ((Notification.Builder) this.f5654A).setContentText(charSequence);
        }
        return this;
    }

    /* renamed from: a */
    public NotificationBuilder m4703a(RemoteViews remoteViews) {
        this.f5665n = remoteViews;
        if (this.f5654A != null) {
            ((Notification.Builder) this.f5654A).setContent(remoteViews);
        }
        return this;
    }

    /* renamed from: a */
    public NotificationBuilder m4707a(PendingIntent pendingIntent) {
        this.f5664m = pendingIntent;
        if (this.f5654A != null) {
            ((Notification.Builder) this.f5654A).setContentIntent(pendingIntent);
        }
        return this;
    }

    /* renamed from: a */
    public NotificationBuilder m4705a(Bitmap bitmap) {
        if (this.f5654A != null) {
            ((Notification.Builder) this.f5654A).setLargeIcon(bitmap);
        }
        return this;
    }

    /* renamed from: a */
    public Notification m4710a() {
        Notification m4698d;
        if (this.f5654A != null) {
            m4698d = ((Notification.Builder) this.f5654A).getNotification();
        } else {
            m4698d = m4698d();
        }
        if (this.f5657f != 0) {
            m4698d.icon = this.f5657f;
            m4698d.iconLevel = this.f5658g;
        }
        return m4698d;
    }

    /* renamed from: d */
    private Notification m4698d() {
        Notification notification = new Notification();
        //notification.setLatestEventInfo(this.f5655d, this.f5662k, this.f5663l, this.f5664m);
        notification.when = this.f5656e;
        notification.number = this.f5661j;
        notification.icon = this.f5659h;
        notification.iconLevel = this.f5660i;
        notification.tickerText = this.f5668q;
        if (this.f5665n != null) {
            notification.contentView = this.f5665n;
        }
        if (this.f5669r != null) {
            notification.tickerView = this.f5669r;
        }
        notification.deleteIntent = this.f5666o;
        if (SDKVersionUtils.sdkThan9()) {
            notification.fullScreenIntent = this.f5667p;
        }
        notification.sound = this.f5670s;
        notification.audioStreamType = this.f5671t;
        notification.vibrate = this.f5672u;
        notification.ledARGB = this.f5673v;
        notification.ledOnMS = this.f5674w;
        notification.ledOffMS = this.f5675x;
        notification.defaults = this.f5676y;
        notification.flags = this.f5677z;
        if (this.f5674w != 0 && this.f5675x != 0) {
            notification.flags |= 1;
        }
        if ((this.f5676y & 4) != 0) {
            notification.flags |= 1;
        }
        return notification;
    }

    /* renamed from: a */
    private static void m4704a(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (viewGroup.getChildAt(i) instanceof TextView) {
                TextView textView = (TextView) viewGroup.getChildAt(i);
                String obj = textView.getText().toString();
                if ("{notification_title_test_tag}".equals(obj)) {
                    f5652b = textView.getTextColors().getDefaultColor();
                } else if ("{notification_text_test_tag}".equals(obj)) {
                    f5653c = textView.getTextColors().getDefaultColor();
                }
            } else if (viewGroup.getChildAt(i) instanceof ViewGroup) {
                m4704a((ViewGroup) viewGroup.getChildAt(i));
            }
        }
    }

    /* renamed from: a */
    private static void m4706a(Context context) {
        if (!f5651a) {
            try {
                Notification notification = new Notification();
                //notification.setLatestEventInfo(context, "{notification_title_test_tag}", "{notification_text_test_tag}", null);
                LinearLayout linearLayout = new LinearLayout(context);
                m4704a((ViewGroup) notification.contentView.apply(context, linearLayout));
                linearLayout.removeAllViews();
                f5651a = true;
            } catch (Exception e) {
                f5651a = false;
            }
        }
    }

    /* renamed from: b */
    public static int m4701b() {
        return f5652b;
    }

    /* renamed from: c */
    public static int m4699c() {
        return f5653c;
    }
}
