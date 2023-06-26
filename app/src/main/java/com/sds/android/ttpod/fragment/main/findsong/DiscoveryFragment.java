package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.View;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.RecommendPost;
import com.sds.android.cloudapi.ttpod.result.PostResult;

import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p124f.MusiccircleContentType;
import com.sds.android.ttpod.framework.modules.p124f.PostUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class DiscoveryFragment extends RecommendPostListFragment {
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        String string = getArguments().getString("name");
        if (StringUtils.isEmpty(string)) {
            string = getString(R.string.discovery);
        }
        getActionBarController().m7193a((CharSequence) string);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_DISCOVERY_POST_INFO_LIST_BY_ID, ReflectUtils.m8375a(getClass(), "updateCelebrityPosts", PostResult.class));
    }

    public void updateCelebrityPosts(PostResult postResult) {
        if (isAdded()) {
            handleResult(convertRecommendPost(postResult.getDataList()), null, postResult.getCode());
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.RecommendPostListFragment
    protected void onLoadData() {
        long valueOf;
        switch (getLoadStatus()) {
            case FIRST_LOAD:
                valueOf = 0L;
                break;
            case NEXT_PAGE:
                valueOf = Long.valueOf(getDataList().get(getDataCount() - 1).getTime());
                break;
            case RELOAD:
                valueOf = Long.valueOf(System.currentTimeMillis());
                break;
            default:
                throw new IllegalStateException();
        }
        CommandCenter.getInstance().execute(new Command(CommandID.REQUEST_CELEBRITY_POSTS, valueOf));
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.RecommendPostListFragment
    public List<Long> getPlaySongId(RecommendPost recommendPost) {
        if (recommendPost == null) {
            throw new IllegalArgumentException("post should not be null");
        }
        int type = recommendPost.getType();
        OnlineMediaItem mediaItem = recommendPost.getMediaItem();
        ArrayList arrayList = new ArrayList();
        if (MusiccircleContentType.SINGLE_SONG.value() == type && mediaItem != null) {
            addSongId(arrayList, Long.valueOf(mediaItem.getSongId()));
        } else if (MusiccircleContentType.DJ.value() == type) {
            addSongId(arrayList, Long.valueOf(mediaItem.getSongId()));
            addSongIdList(arrayList, recommendPost.getSongList());
        } else if (MusiccircleContentType.SONG_LIST.value() == type) {
            addSongIdList(arrayList, recommendPost.getSongList());
        }
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.RecommendPostListFragment
    public void playMediaItemStatistic(long j, int i) {
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_DISCOVERY_PLAY.getValue(), SPage.PAGE_ONLINE_DISCOVERY.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(j)).append("position", Integer.valueOf(i + 1)).post();
    }

    @Override // com.sds.android.ttpod.fragment.main.findsong.RecommendPostListFragment
    public void startPostDetailStatistic(long j, int i) {
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_DISCOVERY_POST.getValue(), SPage.PAGE_ONLINE_DISCOVERY.getValue(), SPage.PAGE_ONLINE_POST_DETAIL.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(j)).append("position", Integer.valueOf(i + 1)).post();
    }

    private void addSongId(List<Long> list, Long l) {
        if (l != null && l.longValue() > 0) {
            list.add(l);
        }
    }

    private void addSongIdList(List<Long> list, ArrayList<OnlineMediaItem> arrayList) {
        if (arrayList != null) {
            Iterator<OnlineMediaItem> it = arrayList.iterator();
            while (it.hasNext()) {
                addSongId(list, Long.valueOf(it.next().getSongId()));
            }
        }
    }

    private List<RecommendPost> convertRecommendPost(List<Post> list) {
        ArrayList arrayList = new ArrayList();
        for (Post post : list) {
            String nickName = post.getUser().getNickName();
            String tweet = post.getTweet();
            int type = post.getType();
            RecommendPost recommendPost = new RecommendPost(Long.valueOf(post.getId()).longValue(), 0, tweet, "", nickName, post.getSongListName(), tweet, PostUtils.m4023d(post), type);
            recommendPost.setTime(post.getCreateTimeInSecond());
            recommendPost.setMediaItem(post.getMediaItem());
            recommendPost.setSongList(post.getSongList());
            arrayList.add(recommendPost);
        }
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }
}
