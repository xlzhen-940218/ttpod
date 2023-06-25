package com.sds.android.ttpod.fragment.search;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.search.OnlineSearchEntryActivity;
import com.sds.android.ttpod.activities.web.WebActivity;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.fragment.WebFragment;
import com.sds.android.ttpod.fragment.main.SearchFragment;
import com.sds.android.ttpod.fragment.main.SearchResultFragment;
import com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.media.mediastore.MediaItem;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SongSearchFragment extends OnlineMediaListFragment implements SearchFragment.InterfaceC1490a, SearchResultFragment.InterfaceC1501a {
    private static final int DEFAULT_SEARCH_PAGE = 1;
    private static final int DEFAULT_SEARCH_SIZE = 50;
    private static final String TAG = "SongSearchFragment";
    private String mUserInput;
    private String mWord;
    private boolean mIsSearching = false;
    private boolean mIsNewSearch = false;

    @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void onReloadData() {
        super.onReloadData();
        requestSongList(this.mWord);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setModule("search");
        //ListStatistic.m5211a(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment
    public View onCreateNodataView(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.search_result_nodata, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.textview_load_failed)).setText(R.string.song_search_nodata);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_SEARCH_SONG_FINISHED, ReflectUtils.m8375a(getClass(), "updateSongSearchFinished", Integer.class, Integer.class, List.class, String.class));
    }

    private void thirdSearch() {
        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.setData(Uri.parse(SearchResultFragment.THIRD_SEACH_URL + this.mWord + "&v=" + EnvironmentUtils.C0603b.m8491c()));
        intent.putExtra(WebFragment.EXTRA_TITLE, getString(R.string.online_search_third_part_title));
        intent.putExtra(WebFragment.EXTRA_HINT_BANNER_SHOW, true);
        startActivity(intent);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        if (getArguments() != null) {
            this.mWord = getArguments().getString(SearchResultFragment.KEY_SEARCH_WORD);
        }
    }

    public void requestSongList(String str, int i) {
        LogUtils.debug(TAG, "requestSongList word: " + str + ",page: " + i + ",mUserInput:" + this.mUserInput);
        if (!this.mIsSearching && !StringUtils.isEmpty(str)) {
            String trim = str.trim();
            this.mIsSearching = true;
            this.mWord = trim;
            long currentTimeMillis = System.currentTimeMillis();
            if (i == 1) {
                show();
                updateStateViews(null);
            }
            CommandCenter.getInstance().m4606a(new Command(CommandID.START_SEARCH_SONG, this.mWord, Integer.valueOf(i), 50, this.mUserInput));
            LogUtils.debug(TAG, "requestSongList " + trim + "cost " + (System.currentTimeMillis() - currentTimeMillis));
        }
    }

    public void requestSongList(String str) {
        clearPage();
        requestSongList(str, 1);
    }

    public void updateSongSearchFinished(Integer num, Integer num2, List list, String str) {
        LogUtils.debug(TAG, "loadPictureAfterSearchFinished " + list.size() + ", searchWord: " + str);
        if (isAdded() && StringUtils.m8344a(this.mWord, str)) {
            updateMediaList(num, num2, list);
            if (this.mIsNewSearch) {
                this.mListView.setSelection(0);
                this.mIsNewSearch = false;
            }
            this.mIsSearching = false;
            if (list.size() == 0 && EnvironmentUtils.C0604c.m8474e()) {
                thirdSearch();
            }
            //SearchStatistic.m4945a(num, getOrigin(), this.mWord);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.SearchFragment.InterfaceC1490a
    public void search(String str, String str2) {
        this.mUserInput = str2;
        this.mIsNewSearch = true;
        requestSongList(str);
    }

    @Override // com.sds.android.ttpod.fragment.main.SearchResultFragment.InterfaceC1501a
    public void onFragmentSelected(String str, String str2) {
        this.mUserInput = str2;
        if (!StringUtils.isEmpty(str) && !StringUtils.m8344a(str, this.mWord)) {
            requestSongList(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void onMediaItemClicked(MediaItem mediaItem, int i) {
        super.onMediaItemClicked(mediaItem, i);
        //StatisticUtils.m4907a("search", "listen", getOrigin(), 0L, //OnlineMediaStatistic.m5029f(), this.mWord, //SearchStatistic.m4950a());
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_SEARCH_SONG_ITEM.getValue(), SPage.PAGE_SEARCH_SINGLE_SONG.getValue(), 0).append("song_id", mediaItem.getSongID()).append(OnlineSearchEntryActivity.KEY_THIRD_ONLINE_SEARCH_KEYWORD, this.mWord).append("position", Integer.valueOf(//OnlineMediaStatistic.m5029f())).post();
    }

    @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    protected void showRightContextMenu(MediaItem mediaItem) {
        if (getOrigin() != null) {
            //StatisticUtils.m4907a(getModule(), "menu", getOrigin(), 0L, //OnlineMediaStatistic.m5029f(), //SearchStatistic.m4938c(), //SearchStatistic.m4950a());
        }
        new DownloadMenuHandler(getActivity()).m6927a(mediaItem, getDownloadOrigin());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
    public void onFavoriteChanged(MediaItem mediaItem, boolean z) {
        super.onFavoriteChanged(mediaItem, z);
        //SearchStatistic.m4943a(z);
        //ListStatistic.m5206a(mediaItem.getSongID().longValue(), //OnlineMediaStatistic.m5029f(), z);
    }

    @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment
    protected boolean onListStatistic() {
        return true;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment
    public String getListenOrigin() {
        String listenOrigin = super.getListenOrigin();
        return StringUtils.isEmpty(this.mWord) ? listenOrigin : listenOrigin + "_" + this.mWord;
    }

    @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment
    public String getDownloadOrigin() {
        String origin = getOrigin();
        return StringUtils.isEmpty(this.mWord) ? origin : origin + "_" + this.mWord;
    }
}
