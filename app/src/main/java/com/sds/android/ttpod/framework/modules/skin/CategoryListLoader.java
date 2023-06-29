package com.sds.android.ttpod.framework.modules.skin;

import android.text.TextUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.ModuleID;
import com.sds.android.ttpod.framework.modules.skin.category.CategoryItem;
import com.sds.android.ttpod.framework.modules.skin.category.OnlineCategoryListResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* renamed from: com.sds.android.ttpod.framework.modules.skin.d */
/* loaded from: classes.dex */
public class CategoryListLoader implements Runnable {

    /* renamed from: a */
    public static final String categorySkinPath;

    /* renamed from: b */
    public static final String categoryBkgPath;

    /* renamed from: c */
    static final /* synthetic */ boolean f6598c;

    /* renamed from: d */
    private static final String categoryListFolder;

    /* renamed from: e */
    private String[] categorys = {categorySkinPath, categoryBkgPath};

    /* renamed from: f */
    private String[] f6601f = {categoryListFolder + "category_skin.json", categoryListFolder + "category_bkg.json"};

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
        categoryListFolder = "category_list" + File.separator;
        categorySkinPath = TTPodConfig.getSkinPath() + File.separator + "category_skin.json";
        categoryBkgPath = TTPodConfig.getBkgs() + File.separator + "category_bkg.json";
    }

    public CategoryListLoader(int i) {
        if (!f6598c && i >= this.categorys.length) {
            throw new AssertionError();
        }
        this.f6603h = this.categorys[i];
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
            ArrayList<CategoryItem> m3859a = onlineCategoryListResult.getData();
            String picUrl = onlineCategoryListResult.getPicUrl();
            //String substring = picUrl.endsWith("/") ? picUrl.substring(0, picUrl.length() - 1) : picUrl;
            Iterator<CategoryItem> it = m3859a.iterator();
            while (it.hasNext()) {
                CategoryItem next = it.next();
                next.setPicUrl(spliceUrl(picUrl, next.getRecommendPicUrl()));
                arrayList.add(next);
            }
            Collections.sort(arrayList);
            j = onlineCategoryListResult.getCTime();
        }
        CommandCenter.getInstance().m4595b(new Command(this.f6605j, arrayList, Long.valueOf(j)), ModuleID.SKIN);
    }

    /* renamed from: a */
    private String spliceUrl(String leftUrl, String rightUrl) {
        if (rightUrl.startsWith("/")) {
            rightUrl = rightUrl.substring(1);
        }
        return leftUrl + "/" + rightUrl;
    }
}
