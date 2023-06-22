package com.sds.android.ttpod.activities.audioeffect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class EffectLevelActivity extends SlidingClosableActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle("等级说明");
        setContentView(R.layout.activity_effect_level);
        ActionBarUtils.m8130a(getActionBarController());
        initLevelDescription();
        initBehavorDescription();
    }

    private void initBehavorDescription() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C0691a("分享音效", "+20", "分享音效每天增加的积分上限为500"));
        arrayList.add(new C0691a("分享的音效被赞", "+5", ""));
        arrayList.add(new C0691a("分享的音效被保存", "+5", ""));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_behavor_description);
        LayoutInflater from = LayoutInflater.from(this);
        addTitleView(from, linearLayout, new C0691a("积分获取方式", "奖励分值", "备注"));
        addDivider(from, linearLayout);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            linearLayout.addView(getItemView(from, R.layout.effect_behavor_item_content, (C0691a) arrayList.get(i)));
            if (i < size - 1) {
                addDivider(from, linearLayout);
            }
        }
    }

    private void initLevelDescription() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C0692b("普通音效达人", R.drawable.img_effect_level_normal, "0~999"));
        arrayList.add(new C0692b("初级音效达人", R.drawable.img_effect_level_junior, "1000~1999"));
        arrayList.add(new C0692b("高级音效达人", R.drawable.img_effect_level_senior, "2000~4999"));
        arrayList.add(new C0692b("资深音效达人", R.drawable.img_effect_level_professional, "5000~9999"));
        arrayList.add(new C0692b("音效发烧友", R.drawable.img_effect_level_fancier, "10000+"));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_level_description);
        LayoutInflater from = LayoutInflater.from(this);
        addTitleView(from, linearLayout, new C0691a("称号", "图标", "积分"));
        addDivider(from, linearLayout);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            linearLayout.addView(getEffectLevelItemView(from, (C0692b) arrayList.get(i)));
            if (i < size - 1) {
                addDivider(from, linearLayout);
            }
        }
    }

    private void addTitleView(LayoutInflater layoutInflater, ViewGroup viewGroup, C0691a c0691a) {
        viewGroup.addView(getItemView(layoutInflater, R.layout.effect_level_item_title, c0691a));
    }

    private void addDivider(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        viewGroup.addView(getHorizontalDivider(layoutInflater), new LinearLayout.LayoutParams(-1, 2));
    }

    private View getEffectLevelItemView(LayoutInflater layoutInflater, C0692b c0692b) {
        View inflate = layoutInflater.inflate(R.layout.effect_level_item_content, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.effect_level_item_title)).setText(c0692b.f2589a);
        ((ImageView) inflate.findViewById(R.id.effect_level_item_icon)).setImageResource(c0692b.f2590b);
        ((TextView) inflate.findViewById(R.id.effect_level_item_score)).setText(c0692b.f2591c);
        return inflate;
    }

    private View getItemView(LayoutInflater layoutInflater, int i, C0691a c0691a) {
        View inflate = layoutInflater.inflate(i, (ViewGroup) null);
        C0693c c0693c = new C0693c(inflate);
        c0693c.f2592a.setText(c0691a.f2586a);
        c0693c.f2593b.setText(c0691a.f2587b);
        c0693c.f2594c.setText(c0691a.f2588c);
        return inflate;
    }

    private View getHorizontalDivider(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.effect_level_item_horizontal_divider, (ViewGroup) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.activities.audioeffect.EffectLevelActivity$c */
    /* loaded from: classes.dex */
    public static class C0693c {

        /* renamed from: a */
        private TextView f2592a;

        /* renamed from: b */
        private TextView f2593b;

        /* renamed from: c */
        private TextView f2594c;

        public C0693c(View view) {
            this.f2592a = (TextView) view.findViewById(R.id.effect_level_item_title);
            this.f2593b = (TextView) view.findViewById(R.id.effect_level_item_icon);
            this.f2594c = (TextView) view.findViewById(R.id.effect_level_item_score);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.activities.audioeffect.EffectLevelActivity$b */
    /* loaded from: classes.dex */
    public static final class C0692b {

        /* renamed from: a */
        private String f2589a;

        /* renamed from: b */
        private int f2590b;

        /* renamed from: c */
        private String f2591c;

        private C0692b(String str, int i, String str2) {
            this.f2589a = str;
            this.f2590b = i;
            this.f2591c = str2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.activities.audioeffect.EffectLevelActivity$a */
    /* loaded from: classes.dex */
    public static final class C0691a {

        /* renamed from: a */
        private String f2586a;

        /* renamed from: b */
        private String f2587b;

        /* renamed from: c */
        private String f2588c;

        private C0691a(String str, String str2, String str3) {
            this.f2586a = str;
            this.f2587b = str2;
            this.f2588c = str3;
        }
    }
}
