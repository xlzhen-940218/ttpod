package com.sds.android.ttpod.media;

import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.media.text.TTTextUtils;

/* loaded from: classes.dex */
public class MediaTag {
    private String mFileName;
    private int mNativeContext;

    private native String album();

    private native String artist();

    private native String comment();

    private native String genre();

    public static native void initGBKMap(byte[] bArr);

    private native int open(String str);

    private native int readOnlyOpen(String str);

    private native String title();

    public native int bitRate();

    public native int channels();

    public native void close();

    public native byte[] cover();

    public native int duration();

    public native int sampleRate();

    public native void save();

    public native void setAlbum(String str);

    public native void setArtist(String str);

    public native void setComment(String str);

    public native void setGenre(String str);

    public native void setTitle(String str);

    public native void setTrack(int i);

    public native void setYear(int i);

    public native int track();

    public native int year();

    static {
        try {
            System.loadLibrary("mediatag");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    public static MediaTag createMediaTag(String str, boolean z) {
        MediaTag mediaTag = new MediaTag();
        if (!mediaTag.openFile(str, z)) {
            mediaTag.close();
            return null;
        }
        return mediaTag;
    }

    public boolean openFile(String str, boolean z) {
        if ((z ? readOnlyOpen(str) : open(str)) == 0) {
            this.mFileName = FileUtils.getFilename(str);
            return true;
        }
        return false;
    }

    private String getTitleOrFileName() {
        String title = title();
        if (StringUtils.isEmpty(title)) {
            return FileUtils.m8401k(this.mFileName);
        }
        return title;
    }

    public String getTitle() {
        return TTTextUtils.validateVisualString(getTitleOrFileName());
    }

    public String getArtist() {
        return TTTextUtils.validateVisualString(artist());
    }

    public String getAlbum() {
        return TTTextUtils.validateVisualString(album());
    }

    public String getGenre() {
        return TTTextUtils.validateVisualString(genre());
    }

    public String getComment() {
        return TTTextUtils.validateVisualString(comment());
    }
}
