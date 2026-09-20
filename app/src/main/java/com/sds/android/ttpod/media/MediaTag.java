package com.sds.android.ttpod.media;

import android.media.MediaMetadataRetriever;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.media.text.TTTextUtils;

import java.io.File;

/* loaded from: classes.dex */
public class MediaTag {
    private String mFilePath;
    private String mFileName;
    private String mTitle;
    private String mArtist;
    private String mAlbum;
    private String mGenre;
    private String mComment;
    private int mYear = 0;
    private int mTrack = 0;
    private int mDuration = 0;
    private int mBitRate = 0;
    private int mSampleRate = 44100;
    private int mChannels = 2;
    private byte[] mCover = null;
    private MediaMetadataRetriever mRetriever;

    public static void initGBKMap(byte[] bArr) {
        // Native GBK mapping is obsolete; Java natively supports standard charsets.
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
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        this.mFilePath = str;
        this.mFileName = FileUtils.getFilename(str);
        try {
            this.mRetriever = new MediaMetadataRetriever();
            this.mRetriever.setDataSource(str);

            this.mTitle = this.mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            this.mArtist = this.mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            if (StringUtils.isEmpty(this.mArtist)) {
                this.mArtist = this.mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST);
            }
            this.mAlbum = this.mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            this.mGenre = this.mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);

            String durationStr = this.mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            if (!StringUtils.isEmpty(durationStr)) {
                try {
                    this.mDuration = Integer.parseInt(durationStr);
                } catch (NumberFormatException ignored) {
                }
            }

            String bitrateStr = this.mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE);
            if (!StringUtils.isEmpty(bitrateStr)) {
                try {
                    this.mBitRate = Integer.parseInt(bitrateStr);
                } catch (NumberFormatException ignored) {
                }
            }

            String yearStr = this.mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR);
            if (StringUtils.isEmpty(yearStr)) {
                yearStr = this.mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE);
            }
            if (!StringUtils.isEmpty(yearStr)) {
                try {
                    if (yearStr.length() > 4) {
                        yearStr = yearStr.substring(0, 4);
                    }
                    this.mYear = Integer.parseInt(yearStr);
                } catch (NumberFormatException ignored) {
                }
            }

            String trackStr = this.mRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER);
            if (!StringUtils.isEmpty(trackStr)) {
                try {
                    int slashIdx = trackStr.indexOf('/');
                    if (slashIdx > 0) {
                        trackStr = trackStr.substring(0, slashIdx);
                    }
                    this.mTrack = Integer.parseInt(trackStr.trim());
                } catch (NumberFormatException ignored) {
                }
            }

            this.mCover = this.mRetriever.getEmbeddedPicture();
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true; // Still allow using file name even if metadata extraction fails
        }
    }

    public void close() {
        if (this.mRetriever != null) {
            try {
                this.mRetriever.release();
            } catch (Throwable ignored) {
            }
            this.mRetriever = null;
        }
    }

    public void save() {
        // Saving tags to files is safely ignored on modern Android scoped storage
    }

    public String title() {
        return this.mTitle != null ? this.mTitle : "";
    }

    public String artist() {
        return this.mArtist != null ? this.mArtist : "";
    }

    public String album() {
        return this.mAlbum != null ? this.mAlbum : "";
    }

    public String genre() {
        return this.mGenre != null ? this.mGenre : "";
    }

    public String comment() {
        return this.mComment != null ? this.mComment : "";
    }

    public int year() {
        return this.mYear;
    }

    public int track() {
        return this.mTrack;
    }

    public int duration() {
        return this.mDuration;
    }

    public int bitRate() {
        return this.mBitRate;
    }

    public int sampleRate() {
        return this.mSampleRate;
    }

    public int channels() {
        return this.mChannels;
    }

    public byte[] cover() {
        return this.mCover;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    public void setAlbum(String str) {
        this.mAlbum = str;
    }

    public void setGenre(String str) {
        this.mGenre = str;
    }

    public void setComment(String str) {
        this.mComment = str;
    }

    public void setYear(int i) {
        this.mYear = i;
    }

    public void setTrack(int i) {
        this.mTrack = i;
    }

    private String getTitleOrFileName() {
        String title = title();
        if (StringUtils.isEmpty(title)) {
            return FileUtils.getFilenameWithoutExtension(this.mFileName);
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
