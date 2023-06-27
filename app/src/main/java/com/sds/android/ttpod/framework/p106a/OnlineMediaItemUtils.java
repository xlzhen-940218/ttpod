package com.sds.android.ttpod.framework.p106a;

import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.util.DebugUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.media.mediastore.AudioQuality;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.sds.android.ttpod.framework.a.l */
/* loaded from: classes.dex */
public class OnlineMediaItemUtils {
    /* renamed from: a */
    public static String m4690a(OnlineMediaItem onlineMediaItem) {
        return m4684a(m4686a(onlineMediaItem, m4683b(onlineMediaItem)));
    }

    /* renamed from: a */
    private static String m4684a(Set<String> set) {
        StringBuilder append = new StringBuilder(Preferences.m3054N()).append(File.separator);
        int length = append.length();
        for (String str : set) {
            append.append(str);
            if (FileUtils.m8414b(append.toString())) {
                return append.toString();
            }
            append.setLength(length);
        }
        return null;
    }

    /* renamed from: a */
    private static Set<String> m4686a(OnlineMediaItem onlineMediaItem, Set<String> set) {
        String title = onlineMediaItem.getTitle();
        String artist = onlineMediaItem.getArtist();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (String str : set) {
            linkedHashSet.add(artist + "-" + title + "." + str);
            linkedHashSet.add(artist + " - " + title + "." + str);
            linkedHashSet.add(title + "-" + artist + "." + str);
            linkedHashSet.add(title + " - " + artist + "." + str);
        }
        return linkedHashSet;
    }

    /* renamed from: b */
    private static Set<String> m4683b(OnlineMediaItem onlineMediaItem) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (OnlineMediaItem.Url url : onlineMediaItem.getDownloadUrls()) {
            linkedHashSet.add(url.getFormat());
        }
        for (OnlineMediaItem.Url url2 : onlineMediaItem.getLLUrls()) {
            linkedHashSet.add(url2.getFormat());
        }
        return linkedHashSet;
    }

    /* renamed from: a */
    public static String m4688a(OnlineMediaItem onlineMediaItem, OnlineMediaItem.Url url) {
        DebugUtils.m8426a(onlineMediaItem, "onlineMediaItem");
        DebugUtils.m8426a(url, "url");
        return Preferences.m3054N() + File.separator + FileUtils.removeWrongCharacter(onlineMediaItem.getArtist() + " - " + onlineMediaItem.getTitle() + "." + url.getFormat());
    }

    /* renamed from: a */
    public static OnlineMediaItem.Url m4689a(OnlineMediaItem onlineMediaItem, int i) {
        return m4687a(onlineMediaItem, m4691a(i));
    }

    /* renamed from: a */
    public static OnlineMediaItem.Url m4687a(OnlineMediaItem onlineMediaItem, AudioQuality audioQuality) {
        if (onlineMediaItem == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(onlineMediaItem.getAuditionUrls());
        if (arrayList.size() == 0) {
            arrayList.addAll(onlineMediaItem.getDownloadUrls());
        }
        OnlineMediaItem.Url m4685a = m4685a(arrayList, audioQuality);
        if (audioQuality.ordinal() == 0) {
            audioQuality = AudioQuality.values()[AudioQuality.values().length - 1];
        }
        OnlineMediaItem.Url url = m4685a;
        int ordinal = audioQuality.ordinal() - 1;
        while (ordinal >= 0 && url == null) {
            OnlineMediaItem.Url m4681b = m4681b(arrayList, AudioQuality.values()[ordinal]);
            ordinal--;
            url = m4681b;
        }
        return url;
    }

    /* renamed from: a */
    private static AudioQuality m4691a(int i) {
        if (i == 0) {
            return Preferences.m3064I();
        }
        if (3 == i) {
            return Preferences.m3062J();
        }
        if (2 == i) {
            return Preferences.m3060K();
        }
        return AudioQuality.STANDARD;
    }

    /* renamed from: b */
    public static OnlineMediaItem.Url m4682b(OnlineMediaItem onlineMediaItem, AudioQuality audioQuality) {
        OnlineMediaItem.Url url = null;
        if (onlineMediaItem != null) {
            ArrayList arrayList = new ArrayList(onlineMediaItem.getDownloadUrls());
            if (AudioQuality.LOSSLESS == audioQuality) {
                arrayList.addAll(onlineMediaItem.getLLUrls());
            }
            int ordinal = audioQuality.ordinal();
            while (ordinal >= 0 && url == null) {
                OnlineMediaItem.Url m4681b = m4681b(arrayList, AudioQuality.values()[ordinal]);
                ordinal--;
                url = m4681b;
            }
        }
        return url;
    }

    /* renamed from: a */
    private static OnlineMediaItem.Url m4685a(List<OnlineMediaItem.Url> list, AudioQuality audioQuality) {
        DebugUtils.m8426a((Object) list, "Urls of OnlineMediaItem");
        int[] bitrateRange = AudioQuality.bitrateRange(audioQuality);
        for (OnlineMediaItem.Url url : list) {
            if (url.getBitrate() >= bitrateRange[0] && url.getBitrate() <= bitrateRange[1]) {
                return url;
            }
        }
        return null;
    }

    /* renamed from: b */
    private static OnlineMediaItem.Url m4681b(List<OnlineMediaItem.Url> list, AudioQuality audioQuality) {
        DebugUtils.m8426a((Object) list, "Urls of OnlineMediaItem");
        int[] bitrateRange = AudioQuality.bitrateRange(audioQuality);
        for (int size = list.size() - 1; size >= 0; size--) {
            OnlineMediaItem.Url url = list.get(size);
            if (url.getBitrate() >= bitrateRange[0] && url.getBitrate() <= bitrateRange[1]) {
                return url;
            }
        }
        return null;
    }

    /* renamed from: c */
    public static Integer m4680c(OnlineMediaItem onlineMediaItem, AudioQuality audioQuality) {
        OnlineMediaItem.Url m4682b = m4682b(onlineMediaItem, audioQuality);
        return Integer.valueOf(m4682b == null ? 0 : (int) m4682b.getSizeInByte());
    }
}
