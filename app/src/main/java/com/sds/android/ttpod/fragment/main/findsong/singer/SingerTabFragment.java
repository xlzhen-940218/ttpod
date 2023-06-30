package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.p075f.TabPagerAdapter;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ListUtils;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.SimpleSlidingTabHost;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class SingerTabFragment extends SlidingClosableFragment implements AbsListView.OnScrollListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private static final int PAGE_1 = 1;
    private static final String TAG = "SingerTabFragment";
    private BaseAdapter mAdapter;
    private View mFailedView;
    protected DataListFooterView mFooterView;
    private String mGroupID;
    private View mHeaderView;
    private SparseBooleanArray mIsErrorNotFirstPage;
    protected ActionExpandableListView mListView;
    private SparseBooleanArray mLoadingStateArray;
    private String mModule;
    private View mNodataView;
    private String mOrigin;
    private SparseArray<Pager> mPagerArray;
    private View mRootView;
    private SingerHeader mSingerHeader;
    private SimpleSlidingTabHost mSlidingTabHost;
    private StateView mStateView;
    private int mTabCount;
    protected int mTab = 0;
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment.2
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            SingerTabFragment.this.pageSelected(i);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }
    };

    protected abstract BaseAdapter getAdapter(int i);

    protected abstract SingerHeader initHeaderSingerView();

    protected abstract List<TabPagerAdapter.C1004a> onBuildTabBinders();

    protected abstract void onItemClick(int i, AdapterView<?> adapterView, View view, int i2, long j);

    protected abstract boolean onItemLongClick(int i, AdapterView<?> adapterView, View view, int i2, long j);

    protected abstract void onRequestData(int i, int i2);

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_singer_tab, (ViewGroup) null, false);
            this.mStateView = (StateView) this.mRootView.findViewById(R.id.empty);
            this.mHeaderView = layoutInflater.inflate(R.layout.singer_header, (ViewGroup) null);
            this.mSingerHeader = initHeaderSingerView();
            ((ViewGroup) this.mHeaderView.findViewById(R.id.header_layout)).addView(this.mSingerHeader.m5470b());
            this.mListView = (ActionExpandableListView) this.mRootView.findViewById(R.id.action_expandable_list_view);
            this.mSlidingTabHost = (SimpleSlidingTabHost) this.mHeaderView.findViewById(R.id.slidingtabhost);
            initSlidingTabHost(this.mSlidingTabHost);
            this.mListView.addHeaderView(this.mHeaderView);
            onCreateListFooterView(layoutInflater);
            this.mFailedView = onCreateFailedView(layoutInflater);
            this.mStateView.setFailedView(this.mFailedView);
            this.mNodataView = onCreateNodataView(layoutInflater);
            this.mStateView.setNodataView(this.mNodataView);
            this.mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    ((ViewGroup.MarginLayoutParams) SingerTabFragment.this.mStateView.getLayoutParams()).setMargins(0, SingerTabFragment.this.mHeaderView.getHeight(), 0, 0);
                    SingerTabFragment.this.mStateView.setState(StateView.EnumC2248b.LOADING);
                    SingerTabFragment.this.mHeaderView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            });
        }
        return this.mRootView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void requestSingerImage() {
        if (this.mSingerHeader != null) {
            this.mSingerHeader.m5472a();
        }
    }

    private void initSlidingTabHost(SimpleSlidingTabHost simpleSlidingTabHost) {
        simpleSlidingTabHost.setTabLayoutAverageSpace(true);
        LogUtils.debug(TAG, "initSlidingTabHost slidingTabHost=" + simpleSlidingTabHost + ",mOnPageChangeListener=" + this.mOnPageChangeListener);
        simpleSlidingTabHost.setOnPageChangeListener(this.mOnPageChangeListener);
        List<TabPagerAdapter.C1004a> onBuildTabBinders = onBuildTabBinders();
        simpleSlidingTabHost.setPagerAdapter(new TabPagerAdapter(onBuildTabBinders));
        this.mTabCount = onBuildTabBinders.size();
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initLoadingStateArray();
        initPagerArray();
        this.mAdapter = getAdapter(this.mTab);
        if (this.mTab == 0) {
            this.mListView.mo1261a(this.mAdapter, R.id.menu_view, R.id.expandable);
        } else {
            this.mListView.setAdapter((ListAdapter) this.mAdapter);
        }
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnItemLongClickListener(this);
        this.mListView.setOnScrollListener(this);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        requestData(1);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mStateView != null) {
            this.mStateView.onThemeLoaded();
        }
        if (this.mSingerHeader != null) {
            this.mSingerHeader.m5468d();
        }
        ThemeManager.m3269a(this.mFooterView, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        ThemeManager.m3269a(this.mFooterView, ThemeElement.SUB_BAR_TEXT);
        ThemeManager.m3269a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        ThemeManager.m3269a(this.mListView, "BackgroundMaskColor");
        if (this.mSlidingTabHost != null) {
            ThemeManager.m3269a(this.mSlidingTabHost, ThemeElement.TILE_MASK);
            this.mSlidingTabHost.setTextColor(ThemeManager.m3254c(ThemeElement.SUB_BAR_TEXT));
            this.mSlidingTabHost.setIndicatorDrawable(ThemeManager.m3265a(ThemeElement.SUB_BAR_INDICATOR));
        }
    }

    private InsetDrawable makeDrawable(Drawable drawable, int i, int i2) {
        if (drawable != null) {
            return new InsetDrawable(drawable, i, 0, i2, 0);
        }
        return null;
    }

    private void initPagerArray() {
        this.mLoadingStateArray = new SparseBooleanArray(this.mTabCount);
        for (int i = 0; i < this.mTabCount; i++) {
            this.mLoadingStateArray.put(i, false);
        }
        this.mIsErrorNotFirstPage = new SparseBooleanArray(this.mTabCount);
        for (int i2 = 0; i2 < this.mTabCount; i2++) {
            this.mIsErrorNotFirstPage.put(i2, false);
        }
    }

    private void initLoadingStateArray() {
        this.mPagerArray = new SparseArray<>(this.mTabCount);
        for (int i = 0; i < this.mTabCount; i++) {
            Pager pager = new Pager();
            pager.m4665b(VIPPolicy.Entry.MAX_LIMIT);
            this.mPagerArray.put(i, pager);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pageSelected(int i) {
        LogUtils.debug(TAG, "pageSelected tab=" + i + ",mTab=" + this.mTab);
        if (this.mTab != i) {
            this.mTab = i;
            onPageSelected(i);
        }
    }

    public void setCurrentItem(int i) {
        this.mSlidingTabHost.mo1478a(i);
    }

    protected void onPageSelected(int i) {
        this.mAdapter = getAdapter(i);
        if (this.mTab == 0) {
            this.mListView.mo1261a(this.mAdapter, R.id.menu_view, R.id.expandable);
        } else {
            this.mListView.setAdapter((ListAdapter) this.mAdapter);
        }
        if (this.mAdapter != null) {
            Pager pager = getPager(i);
            if (this.mAdapter.getCount() == 0) {
                requestData(pager.m4669a());
                this.mListView.removeFooterView(this.mFooterView);
                this.mStateView.setVisibility(View.VISIBLE);
                this.mStateView.setState(StateView.EnumC2248b.LOADING);
            } else {
                this.mListView.removeFooterView(this.mFooterView);
                if (this.mLoadingStateArray.get(i)) {
                    if (pager.m4669a() > 1) {
                        this.mListView.addFooterView(this.mFooterView);
                        showFooterLoading();
                    }
                } else {
                    if (pager.m4669a() == pager.m4658g()) {
                        this.mListView.addFooterView(this.mFooterView);
                        showLastPageFooterText();
                    }
                    if (this.mIsErrorNotFirstPage.get(this.mTab)) {
                        this.mListView.addFooterView(this.mFooterView);
                        showNotFirstPageError();
                    }
                }
                this.mStateView.setVisibility(View.GONE);
            }
        }
        onThemeLoaded();
    }

    private View onCreateHeaderView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.singer_header, (ViewGroup) null);
    }

    protected void configFailedView(View view) {
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SingerTabFragment.this.requestData(1);
                }
            });
        }
    }

    protected void configListFooterView(View view) {
    }

    protected void configNodataView(View view) {
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    SingerTabFragment.this.requestData(1);
                }
            });
        }
    }

    protected View onCreateNodataView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.loadingview_nodata, (ViewGroup) null);
    }

    protected View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.loadingview_failed, (ViewGroup) null);
    }

    protected View onCreateListFooterView(LayoutInflater layoutInflater) {
        this.mFooterView = new DataListFooterView(getActivity());
        return this.mFooterView;
    }

    private void notifyDataSetChanged(int i) {
        this.mAdapter = getAdapter(i);
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        notifyDataSetChanged(this.mTab);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int m8266a;
        if (this.mAdapter != null && this.mListView != null && (m8266a = ListViewUtils.m8266a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount())) > -1) {
            onItemClick(this.mTab, adapterView, view, m8266a, j);
        }
    }

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        int m8266a;
        if (this.mAdapter == null || this.mListView == null || (m8266a = ListViewUtils.m8266a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount())) <= -1) {
            return false;
        }
        return onItemLongClick(this.mTab, adapterView, view, m8266a, j);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        LogUtils.debug(TAG, "performOnScroll firstVisibleItem=" + i + ",visibleItemCount=" + i2 + ",totalItemCount=" + i3);
        if (ListViewUtils.m8262b(i, i2, i3) && !this.mLoadingStateArray.get(this.mTab) && this.mStateView.getVisibility() != View.VISIBLE) {
            LogUtils.debug(TAG, "onScroll mTab=" + this.mTab + ",mLoadingStateArray.get(mTab)=" + this.mLoadingStateArray.get(this.mTab));
            Pager pager = getPager(this.mTab);
            LogUtils.debug(TAG, "onScroll mPager.getCurrent()=" + pager.m4669a() + ",mPager.end()=" + pager.m4658g());
            if (pager.m4669a() == pager.m4658g()) {
                showLastPageFooterText();
            } else {
                requestData(pager.m4662d());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Pager getPager(int i) {
        return this.mPagerArray.get(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showLastPageFooterText() {
        LogUtils.debug(TAG, "showLastPageFooterText");
        if (this.mFooterView != null) {
            this.mFooterView.m1875a(getString(R.string.count_of_media, Integer.valueOf(this.mAdapter.getCount())));
            this.mFooterView.setOnClickListener(null);
        }
    }

    protected void showNotFirstPageError() {
        LogUtils.debug(TAG, "showNotFirstPageError");
        if (this.mFooterView != null) {
            this.mFooterView.m1874b();
            this.mFooterView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.singer.SingerTabFragment.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SingerTabFragment.this.requestData(SingerTabFragment.this.getPager(SingerTabFragment.this.mTab).m4662d());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestData(int i) {
        if (!this.mLoadingStateArray.get(this.mTab)) {
            LogUtils.debug(TAG, "requestData, page=" + i + ",mTab=" + this.mTab);
            this.mLoadingStateArray.put(this.mTab, true);
            this.mListView.removeFooterView(this.mFooterView);
            if (i > 1) {
                this.mListView.addFooterView(this.mFooterView);
                showFooterLoading();
            } else {
                this.mStateView.setVisibility(View.VISIBLE);
                this.mStateView.setState(StateView.EnumC2248b.LOADING);
            }
            getPager(this.mTab).m4663c(i);
            onRequestData(this.mTab, i);
        }
    }

    private void showFooterLoading() {
        if (this.mFooterView != null) {
            this.mFooterView.m1877a();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateStateView(int i, int i2, ArrayList arrayList, int i3) {
        if (isViewAccessAble()) {
            LogUtils.debug(TAG, "updateStateView tab=" + i + ",resultCode=" + i2 + ",data=" + (arrayList == null ? 0 : arrayList.size()) + ",totalPage=" + i3);
            this.mLoadingStateArray.put(i, false);
            StateView.EnumC2248b state = getState(i2, arrayList);
            Pager pager = getPager(i);
            if (state == StateView.EnumC2248b.SUCCESS && i3 >= 1) {
                pager.m4665b(i3);
            }
            if (this.mTab == i) {
                this.mIsErrorNotFirstPage.put(this.mTab, false);
                if (pager.m4669a() > 1) {
                    switch (state) {
                        case SUCCESS:
                            this.mFooterView.m1873c();
                            this.mListView.removeFooterView(this.mFooterView);
                            if (pager.m4669a() == pager.m4658g()) {
                                this.mListView.addFooterView(this.mFooterView);
                                return;
                            }
                            return;
                        case FAILED:
                        case NO_DATA:
                            pager.m4663c(pager.m4669a() - 1);
                            this.mIsErrorNotFirstPage.put(this.mTab, true);
                            showNotFirstPageError();
                            return;
                        default:
                            return;
                    }
                }
                this.mListView.removeFooterView(this.mFooterView);
                switch (state) {
                    case SUCCESS:
                        if (pager.m4669a() == pager.m4658g()) {
                            this.mListView.addFooterView(this.mFooterView);
                        }
                        this.mStateView.setVisibility(View.GONE);
                        break;
                    case FAILED:
                        this.mStateView.setVisibility(View.VISIBLE);
                        configFailedView(this.mFailedView);
                        break;
                    case NO_DATA:
                        this.mStateView.setVisibility(View.VISIBLE);
                        configNodataView(this.mNodataView);
                        break;
                }
                this.mStateView.setState(state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StateView.EnumC2248b getState(int i, ArrayList arrayList) {
        if (i != 1) {
            return StateView.EnumC2248b.FAILED;
        }
        return ListUtils.m4718a(arrayList) ? StateView.EnumC2248b.NO_DATA : StateView.EnumC2248b.SUCCESS;
    }

    public void setTabText(int i, String str) {
        setTabText((SimpleSlidingTabHost) this.mHeaderView.findViewById(R.id.slidingtabhost), i, str);
    }

    private void setTabText(SimpleSlidingTabHost simpleSlidingTabHost, int i, String str) {
        simpleSlidingTabHost.m1475a(i, str);
    }

    public void setGroupID(String str) {
        this.mGroupID = str;
    }

    public void setOrigin(String str) {
        this.mOrigin = str;
    }

    public void setModule(String str) {
        this.mModule = str;
    }

    public String getGroupID() {
        return this.mGroupID;
    }

    public String getOrigin() {
        return this.mOrigin;
    }

    public String getModule() {
        return this.mModule;
    }
}
