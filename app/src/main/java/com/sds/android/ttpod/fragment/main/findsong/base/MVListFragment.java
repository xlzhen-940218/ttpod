package com.sds.android.ttpod.fragment.main.findsong.base;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MVOnlineData;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.TTPodConfig;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.view.ModifyFitCenterImageView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.p107a.MVStatistic;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.io.File;

/* loaded from: classes.dex */
public abstract class MVListFragment extends ListLoadingFragment<MVOnlineData> {
    private static final String SINGER_NAME_SEPARATOR = " - ";

    protected abstract void playMv(MVOnlineData mVOnlineData);

    public MVListFragment(CommandID commandID, CommandID commandID2, BaseListAdapter<MVOnlineData> baseListAdapter) {
        super(commandID, commandID2, baseListAdapter);
    }

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        ((AbstractView$OnClickListenerC1592a) this.mListAdapter).m5543a((MVOnlineData) view.getTag(R.id.view_bind_data), getActivity());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String generateLocalMvFilePath(String str, MVOnlineData mVOnlineData) {
        return TTPodConfig.m5283y() + File.separator + (mVOnlineData.getSinger() + SINGER_NAME_SEPARATOR + mVOnlineData.getName()) + ("." + FileUtils.m8399m(FileUtils.m8402j(str)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String generateMvThumbnailPath(MVOnlineData mVOnlineData, String str) {
        return TTPodConfig.m5282z() + File.separator + (mVOnlineData.getSinger() + SINGER_NAME_SEPARATOR + mVOnlineData.getName()) + str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static MVOnlineData parseMvDataFromFile(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = SINGER_NAME_SEPARATOR;
        int indexOf = str.indexOf(SINGER_NAME_SEPARATOR);
        if (indexOf < 0) {
            str2 = SINGER_NAME_SEPARATOR.trim();
            indexOf = str.indexOf(str2);
        }
        if (indexOf <= 0) {
            return null;
        }
        MVOnlineData mVOnlineData = new MVOnlineData();
        String substring = str.substring(0, indexOf);
        mVOnlineData.setName(str.substring(indexOf + str2.length()));
        mVOnlineData.setSinger(substring);
        return mVOnlineData;
    }

    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment$a */
    /* loaded from: classes.dex */
    protected static abstract class AbstractView$OnClickListenerC1592a extends BaseListAdapter<MVOnlineData> implements View.OnClickListener {

        /* renamed from: e */
        protected Activity f5263e;

        /* renamed from: a */
        protected abstract void mo5542a(ActionItem actionItem, int i, MVOnlineData mVOnlineData);

        /* renamed from: a */
        protected abstract ActionItem[] mo5544a(MVOnlineData mVOnlineData);

        /* renamed from: a */
        public void m5546a(Activity activity) {
            this.f5263e = activity;
        }

        @Override // com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        protected View mo5402a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = this.f3157c.inflate(R.layout.mv_list_item, viewGroup, false);
            inflate.setTag(new C1594a(inflate));
            return inflate;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        public void mo5400a(View view, MVOnlineData mVOnlineData, int i) {
            if (this.f3158d != null) {
                MVOnlineData mVOnlineData2 = (MVOnlineData) this.f3158d.get(i);
                view.setTag(R.id.view_bind_data, mVOnlineData2);
                C1594a c1594a = (C1594a) view.getTag();
                c1594a.f5268c.setText(mVOnlineData2.getName());
                c1594a.f5269d.setText(mVOnlineData2.getSinger());
                c1594a.f5266a.setTag(R.id.view_bind_data, mVOnlineData2);
                c1594a.f5266a.setOnClickListener(this);
                ImageCacheUtils.m4752a(c1594a.f5270e, mVOnlineData2.getPicUrl(), this.f5263e.getResources().getDimensionPixelSize(R.dimen.mv_thumbnail_width), this.f5263e.getResources().getDimensionPixelSize(R.dimen.mv_thumbnail_height), (int) R.drawable.img_mv_default_image);
                ThemeManager.m3269a(c1594a.f5268c, ThemeElement.SONG_LIST_ITEM_TEXT);
                ThemeManager.m3269a(c1594a.f5269d, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
                ThemeManager.m3269a(c1594a.f5267b, ThemeElement.SONG_LIST_ITEM_MENU_IMAGE);
                ThemeUtils.m8175a(c1594a.f5267b, (int) R.string.icon_arrow_down, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MVOnlineData mVOnlineData = (MVOnlineData) view.getTag(R.id.view_bind_data);
            if (mVOnlineData != null) {
                m5543a(mVOnlineData, this.f5263e);
                MVStatistic.m5070d();
            }
        }

        /* renamed from: a */
        public void m5543a(final MVOnlineData mVOnlineData, Context context) {
            PopupsUtils.m6725a(context, mo5544a(mVOnlineData), context.getString(R.string.mv_download) + " : " + mVOnlineData.getName(), new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment.a.1
                @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
                /* renamed from: a */
                public void mo5409a(ActionItem actionItem, int i) {
                    AbstractView$OnClickListenerC1592a.this.mo5542a(actionItem, i, mVOnlineData);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: com.sds.android.ttpod.fragment.main.findsong.base.MVListFragment$a$a */
        /* loaded from: classes.dex */
        public static class C1594a {

            /* renamed from: a */
            private View f5266a;

            /* renamed from: b */
            private IconTextView f5267b;

            /* renamed from: c */
            private TextView f5268c;

            /* renamed from: d */
            private TextView f5269d;

            /* renamed from: e */
            private ModifyFitCenterImageView f5270e;

            public C1594a(View view) {
                this.f5268c = (TextView) view.findViewById(R.id.mv_name);
                this.f5270e = (ModifyFitCenterImageView) view.findViewById(R.id.mv_img);
                this.f5269d = (TextView) view.findViewById(R.id.mv_description);
                this.f5266a = view.findViewById(R.id.menu_view);
                this.f5267b = (IconTextView) view.findViewById(R.id.menu_icon_image);
                this.f5267b.setText(R.string.icon_arrow_down);
            }

            /* renamed from: a */
            public View m5541a() {
                return this.f5266a;
            }

            /* renamed from: b */
            public IconTextView m5539b() {
                return this.f5267b;
            }

            /* renamed from: c */
            public TextView m5537c() {
                return this.f5268c;
            }

            /* renamed from: d */
            public TextView m5535d() {
                return this.f5269d;
            }

            /* renamed from: e */
            public ImageView m5533e() {
                return this.f5270e;
            }
        }
    }
}
