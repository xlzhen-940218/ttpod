package com.sds.android.ttpod.component.exception;

import android.os.Environment;

import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.core.statistic.SEvent;
import com.sds.android.sdk.core.statistic.SManager;
import com.sds.android.sdk.core.statistic.SUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.sds.android.ttpod.component.exception.a */
/* loaded from: classes.dex */
public class StatisticPageUtils {
    /* renamed from: a */
    public static String m6635a() {
        File[] listFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + EnvironmentUtils.m8526a() + "/cache/").listFiles();
        if (listFiles == null) {
            return "";
        }
        Arrays.sort(listFiles, new C1213a());
        StringBuilder sb = new StringBuilder();
        sb.append("pages:");
        int i = 0;
        for (int length = listFiles.length - 1; length > 0; length--) {
            File file = listFiles[length];
            if (file.getName().startsWith(SManager.PREFIX_STATISTIC_FILE_NAME)) {
                if (i >= 2) {
                    break;
                }
                i++;
                sb.append(m6634a(file.getAbsolutePath()));
            }
        }
        sb.append("\r\n");
        LogUtils.m8381c("StatisticPageUtils", "getRecentStatisticPages pages=" + sb.toString());
        return sb.toString();
    }

    /* renamed from: a */
    private static String m6634a(String str) {
        JSONArray readEventFromFile;
        if (StringUtils.m8346a(str) || (readEventFromFile = SUtils.readEventFromFile(str)) == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < readEventFromFile.length()) {
                try {
                    JSONObject jSONObject = (JSONObject) readEventFromFile.get(i2);
                    if (StringUtils.m8344a(String.valueOf(jSONObject.get(FeedbackMessage.TYPE_EVENT)), "PAGE_CLICK")) {
                        String valueOf = String.valueOf(jSONObject.get("action"));
                        sb.append(valueOf).append(",").append(String.valueOf(jSONObject.get(SEvent.FIELD_PAGE))).append(",").append(String.valueOf(jSONObject.get(SEvent.FIELD_TO))).append(";");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i = i2 + 1;
            } else {
                return sb.toString();
            }
        }
    }

    /* compiled from: StatisticPageUtils.java */
    /* renamed from: com.sds.android.ttpod.component.exception.a$a */
    /* loaded from: classes.dex */
    private static class C1213a implements Comparator<File> {
        private C1213a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(File file, File file2) {
            if (StringUtils.m8346a(file.getName()) || StringUtils.m8346a(file2.getName())) {
                return -1;
            }
            return file.getName().compareTo(file2.getName());
        }
    }
}
