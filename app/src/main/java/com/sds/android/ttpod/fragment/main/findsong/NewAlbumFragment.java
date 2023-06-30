package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.FirstPublishAlbumData;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewAlbumResult;

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
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.NetworkLoadView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class NewAlbumFragment extends SlidingClosableFragment {
    private static final String CURRENT_WEEK = "本周新碟";
    private static final int DEFAULT_PAGE_TOTAL = 1;
    private static final String MANY_SINGERS = "群星";
    private static final int NUMBER_ROW = 2;
    private static final int PAGE_1 = 1;
    private static final int PAGE_SIZE = 10;
    private static final String WEEK = "周";
    private C1531a mAdapter;
    private MusiccircleFooter mFooter;
    private ArrayList<C1534b> mListDataList;
    private ListView mListView;
    private NetworkLoadView mLoadingView;
    private FirstPublishNewAlbumResult mResult;
    private Pager mPager = new Pager();
    private RequestState mRequestState = RequestState.IDLE;
    private NetworkLoadView.InterfaceC2206b mOnLoadingViewStartLoadingListener = new NetworkLoadView.InterfaceC2206b() { // from class: com.sds.android.ttpod.fragment.main.findsong.NewAlbumFragment.2
        @Override // com.sds.android.ttpod.widget.NetworkLoadView.InterfaceC2206b
        /* renamed from: a */
        public void mo1678a() {
            if (NewAlbumFragment.this.mRequestState != RequestState.REQUESTING) {
                NewAlbumFragment.this.requestAlbumList(1);
            }
        }
    };

    public NewAlbumFragment() {
        initBundle(SPage.PAGE_ONLINE_NEW_ALBUM, (String) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mFooter = new MusiccircleFooter(layoutInflater, null);
        return layoutInflater.inflate(R.layout.fragment_new_album_publish, viewGroup, false);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().m7189b(R.string.new_album_publish);
        this.mLoadingView = (NetworkLoadView) view.findViewById(R.id.load_view);
        this.mListView = (ListView) view.findViewById(R.id.listview_new_disc);
        this.mAdapter = new C1531a();
        this.mLoadingView.setOnStartLoadingListener(this.mOnLoadingViewStartLoadingListener);
        this.mListView.addFooterView(this.mFooter.m7934a());
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.NewAlbumFragment.1
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (ListViewUtils.m8262b(i, i2, i3) && NewAlbumFragment.this.mRequestState != RequestState.REQUESTING) {
                    NewAlbumFragment.this.requestAlbumList(NewAlbumFragment.this.mPager.m4669a());
                }
            }
        });
        this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.LOADING);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_NEW_ALBUM_PUBLISH_LIST, ReflectUtils.loadMethod(getClass(), "updateAlbumResult", FirstPublishNewAlbumResult.class));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        updatePublishData(this.mResult);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mAdapter.notifyDataSetChanged();
        this.mLoadingView.onThemeLoaded();
        this.mFooter.onThemeLoaded();
        ThemeManager.m3269a(this.mListView, "BackgroundMaskColor");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestAlbumList(int i) {
        this.mRequestState = RequestState.REQUESTING;
        if (i != 1) {
            this.mFooter.m7932a(false, 0, getString(R.string.loading));
        }
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_NEW_ALBUM_PUBLISH_LIST, Integer.valueOf(i), 10));
    }

    public void updateAlbumResult(FirstPublishNewAlbumResult firstPublishNewAlbumResult) {
        this.mResult = firstPublishNewAlbumResult;
        ResultHelper.m5665a(this, firstPublishNewAlbumResult, new ResultHelper.InterfaceC1510a<FirstPublishNewAlbumResult>() { // from class: com.sds.android.ttpod.fragment.main.findsong.NewAlbumFragment.3
            @Override // com.sds.android.ttpod.fragment.main.ResultHelper.InterfaceC1510a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo5564a(FirstPublishNewAlbumResult firstPublishNewAlbumResult2) {
                NewAlbumFragment.this.updatePublishData(firstPublishNewAlbumResult2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePublishData(FirstPublishNewAlbumResult firstPublishNewAlbumResult) {
        boolean z;
        if (firstPublishNewAlbumResult != null) {
            if (!firstPublishNewAlbumResult.isSuccess()) {
                if (this.mPager.m4664c() == 1) {
                    this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                } else {
                    this.mFooter.m7932a(true, 8, getString(R.string.load_comment_fail));
                }
                this.mRequestState = RequestState.REQUESTED_FAIL;
                return;
            }
            if (this.mPager.m4664c() == 1) {
                this.mPager.m4668a(1);
                this.mPager.m4665b(firstPublishNewAlbumResult.getPageCount());
                this.mPager.m4663c(1);
            } else {
                this.mFooter.m7932a(true, 8, getString(R.string.load_comment_fail));
            }
            this.mLoadingView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
            if (this.mListDataList == null) {
                this.mListDataList = new ArrayList<>();
            }
            for (FirstPublishAlbumData firstPublishAlbumData : firstPublishNewAlbumResult.getDataList()) {
                Iterator<C1534b> it = this.mListDataList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    C1534b next = it.next();
                    if (next.m5623a() == firstPublishAlbumData.getYear() && next.m5622b() == firstPublishAlbumData.getWeek()) {
                        if (!isAlubmDataAdded(next.m5621c(), firstPublishAlbumData)) {
                            next.m5621c().add(firstPublishAlbumData);
                        }
                        z = true;
                    }
                }
                if (!z) {
                    this.mListDataList.add(new C1534b(firstPublishAlbumData.getYear(), firstPublishAlbumData.getWeek(), firstPublishAlbumData));
                }
            }
            if (!this.mPager.m4661d(this.mPager.m4662d())) {
                this.mPager.m4663c(this.mPager.m4662d());
            } else {
                this.mListView.setOnScrollListener(null);
            }
            this.mAdapter.m5629a(this.mListDataList);
            this.mRequestState = RequestState.REQUESTED_SUCCESS;
        }
    }

    private boolean isAlubmDataAdded(List<FirstPublishAlbumData> list, FirstPublishAlbumData firstPublishAlbumData) {
        Iterator<FirstPublishAlbumData> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getMsgId() == firstPublishAlbumData.getMsgId()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.NewAlbumFragment$b */
    /* loaded from: classes.dex */
    public class C1534b {

        /* renamed from: b */
        private int f5183b;

        /* renamed from: c */
        private int f5184c;

        /* renamed from: d */
        private ArrayList<FirstPublishAlbumData> f5185d = new ArrayList<>();

        public C1534b(int i, int i2, FirstPublishAlbumData firstPublishAlbumData) {
            this.f5183b = i;
            this.f5184c = i2;
            this.f5185d.add(firstPublishAlbumData);
        }

        /* renamed from: a */
        public int m5623a() {
            return this.f5183b;
        }

        /* renamed from: b */
        public int m5622b() {
            return this.f5184c;
        }

        /* renamed from: c */
        public ArrayList<FirstPublishAlbumData> m5621c() {
            return this.f5185d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.NewAlbumFragment$a */
    /* loaded from: classes.dex */
    public class C1531a extends SectionListAdapter {

        /* renamed from: d */
        private ArrayList<C1534b> f5174d;

        /* renamed from: e */
        private View.OnClickListener f5175e = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.NewAlbumFragment.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FirstPublishAlbumData firstPublishAlbumData = (FirstPublishAlbumData) view.getTag(R.id.view_bind_data);
                NewAlbumFragment.this.launchFragment(SubPostDetailFragment.createById(firstPublishAlbumData.getMsgId(), "first-publish"));

            }
        };

        /* renamed from: c */
        private LayoutInflater f5173c = LayoutInflater.from(BaseApplication.getApplication());

        public C1531a() {
        }

        /* renamed from: a */
        public void m5629a(ArrayList<C1534b> arrayList) {
            this.f5174d = arrayList;
            m7572b();
            notifyDataSetChanged();
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected int mo5528a() {
            return this.f5174d.size();
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected int mo5527a(int i) {
            C1534b c1534b;
            ArrayList<FirstPublishAlbumData> m5621c;
            if (i >= mo5528a() || (c1534b = this.f5174d.get(i)) == null || (m5621c = c1534b.m5621c()) == null) {
                return 0;
            }
            return (int) Math.ceil(m5621c.size() / 2.0d);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: c */
        public FirstPublishAlbumData mo5526a(int i, int i2) {
            C1534b c1534b;
            ArrayList<FirstPublishAlbumData> m5621c;
            if (i >= this.f5174d.size() || (c1534b = this.f5174d.get(i)) == null || (m5621c = c1534b.m5621c()) == null || i2 >= m5621c.size()) {
                return null;
            }
            return m5621c.get(i2);
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: c */
        protected Object mo5517c(int i) {
            if (i < this.f5174d.size()) {
                return this.f5174d.get(i);
            }
            return null;
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected View getSectionConvertView(ViewGroup viewGroup) {
            return this.f5173c.inflate(R.layout.new_disc_section_view, viewGroup, false);
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: b */
        protected View getSubConvertView(ViewGroup viewGroup) {
            View inflate = this.f5173c.inflate(R.layout.new_disc_list_item, viewGroup, false);
            inflate.setTag(new C1533a[]{new C1533a(inflate.findViewById(R.id.song_item1)), new C1533a(inflate.findViewById(R.id.song_item2))});
            return inflate;
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected void mo5524a(int i, View view) {
            String str;
            C1534b c1534b = this.f5174d.get(i);
            if (c1534b != null) {
                TextView textView = (TextView) view.findViewById(R.id.new_disc_section_week);
                TextView textView2 = (TextView) view.findViewById(R.id.new_disc_section_year);
                if (i == 0) {
                    str = NewAlbumFragment.CURRENT_WEEK;
                    textView2.setVisibility(View.GONE);
                } else {
                    textView2.setVisibility(View.VISIBLE);
                    textView2.setText(String.valueOf(c1534b.m5623a()));
                    str = String.valueOf(c1534b.m5622b()) + NewAlbumFragment.WEEK;
                }
                textView.setText(str);
                ThemeManager.m3269a(textView, ThemeElement.TILE_TEXT);
                ThemeManager.m3269a(textView2, ThemeElement.TILE_SUB_TEXT);
                ThemeManager.m3269a(view.findViewById(R.id.id_title_bar_left_line), ThemeElement.SONG_LIST_ITEM_INDICATOR);
                ThemeManager.m3269a(view.findViewById(R.id.id_title_bar_layout), ThemeElement.TILE_BACKGROUND);
            }
        }

        @Override // com.sds.android.ttpod.adapter.SectionListAdapter
        /* renamed from: a */
        protected void mo5525a(int i, int i2, View view) {
            Object tag = view.getTag();
            if (tag != null) {
                FirstPublishAlbumData mo5526a = mo5526a(i, i2 * 2);
                FirstPublishAlbumData mo5526a2 = mo5526a(i, (i2 * 2) + 1);
                C1533a[] c1533aArr = (C1533a[]) tag;
                m5631a(c1533aArr[0], mo5526a);
                m5631a(c1533aArr[1], mo5526a2);
            }
        }

        /* renamed from: a */
        private void m5631a(C1533a c1533a, FirstPublishAlbumData firstPublishAlbumData) {
            String str;
            String str2;
            if (c1533a != null && firstPublishAlbumData != null) {
                m5630a(c1533a, true);
                String title = firstPublishAlbumData.getTitle();
                if (title.contains("-")) {
                    int indexOf = title.indexOf("-");
                    String substring = title.substring(0, indexOf);
                    int i = indexOf + 2;
                    if (i < title.length()) {
                        title = title.substring(i);
                    }
                    str = title;
                    str2 = substring;
                } else {
                    str = title;
                    str2 = NewAlbumFragment.MANY_SINGERS;
                }
                c1533a.f5179c.setText(str);
                c1533a.f5180d.setText(str2);
                c1533a.f5178b.setTag(R.id.view_bind_data, firstPublishAlbumData);
                c1533a.f5178b.setOnClickListener(this.f5175e);
                ThemeManager.m3269a(c1533a.f5179c, ThemeElement.COMMON_TITLE_TEXT);
                ThemeManager.m3269a(c1533a.f5180d, ThemeElement.COMMON_CONTENT_TEXT);
                ImageView imageView = c1533a.f5181e;
                if (imageView.getTag(R.id.view_bind_data) != firstPublishAlbumData.getPicUrl()) {
                    imageView.setTag(R.id.view_bind_data, firstPublishAlbumData.getPicUrl());
                    int m7225c = DisplayUtils.getWidth() / 2;
                    ImageCacheUtils.displayImage(imageView, firstPublishAlbumData.getPicUrl(), m7225c, m7225c, (int) R.drawable.img_background_song_publish);
                    return;
                }
                return;
            }
            m5630a(c1533a, false);
        }

        /* renamed from: a */
        private void m5630a(C1533a c1533a, boolean z) {
            if (c1533a != null) {
                int i = z ? 0 : 4;
                c1533a.f5179c.setVisibility(i);
                c1533a.f5180d.setVisibility(i);
                c1533a.f5178b.setVisibility(i);
                c1533a.f5181e.setVisibility(i);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: com.sds.android.ttpod.fragment.main.findsong.NewAlbumFragment$a$a */
        /* loaded from: classes.dex */
        public class C1533a {

            /* renamed from: b */
            private View f5178b;

            /* renamed from: c */
            private TextView f5179c;

            /* renamed from: d */
            private TextView f5180d;

            /* renamed from: e */
            private ImageView f5181e;

            public C1533a(View view) {
                this.f5178b = view;
                this.f5179c = (TextView) view.findViewById(R.id.item_name);
                this.f5180d = (TextView) view.findViewById(R.id.item_artist);
                this.f5181e = (ImageView) view.findViewById(R.id.item_picture);
            }
        }
    }
}
