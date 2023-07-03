package com.sds.android.ttpod.framework.support.search.p135a;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.framework.support.search.SearchTaskType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.io.File;
import java.util.List;

/* renamed from: com.sds.android.ttpod.framework.support.search.a.a */
/* loaded from: classes.dex */
public abstract class LyrPicSearchTaskBaseInfo {

    /* renamed from: c */
    private String title;

    /* renamed from: d */
    private String singer;

    /* renamed from: e */
    private boolean f7276e;

    /* renamed from: l */
    private boolean f7283l;

    /* renamed from: m */
    private boolean f7284m;

    /* renamed from: a */
    private List f7272a = null;

    /* renamed from: b */
    private Object f7273b = null;

    /* renamed from: f */
    private boolean auto = false;

    /* renamed from: g */
    private String f7278g = "";

    /* renamed from: h */
    private String f7279h = "";

    /* renamed from: i */
    private int f7280i = EnvironmentUtils.DeviceConfig.getNetworkType();

    /* renamed from: k */
    private MediaItem mediaItem = null;

    /* renamed from: j */
    private String[] songInfo = null;

    /* renamed from: d */
    public abstract SearchTaskType getSearchTaskType();

    /* renamed from: a */
    public boolean m2211a() {
        return this.f7284m;
    }

    /* renamed from: a */
    public void m2207a(boolean z) {
        this.f7284m = z;
    }

    /* renamed from: b */
    public boolean m2206b() {
        return this.f7276e;
    }

    /* renamed from: b */
    public void m2204b(boolean z) {
        this.f7276e = z;
    }

    /* renamed from: c */
    public boolean m2203c() {
        return this.f7283l;
    }

    /* renamed from: c */
    public void m2201c(boolean z) {
        this.f7283l = z;
    }

    /* renamed from: e */
    public boolean isAuto() {
        return this.auto;
    }

    /* renamed from: d */
    public void setAuto(boolean z) {
        this.auto = z;
    }

    /* renamed from: f */
    public String m2197f() {
        return this.f7278g;
    }

    /* renamed from: a */
    public void m2208a(String str) {
        this.f7278g = str;
    }

    /* renamed from: g */
    public String m2196g() {
        return this.f7279h;
    }

    /* renamed from: b */
    public void m2205b(String str) {
        this.f7279h = str;
    }

    /* renamed from: h */
    public String[] getSongInfo() {
        if (this.songInfo == null) {
            this.songInfo = buildSongInfo();
        }
        return this.songInfo;
    }

    /* renamed from: i */
    public MediaItem getMediaItem() {
        return this.mediaItem;
    }

    /* renamed from: a */
    public void setMediaItem(MediaItem mediaItem) {
        this.mediaItem = mediaItem;
    }

    /* renamed from: j */
    public String getTitle() {
        return this.title;
    }

    /* renamed from: c */
    public void setTitle(String title) {
        this.title = title;
    }

    /* renamed from: k */
    public String getSinger() {
        return this.singer;
    }

    /* renamed from: d */
    public void setSinger(String singer) {
        this.singer = singer;
    }

    /* renamed from: a */
    public void m2209a(Object obj) {
        this.f7273b = obj;
    }

    /* renamed from: l */
    private String[] buildSongInfo() {
        String localDataSource;
        String dirPath;
        String songSuffix;
        String str3;
        String str4 = null;
        if (this.mediaItem == null || this.mediaItem.getLocalDataSource() == null) {
            return null;
        }
        int lastIndexOf = this.mediaItem.getLocalDataSource().lastIndexOf(124);
        if (lastIndexOf > 0) {
            int i = lastIndexOf + 1;
            localDataSource = this.mediaItem.getLocalDataSource().substring(0, lastIndexOf);
            str4 = this.mediaItem.getLocalDataSource().substring(i);
        } else {
            localDataSource = this.mediaItem.getLocalDataSource();
        }
        String songSingerName = "";
        if (localDataSource == null) {
            dirPath = localDataSource;
            songSuffix = "";
        } else {
            int lastIndexOf2 = localDataSource.lastIndexOf(File.separatorChar) + 1;
            if (lastIndexOf2 > 0) {
                String substring = localDataSource.substring(lastIndexOf2);
                str3 = localDataSource.substring(0, lastIndexOf2);
                localDataSource = substring;
            } else {
                str3 = localDataSource;
            }
            int lastIndexOf3 = localDataSource.lastIndexOf(46);
            if (lastIndexOf3 <= 0) {
                dirPath = str3;
                songSingerName = localDataSource;
                songSuffix = "";
            } else {
                String substring2 = localDataSource.substring(lastIndexOf3 + 1);
                dirPath = str3;
                songSingerName = localDataSource.substring(0, lastIndexOf3);
                songSuffix = substring2;
            }
        }
        return new String[]{dirPath, songSingerName, songSuffix, str4};
    }
}
