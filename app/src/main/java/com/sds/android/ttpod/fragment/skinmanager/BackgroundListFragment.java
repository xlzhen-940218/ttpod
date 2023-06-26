package com.sds.android.ttpod.fragment.skinmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import com.sds.android.cloudapi.ttpod.data.OnlineSkinItem;
import com.sds.android.cloudapi.ttpod.result.OnlinePagedSkinListResult;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.BackgroundItem;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class BackgroundListFragment extends BackgroundBaseFragment {
    private static final int DEFAULT_PAGE_SIZE = 12;
    private static final int ITEM_COUNT = 100;
    private String mCategoryName;
    private int mId;
    private DataListFooterView mListFooterView;
    private Pager mDataPager = new Pager();
    private boolean mIsErrorNotFirstPage = false;
    private AbsListView.OnScrollListener mOnScrollListener = new AbsListView.OnScrollListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.BackgroundListFragment.1
        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (ListViewUtils.m8262b(i, i2, i3) && !BackgroundListFragment.this.mIsLoading) {
                if (BackgroundListFragment.this.mDataPager.m4669a() >= BackgroundListFragment.this.mDataPager.m4658g()) {
                    BackgroundListFragment.this.mBackgroundListView.removeFooterView(BackgroundListFragment.this.mListFooterView);
                } else if (!BackgroundListFragment.this.mIsErrorNotFirstPage) {
                    BackgroundListFragment.this.requestData(BackgroundListFragment.this.mDataPager.m4662d());
                } else {
                    BackgroundListFragment.this.showNotFirstPageError();
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.REQUEST_PAGED_BKG_LIST_FINISHED, ReflectUtils.m8375a(getClass(), "updateDataListForAdapter", OnlinePagedSkinListResult.class));
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getActivity().getIntent().getExtras();
        this.mId = extras.getInt("id");
        this.mCategoryName = extras.getString("name");
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment
    protected void initListViewHeader() {
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment
    protected void initListViewFooter() {
        this.mListFooterView = new DataListFooterView(getActivity());
        this.mBackgroundListView.addFooterView(this.mListFooterView);
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBackgroundListView.setOnScrollListener(this.mOnScrollListener);
        getActionBarController().m7193a((CharSequence) this.mCategoryName);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        requestData(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestData(int i) {
        this.mIsLoading = true;
        if (i > 1) {
            this.mListFooterView.m1877a();
        }
        loadDataList(i, 12);
        this.mDataPager.m4663c(i);
    }

    private void loadDataList(int i, int i2) {
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_PAGED_BKG_LIST, Integer.valueOf(this.mId), Integer.valueOf(i), Integer.valueOf(i2)));
    }

    @Override // com.sds.android.ttpod.fragment.skinmanager.base.BackgroundBaseFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeManager.m3269a(this.mListFooterView, ThemeElement.BACKGROUND_MASK);
    }

    public void updateDataListForAdapter(OnlinePagedSkinListResult onlinePagedSkinListResult) {
        if (isAdded()) {
            if (onlinePagedSkinListResult.getCode() == 1) {
                ArrayList<OnlineSkinItem> skins = onlinePagedSkinListResult.getData().getSkins();
                ArrayList<BackgroundItem> arrayList = new ArrayList<>();
                String mainUrl = onlinePagedSkinListResult.getMainUrl();
                String substring = mainUrl.substring(0, mainUrl.length() - 1);
                Iterator<OnlineSkinItem> it = skins.iterator();
                while (it.hasNext()) {
                    OnlineSkinItem next = it.next();
                    if (!next.getSkinUrl().startsWith(substring)) {
                        next.setPictureUrl(substring + next.getRecommendPicUrl());
                        next.setSkinUrl(substring + next.getSkinUrl());
                        BackgroundItem backgroundItem = new BackgroundItem(next);
                        backgroundItem.m3332a(next.getId() + "_" + next.getName());
                        if (FileUtils.m8419a(backgroundItem.m3325h())) {
                            backgroundItem.m3333a(BackgroundItem.EnumC2011a.ADD_BY_USER);
                        }
                        arrayList.add(backgroundItem);
                    } else {
                        return;
                    }
                }
                if (arrayList.size() == 0) {
                    this.mStateView.setState(StateView.EnumC2248b.NO_DATA);
                } else {
                    this.mDataPager.m4665b((int) Math.ceil(8.333333333333334d));
                    if (this.mDataPager.m4669a() > 1) {
                        this.mListFooterView.m1873c();
                    }
                    this.mBackgroundAdapter.m5366a(arrayList);
                    this.mStateView.setState(StateView.EnumC2248b.SUCCESS);
                }
                this.mIsErrorNotFirstPage = false;
            } else if (this.mDataPager.m4669a() > 1) {
                this.mIsErrorNotFirstPage = true;
                this.mDataPager.m4663c(this.mDataPager.m4669a() - 1);
                showNotFirstPageError();
            } else {
                this.mIsErrorNotFirstPage = false;
                this.mStateView.setState(StateView.EnumC2248b.FAILED);
            }
            this.mIsLoading = false;
        }
    }

    protected void showNotFirstPageError() {
        if (this.mListFooterView != null) {
            this.mListFooterView.m1875a(getResources().getString(R.string.retry_next_page));
            this.mListFooterView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.skinmanager.BackgroundListFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BackgroundListFragment.this.requestData(BackgroundListFragment.this.mDataPager.m4662d());
                }
            });
        }
    }
}
