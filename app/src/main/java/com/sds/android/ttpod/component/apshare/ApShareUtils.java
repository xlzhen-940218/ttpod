package com.sds.android.ttpod.component.apshare;

import android.os.SystemClock;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.ttpod.adapter.p069a.Velocity;
import com.sds.android.ttpod.fragment.apshare.TransmittableMediaItem;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

/* renamed from: com.sds.android.ttpod.component.apshare.a */
/* loaded from: classes.dex */
public class ApShareUtils {

    /* renamed from: a */
    private static boolean f3705a = false;

    /* renamed from: b */
    private static boolean f3706b = false;

    /* renamed from: c */
    private static String f3707c;

    /* renamed from: a */
    public static String m7121a(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    /* renamed from: a */
    public static String m7116a(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(63);
        if (lastIndexOf > 0) {
            str = str.substring(0, lastIndexOf);
        }
        int lastIndexOf2 = str.lastIndexOf(File.separatorChar);
        return lastIndexOf2 >= 0 ? str.substring(lastIndexOf2 + 1) : str;
    }

    /* renamed from: b */
    public static String m7111b(String str) {
        int lastIndexOf;
        String m7116a = m7116a(str);
        return (m7116a.length() <= 0 || (lastIndexOf = m7116a.lastIndexOf(46)) < 0) ? "" : m7116a.substring(lastIndexOf + 1);
    }

    /* renamed from: a */
    public static String m7115a(String str, String str2) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("([{/\\\\:*?\"<>|}\\u0000-\\u001f\\uD7B0-\\uFFFF]+)", str2);
    }

    /* renamed from: a */
    public static void m7117a(OutputStream outputStream, TransmittableMediaItem transmittableMediaItem) {
        transmittableMediaItem.m5772a();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println(JSONUtils.toJson(new ExchangedItem(transmittableMediaItem)));
        printWriter.flush();
    }

    /* renamed from: a */
    public static TransmittableMediaItem m7118a(BufferedReader bufferedReader) {
        new Properties();
        try {
            return ((ExchangedItem) JSONUtils.fromJson(bufferedReader.readLine(), ExchangedItem.class)).m7097g();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static void m7114a(List<TransmittableMediaItem> list, TransmittableMediaItem transmittableMediaItem, long j) {
        TransmittableMediaItem m7113a = m7113a(list, transmittableMediaItem.m5772a().getID());
        if (m7113a != null) {
            Velocity m5759f = m7113a.m5759f();
            if (m5759f == null) {
                m5759f = new Velocity(SystemClock.uptimeMillis());
                m7113a.m5769a(m5759f);
            }
            m7113a.m5768a(TransmittableMediaItem.EnumC1381a.TRANSMITTING);
            m7113a.m5771a((int) ((100 * j) / m7113a.m5772a().getSize()));
            m5759f.m7626a(j, SystemClock.uptimeMillis());
        }
    }

    /* renamed from: a */
    public static TransmittableMediaItem m7113a(List<TransmittableMediaItem> list, String str) {
        if (list != null) {
            for (TransmittableMediaItem transmittableMediaItem : list) {
                if (transmittableMediaItem.m5772a().getID().equals(str)) {
                    return transmittableMediaItem;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static MediaItem m7119a(MediaItem mediaItem) {
        MediaItem mediaItem2 = new MediaItem(mediaItem.getID(), 0L, mediaItem.getLocalDataSource(), "", mediaItem.getTitle(), mediaItem.getArtist(), mediaItem.getAlbum(), "", "", mediaItem.getMimeType(), 0, mediaItem.getDuration(), 0, 0, 0, 0, 0, 0, "", 0, 0, 0L, 0L, 0L, false, "", "");
        mediaItem2.setSize(mediaItem.getSize());
        return mediaItem2;
    }

    /* renamed from: a */
    public static void m7112a(boolean z) {
        f3705a = z;
    }

    /* renamed from: b */
    public static void m7110b(boolean z) {
        f3706b = z;
    }

    /* renamed from: a */
    public static void m7120a(WifiAPManager wifiAPManager) {
        if (wifiAPManager != null) {
            if (f3705a) {
                wifiAPManager.m7031h();
            } else {
                wifiAPManager.m7032g();
            }
            wifiAPManager.m7044a(f3706b);
        }
    }

    /* renamed from: c */
    public static void m7109c(String str) {
        f3707c = str;
    }

    /* renamed from: a */
    public static String m7122a() {
        return f3707c;
    }
}
