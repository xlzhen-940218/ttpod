package com.sds.android.ttpod.fragment.main.findsong;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicCategoryResult;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult;

import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.web.WebActivity;
import com.sds.android.ttpod.adapter.FindSongGridSectionListAdapter;
import com.sds.android.ttpod.adapter.GridListAdapter;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.fragment.main.ResultHelper;
import com.sds.android.ttpod.fragment.main.findsong.base.GridViewFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.widget.NetworkLoadView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SongCategoryChannelFragment extends GridViewFragment<OnlineMusicCategoryResult.CategoryData> {
    private static final int REQUEST_SUB_CATEGORY_PAGE = 1;
    private static final int REQUEST_SUB_CATEGORY_PAGE_SIZE = 15;
    private C1575a mAdapter;
    private long mId;
    private NetworkLoadView.InterfaceC2206b mOnStartLoadingListener;
    private OnlineMusicSubCategoryResult mSubCategoryResult;
    private String mTitle;

    public SongCategoryChannelFragment(FindSongGridSectionListAdapter.C0963a<OnlineMusicCategoryResult.CategoryData> c0963a) {
        super(null);
        this.mAdapter = new C1575a();
        this.mOnStartLoadingListener = new NetworkLoadView.InterfaceC2206b() { // from class: com.sds.android.ttpod.fragment.main.findsong.SongCategoryChannelFragment.1
            @Override // com.sds.android.ttpod.widget.NetworkLoadView.InterfaceC2206b
            /* renamed from: a */
            public void mo1678a() {
                SongCategoryChannelFragment.this.requestSubCategory();
            }
        };
        this.mTitle = c0963a.m7617c();
        this.mId = c0963a.m7618b();
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.GridViewFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.GridViewFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_MUSIC_SUB_CATEGORY, ReflectUtils.m8375a(getClass(), "updateSubCategory", OnlineMusicSubCategoryResult.class));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.GridViewFragment, com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mLoadView.setOnStartLoadingListener(this.mOnStartLoadingListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestSubCategory() {
        CommandCenter.getInstance().execute(new Command(CommandID.GET_MUSIC_SUB_CATEGORY, Long.valueOf(this.mId), 1, 15));
    }

    public void updateSubCategory(OnlineMusicSubCategoryResult onlineMusicSubCategoryResult) {
        this.mSubCategoryResult = onlineMusicSubCategoryResult;
        ResultHelper.m5665a(this, this.mSubCategoryResult, new ResultHelper.InterfaceC1510a<OnlineMusicSubCategoryResult>() { // from class: com.sds.android.ttpod.fragment.main.findsong.SongCategoryChannelFragment.2
            @Override // com.sds.android.ttpod.fragment.main.ResultHelper.InterfaceC1510a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo5564a(OnlineMusicSubCategoryResult onlineMusicSubCategoryResult2) {
                SongCategoryChannelFragment.this.updateSubCategoryView(onlineMusicSubCategoryResult2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSubCategoryView(OnlineMusicSubCategoryResult onlineMusicSubCategoryResult) {
        if (onlineMusicSubCategoryResult != null) {
            if (!onlineMusicSubCategoryResult.isSuccess()) {
                this.mLoadView.setLoadState(NetworkLoadView.EnumC2205a.FAILED);
                return;
            }
            ArrayList<OnlineMusicSubCategoryResult.SubCategoryData> subCategoryList = onlineMusicSubCategoryResult.getSubCategoryList();
            if (subCategoryList != null) {
                this.mAdapter = new C1575a();
                this.mAdapter.setDataList((List) subCategoryList);
                setGridListAdapter(this.mAdapter);
                this.mLoadView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.GridViewFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        updateSubCategoryView(this.mSubCategoryResult);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.GridViewFragment
    protected String onLoadTitleText() {
        return this.mTitle;
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.GridViewFragment
    public void updateDataList(ArrayList<OnlineMusicCategoryResult.CategoryData> arrayList) {
        super.updateDataList(arrayList);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.mSubCategoryResult != null) {
            OnlineMusicSubCategoryResult.SubCategoryData subCategoryData = this.mSubCategoryResult.getSubCategoryList().get(i);
            if (subCategoryData != null) {
                String name = subCategoryData.getName();
                if (!StringUtils.isEmpty(subCategoryData.getUrl())) {
                    gotoBrowserPage(subCategoryData.getUrl(), name);
                } else {
                    launchFragment(new SubSongCategoryDetailFragment(subCategoryData));
                }
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_LIBRARY_THREE.getValue(), this.mTitle, name).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(subCategoryData.getId())).post();
            }
            //MusicLibraryStatistic.m5055f((int) subCategoryData.getId(), subCategoryData.getName());
        }
    }

    private void gotoBrowserPage(String str, String str2) {
        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.setData(Uri.parse(str));
        if (str2 == null) {
            str2 = "";
        }
        intent.putExtra(WebFragment.EXTRA_TITLE, str2);
        intent.putExtra(WebFragment.EXTRA_HINT_BANNER_SHOW, false);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.findsong.SongCategoryChannelFragment$a */
    /* loaded from: classes.dex */
    public class C1575a extends GridListAdapter<OnlineMusicSubCategoryResult.SubCategoryData> {
        private C1575a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.GridListAdapter
        /* renamed from: a */
        public String mo5568b(OnlineMusicSubCategoryResult.SubCategoryData subCategoryData) {
            return subCategoryData.getName();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.GridListAdapter
        /* renamed from: b  reason: avoid collision after fix types in other method */
        public String mo5566c(OnlineMusicSubCategoryResult.SubCategoryData subCategoryData) {
            return subCategoryData.getCount() == 0 ? "" : this.context.getString(R.string.count_of_media, Integer.valueOf(subCategoryData.getCount()));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.GridListAdapter
        /* renamed from: c  reason: avoid collision after fix types in other method */
        public String mo5565d(OnlineMusicSubCategoryResult.SubCategoryData subCategoryData) {
            return null;
        }

        @Override // com.sds.android.ttpod.adapter.GridListAdapter, com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        protected View getConvertView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            View inflate = layoutInflater.inflate(R.layout.song_category_grid_list_view_item, viewGroup, false);
            inflate.setTag(new GridViewHolder(inflate));
            return inflate;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.adapter.GridListAdapter, com.sds.android.ttpod.adapter.BaseListAdapter
        /* renamed from: a */
        public void buildDataUI(View view, OnlineMusicSubCategoryResult.SubCategoryData subCategoryData, int i) {
            super.buildDataUI(view, subCategoryData, i);
            m7607a((GridViewHolder) view.getTag());
        }
    }
}
