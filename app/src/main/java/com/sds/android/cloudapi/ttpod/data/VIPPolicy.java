package com.sds.android.cloudapi.ttpod.data;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class VIPPolicy {
    private static final String KEY_POLICY = "policy";
    private static final String KEY_PRIZE = "prize";
    private static final String KEY_PRODUCTS = "products";
    private static final String KEY_VALID = "valid";
    @SerializedName(value = KEY_POLICY)
    private ArrayList<Entry> mPolicyEntries;
    @SerializedName(value = KEY_PRIZE)
    private String mPrize;
    @SerializedName(value = KEY_PRODUCTS)
    private ArrayList<Product> mProducts;
    @SerializedName(value = KEY_VALID)
    private boolean mValid = false;

    public boolean isValid() {
        return this.mValid;
    }

    public String getPrize() {
        return this.mPrize;
    }

    public ArrayList<Entry> getPolicyEntries() {
        return this.mPolicyEntries;
    }

    public ArrayList<Product> getProducts() {
        return this.mProducts;
    }

    /* loaded from: classes.dex */
    public static class Entry {
        private static final int DEFAULT_MAX_BITRATE = 64;
        private static final String KEY_LEVEL = "level";
        private static final String KEY_MAX_AUDITION_BITRATE = "max_audition_bitrate";
        private static final String KEY_MAX_CACHE_COUNT = "max_cache_count";
        private static final String KEY_MAX_DOWNLOAD_BITRATE = "max_download_bitrate";
        private static final String KEY_MAX_DOWNLOAD_COUNT = "max_download_count";
        public static final int MAX_LIMIT = Integer.MAX_VALUE;
        @SerializedName(value = KEY_LEVEL)
        private int mLevel = 0;
        @SerializedName(value = KEY_MAX_AUDITION_BITRATE)
        private int mMaxAuditionBitRate = 64;
        @SerializedName(value = KEY_MAX_CACHE_COUNT)
        private int mMaxCacheCount = 1;
        @SerializedName(value = KEY_MAX_DOWNLOAD_BITRATE)
        private int mMaxDownloadBitRate = 0;
        @SerializedName(value = KEY_MAX_DOWNLOAD_COUNT)
        private int mMaxDownloadCount = 0;

        public int getLevel() {
            return this.mLevel;
        }

        public int getMaxAuditionBitRate() {
            return this.mMaxAuditionBitRate >= 0 ? this.mMaxAuditionBitRate : MAX_LIMIT;
        }

        public int getMaxCacheCount() {
            return this.mMaxCacheCount >= 0 ? this.mMaxCacheCount : MAX_LIMIT;
        }

        public int getMaxDownloadBitRate() {
            return this.mMaxDownloadBitRate >= 0 ? this.mMaxDownloadBitRate : MAX_LIMIT;
        }

        public int getMaxDownloadCount() {
            return this.mMaxDownloadCount >= 0 ? this.mMaxDownloadCount : MAX_LIMIT;
        }
    }

    /* loaded from: classes.dex */
    public static class Product {
        private static final String KEY_DESC = "desc";
        private static final String KEY_DISCOUNT = "discount";
        private static final String KEY_PRICE = "price";
        private static final String KEY_PRODUCT_ID = "product_id";
        @SerializedName(value = KEY_PRODUCT_ID)
        private int mProductId = 0;
        @SerializedName(value = KEY_PRICE)
        private int mPrice = 0;
        @SerializedName(value = "desc")
        private String mDesc = "";
        @SerializedName(value = KEY_DISCOUNT)
        private String mDiscount = "";

        public int getProductId() {
            return this.mProductId;
        }

        public int getPrice() {
            return this.mPrice;
        }

        public String getDesc() {
            return this.mDesc;
        }

        public String getDiscount() {
            return this.mDiscount;
        }
    }
}
