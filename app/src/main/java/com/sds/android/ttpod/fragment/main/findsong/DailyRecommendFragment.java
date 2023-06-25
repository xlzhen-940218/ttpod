package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import com.sds.android.cloudapi.ttpod.data.IntroductionData;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.IntroductionResult;

import com.sds.android.sdk.lib.request.Extra;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
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
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class DailyRecommendFragment extends SceneRecommendFragment {
    private IntroductionData mIntroductionData;
    private long mIntroductionId;
    private MediaItemListResult mResult;

    public DailyRecommendFragment(long j) {
        this.mIntroductionId = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_DAILY_RECOMMEND, ReflectUtils.m8375a(getClass(), "updateDailyRecommendResult", MediaItemListResult.class));
        map.put(CommandID.UPDATE_POPULAR_SONG_INTRODUCTION, ReflectUtils.m8375a(getClass(), "updateDailyRecommendHeader", IntroductionResult.class));
    }

    public void updateDailyRecommendResult(MediaItemListResult mediaItemListResult) {
        this.mResult = mediaItemListResult;
        ResultHelper.m5665a(this, mediaItemListResult, new ResultHelper.InterfaceC1510a<MediaItemListResult>() { // from class: com.sds.android.ttpod.fragment.main.findsong.DailyRecommendFragment.1
            @Override // com.sds.android.ttpod.fragment.main.ResultHelper.InterfaceC1510a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo5564a(MediaItemListResult mediaItemListResult2) {
                DailyRecommendFragment.this.updateDailyRecommendList(mediaItemListResult2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDailyRecommendList(MediaItemListResult mediaItemListResult) {
        if (mediaItemListResult != null) {
            ArrayList<MediaItem> m4517a = mediaItemListResult.m4517a();
            Extra m4514b = mediaItemListResult.m4514b();
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

    public void updateDailyRecommendHeader(IntroductionResult introductionResult) {
        if (introductionResult == null || introductionResult.getData() == null || !introductionResult.isSuccess()) {
            PopupsUtils.m6760a((int) R.string.request_detail_fail);
            return;
        }
        this.mIntroductionData = introductionResult.getData().get(0);
        if (this.mIntroductionData != null) {
            getDetailHeader().m5486a(this.mIntroductionData.getDetail(), this.mIntroductionData.getPicUrl());
            this.mOnlineMediaListFragment.onThemeChanged();
            hideListFootLoadView();
            updatePlayStatus(SupportFactory.m2397a(BaseApplication.getApplication()).m2463m());
            showSecondLoadView();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    public void requestDataList(int i) {
        super.requestDataList(i);
        CommandCenter.getInstance().m4606a(new Command(CommandID.GET_DAILY_RECOMMEND, Integer.valueOf(i)));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SceneRecommendFragment
    protected Bundle buildForwardIntroductionArguments() {
        Bundle bundle = new Bundle();
        bundle.putString("name", onLoadTitleText());
        if (this.mIntroductionData != null) {
            bundle.putString(User.KEY_AVATAR, this.mIntroductionData.getPicUrl());
            bundle.putString("desc", this.mIntroductionData.getDetail());
        }
        return bundle;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        updateDailyRecommendList(this.mResult);
        if (this.mIntroductionId != 0) {
            CommandCenter.getInstance().m4606a(new Command(CommandID.GET_POPULAR_SONG_INTRODUCTION, Long.valueOf(this.mIntroductionId)));
        }
    }


    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    protected String onLoadTitleText() {
        return this.mIntroductionData == null ? getString(R.string.daily_recommend_title) : this.mIntroductionData.getName();
    }


}
