package com.sds.android.cloudapi.ttpod.result;



import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class SingerBriefResult extends SingerBaseResult {
    @SerializedName(value = "data")
    private SingerBriefData mData;

    /* loaded from: classes.dex */
    public static class SingerBriefData implements Serializable {
        @SerializedName(value = "achievements")
        private String mAchievements;
        @SerializedName(value = "activeYear")
        private int mActiveYear;
        @SerializedName(value = "alias")
        private List<String> mAlias;
        @SerializedName(value = "area")
        private String mArea;
        @SerializedName(value = "audit")
        private int mAudit;
        @SerializedName(value = "avatarId")
        private long mAvatarId;
        @SerializedName(value = "birthDate")
        private String mBirthDate;
        @SerializedName(value = "birthPlace")
        private String mBirthPlace;
        @SerializedName(value = "birthYear")
        private int mBirthYear;
        @SerializedName(value = "bloodType")
        private String mBloodType;
        @SerializedName(value = "brief")
        private String mBrief;
        @SerializedName(value = "career")
        private String mCareer;
        @SerializedName(value = "chineseName")
        private String mChineseName;
        @SerializedName(value = "companyId")
        private long mCompanyId;
        @SerializedName(value = "companyName")
        private String mCompanyName;
        @SerializedName(value = "country")
        private String mCountry;
        @SerializedName(value = "deathYear")
        private int mDeathYear;
        @SerializedName(value = "englishName")
        private String mEnglishName;
        @SerializedName(value = "enter")
        private long mEnter;
        @SerializedName(value = "gender")
        private int mGender;
        @SerializedName(value = "genres")
        private List<SingerTag> mGenres;
        @SerializedName(value = "group")
        private String mGroup;
        @SerializedName(value = "height")
        private String mHeight;
        @SerializedName(value = "horoscope")
        private int mHoroScope;
        @SerializedName(value = "id")
        private long mId;
        @SerializedName(value = "identifier")
        private String mIdentifier;
        @SerializedName(value = "lang")
        private String mLang;
        @SerializedName(value = "name")
        private String mName;
        @SerializedName(value = "nation")
        private String mNation;
        @SerializedName(value = "picUrl")
        private String mPicUrl;
        @SerializedName(value = "school")
        private String mSchool;
        @SerializedName(value = "status")
        private int mStatus;
        @SerializedName(value = "styles")
        private List<SingerTag> mStyles;
        @SerializedName(value = "tags")
        private List<SingerTag> mTags;
        @SerializedName(value = "type")
        private int mType;
        @SerializedName(value = "weight")
        private String mWeight;

        public long getId() {
            return this.mId;
        }

        public String getName() {
            return this.mName;
        }

        public int getType() {
            return this.mType;
        }

        public String getIdentifier() {
            return this.mIdentifier;
        }

        public int getStatus() {
            return this.mStatus;
        }

        public int getAudit() {
            return this.mAudit;
        }

        public int getActiveYear() {
            return this.mActiveYear;
        }

        public String getBrief() {
            return this.mBrief;
        }

        public String getGroup() {
            return this.mGroup;
        }

        public String getArea() {
            return this.mArea;
        }

        public int getGender() {
            return this.mGender;
        }

        public String getLang() {
            return this.mLang;
        }

        public long getAvatarId() {
            return this.mAvatarId;
        }

        public String getCountry() {
            return this.mCountry;
        }

        public List<SingerTag> getTags() {
            if (this.mTags == null) {
                this.mTags = new ArrayList();
            }
            return this.mTags;
        }

        public String getChineseName() {
            return this.mChineseName;
        }

        public String getEnglishName() {
            return this.mEnglishName;
        }

        public List<String> getAlias() {
            if (this.mAlias == null) {
                this.mAlias = new ArrayList();
            }
            return this.mAlias;
        }

        public String getNation() {
            return this.mNation;
        }

        public String getBirthPlace() {
            return this.mBirthPlace;
        }

        public String getCareer() {
            return this.mCareer;
        }

        public String getSchool() {
            return this.mSchool;
        }

        public long getCompanyId() {
            return this.mCompanyId;
        }

        public String getAchievements() {
            return this.mAchievements;
        }

        public String getBloodType() {
            return this.mBloodType;
        }

        public String getHeight() {
            return this.mHeight;
        }

        public String getWeight() {
            return this.mWeight;
        }

        public long getEnter() {
            return this.mEnter;
        }

        public int getBirthYear() {
            return this.mBirthYear;
        }

        public int getDeathYear() {
            return this.mDeathYear;
        }

        public String getBirthDate() {
            return this.mBirthDate;
        }

        public int getHoroScope() {
            return this.mHoroScope;
        }

        public String getPicUrl() {
            return this.mPicUrl;
        }

        public void setPicUrl(String str) {
            this.mPicUrl = str;
        }

        public String getCompanyName() {
            return this.mCompanyName;
        }

        public void setCompanyName(String str) {
            this.mCompanyName = str;
        }

        public List<SingerTag> getStyles() {
            if (this.mStyles == null) {
                this.mStyles = new ArrayList();
            }
            return this.mStyles;
        }

        public List<SingerTag> getGenres() {
            if (this.mGenres == null) {
                this.mGenres = new ArrayList();
            }
            return this.mGenres;
        }
    }

    public SingerBriefData getData() {
        if (this.mData == null) {
            this.mData = new SingerBriefData();
        }
        return this.mData;
    }
}
