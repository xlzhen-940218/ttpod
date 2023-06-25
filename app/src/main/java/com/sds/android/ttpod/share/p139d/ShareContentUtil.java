package com.sds.android.ttpod.share.p139d;

import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.share.ShareInfo;
import com.sds.android.ttpod.share.ShareType;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* renamed from: com.sds.android.ttpod.share.d.b */
/* loaded from: classes.dex */
public class ShareContentUtil {

    /* renamed from: a */
    private static boolean f7451a;

    static {
        f7451a = false;
        try {
            f7451a = new Date().before(new SimpleDateFormat("yyyy-MM-dd").parse("2014-10-15"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static String m1936a(ShareInfo shareInfo) {
        String str = "";
        if (m1934a(shareInfo.m1954g())) {
            str = shareInfo.m1954g() + " - ";
        }
        if (!TextUtils.isEmpty(shareInfo.m1956f())) {
            return str + shareInfo.m1956f();
        }
        return str;
    }

    /* renamed from: b */
    public static String m1932b(ShareInfo shareInfo) {
        if (shareInfo.m1952h().longValue() > 0) {
            return "http://www.dongting.com/?song_id=" + shareInfo.m1952h();
        }
        return "http://www.dongting.com/#a=searchlist&q=" + URLEncoder.encode(shareInfo.m1956f());
    }

    /* renamed from: a */
    public static String m1938a(long j) {
        return "http://pic.ttpod.cn/singerpic" + "/" + (j / 255) + "/" + (j / 7) + "/" + j + "_320.jpg";
    }

    /* renamed from: a */
    public static String m1935a(ShareInfo shareInfo, ShareType shareType) {
        String m1936a = m1936a(shareInfo);
        if (shareInfo.m1948j()) {
            return shareInfo.m1956f() + BaseApplication.getApplication().getString(R.string.share_audition) + shareInfo.m1946l();
        }
        return BaseApplication.getApplication().getString(R.string.share_info_prefix) + m1936a + BaseApplication.getApplication().getString(R.string.share_info_end) + m1931b(shareInfo, shareType);
    }

    /* renamed from: b */
    public static String m1931b(ShareInfo shareInfo, ShareType shareType) {
        StringBuffer stringBuffer = new StringBuffer();
        String m1932b = m1932b(shareInfo);
        if (shareType == ShareType.SINA_WEIBO && shareInfo.m1952h().longValue() <= 0) {
            m1932b = URLEncoder.encode(m1932b);
        }
        if (!StringUtils.isEmpty(m1932b)) {
            stringBuffer.append(BaseApplication.getApplication().getString(R.string.share_audition) + m1932b);
        }
        if (shareType == ShareType.SINA_WEIBO) {
            if (f7451a) {
                stringBuffer.append(BaseApplication.getApplication().getString(R.string.share_china_voice_message));
            } else {
                stringBuffer.append(BaseApplication.getApplication().getString(R.string.share_download_message));
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static String m1933a(String str, String str2, ShareInfo shareInfo, ShareType shareType) {
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(str)) {
            sb.append(str);
            sb.append(" //");
        }
        sb.append(m1935a(shareInfo, shareType));
        sb.append(" ");
        if (!f7451a) {
            sb.append(str2);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static String m1937a(OnlineMediaItem onlineMediaItem) {
        List<OnlineMediaItem.Url> downloadUrls;
        if (onlineMediaItem == null || (downloadUrls = onlineMediaItem.getDownloadUrls()) == null || downloadUrls.size() <= 0) {
            return "";
        }
        return downloadUrls.get(0).getUrl();
    }

    /* renamed from: a */
    public static boolean m1934a(String str) {
        return (TextUtils.isEmpty(str) || "<unknown>".equalsIgnoreCase(str) || "<unknown>".equalsIgnoreCase(str)) ? false : true;
    }
}
