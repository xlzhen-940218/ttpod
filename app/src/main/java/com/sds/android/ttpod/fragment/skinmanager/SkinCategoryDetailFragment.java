package com.sds.android.ttpod.fragment.skinmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.cloudapi.ttpod.result.OnlinePagedSkinListResult;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.skinmanager.base.ActionBarThemeListFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.SkinItem;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.framework.p106a.SkinUtils;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class SkinCategoryDetailFragment extends ActionBarThemeListFragment {
    private static final int DEFAULT_PAGE_SIZE = 12;
    private String mCategoryName;
    private DataListFooterView mFooterView;
    private int mId;
    private boolean mIsLoading = false;
    private Pager mPager = new Pager();
    private boolean mIsErrorNotFirstPage = false;
    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.SkinCategoryDetailFragment.1
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            switch (i) {
                case 0:
                    SkinCategoryDetailFragment.this.mEnableRefreshProgressbar = true;
                    return;
                case 1:
                    SkinCategoryDetailFragment.this.mEnableRefreshProgressbar = false;
                    return;
                default:
                    return;
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (ListViewUtils.m8262b(i, i2, i3) && !SkinCategoryDetailFragment.this.mIsLoading) {
                if (SkinCategoryDetailFragment.this.mPager.m4669a() >= SkinCategoryDetailFragment.this.mPager.m4658g()) {
                    SkinCategoryDetailFragment.this.mThemeListView.removeFooterView(SkinCategoryDetailFragment.this.mFooterView);
                } else if (!SkinCategoryDetailFragment.this.mIsErrorNotFirstPage) {
                    SkinCategoryDetailFragment.this.requestData(SkinCategoryDetailFragment.this.mPager.m4662d());
                } else {
                    SkinCategoryDetailFragment.this.showNotFirstPageError();
                }
            }
        }
    };

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected void initListViewFooter() {
        this.mFooterView = new DataListFooterView(getActivity());
        this.mThemeListView.addFooterView(this.mFooterView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.skinmanager.base.ActionBarThemeListFragment, com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.REQUEST_PAGED_SKIN_LIST_FINISHED, ReflectUtils.loadMethod(getClass(), "updateDataListForAdapter", OnlinePagedSkinListResult.class));
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected void loadData() {
        requestData(1);
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected AbsListView.OnScrollListener getOnScrollListener() {
        return this.mOnScrollListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestData(int i) {
        this.mIsLoading = true;
        if (i > 1) {
            this.mFooterView.m1877a();
        }
        loadDataList(i, 12);
        this.mPager.m4663c(i);
    }

    private void loadDataList(int i, int i2) {
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_PAGED_SKIN_LIST, Integer.valueOf(this.mId), Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public void updateDataListForAdapter(OnlinePagedSkinListResult onlinePagedSkinListResult) {
        if (isAdded()) {
            if (onlinePagedSkinListResult.getCode() == 1) {
                OnlinePagedSkinListResult.OnlinePagedSkinListData data = onlinePagedSkinListResult.getData();
                ArrayList<OnlineSkinItem> skins = data.getSkins();
                ArrayList<SkinItem> arrayList = new ArrayList<>();
                String mainUrl = onlinePagedSkinListResult.getMainUrl();
                Iterator<OnlineSkinItem> it = skins.iterator();
                while (it.hasNext()) {
                    OnlineSkinItem next = it.next();
                    if (SkinUtils.m4649a(next)) {
                        next.setPictureUrl(mainUrl + next.getRecommendPicUrl());
                        next.setSkinUrl("http://api.skin.ttpod.com/skin/apiSkin/download?id=" + next.getId());
                        SkinItem skinItem = new SkinItem(next);
                        if (FileUtils.m8419a(skinItem.getPath())) {
                            skinItem.setType(0);
                        }
                        arrayList.add(skinItem);
                    }
                }
                if (arrayList.size() == 0) {
                    this.mStateView.setState(StateView.EnumC2248b.NO_DATA);
                } else {
                    this.mPager.m4665b((int) Math.ceil(data.getSkinsCount() / 12.0d));
                    if (this.mPager.m4669a() > 1) {
                        this.mThemeAdapter.m5333b(arrayList);
                        setOnlineSkinInfoMap(arrayList);
                        this.mFooterView.m1873c();
                    } else {
                        setAdapterDataSource(arrayList);
                    }
                    this.mThemeAdapter.notifyDataSetChanged();
                    this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
                }
                this.mIsErrorNotFirstPage = false;
            } else if (this.mPager.m4669a() > 1) {
                this.mIsErrorNotFirstPage = true;
                this.mPager.m4663c(this.mPager.m4669a() - 1);
                showNotFirstPageError();
            } else {
                this.mIsErrorNotFirstPage = false;
                this.mStateView.setState(StateView.EnumC2248b.FAILED);
            }
            this.mIsLoading = false;
        }
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getActivity().getIntent().getExtras();
        this.mId = extras.getInt("id");
        this.mCategoryName = extras.getString("name");
        this.mSubClassTag = SkinCategoryDetailFragment.class.getSimpleName() + this.mCategoryName;
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.ActionBarThemeListFragment, com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeManager.m3269a(this.mFooterView, "BackgroundMaskColor");
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected ArrayList<SkinItem> loadDataFromCache() {
        return null;
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BaseThemeFragment
    protected CommandID getLoadDataCommandID() {
        return null;
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.ActionBarThemeListFragment
    protected String getTitle() {
        return this.mCategoryName;
    }

    protected void showNotFirstPageError() {
        if (this.mFooterView != null) {
            this.mFooterView.m1875a(getResources().getString(R.string.retry_next_page));
            this.mFooterView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.SkinCategoryDetailFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    SkinCategoryDetailFragment.this.requestData(SkinCategoryDetailFragment.this.mPager.m4662d());
                }
            });
        }
    }
}
