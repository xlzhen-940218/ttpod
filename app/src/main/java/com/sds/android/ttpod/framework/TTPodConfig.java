package com.sds.android.ttpod.framework;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;

import java.io.File;

/* renamed from: com.sds.android.ttpod.framework.a */
/* loaded from: classes.dex */
public class TTPodConfig {

    /* renamed from: a */
    private static boolean finishSplash = false;

    /* renamed from: b */
    private static String cachePath;

    /* renamed from: c */
    private static String ttpodPath;

    /* renamed from: a */
    public static void initTTPodConfig(boolean z) {
        ttpodPath = EnvironmentUtils.C0605d.getSdcardPath() + File.separator + "ttpod";
        cachePath = ttpodPath + File.separator + "cache";
        if (z) {
            FileUtils.createFolder(getCacheImagePath());
            FileUtils.createFolder(getCacheObjectPath());
            FileUtils.createFolder(getSplashPath());
            FileUtils.createFile(getSplashPath() + File.separator + ".nomedia");
            FileUtils.createFolder(getMyMusicsPath());
            FileUtils.createFolder(getSkinPath());
            FileUtils.createFolder(getCacheMediaPath());
            FileUtils.createFile(getCacheMediaPath() + File.separator + ".nomedia");
            FileUtils.createFolder(getCacheTmpPath());
            FileUtils.createFolder(getLyricPath());
            FileUtils.createFolder(getArtistPath());
            FileUtils.createFolder(getCacheEmbedPath());
            FileUtils.createFolder(getCacheEffectPath());
            FileUtils.createFolder(getEffectPath());
            FileUtils.createFolder(getBkgs());
            FileUtils.createFile(getBkgs() + File.separator + ".nomedia");
            FileUtils.createFolder(getTTPodLog());
        }
    }

    /* renamed from: a */
    public static String getTTPodLog() {
        return EnvironmentUtils.C0605d.getSdcardPath() + File.separator + "TTPOD_LOG";
    }

    /* renamed from: b */
    public static boolean getFinishSplash() {
        return finishSplash;
    }

    /* renamed from: c */
    public static void setFinishSplash() {
        finishSplash = true;
    }

    /* renamed from: d */
    public static String getCachePath() {
        return cachePath;
    }

    /* renamed from: e */
    public static String getEffectPath() {
        return ttpodPath + File.separator + "effect";
    }

    /* renamed from: f */
    public static String getCacheEffectPath() {
        return cachePath + File.separator + ".effect";
    }

    /* renamed from: g */
    public static String getCacheMediaPath() {
        return cachePath + File.separator + "media";
    }

    /* renamed from: h */
    public static String getCacheImagePath() {
        return cachePath + File.separator + "image";
    }

    /* renamed from: i */
    public static String getCacheObjectPath() {
        return cachePath + File.separator + "object";
    }

    /* renamed from: j */
    public static String getCacheTmpPath() {
        return cachePath + File.separator + ".tmp";
    }

    /* renamed from: k */
    public static String getSplashPath() {
        return ttpodPath + File.separator + "splash";
    }

    /* renamed from: l */
    public static String getMyMusicsPath() {
        return ttpodPath + File.separator + "MyMusics";
    }

    /* renamed from: m */
    public static String getBkgs() {
        return ttpodPath + File.separator + "bkgs";
    }

    /* renamed from: n */
    public static String getSkinPath() {
        return ttpodPath + File.separator + "skin";
    }

    /* renamed from: o */
    public static String getBkgsPath() {
        return ttpodPath + File.separator + "bkgs";
    }

    /* renamed from: p */
    public static String m5292p() {
        return "bkgs";
    }

    /* renamed from: q */
    public static String getSongPath() {
        return ttpodPath + File.separator + "song";
    }

    /* renamed from: r */
    public static String getLyricPath() {
        return ttpodPath + File.separator + "lyric";
    }

    /* renamed from: s */
    public static String getArtistPath() {
        return ttpodPath + File.separator + "artist";
    }

    /* renamed from: t */
    public static String getCacheEmbedPath() {
        return cachePath + File.separator + "embed";
    }

    /* renamed from: u */
    public static String getArtPath() {
        return ttpodPath + File.separator + "art";
    }

    /* renamed from: v */
    public static String getEqualizerPath() {
        return ttpodPath + File.separator + "Equalizer";
    }

    /* renamed from: w */
    public static String getAppPath() {
        return ttpodPath + File.separator + "app";
    }

    /* renamed from: x */
    public static String getLandscapePath() {
        return ttpodPath + File.separator + "landscape";
    }

    /* renamed from: y */
    public static String getMvPath() {
        return ttpodPath + File.separator + "mv";
    }

    /* renamed from: z */
    public static String getMvCachePath() {
        return getMvPath() + File.separator + ".cache";
    }

    /* renamed from: A */
    public static String getImagePath() {
        return ttpodPath + File.separator + "image";
    }

    /* renamed from: B */
    public static String getTTPodPath() {
        return ttpodPath;
    }

    /* renamed from: a */
    public static String getSongIdPath(Long l) {
        return Preferences.getCacheMediaFolderPath() + File.separator + l;
    }

    /* renamed from: C */
    public static String getAudioTmp() {
        return Preferences.getCacheMediaFolderPath() + File.separator + "audio.tmp";
    }
}
