package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AlbumData implements BaseId, Serializable {
    private static final String KEY_ALIAS = "alias";
    private static final String KEY_COMPANY_ID = "companyId";
    private static final String KEY_COMPANY_NAME = "companyName";
    private static final String KEY_COVER_ID = "coverId";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_GENRES = "genres";
    private static final String KEY_ID = "albumId";
    private static final String KEY_LANG = "lang";
    private static final String KEY_NAME = "name";
    private static final String KEY_PICURL = "picUrl";
    private static final String KEY_PUBLISHER = "publisher";
    private static final String KEY_PUBLISH_YEAR = "publishYear";
    private static final String KEY_SINGER_ID = "singerId";
    private static final String KEY_SINGER_NAME = "singerName";
    private static final String KEY_SINGER_PICURL = "singerPicUrl";
    private static final String KEY_SONGS = "songs";
    private static final String KEY_STYLES = "styles";
    private static final String KEY_TAGS = "tags";
    private static final String KEY_TITLE_SONGS = "titleSongs";
    private static final String KEY_TYPE = "type";
    private static final String KEY_TYPE_NAME = "typeName";
    @SerializedName(value = "alias")
    private String mAlias;
    @SerializedName(value = KEY_COMPANY_ID)
    private long mCompanyId;
    @SerializedName(value = KEY_COMPANY_NAME)
    private String mCompanyName;
    @SerializedName(value = KEY_COVER_ID)
    private long mCoverId;
    @SerializedName(value = "description")
    private String mDescription;
    @SerializedName(value = "genres")
    private TagData mGenres;
    @SerializedName(value = KEY_ID)
    private long mId;
    @SerializedName(value = KEY_LANG)
    private String mLang;
    @SerializedName(value = "name")
    private String mName;
    @SerializedName(value = KEY_PICURL)
    private String mPicUrl;
    @SerializedName(value = KEY_PUBLISH_YEAR)
    private String mPublishYear;
    @SerializedName(value = KEY_PUBLISHER)
    private String mPublisher;
    @SerializedName(value = KEY_SINGER_ID)
    private long mSingerId;
    @SerializedName(value = KEY_SINGER_NAME)
    private String mSingerName;
    @SerializedName(value = KEY_SINGER_PICURL)
    private String mSingerPicUrl;
    @SerializedName(value = KEY_SONGS)
    private List<Long> mSongIds;
    @SerializedName(value = KEY_STYLES)
    private TagData mStyle;
    @SerializedName(value = KEY_TAGS)
    private List<AlbumTag> mTags;
    @SerializedName(value = KEY_TITLE_SONGS)
    private List<Long> mTitleSongs;
    @SerializedName(value = "type")
    private int mType;
    @SerializedName(value = KEY_TYPE_NAME)
    private String mTypeName;

    @Override // com.sds.android.cloudapi.ttpod.data.BaseId
    public long getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getAlias() {
        return this.mAlias;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public int getType() {
        return this.mType;
    }

    public String getTypeName() {
        return this.mTypeName;
    }

    public long getCoverId() {
        return this.mCoverId;
    }

    public long getSingerId() {
        return this.mSingerId;
    }

    public String getSingerName() {
        return this.mSingerName;
    }

    public String getPublishYear() {
        return this.mPublishYear;
    }

    public String getPublisher() {
        return this.mPublisher;
    }

    public long getCompanyId() {
        return this.mCompanyId;
    }

    public List<Long> getTitleSongs() {
        if (this.mTitleSongs == null) {
            this.mTitleSongs = new ArrayList();
        }
        return this.mTitleSongs;
    }

    public List<Long> getSongIds() {
        if (this.mSongIds == null) {
            this.mSongIds = new ArrayList();
        }
        return this.mSongIds;
    }

    public List<AlbumTag> getTags() {
        if (this.mTags == null) {
            this.mTags = new ArrayList();
        }
        return this.mTags;
    }

    public String getLang() {
        return this.mLang;
    }

    public String getPicUrl() {
        return this.mPicUrl;
    }

    public String getSingerPicUrl() {
        return this.mSingerPicUrl;
    }

    public TagData getStyle() {
        if (this.mStyle == null) {
            this.mStyle = new TagData();
        }
        return this.mStyle;
    }

    public TagData getGenres() {
        if (this.mGenres == null) {
            this.mGenres = new TagData();
        }
        return this.mGenres;
    }

    public String getCompanyName() {
        return this.mCompanyName;
    }
}
