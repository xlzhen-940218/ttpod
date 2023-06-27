package com.sds.android.ttpod.framework.base;

import com.sds.android.cloudapi.ttpod.api.UrlList;
import com.sds.android.sdk.lib.request.BaseResult;
import java.io.Serializable;

/* renamed from: com.sds.android.ttpod.framework.base.j */
/* loaded from: classes.dex */
public class ValidityResult implements Serializable {

    /* renamed from: a */
    private BaseResult f5732a;

    /* renamed from: b */
    private long f5733b = System.currentTimeMillis();

    /* renamed from: c */
    private long f5734c;

    public ValidityResult(BaseResult baseResult, String str) {
        this.f5732a = baseResult;
        this.f5734c = EnumC1804a.from(str).getExpiredTime();
    }

    /* renamed from: a */
    public BaseResult m4562a() {
        return this.f5732a;
    }

    /* renamed from: b */
    public boolean m4561b() {
        return System.currentTimeMillis() - this.f5733b > this.f5734c;
    }

    /* compiled from: ValidityResult.java */
    /* renamed from: com.sds.android.ttpod.framework.base.j$a */
    /* loaded from: classes.dex */
    private enum EnumC1804a {
        TYPE_DEFAULT(0),
        TYPE_SHORT(1800),
        TYPE_MEDIUM(3600),
        TYPE_LONG(86400);
        
        private long mExpiredTime;

        EnumC1804a(int i) {
            this.mExpiredTime = i * 1000;
        }

        public long getExpiredTime() {
            return this.mExpiredTime;
        }

        public static EnumC1804a from(String str) {
            EnumC1804a enumC1804a = TYPE_DEFAULT;
            if (str.contains(UrlList.recommend + "/recomm_modules")) {
                return TYPE_DEFAULT;
            }
            if (str.contains(UrlList.moduleCategory)) {
                return TYPE_LONG;
            }
            if (str.contains(UrlList.moduleSubCategory)) {
                return TYPE_LONG;
            }
            if (str.contains("http://fm.api.ttpod.com")) {
                return TYPE_LONG;
            }
            if (str.contains("http://v1.ard.h.itlily.com/new/plaza")) {
                return TYPE_DEFAULT;
            }
            if (str.contains(UrlList.recommend)) {
                return TYPE_SHORT;
            }
            if (str.contains("http://v1.ard.q.itlily.com/share/get_celebrities")) {
                return TYPE_DEFAULT;
            }
            if (str.contains("http://v1.ard.tj.itlily.com/ttpod?a=getnewttpod")) {
                if (str.contains("size:1000")) {
                    return TYPE_LONG;
                }
                return TYPE_SHORT;
            } else if (str.contains("http://so.ard.iyyin.com/v2/songs/singersearch") || str.contains("http://so.ard.iyyin.com/albums")) {
                return TYPE_MEDIUM;
            } else {
                if (str.contains("http://v1.ard.q.itlily.com/share") && str.contains("user_timeline")) {
                    return TYPE_MEDIUM;
                }
                if (str.contains("http://ting.hotchanson.com/v2/songs/download") || str.contains("http://ting.hotchanson.com/songs/downwhite")) {
                    return TYPE_MEDIUM;
                }
                if (str.contains("http://so.ard.iyyin.com/meta/query_tab")) {
                    return TYPE_LONG;
                }
                return enumC1804a;
            }
        }
    }
}
