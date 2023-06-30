package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.RecommendPost;
import com.sds.android.cloudapi.ttpod.result.RecommendPostResult;

import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.ListUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class PrivateCustomFragment extends RecommendPostListFragment {
    private static final int FIRST_PAGE = 1;
    private int mCurrentPage = 1;

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        String string = getArguments().getString("name");
        if (StringUtils.isEmpty(string)) {
            string = getString(R.string.private_custom);
        }
        getActionBarController().m7193a((CharSequence) string);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_PRIVATE_CUSTOM_POSTS, ReflectUtils.loadMethod(getClass(), "updatePrivateCustomResult", RecommendPostResult.class));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.RecommendPostListFragment
    public void startPostDetailStatistic(long j, int i) {
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_LIKE_POST.getValue(), SPage.PAGE_ONLINE_PERSONALIZED_RECOMMEND.getValue(), SPage.PAGE_ONLINE_POST_DETAIL.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(j)).append("position", Integer.valueOf(i + 1)).post();
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.RecommendPostListFragment
    public void playMediaItemStatistic(long j, int i) {
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_LIKE_PLAY.getValue(), SPage.PAGE_ONLINE_PERSONALIZED_RECOMMEND.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(j)).append("position", Integer.valueOf(i + 1)).post();
    }

    private void requestPostListResultStatistic(List<RecommendPost> list) {
        if (!ListUtils.m4718a(list)) {
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < list.size()) {
                    RecommendPost recommendPost = list.get(i2);
                    if (recommendPost != null) {
                        if (i2 > 0) {
                            stringBuffer.append(",");
                        }
                        stringBuffer.append(recommendPost.getId());
                    }
                    i = i2 + 1;
                } else {
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_RESULT_LIKE_POST.getValue(), SPage.PAGE_ONLINE_PERSONALIZED_RECOMMEND.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, stringBuffer.toString());
                    return;
                }
            }
        }
    }

    public void updatePrivateCustomResult(RecommendPostResult recommendPostResult) {
        if (isAdded() && recommendPostResult != null) {
            requestPostListResultStatistic(recommendPostResult.getDataList());
            handleResult(recommendPostResult.getDataList(), null, recommendPostResult.getCode());
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.RecommendPostListFragment
    protected void onLoadData() {
        if (getLoadStatus() == RecommendPostListFragment.EnumC1567a.NEXT_PAGE) {
            this.mCurrentPage++;
        } else {
            this.mCurrentPage = 1;
        }
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_PRIVATE_CUSTOM_POSTS, Integer.valueOf(this.mCurrentPage), 10));
    }
}
