package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.IntroductionData;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.IntroductionResult;

import com.sds.android.sdk.lib.request.Extra;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.TTPodApplication;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.main.ResultHelper;
import com.sds.android.ttpod.fragment.main.findsong.singer.SceneRecommendFragment;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.MediaItemListResult;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class PopularSongFragment extends SceneRecommendFragment {
    private IntroductionData mIntroductionData;
    private long mModuleId;
    private MediaItemListResult mResult;
    private String mVersion = null;
    private boolean mNeedToast = false;

    public PopularSongFragment(long j) {
        this.mModuleId = j;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setTitle(onLoadTitleText());
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SceneRecommendFragment
    protected Bundle buildForwardIntroductionArguments() {
        Bundle bundle = new Bundle();
        if (this.mIntroductionData == null) {
            bundle.putString("name", onLoadTitleText());
        } else {
            bundle.putString("name", this.mIntroductionData.getName());
            bundle.putString(User.KEY_AVATAR, this.mIntroductionData.getPicUrl());
            bundle.putString("desc", this.mIntroductionData.getDetail());
        }
        return bundle;
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.singer.SceneRecommendFragment
    protected void doNextPageAction() {
        if (!EnvironmentUtils.DeviceConfig.isConnected()) {
            PopupsUtils.m6760a((int) R.string.network_unavailable);
            return;
        }
        this.mNeedToast = true;
        getDetailHeader().m5485b();
        this.mOnlineMediaListFragment.requestHomePage();
    }

    public void updatePopularSongResult(MediaItemListResult mediaItemListResult) {
        this.mResult = mediaItemListResult;
        ResultHelper.m5665a(this, mediaItemListResult, new ResultHelper.InterfaceC1510a<MediaItemListResult>() { // from class: com.sds.android.ttpod.fragment.main.findsong.PopularSongFragment.1
            @Override // com.sds.android.ttpod.fragment.main.ResultHelper.InterfaceC1510a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo5564a(MediaItemListResult mediaItemListResult2) {
                PopularSongFragment.this.updatePopularSongList(mediaItemListResult2);
            }
        });
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        updatePopularSongList(this.mResult);
        if (this.mModuleId != 0) {
            CommandCenter.getInstance().execute(new Command(CommandID.GET_POPULAR_SONG_INTRODUCTION, Long.valueOf(this.mModuleId)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePopularSongList(MediaItemListResult mediaItemListResult) {
        if (mediaItemListResult != null) {
            Extra m4514b = mediaItemListResult.m4514b();
            ArrayList<MediaItem> m4517a = mediaItemListResult.m4517a();
            int i = 1;
            String str = null;
            if (m4514b != null) {
                i = m4514b.m8556b();
                Object m8555c = m4514b.m8555c();
                if (m8555c != null) {
                    str = m8555c.toString();
                }
            }
            if (getDetailHeader() != null) {
                getDetailHeader().m5484c();
            }
            updateData(m4517a, Integer.valueOf(i), str);
            removeSecondLoadView();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_POPULAR_SONG_LIST, ReflectUtils.m8375a(getClass(), "updatePopularSongResult", MediaItemListResult.class));
        map.put(CommandID.UPDATE_POPULAR_SONG_INTRODUCTION, ReflectUtils.m8375a(getClass(), "updatePopularSongIntroduction", IntroductionResult.class));
    }

    public void updatePopularSongIntroduction(IntroductionResult introductionResult) {
        if (introductionResult == null || introductionResult.getData() == null || !introductionResult.isSuccess()) {
            PopupsUtils.m6760a((int) R.string.request_detail_fail);
            return;
        }
        IntroductionData introductionData = introductionResult.getData().get(0);
        this.mIntroductionData = introductionData;
        if (this.mIntroductionData != null) {
            getDetailHeader().m5486a(introductionData.getDetail(), introductionData.getPicUrl());
            showSecondLoadView();
            this.mOnlineMediaListFragment.onThemeChanged();
            updatePlayStatus(SupportFactory.m2397a(BaseApplication.getApplication()).m2463m());
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    protected String onLoadTitleText() {
        return TTPodApplication.getApplication().getString(R.string.other_likes);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    public void requestDataList(int i) {
        CommandCenter.getInstance().execute(new Command(CommandID.GET_POPULAR_SONG_LIST, Integer.valueOf(i), this.mVersion));
    }

    public void updateData(ArrayList<MediaItem> arrayList, Integer num, String str) {
        super.updateData(arrayList, num);
        String str2 = this.mVersion;
        if (arrayList != null && !TextUtils.isEmpty(str)) {
            this.mVersion = str;
        }
        if (this.mNeedToast) {
            this.mNeedToast = false;
            if (!TextUtils.isEmpty(str) && arrayList != null) {
                if (str.equals(str2)) {
                    PopupsUtils.m6760a((int) R.string.already_latest);
                    return;
                } else {
                    PopupsUtils.m6760a((int) R.string.update_successful);
                    return;
                }
            }
            PopupsUtils.m6760a((int) R.string.update_failed);
        }
    }
}
