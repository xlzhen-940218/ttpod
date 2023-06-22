package com.sds.android.ttpod.framework.p106a.p107a;

import com.sds.android.sdk.core.statistic.KVStatisticEvent;
import com.sds.android.sdk.core.statistic.SessionStatisticEvent;
import com.sds.android.sdk.core.statistic.SingleStatisticEvent;
import com.sds.android.sdk.core.statistic.StatisticEvent;
import com.sds.android.sdk.core.statistic.StatisticManager;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.activities.search.OnlineSearchEntryActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.support.SupportFactory;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.sds.android.ttpod.framework.a.a.s */
/* loaded from: classes.dex */
public class StatisticUtils {

    /* renamed from: a */
    public static final HashMap<String, Integer> f5619a = new HashMap<>();

    static {
        f5619a.put("startup", 1);
        f5619a.put("push", 1);
        f5619a.put("error", 1);
        f5619a.put("local", 1);
        f5619a.put("theme", 1);
        f5619a.put("search", 1);
        f5619a.put("find_music", 1);
        f5619a.put("discovery", 1);
        f5619a.put("share", 1);
        f5619a.put("lyric_pic", 1);
        f5619a.put("recommend", 1);
        f5619a.put("download_manager", 1);
        f5619a.put("mv", 1);
        f5619a.put("sdk_ad", 1);
        f5619a.put("musicCircle", 1);
        f5619a.put("update", 1);
        f5619a.put("song", 1);
        f5619a.put("download", 1);
        f5619a.put("start_time", 1);
        f5619a.put("splash", 1);
        f5619a.put("360", 1);
        f5619a.put("guide", 1);
        f5619a.put("audio_effect", 1);
        f5619a.put("ktv", 1);
        f5619a.put("library_music", 1);
        f5619a.put(OnlineSearchEntryActivity.KEY_THIRD_APP_IDENTITY, 1);
        f5619a.put("song_tj", 1);
        f5619a.put("song_rank", 1);
    }

    /* renamed from: a */
    public static void m4910a(String str, String str2, String str3) {
        //m4909a(str, str2, str3, 0L);
    }

    /* renamed from: a */
    public static void m4909a(String str, String str2, String str3, long j) {
        //m4908a(str, str2, str3, j, 0L);
    }

    /* renamed from: a */
    public static void m4908a(String str, String str2, String str3, long j, long j2) {
       /* LogUtils.m8379d("StatisticUtils", "onEvent_noMessage  moduleId:" + str + " type:" + str2 + " origin:" + str3 + " optValue:" + j + " optValue2:" + j2);
        SingleStatisticEvent instance = SingleStatisticEvent.instance(str, str2, str3, 1, f5619a.get(str).intValue(), 2);
        instance.setOptValue(j);
        instance.setOptValue2(j2);
        m4911a(instance);*/
    }

    /* renamed from: a */
    public static void m4907a(String str, String str2, String str3, long j, long j2, String str4, String str5) {
        LogUtils.m8379d("StatisticUtils", "onEvent_withMessage moduleId:" + str + " type:" + str2 + " origin:" + str3 + " optValue:" + j + " optValue2:" + j2 + " optMessage:" + str4 + " optMessage2:" + str5);
        SingleStatisticEvent instance = SingleStatisticEvent.instance(str, str2, str3, 1, f5619a.get(str).intValue(), 3);
        instance.setOptValue(j);
        instance.setOptValue2(j2);
        instance.setOptMessage(str4);
        instance.setOptMessage2(str5);
        m4911a(instance);
    }

    /* renamed from: a */
    public static void m4906a(String str, String str2, String str3, long j, String str4, String str5) {
        m4907a(str, str2, str3, j, 0L, str4, str5);
    }

    /* renamed from: a */
    public static void m4917a(int i, int i2, long j) {
        //m4916a(i, i2, j, KVStatisticEvent.ValueOperateMode.ADD);
    }

    /* renamed from: a */
    public static void m4916a(int i, int i2, long j, KVStatisticEvent.ValueOperateMode valueOperateMode) {
        /*LogUtils.m8386a("StatisticUtils", "onKVStatisticEvent keyCode = %d, controlCode = %d, value = %d", Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j));
        KVStatisticEvent instance = KVStatisticEvent.instance(i, i2);
        instance.setLongValue(j, valueOperateMode);
        m4911a(instance);*/
    }

    /* renamed from: a */
    public static void m4915a(int i, int i2, Enum r4, KVStatisticEvent.ValueOperateMode valueOperateMode) {
      /*  KVStatisticEvent instance = KVStatisticEvent.instance(i, i2);
        if (KVStatisticEvent.ValueOperateMode.UNIQUE == valueOperateMode) {
            instance.setEnumValue(r4, KVStatisticEvent.ValueOperateMode.UNIQUE);
        } else {
            instance.setEnumValue(r4);
        }
        m4911a(instance);*/
    }

    /* renamed from: a */
    public static void m4914a(int i, int i2, String str) {
        KVStatisticEvent instance = KVStatisticEvent.instance(i, i2);
        instance.setStringValue(str);
        m4911a(instance);
    }

    /* renamed from: a */
    public static void m4913a(int i, int i2, String str, KVStatisticEvent.ValueOperateMode valueOperateMode) {
      /*  KVStatisticEvent instance = KVStatisticEvent.instance(i, i2);
        if (KVStatisticEvent.ValueOperateMode.UNIQUE == valueOperateMode) {
            instance.setStringValue(str, KVStatisticEvent.ValueOperateMode.UNIQUE);
        } else {
            instance.setStringValue(str);
        }
        m4911a(instance);*/
    }

    /* renamed from: a */
    protected static void m4911a(Object obj) {
       /* try {
            if (BaseApplication.getApplication().m4631g()) {
                m4902c(obj);
            } else {
                m4904b(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /* renamed from: c */
    private static void m4902c(Object obj) {
        if (obj instanceof StatisticEvent) {
            SupportFactory.m2397a(BaseApplication.getApplication()).m2500a((StatisticEvent) obj);
        } else if (obj instanceof Map) {
            SupportFactory.m2397a(BaseApplication.getApplication()).m2488a((Map) obj);
        }
    }

    /* renamed from: b */
    protected static void m4904b(Object obj) {
        if (obj instanceof StatisticEvent) {
            //StatisticManager.getInstance().addEvent((StatisticEvent) obj);
        } else {
            OnlineMediaStatistic.m5045a(obj.toString());
        }
    }

    /* renamed from: b */
    public static SessionStatisticEvent m4903b(String str, String str2, String str3, long j) {
        return SessionStatisticEvent.instance(str, str2, str3, f5619a.get(str).intValue(), j);
    }

    /* renamed from: a */
    public static void m4912a(SessionStatisticEvent sessionStatisticEvent) {
        LogUtils.m8379d("StatisticUtils", "putSessionStatisticEvent");
        m4911a((Object) sessionStatisticEvent);
    }

    /* renamed from: a */
    public static void m4905a(Map<String, String> map) {
        m4911a((Object) map);
    }

    /* renamed from: a */
    public static void m4918a() {
        //StatisticManager.destroyInstance();
    }
}
