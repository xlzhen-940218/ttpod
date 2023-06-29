package com.sds.android.ttpod.framework.modules.skin.lyric;

import android.text.TextUtils;

import java.io.File;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d.i */
/* loaded from: classes.dex */
public final class LyricInfo {

    /* renamed from: a */
    private String title;

    /* renamed from: b */
    private String singer;

    /* renamed from: c */
    private String album;

    /* renamed from: d */
    private String byName;

    /* renamed from: e */
    private String lyricPath;

    /* renamed from: f */
    private long offsetTime;

    /* renamed from: g */
    private long currentTime;

    /* renamed from: h */
    private long totalTime;

    /* renamed from: i */
    private long lyricLastModified;

    public int hashCode() {
        return (((((((((this.lyricPath == null ? 0 : this.lyricPath.hashCode())
                + (((this.byName == null ? 0 : this.byName.hashCode())
                + (((this.singer == null ? 0 : this.singer.hashCode())
                + (((this.album == null ? 0 : this.album.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)
                + ((int) (this.offsetTime ^ (this.offsetTime >>> 1)))) * 31)
                + (this.title != null ? this.title.hashCode() : 0)) * 31)
                + ((int) (this.totalTime ^ (this.totalTime >>> 1)))) * 31)
                + ((int) (this.lyricLastModified ^ (this.lyricLastModified >>> 1)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LyricInfo lyricInfo = (LyricInfo) obj;
        if (TextUtils.equals(this.title, lyricInfo.title) && TextUtils.equals(this.singer, lyricInfo.singer)
                && TextUtils.equals(this.album, lyricInfo.album) && TextUtils.equals(this.byName, lyricInfo.byName)
                && TextUtils.equals(this.lyricPath, lyricInfo.lyricPath) && this.offsetTime == lyricInfo.offsetTime
                && this.lyricLastModified == lyricInfo.lyricLastModified) {
            return this.totalTime == lyricInfo.totalTime;
        }
        return false;
    }

    /* renamed from: a */
    public String getLyricPath() {
        return this.lyricPath;
    }

    /* renamed from: a */
    public void setLyricPath(String lyricPath) {
        this.lyricPath = lyricPath;
        this.lyricLastModified = new File(lyricPath).lastModified();
    }

    /* renamed from: b */
    public long getLyricLastModified() {
        return this.lyricLastModified;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!LyricUtils.isEmpty(this.title)) {
            stringBuilder.append(String.format("[ti:%s]\n", this.title));
        }
        if (!LyricUtils.isEmpty(this.singer)) {
            stringBuilder.append(String.format("[ar:%s]\n", this.singer));
        }
        if (!LyricUtils.isEmpty(this.album)) {
            stringBuilder.append(String.format("[al:%s]\n", this.album));
        }
        if (!LyricUtils.isEmpty(this.byName)) {
            stringBuilder.append(String.format("[by:%s]\n", this.byName));
        }
        if (this.offsetTime != 0) {
            stringBuilder.append(String.format("[offset:%d]\n", Long.valueOf(this.offsetTime)));
        }
        if (this.totalTime != 0) {
            stringBuilder.append(String.format("[total:%d]\n", Long.valueOf(this.totalTime)));
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    public void setTitle(String title) {
        this.title = title;
    }

    /* renamed from: c */
    public void setSinger(String singer) {
        this.singer = singer;
    }

    /* renamed from: d */
    public void setAlbum(String album) {
        this.album = album;
    }

    /* renamed from: e */
    public void setByName(String byName) {
        this.byName = byName;
    }

    /* renamed from: a */
    public void setOffsetTime(long offsetTime) {
        this.offsetTime = offsetTime;
    }

    /* renamed from: b */
    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    /* renamed from: c */
    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    /* renamed from: c */
    public long getOffsetTime() {
        return this.offsetTime;
    }

    /* renamed from: d */
    public long getCurrentTime() {
        return this.currentTime;
    }

    /* renamed from: e */
    public long getTotalTime() {
        return this.totalTime;
    }

    /* renamed from: a */
    public int next(int i) {
        this.currentTime += i;
        return (int) (this.currentTime - this.offsetTime);
    }

    /* renamed from: f */
    public boolean syncToOffset() {
        boolean z = this.offsetTime != this.currentTime;
        if (z) {
            this.offsetTime = this.currentTime;
        }
        return z;
    }

    /* renamed from: g */
    public int syncToCurrent() {
        int i = (int) (this.offsetTime - this.currentTime);
        this.currentTime = this.offsetTime;
        return i;
    }
}
