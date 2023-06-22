package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;
import com.sds.android.ttpod.media.mediastore.old.MediaStoreOld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class OnlineMediaItem implements Serializable {
    @SerializedName(value = "album_name")
    private String mAlbum;
    @SerializedName(value = "singer_id")
    private long mArtistId;
    @SerializedName(value = "pick_count")
    private int mPickCount;
    @SerializedName(value = "song_id")
    private long mSongId;
    @SerializedName(value = "song_url")
    private String mSongUrl;
    @SerializedName(value = "vip")
    private int mVip;
    @SerializedName(value = "song_name")
    private String mTitle = "";
    @SerializedName(value = "singer_name")
    private String mArtist = "";
    @SerializedName(value = "audition_list")
    private ArrayList<Url> mAuditionUrls = new ArrayList<>();
    @SerializedName(value = "url_list")
    private ArrayList<Url> mDownloadUrls = new ArrayList<>();
    @SerializedName(value = "mv_list")
    private ArrayList<Url> mMVUrls = new ArrayList<>();
    @SerializedName(value = "ll_list")
    private ArrayList<Url> mLLUrls = new ArrayList<>();
    @SerializedName(value = "ae")
    private AudioEffect mAudioEffect = new AudioEffect();
    @SerializedName(value = "flag")
    private int mCensorLevel = 0;
    @SerializedName(value = "out_list")
    private ArrayList<OutListItem> mOutList = new ArrayList<>();

    public ArrayList<OutListItem> getOutList() {
        return this.mOutList;
    }

    public int getCensorLevel() {
        return this.mCensorLevel;
    }

    public String getSongUrl() {
        return this.mSongUrl == null ? "" : this.mSongUrl;
    }

    public long getSongId() {
        return this.mSongId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public long getArtistId() {
        return this.mArtistId;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    private void offsetPickCount(int i) {
        this.mPickCount = Math.max(0, this.mPickCount + i);
    }

    public void increasePickCount() {
        offsetPickCount(1);
    }

    public void decreasePickCount() {
        offsetPickCount(-1);
    }

    public int getPickCount() {
        return this.mPickCount;
    }

    public int getVip() {
        return this.mVip;
    }

    public List<Url> getAuditionUrls() {
        return this.mAuditionUrls;
    }

    public List<Url> getDownloadUrls() {
        return this.mDownloadUrls;
    }

    public List<Url> getMVUrls() {
        return this.mMVUrls;
    }

    public List<Url> getLLUrls() {
        return this.mLLUrls;
    }

    public AudioEffect getAudioEffect() {
        return this.mAudioEffect;
    }

    /* loaded from: classes.dex */
    public static class Url implements Serializable {
        @SerializedName(value = "bitrate")
        private int mBitrate;
        @SerializedName(value = "type")
        private int mType;
        @SerializedName(value = MediaStoreOld.MediasColumns.DURATION)
        private String mDuration = "";
        @SerializedName(value = "size")
        private String mSize = "";
        @SerializedName(value = "url")
        private String mUrl = "";
        @SerializedName(value = "format")
        private String mFormat = "";
        @SerializedName(value = "type_description")
        private String mTypeDescription = "";

        public String getDuration() {
            return this.mDuration;
        }

        public String getSize() {
            return this.mSize;
        }

        public double getSizeInByte() {
            return parseFileSize(this.mSize);
        }

        private double parseFileSize(String str) {
            if (str == null || str.length() == 0) {
                return 0.0d;
            }
            String replaceAll = str.replaceAll("\\d+.\\d+", "");
            if (replaceAll.equals(str)) {
                replaceAll = str.replaceAll("\\d+", "");
            }
            String replaceAll2 = str.replaceAll(replaceAll, "");
            if (replaceAll2.length() > 0) {
                if (replaceAll != null && replaceAll.substring(0, 1).equals("M")) {
                    return Double.valueOf(replaceAll2).doubleValue() * 1048576.0d;
                }
                if (replaceAll == null || !replaceAll.substring(0, 1).equals("K")) {
                    return 0.0d;
                }
                return Double.valueOf(replaceAll2).doubleValue() * 1024.0d;
            }
            return 0.0d;
        }

        public String getUrl() {
            return this.mUrl;
        }

        public String getFormat() {
            return this.mFormat;
        }

        public int getBitrate() {
            return this.mBitrate;
        }

        public int getType() {
            return this.mType;
        }

        public String getTypeDescription() {
            return this.mTypeDescription;
        }
    }

    /* loaded from: classes.dex */
    public static class AudioEffect implements Serializable {
        @SerializedName(value = "_id")
        private String mID = "";
        @SerializedName(value = "style")
        private int mStyle = 0;
        @SerializedName(value = "device")
        private String mDevice = "";
        @SerializedName(value = "output")
        private int mOutput = 0;
        @SerializedName(value = User.KEY_NICK_NAME)
        private String mNickName = "";
        @SerializedName(value = User.KEY_AVATAR)
        private String mPic = "";
        @SerializedName(value = "exp")
        private AudioEffectUserExp mExp = new AudioEffectUserExp();
        @SerializedName(value = "pick_count")
        private int mPickCount = 0;
        @SerializedName(value = "user_id")
        private int mUserId = 0;
        @SerializedName(value = "audio_effect")
        private AudioEffectItemData mEffectData = new AudioEffectItemData();

        public short[] getDataEqualizer() {
            return this.mEffectData.getEqualizer();
        }

        public int getDataBass() {
            return this.mEffectData.getBass();
        }

        public int getDataTreble() {
            return this.mEffectData.getTreble();
        }

        public int getDataVirtualizer() {
            return this.mEffectData.getVirtualizer();
        }

        public int getDataReverb() {
            return this.mEffectData.getReverb();
        }

        public float getDataBalance() {
            return this.mEffectData.getBalance();
        }

        public boolean getDataIsLimit() {
            return this.mEffectData.getIsLimit();
        }

        public int getStyle() {
            return this.mStyle;
        }

        public String getDevice() {
            return this.mDevice;
        }

        public String getId() {
            return this.mID;
        }

        public int getOutput() {
            return this.mOutput;
        }

        public String getNickName() {
            return this.mNickName;
        }

        public String getPic() {
            return this.mPic;
        }

        public int getTotal() {
            return this.mExp.getTotal();
        }

        public int getPickCount() {
            return this.mPickCount;
        }

        public int getUserId() {
            return this.mUserId;
        }

        public String getGenre() {
            return "";
        }

        public short[] getEqualizer() {
            return new short[]{0};
        }
    }

    /* loaded from: classes.dex */
    public static class OutListItem implements Serializable {
        @SerializedName(value = "logo")
        private String mLogo;
        @SerializedName(value = "name")
        private String mName;
        @SerializedName(value = "url")
        private String mUrl;

        public String getLogoUrl() {
            return this.mLogo;
        }

        public String getName() {
            return this.mName;
        }

        public String getUrl() {
            return this.mUrl;
        }
    }
}
