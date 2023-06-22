package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import com.sds.android.cloudapi.ttpod.data.SingerData;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.BaseListAdapter;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment;
import com.sds.android.ttpod.fragment.main.findsong.singer.SingerDetailFragmentNew;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.p107a.MusicLibraryStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.OnlineMediaStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.utils.ArtistUtils;
import com.sds.android.ttpod.utils.ListViewUtils;

/* loaded from: classes.dex */
public class SingerCategoryHotDetailFragment extends ListLoadingFragment<SingerData> {
    public static final String KEY_CHANNEL = "key_channel";
    public static final String KEY_DATA = "key_data";
    private int mId;
    private String mTitle;

    public SingerCategoryHotDetailFragment(String str, int i) {
        super(CommandID.GET_SINGER_CATEGORY_DETAIL, CommandID.UPDATE_SINGER_CATEGORY_DETAIL, null);
        setAdapter(new C1571a());
        this.mId = i;
        this.mTitle = str;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(this.mTitle);
        setPageProperties(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(this.mId));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment
    protected void setupListFooter() {
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mListView.setDivider(null);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment
    protected String onLoadTitleText() {
        return this.mTitle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performSingerClick(SingerData singerData) {
        if (singerData != null) {
            MusicLibraryStatistic.m5067a("singer-rank-detail_" + this.mTitle + "_" + MusicLibraryStatistic.m5069a());
            Bundle bundle = new Bundle();
            OnlineMediaStatistic.m5045a("library-music-singer_" + this.mTitle + "_" + MusicLibraryStatistic.m5069a());
            String name = singerData.getName();
            SingerDetailFragmentNew singerDetailFragmentNew = new SingerDetailFragmentNew(name);
            bundle.putString(KEY_CHANNEL, this.mTitle);
            bundle.putSerializable("key_data", singerData);
            singerDetailFragmentNew.setArguments(bundle);
            launchFragment(singerDetailFragmentNew);
            new SUserEvent("PAGE_CLICK", SAction.ACTION_SINGER_NAME.getValue(), this.mTitle, name).append(BaseFragment.KEY_SONG_LIST_ID, Integer.valueOf(singerData.getId())).post();
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ListLoadingFragment
    protected void requestDataList(int i) {
        CommandCenter.m4607a().m4606a(new Command(this.mRequestId, Integer.valueOf(this.mId), Integer.valueOf(i)));
    }

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        return false;
    }

    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.SingerCategoryHotDetailFragment$a */
    /* loaded from: classes.dex */
    private class C1571a extends BaseListAdapter<SingerData> {

        /* renamed from: e */
        private View.OnClickListener f5240e;

        private C1571a() {
            this.f5240e = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.SingerCategoryHotDetailFragment.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SingerCategoryHotDetailFragment.this.performSingerClick((SingerData) view.getTag(R.id.view_bind_data));
                }
            };
        }

        @Override // com.sds.android.ttpod.adapter.BaseListAdapter, android.widget.Adapter
        public int getCount() {
            if (this.f3158d == null) {
                return 0;
            }
            int size = this.f3158d.size();
            return size % 4 == 0 ? size / 4 : (size / 4) + 1;
        }

        @Override // com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        protected View mo5402a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = this.f3157c.inflate(R.layout.singer_hot_list_item, viewGroup, false);
            inflate.setTag(new NewSongPublishFragment.C1542b[]{new NewSongPublishFragment.C1542b(inflate.findViewById(R.id.song_item1)), new NewSongPublishFragment.C1542b(inflate.findViewById(R.id.song_item2)), new NewSongPublishFragment.C1542b(inflate.findViewById(R.id.song_item3)), new NewSongPublishFragment.C1542b(inflate.findViewById(R.id.song_item4))});
            return inflate;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo5400a(View view, SingerData singerData, int i) {
            if (this.f3158d != null) {
                NewSongPublishFragment.C1542b[] c1542bArr = (NewSongPublishFragment.C1542b[]) view.getTag();
                int i2 = i * 4;
                m5574a(c1542bArr[0], i2);
                m5574a(c1542bArr[1], i2 + 1);
                m5574a(c1542bArr[2], i2 + 2);
                m5574a(c1542bArr[3], i2 + 3);
            }
        }

        /* renamed from: a */
        private void m5574a(NewSongPublishFragment.C1542b c1542b, int i) {
            if (c1542b != null && i < this.f3158d.size()) {
                SingerData singerData = (SingerData) this.f3158d.get(i);
                m5573a(c1542b, true);
                c1542b.m5608d().setVisibility(View.INVISIBLE);
                c1542b.m5612b().setText(singerData.getName());
                c1542b.m5614a().setTag(R.id.view_bind_data, singerData);
                c1542b.m5614a().setOnClickListener(this.f5240e);
                ThemeManager.m3269a(c1542b.m5612b(), ThemeElement.COMMON_CONTENT_TEXT);
                ImageView m5610c = c1542b.m5610c();
                if (!ListViewUtils.m8265a(m5610c, (int) R.drawable.img_background_song_publish)) {
                    int m7225c = DisplayUtils.m7225c() / 4;
                    ImageCacheUtils.m4751a(m5610c, ArtistUtils.m8310a(singerData.getId()), m7225c, m7225c, (int) R.drawable.img_background_song_publish, (int) R.anim.fade_in);
                    return;
                }
                return;
            }
            m5573a(c1542b, false);
        }

        /* renamed from: a */
        private void m5573a(NewSongPublishFragment.C1542b c1542b, boolean z) {
            if (c1542b != null) {
                int i = z ? 0 : 4;
                c1542b.m5608d().setVisibility(i);
                c1542b.m5612b().setVisibility(i);
                c1542b.m5614a().setVisibility(i);
                c1542b.m5610c().setVisibility(i);
            }
        }
    }
}
