package com.sds.android.ttpod.framework.modules.skin;

import android.text.TextUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.skin.p128a.CategoryItem;
import com.sds.android.ttpod.framework.modules.skin.p128a.OnlineCategoryListResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d */
/* loaded from: classes.dex */
public class CategoryListLoader implements Runnable {

    /* renamed from: a */
    public static final String f6596a;

    /* renamed from: b */
    public static final String f6597b;

    /* renamed from: c */
    static final /* synthetic */ boolean f6598c;

    /* renamed from: d */
    private static final String f6599d;

    /* renamed from: e */
    private String[] f6600e = {f6596a, f6597b};

    /* renamed from: f */
    private String[] f6601f = {f6599d + "category_skin.json", f6599d + "category_bkg.json"};

    /* renamed from: g */
    private CommandID[] f6602g = {CommandID.ON_SKIN_CATEGORY_LIST_PARSED, CommandID.ON_BACKGROUND_CATEGORY_LIST_PARSED};

    /* renamed from: h */
    private String f6603h;

    /* renamed from: i */
    private String f6604i;

    /* renamed from: j */
    private CommandID f6605j;

    static {
        f6598c = !CategoryListLoader.class.desiredAssertionStatus();
        f6599d = "category_list" + File.separator;
        f6596a = TTPodConfig.m5294n() + File.separator + "category_skin.json";
        f6597b = TTPodConfig.m5295m() + File.separator + "category_bkg.json";
    }

    public CategoryListLoader(int i) {
        if (!f6598c && i >= this.f6600e.length) {
            throw new AssertionError();
        }
        this.f6603h = this.f6600e[i];
        this.f6604i = this.f6601f[i];
        this.f6605j = this.f6602g[i];
    }

    @Override // java.lang.Runnable
    public void run() {
        m3694a();
    }

    /* renamed from: a */
    private void m3694a() {
        long j;
        ArrayList arrayList = new ArrayList();
        String m3766a = CachedOnlineListReader.m3766a(this.f6603h, this.f6604i);
        OnlineCategoryListResult onlineCategoryListResult = TextUtils.isEmpty(m3766a) ? null : (OnlineCategoryListResult) JSONUtils.fromJson(m3766a, OnlineCategoryListResult.class);
        if (onlineCategoryListResult == null) {
            j = 0;
        } else {
            ArrayList<CategoryItem> m3859a = onlineCategoryListResult.m3859a();
            String m3857c = onlineCategoryListResult.m3857c();
            String substring = m3857c.endsWith("/") ? m3857c.substring(0, m3857c.length() - 1) : m3857c;
            Iterator<CategoryItem> it = m3859a.iterator();
            while (it.hasNext()) {
                CategoryItem next = it.next();
                next.m3864a(m3693a(substring, next.m3861d()));
                arrayList.add(next);
            }
            Collections.sort(arrayList);
            j = onlineCategoryListResult.m3858b();
        }
        CommandCenter.m4607a().m4595b(new Command(this.f6605j, arrayList, Long.valueOf(j)), ModuleID.SKIN);
    }

    /* renamed from: a */
    private String m3693a(String str, String str2) {
        if (str2.startsWith("/")) {
            str2 = str2.substring(1, str2.length());
        }
        return str + "/" + str2;
    }
}
