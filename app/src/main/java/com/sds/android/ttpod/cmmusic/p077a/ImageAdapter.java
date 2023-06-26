package com.sds.android.ttpod.cmmusic.p077a;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sds.android.sdk.lib.util.StringUtils;

import com.sds.android.ttpod.R;
import com.sds.android.ttpod.cmmusic.p080d.CmmusicStatistic;
import com.sds.android.ttpod.cmmusic.p081e.AsyncImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.sds.android.ttpod.cmmusic.a.b */
/* loaded from: classes.dex */
public class ImageAdapter extends PagerAdapter {

    /* renamed from: a */
    private ArrayList<HashMap<String, String>> f3453a;

    /* renamed from: b */
    private List<View> f3454b;

    /* renamed from: c */
    private Context f3455c;

    /* renamed from: d */
    private AsyncImageLoader f3456d = new AsyncImageLoader();

    public ImageAdapter(ArrayList<HashMap<String, String>> arrayList, List<View> list, Context context) {
        this.f3454b = list;
        this.f3453a = arrayList;
        this.f3455c = context;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.f3454b.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override // android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, final int i) {
        final ImageView imageView = (ImageView) this.f3454b.get(i).findViewById(R.id.image);
        this.f3456d.m7301a(this.f3453a.get(i).get("img"), new AsyncImageLoader.InterfaceC1029a() { // from class: com.sds.android.ttpod.cmmusic.a.b.1
            @Override // com.sds.android.ttpod.cmmusic.p081e.AsyncImageLoader.InterfaceC1029a
            /* renamed from: a */
            public void mo7300a(Drawable drawable, String str) {
                imageView.setBackgroundDrawable(drawable);
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.cmmusic.a.b.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("pagename", "AdSeatContent");
                        String str2 = (String) ((HashMap) ImageAdapter.this.f3453a.get(i)).get("href");
                        if (StringUtils.equals(str2, "ttlink:a1")) {
                            CmmusicStatistic.m7313a("tag_6");
                            intent.setAction("com.sds.android.ttpod.cmmusic.listen_control");
                            bundle.putString("href", "tag_6");
                        } else if (StringUtils.equals(str2, "ttlink:a2")) {
                            CmmusicStatistic.m7313a("tag_7");
                            intent.setAction("com.sds.android.ttpod.cmmusic.listen_control");
                            bundle.putString("href", "tag_7");
                        } else if (StringUtils.equals(str2, "ttlink:a3")) {
                            CmmusicStatistic.m7313a("tag_8");
                            intent.setAction("com.sds.android.ttpod.cmmusic.listen_control");
                            bundle.putString("href", "tag_8");
                        } else if (StringUtils.equals(str2, "ttlink:a4")) {
                            CmmusicStatistic.m7313a("tag_9");
                            intent.setAction("com.sds.android.ttpod.cmmusic.listen_control");
                            bundle.putString("href", "tag_9");
                        } else if (StringUtils.equals(str2, "ttlink:a5")) {
                            CmmusicStatistic.m7313a("tag_10");
                            intent.setAction("com.sds.android.ttpod.cmmusic.listen_control");
                            bundle.putString("href", "tag_10");
                        } else if (str2 != null && !str2.equals("")) {
                            intent.setAction("com.sds.android.ttpod.cmmusic.webview");
                            bundle.putString("href", str2);
                        }
                        intent.putExtras(bundle);
                        ImageAdapter.this.f3455c.startActivity(intent);
                    }
                });
            }
        });
        viewGroup.removeView(this.f3454b.get(i));
        viewGroup.addView(this.f3454b.get(i));
        return this.f3454b.get(i);
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.f3454b.get(i));
    }
}
