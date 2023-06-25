package com.sds.android.ttpod.framework.p106a;

import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.cloudapi.ttpod.result.OnlineSkinListResult;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.modules.skin.SkinItem;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

/* renamed from: com.sds.android.ttpod.framework.a.r */
/* loaded from: classes.dex */
public class SkinUtils {
    /* renamed from: a */
    public static String m4646a(String str, int i) {
        String str2;
        switch (i) {
            case 1:
                str2 = "assets://";
                break;
            case 2:
                str2 = "package://";
                break;
            default:
                str2 = "file://";
                break;
        }
        return str2 + str;
    }

    /* renamed from: a */
    public static String m4647a(String str) {
        if (str.startsWith("assets://")) {
            return str.substring("assets://".length());
        }
        if (str.startsWith("file://")) {
            return str.substring("file://".length());
        }
        if (str.startsWith("package://")) {
            return str.substring("package://".length());
        }
        return str;
    }

    /* renamed from: b */
    public static String m4644b(String str) {
        if (str.startsWith("assets://") || !str.startsWith("file://")) {
            return "assets://";
        }
        return "file://";
    }

    /* renamed from: a */
    public static String m4645a(String str, final String str2) {
        String str3;
        String[] list = new File(str).list(new FilenameFilter() { // from class: com.sds.android.ttpod.framework.a.r.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str4) {
                return str4.matches(str2 + "[0-9]+\\.json");
            }
        });
        if (list == null || list.length <= 0) {
            return null;
        }
        Arrays.sort(list);
        if (list.length > 1) {
            for (int i = 0; i < list.length - 1; i++) {
                FileUtils.m8404h(str + File.separator + list[i]);
            }
            str3 = list[list.length - 1];
        } else {
            str3 = list[0];
        }
        return str + File.separator + str3;
    }

    /* renamed from: a */
    public static File[] m4650a() {
        File file = new File(TTPodConfig.m5294n());
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        return file.listFiles(new FilenameFilter() { // from class: com.sds.android.ttpod.framework.a.r.2
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                return str.toLowerCase(Locale.US).endsWith(".tsk");
            }
        });
    }

    /* renamed from: a */
    public static boolean m4649a(OnlineSkinItem onlineSkinItem) {
        String m8506e = EnvironmentUtils.C0602a.m8506e();
        String version = onlineSkinItem.getVersion();
        return version == null || version.compareTo(m8506e) <= 0;
    }

    /* renamed from: a */
    public static ArrayList<SkinItem> m4648a(OnlineSkinListResult onlineSkinListResult) {
        ArrayList<SkinItem> arrayList = new ArrayList<>();
        if (onlineSkinListResult != null && onlineSkinListResult.getSkinItems() != null) {
            Iterator<OnlineSkinItem> it = onlineSkinListResult.getSkinItems().iterator();
            while (it.hasNext()) {
                OnlineSkinItem next = it.next();
                if (m4649a(next)) {
                    next.setPictureUrl(onlineSkinListResult.getMainUrl() + next.getRecommendPicUrl());
                    next.setSkinUrl("http://api.skin.ttpod.com/skin/apiSkin/download?id=" + next.getId());
                    SkinItem skinItem = new SkinItem(next);
                    if (FileUtils.m8419a(skinItem.getPath())) {
                        skinItem.m3574a(0);
                    }
                    arrayList.add(skinItem);
                }
            }
        }
        return arrayList;
    }
}
