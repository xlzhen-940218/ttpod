package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.RadioChannel;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult;

import com.sds.android.sdk.lib.request.DataListResult;
import com.sds.android.sdk.lib.request.Extra;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.p086c.OnlinePlayingGroupUtils;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.main.ResultHelper;
import com.sds.android.ttpod.fragment.main.findsong.singer.SceneRecommendFragment;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.MediaItemListResult;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class SongCategoryDetailFragment extends SceneRecommendFragment {
    private final int mId;
    private MediaItemListResult mResult;
    private OnlineMusicSubCategoryResult.SubCategoryData mSubCategoryData;

    public SongCategoryDetailFragment(String str) {
        this.mId = Integer.valueOf(str).intValue();
    }

    public SongCategoryDetailFragment(OnlineMusicSubCategoryResult.SubCategoryData subCategoryData) {
        this.mId = Integer.valueOf(String.valueOf(subCategoryData.getId())).intValue();
        initSubCategoryData(subCategoryData);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    private void initSubCategoryData(OnlineMusicSubCategoryResult.SubCategoryData subCategoryData) {
        this.mSubCategoryData = subCategoryData;
        setPlayingGroupName(OnlinePlayingGroupUtils.m6913a(this.mSubCategoryData));
        //MusicLibraryStatistic.m5067a("category-detail_" + this.mSubCategoryData.getName() + "_" + //MusicLibraryStatistic.m5069a());
        updateStatisticListenInfo();
        updatePage(onLoadTitleText());

    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_SONG_CATEGORY_DETAIL, ReflectUtils.m8375a(getClass(), "updateCategoryDetailResult", MediaItemListResult.class, String.class));
        map.put(CommandID.UPDATE_SONG_CATEGORY_INFO, ReflectUtils.m8375a(getClass(), "updateCategoryInfo", DataListResult.class, String.class));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        updateCategoryDetailView(this.mResult);
        if (this.mId != 0) {
            CommandCenter.getInstance().m4606a(new Command(CommandID.GET_SONG_CATEGORY_INFO, getRequestId()));
        }
    }

    public void updateCategoryDetailResult(MediaItemListResult mediaItemListResult, String str) {
        if (str != null && str.equals(getRequestId())) {
            this.mResult = mediaItemListResult;
            ResultHelper.m5665a(this, mediaItemListResult, new ResultHelper.InterfaceC1510a<MediaItemListResult>() { // from class: com.sds.android.ttpod.fragment.main.findsong.SongCategoryDetailFragment.1
                @Override // com.sds.android.ttpod.fragment.main.ResultHelper.InterfaceC1510a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo5564a(MediaItemListResult mediaItemListResult2) {
                    SongCategoryDetailFragment.this.updateCategoryDetailView(mediaItemListResult2);
                }
            });
        }
    }

    public void updateCategoryInfo(DataListResult dataListResult, String str) {
        if (dataListResult.isSuccess() && dataListResult.getDataList() != null && str != null && str.equals(getRequestId())) {
            ArrayList dataList = dataListResult.getDataList();
            if (dataList != null && !dataList.isEmpty()) {
                Iterator it = dataList.iterator();
                while (it.hasNext()) {
                    RadioChannel radioChannel = (RadioChannel) it.next();
                    if (this.mId == radioChannel.getChannelId()) {
                        initSubCategoryData(convert(radioChannel));
                        getDetailHeader().m5488a(this.mSubCategoryData);
                        setTitle(onLoadTitleText());
                    }
                }
            }
            this.mOnlineMediaListFragment.onThemeChanged();
            hideListFootLoadView();
            updatePlayStatus(SupportFactory.m2397a(BaseApplication.getApplication()).m2463m());
            showSecondLoadView();
        }
    }

    private OnlineMusicSubCategoryResult.SubCategoryData convert(RadioChannel radioChannel) {
        OnlineMusicSubCategoryResult.SubCategoryData subCategoryData = new OnlineMusicSubCategoryResult.SubCategoryData();
        subCategoryData.setId(radioChannel.getChannelId());
        subCategoryData.setDetail(radioChannel.getDetails());
        subCategoryData.setLargePicUrl(radioChannel.getLargePicUrl());
        subCategoryData.setName(radioChannel.getChannelName());
        subCategoryData.setParentName(radioChannel.getParentName());
        subCategoryData.setPicUrl(radioChannel.get240X200PicUrl());
        subCategoryData.setUrl(radioChannel.getUrl());
        subCategoryData.setCount(radioChannel.getQuantity());
        return subCategoryData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCategoryDetailView(MediaItemListResult mediaItemListResult) {
        if (mediaItemListResult != null) {
            Extra m4514b = mediaItemListResult.m4514b();
            ArrayList<MediaItem> m4517a = mediaItemListResult.m4517a();
            int m8556b = m4514b == null ? 1 : m4514b.m8556b();
            this.mOnlineMediaListFragment.clearMediaList();
            if (getDetailHeader() != null) {
                getDetailHeader().m5484c();
            }
            updateData(m4517a, Integer.valueOf(m8556b));
            removeSecondLoadView();
            this.mOnlineMediaListFragment.showLastPageFooterText();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    public void doStatisticMediaClick(MediaItem mediaItem) {
        super.doStatisticMediaClick(mediaItem);
        Preferences.m2828t(OnlinePlayingGroupUtils.m6913a(this.mSubCategoryData));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    protected String onLoadTitleText() {
        return this.mSubCategoryData == null ? "" : this.mSubCategoryData.getName();
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SceneRecommendFragment
    protected Bundle buildForwardIntroductionArguments() {
        if (this.mSubCategoryData == null) {
            PopupsUtils.m6760a((int) R.string.post_no_description);
        }
        Bundle bundle = new Bundle();
        bundle.putString("name", this.mSubCategoryData.getName());
        bundle.putString(User.KEY_AVATAR, this.mSubCategoryData.getLargePicUrl());
        bundle.putString("desc", this.mSubCategoryData.getDetail());
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    public void requestDataList(int i) {
        super.requestDataList(i);
        CommandCenter.getInstance().m4606a(new Command(CommandID.GET_SONG_CATEGORY_DETAIL, Integer.valueOf(String.valueOf(this.mId)), Integer.valueOf(i), getRequestId()));
    }



    private void updateStatisticListenInfo() {
        if (this.mSubCategoryData != null) {
            //OnlineMediaStatistic.m5045a(origin());
            //OnlineMediaStatistic.m5054a();
        }
    }

    private String origin() {
        return "library-music-category_" + this.mSubCategoryData.getName();
    }



    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SceneRecommendFragment
    protected String getRequestId() {
        return toString() + this.mId;
    }
}
