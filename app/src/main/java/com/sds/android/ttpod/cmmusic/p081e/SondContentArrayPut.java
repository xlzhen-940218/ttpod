package com.sds.android.ttpod.cmmusic.p081e;

import com.sds.android.ttpod.cmmusic.p078b.SondContentInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.sds.android.ttpod.cmmusic.e.f */
/* loaded from: classes.dex */
public class SondContentArrayPut {
    /* renamed from: a */
    public static ArrayList<HashMap<String, String>> m7272a(List<SondContentInfo> list) {
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        try {
            for (SondContentInfo sondContentInfo : list) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("resource_name", sondContentInfo.m7342a());
                hashMap.put("resource_songer", sondContentInfo.m7341b());
                hashMap.put("zhenling_id", sondContentInfo.m7339d());
                hashMap.put("cailing_id", sondContentInfo.m7340c());
                hashMap.put("music_id", sondContentInfo.m7338e());
                arrayList.add(hashMap);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }
}
