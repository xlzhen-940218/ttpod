package com.sds.android.ttpod.fragment.main.findsong;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MusicCircleFirstPublish;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewSongMoreResult;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.MusiccircleFooter;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.adapter.SectionListAdapter;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.component.RequestState;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.ResultHelper;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.NewSongPublishHeaderView;
import com.sds.android.ttpod.widget.SimpleSongView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class NewSongPublishFragment extends SlidingClosableFragment implements AdapterView.OnItemClickListener {
    private static final int DEFAULT_PAGE_TOTAL = 1;
    private static final int MAX_HEADER_VIEW_NUMBER = 3;
    private static final int PAGE_1 = 1;
    private static final int PAGE_SIZE = 30;
    private C1540a mAdapter;
    private ArrayList<FirstPublishNewSongMoreResult.AlbumData> mAlbumList;
    private MusiccircleFooter mFooter;
    private ArrayList<MusicCircleFirstPublish> mHeaderDataList;
    private NewSongPublishHeaderView mListHeader;
    private ListView mListView;
    private NetworkLoadView mLoadingView;
    private FirstPublishNewSongMoreResult mResult;
    private Pager mPager = new Pager();
    private RequestState mRequestState = RequestState.IDLE;
    private SimpleSongView.InterfaceC2231b mOnSectionViewItemClickListener = new SimpleSongView.InterfaceC2231b() { // from class: com.sds.android.ttpod.fragment.main.findsong.NewSongPublishFragment.4
        @Override // com.sds.android.ttpod.widget.SimpleSongView.InterfaceC2231b
        /* renamed from: a */
        public void mo1537a(Object obj) {
            NewSongPublishFragment.this.launchFragment(SubPostDetailFragment.createById(((MusicCircleFirstPublish) obj).getMsgId(), "first-publish"));
        }
    };
    private NetworkLoadView.InterfaceC2206b mOnLoadingViewStartLoadingListener = new NetworkLoadView.InterfaceC2206b() { // from class: com.sds.android.ttpod.fragment.main.findsong.NewSongPublishFragment.5
        @Override // com.sds.android.ttpod.widget.NetworkLoadView.InterfaceC2206b
        /* renamed from: a */
        public void mo1678a() {
            if (NewSongPublishFragment.this.mRequestState != RequestState.REQUESTING) {
                NewSongPublishFragment.this.requestDataList(1);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_MORE_NEW_SONG_PUBLISH_LIST, ReflectUtils.m8375a(getClass(), "updatePublishResult", FirstPublishNewSongMoreResult.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mFooter = new MusiccircleFooter(layoutInflater, new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.NewSongPublishFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NewSongPublishFragment.this.requestDataList(NewSongPublishFragment.this.mPager.m4669a());
            }
        });
        this.mFooter.onThemeLoaded();
        return layoutInflater.inflate(R.layout.fragment_new_song_publish, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().m7189b(R.string.new_song_publish);
        this.mLoadingView = (NetworkLoadView) view.findViewById(R.id.market_load_view);
        this.mListView = (ListView) view.findViewById(R.id.market_app_list);
        this.mListHeader = new NewSongPublishHeaderView(getActivity());
        this.mAdapter = new C1540a();
        this.mAdapter.m5619a(getActivity());
        this.mListView.setOnScrollListener(new ListViewUtils.C0631a());
        this.mLoadingView.setOnStartLoadingListener(this.mOnLoadingViewStartLoadingListener);
        this.mListHeader.setOnSectionViewItemClickListener(this.mOnSectionViewItemClickListener);
        this.mListView.addHeaderView(this.mListHeader);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setDivider(null);
        this.mListView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.NewSongPublishFragment.2
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (ListViewUtils.m8262b(i, i2, i3) && NewSongPublishFragment.this.mRequestState != RequestState.REQUESTING) {
                    NewSongPublishFragment.this.requestDataList(NewSongPublishFragment.this.mPager.m4669a());
                }
            }
        });
        this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.LOADING);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        updatePublishData(this.mResult);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mListHeader.onThemeLoaded();
        this.mAdapter.notifyDataSetChanged();
        this.mLoadingView.onThemeLoaded();
        ThemeManager.m3269a(this.mListView, ThemeElement.BACKGROUND_MASK);
    }

    protected void requestDataList(int i) {
        this.mRequestState = RequestState.REQUESTING;
        if (i != 1) {
            this.mFooter.m7932a(false, 0, getString(R.string.loading));
        }
        CommandCenter.m4607a().m4606a(new Command(CommandID.REQUEST_MORE_NEW_SONG_PUBLISH_LIST, Integer.valueOf(i), 30));
    }

    public void updatePublishResult(FirstPublishNewSongMoreResult firstPublishNewSongMoreResult) {
        this.mResult = firstPublishNewSongMoreResult;
        ResultHelper.m5665a(this, firstPublishNewSongMoreResult, new ResultHelper.InterfaceC1510a<FirstPublishNewSongMoreResult>() { // from class: com.sds.android.ttpod.fragment.main.findsong.NewSongPublishFragment.3
            @Override // com.sds.android.ttpod.fragment.main.ResultHelper.InterfaceC1510a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo5564a(FirstPublishNewSongMoreResult firstPublishNewSongMoreResult2) {
                NewSongPublishFragment.this.updatePublishData(firstPublishNewSongMoreResult2);
            }
        });
    }

    public void updatePublishData(FirstPublishNewSongMoreResult firstPublishNewSongMoreResult) {
        if (firstPublishNewSongMoreResult != null) {
            if (!firstPublishNewSongMoreResult.isSuccess()) {
                if (this.mPager.m4664c() == 1) {
                    this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                } else {
                    this.mFooter.m7932a(true, 8, getString(R.string.load_comment_fail));
                }
                this.mRequestState = RequestState.REQUESTED_FAIL;
                return;
            }
            if (this.mPager.m4664c() == 1) {
                this.mListView.addFooterView(this.mFooter.m7934a());
                this.mPager.m4668a(1);
                this.mPager.m4665b(firstPublishNewSongMoreResult.getPageCount());
                this.mPager.m4663c(1);
                this.mHeaderDataList = firstPublishNewSongMoreResult.getSingleList();
                this.mListHeader.mo1543a(this.mHeaderDataList, 3);
            }
            this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
            if (this.mAlbumList == null) {
                this.mAlbumList = new ArrayList<>();
            }
            this.mAlbumList.addAll(firstPublishNewSongMoreResult.getDataList());
            if (!this.mPager.m4661d(this.mPager.m4662d())) {
                this.mPager.m4663c(this.mPager.m4662d());
            } else {
                this.mListView.removeFooterView(this.mFooter.m7934a());
                this.mListView.setOnScrollListener(null);
            }
            this.mAdapter.m5616a(this.mAlbumList);
            this.mRequestState = RequestState.REQUESTED_SUCCESS;
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.NewSongPublishFragment$a */
    /* loaded from: classes.dex */
    public class C1540a extends SectionListAdapter {

        /* renamed from: c */
        private Activity f5192c;

        /* renamed from: e */
        private ArrayList<FirstPublishNewSongMoreResult.AlbumData> f5194e;

        /* renamed from: f */
        private boolean f5195f = false;

        /* renamed from: g */
        private View.OnClickListener f5196g = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.NewSongPublishFragment.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NewSongPublishFragment.this.launchFragment(SubPostDetailFragment.createById(((FirstPublishNewSongMoreResult.AlbumData) view.getTag(R.id.view_bind_data)).getMsgId(), "first-publish"));
            }
        };

        /* renamed from: d */
        private LayoutInflater f5193d = LayoutInflater.from(BaseApplication.getApplication());

        public C1540a() {
        }

        /* renamed from: a */
        public void m5619a(Activity activity) {
            this.f5192c = activity;
        }

        /* renamed from: a */
        public void m5616a(ArrayList arrayList) {
            this.f5194e = arrayList;
            m7572b();
            notifyDataSetChanged();
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected int mo5528a() {
            return 1;
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected int mo5527a(int i) {
            if (this.f5194e != null) {
                return (int) Math.ceil(this.f5194e.size() / 3.0d);
            }
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: c */
        public FirstPublishNewSongMoreResult.AlbumData mo5526a(int i, int i2) {
            if (this.f5194e == null || i2 >= this.f5194e.size()) {
                return null;
            }
            return this.f5194e.get(i2);
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: c */
        protected Object mo5517c(int i) {
            if (this.f5194e != null) {
                return this.f5194e;
            }
            return null;
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected View mo5523a(ViewGroup viewGroup) {
            return this.f5193d.inflate(R.layout.new_song_publish_section_view, viewGroup, false);
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: b */
        protected View mo5519b(ViewGroup viewGroup) {
            View inflate = this.f5193d.inflate(R.layout.new_song_publish_list_item, viewGroup, false);
            inflate.setTag(new C1542b[]{new C1542b(inflate.findViewById(R.id.song_item1)), new C1542b(inflate.findViewById(R.id.song_item2)), new C1542b(inflate.findViewById(R.id.song_item3))});
            return inflate;
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected void mo5524a(int i, View view) {
            if (this.f5194e != null) {
                View findViewById = view.findViewById(R.id.new_song_publish_divider_line);
                TextView textView = (TextView) view.findViewById(R.id.new_song_publish_section_year);
                ((TextView) view.findViewById(R.id.new_song_publish_section_week)).setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                ThemeManager.m3269a(findViewById, ThemeElement.COMMON_SEPARATOR);
                ThemeManager.m3269a(textView, ThemeElement.TILE_TEXT);
            }
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected void mo5525a(int i, int i2, View view) {
            FirstPublishNewSongMoreResult.AlbumData mo5526a = mo5526a(i, i2 * 3);
            FirstPublishNewSongMoreResult.AlbumData mo5526a2 = mo5526a(i, (i2 * 3) + 1);
            FirstPublishNewSongMoreResult.AlbumData mo5526a3 = mo5526a(i, (i2 * 3) + 2);
            Object tag = view.getTag();
            if (tag != null) {
                C1542b[] c1542bArr = (C1542b[]) tag;
                m5618a(c1542bArr[0], mo5526a);
                m5618a(c1542bArr[1], mo5526a2);
                m5618a(c1542bArr[2], mo5526a3);
            }
        }

        /* renamed from: a */
        private void m5618a(C1542b c1542b, FirstPublishNewSongMoreResult.AlbumData albumData) {
            if (c1542b != null && albumData != null) {
                m5617a(c1542b, true);
                c1542b.f5201d.setVisibility(View.INVISIBLE);
                c1542b.f5199b.setText(albumData.getTitle());
                c1542b.f5198a.setTag(R.id.view_bind_data, albumData);
                c1542b.f5198a.setOnClickListener(this.f5196g);
                ThemeManager.m3269a(c1542b.f5199b, ThemeElement.COMMON_CONTENT_TEXT);
                ImageView imageView = c1542b.f5200c;
                int m7225c = DisplayUtils.m7225c() / 4;
                ImageCacheUtils.m4752a(imageView, albumData.getPicUrl(), m7225c, m7225c, (int) R.drawable.img_background_song_publish);
                return;
            }
            m5617a(c1542b, false);
        }

        /* renamed from: a */
        private void m5617a(C1542b c1542b, boolean z) {
            if (c1542b != null) {
                int i = z ? 0 : 4;
                c1542b.f5201d.setVisibility(i);
                c1542b.f5199b.setVisibility(i);
                c1542b.f5198a.setVisibility(i);
                c1542b.f5200c.setVisibility(i);
            }
        }
    }

    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.NewSongPublishFragment$b */
    /* loaded from: classes.dex */
    public static class C1542b {

        /* renamed from: a */
        private View f5198a;

        /* renamed from: b */
        private TextView f5199b;

        /* renamed from: c */
        private ImageView f5200c;

        /* renamed from: d */
        private ImageView f5201d;

        public C1542b(View view) {
            this.f5198a = view.findViewById(R.id.item_click_view);
            this.f5199b = (TextView) view.findViewById(R.id.item_name);
            this.f5200c = (ImageView) view.findViewById(R.id.item_picture);
            this.f5201d = (ImageView) view.findViewById(R.id.first_publish_flag);
        }

        /* renamed from: a */
        public View m5614a() {
            return this.f5198a;
        }

        /* renamed from: b */
        public TextView m5612b() {
            return this.f5199b;
        }

        /* renamed from: c */
        public ImageView m5610c() {
            return this.f5200c;
        }

        /* renamed from: d */
        public ImageView m5608d() {
            return this.f5201d;
        }
    }
}
