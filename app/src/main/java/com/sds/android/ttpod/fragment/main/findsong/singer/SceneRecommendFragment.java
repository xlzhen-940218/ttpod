package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.View;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.MusiccircleFooter;
import com.sds.android.ttpod.fragment.main.findsong.SceneRecommendHeader;
import com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment;
import com.sds.android.ttpod.fragment.musiccircle.IntroductionFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.widget.StateView;

/* loaded from: classes.dex */
public abstract class SceneRecommendFragment extends ImageHeaderMusicListFragment {
    private SceneRecommendHeader mDetailHeader;
    private MusiccircleFooter mUpdateListLoadView;
    private View.OnClickListener mViewOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.singer.SceneRecommendFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageview_header_play /* 2131232090 */:
                    if (!SceneRecommendFragment.this.isSongListNotLoaded()) {
                        SceneRecommendFragment.this.replayPlayMediaRepeat(0L);
                        SceneRecommendFragment.this.postButtonClickStatistic(SAction.ACTION_CLICK_ONLINE_SONG_LIST_PLAY_ALL);
                        return;
                    }
                    return;
                case R.id.textview_header_detail /* 2131232091 */:
                    SceneRecommendFragment.this.launchFragment((BaseFragment) Fragment.instantiate(SceneRecommendFragment.this.getActivity(), IntroductionFragment.class.getName(), SceneRecommendFragment.this.buildForwardIntroductionArguments()));
                    SceneRecommendFragment.this.postForwardIntroductionStatistic();
                    return;
                case R.id.text_next_page /* 2131232097 */:
                    SceneRecommendFragment.this.doNextPageAction();
                    SceneRecommendFragment.this.postButtonClickStatistic(SAction.ACTION_CLICK_ONLINE_SONG_LIST_REPLACE_ALL);
                    return;
                case R.id.text_download_all /* 2131232100 */:
                    if (!SceneRecommendFragment.this.isSongListNotLoaded()) {
                        SceneRecommendFragment.this.downloadAllMediaList();
                        SceneRecommendFragment.this.postButtonClickStatistic(SAction.ACTION_CLICK_ONLINE_SONG_LIST_DOWNLOAD_ALL);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    protected abstract Bundle buildForwardIntroductionArguments();

    protected abstract void postButtonClickStatistic(SAction sAction);

    protected abstract void postForwardIntroductionStatistic();

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment
    protected void setupListHeader() {
        this.mDetailHeader = new SceneRecommendHeader(getActivity(), getLayoutInflater(null), this.mOnlineMediaListFragment.getListView());
        this.mOnlineMediaListFragment.getListView().addHeaderView(this.mDetailHeader.m5490a());
        this.mDetailHeader.m5489a(this.mViewOnClickListener);
        this.mUpdateListLoadView = new MusiccircleFooter(getLayoutInflater(null), null);
        this.mOnlineMediaListFragment.getListView().addFooterView(this.mUpdateListLoadView.m7934a());
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.base.ImageHeaderMusicListFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mDetailHeader != null) {
            this.mDetailHeader.m5483d();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showSecondLoadView() {
        this.mOnlineMediaListFragment.getStateView().setState(StateView.EnumC2248b.SUCCESS);
        this.mUpdateListLoadView.m7932a(false, 0, getString(R.string.loading));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeSecondLoadView() {
        this.mUpdateListLoadView.m7932a(false, 8, "");
        this.mOnlineMediaListFragment.getListView().removeFooterView(this.mUpdateListLoadView.m7934a());
    }

    protected void doNextPageAction() {
        this.mDetailHeader.m5485b();
        this.mOnlineMediaListFragment.repeatPageRequestData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void hideListFootLoadView() {
        this.mOnlineMediaListFragment.getListView().setOnScrollListener(null);
        this.mOnlineMediaListFragment.setNeedShowFootAnimation(false);
    }

    public SceneRecommendHeader getDetailHeader() {
        return this.mDetailHeader;
    }

    protected String getRequestId() {
        return null;
    }
}
