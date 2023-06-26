package com.sds.android.ttpod.fragment.main;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.HotWords;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.widget.SimpleGridView;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SearchFragment extends SlidingClosableFragment {
    private static final int DEFAULT_HOTWORD_COUNT = 8;
    private static final String TAG = "SearchFragment";
    private View mHeaderPanel;
    private SimpleGridView mHotwordGridView;
    private StateView mHotwordStateView;
    private View mImageSearchView;
    private View mRecognizerView;
    private View mRootView;
    private Animation mRotateAnimation;
    private View mSearchInputView;
    private SearchResultFragment mSearchResultFragment;
    private String mSearchWord;
    private List<HotWords> mHotwords = new ArrayList();
    private boolean mReloadTheme = true;
    private List<View> mCachedHotwordViews = new ArrayList(8);
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.SearchFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.edittext_search_input /* 2131230775 */:
                case R.id.head_panel /* 2131231531 */:
                case R.id.imageview_search /* 2131231532 */:
                    SearchFragment.this.startSearch("");
                    return;
                case R.id.recognizer /* 2131231530 */:
                default:
                    return;
            }
        }
    };

    /* renamed from: com.sds.android.ttpod.fragment.main.SearchFragment$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1490a {
        void search(String str, String str2);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mRotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        this.mRotateAnimation.setRepeatCount(0);
        this.mSearchWord = getArguments().getString(SearchResultFragment.KEY_SEARCH_WORD);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_main_search, viewGroup, false);
            this.mRecognizerView = this.mRootView.findViewById(R.id.recognizer);
            this.mRecognizerView.setOnClickListener(this.mOnClickListener);
            this.mHeaderPanel = this.mRootView.findViewById(R.id.head_panel);
            this.mHeaderPanel.setOnClickListener(this.mOnClickListener);
            this.mSearchInputView = this.mRootView.findViewById(R.id.edittext_search_input);
            this.mSearchInputView.setOnClickListener(this.mOnClickListener);
            this.mImageSearchView = this.mRootView.findViewById(R.id.imageview_search);
            this.mImageSearchView.setOnClickListener(this.mOnClickListener);
            this.mHotwordStateView = (StateView) this.mRootView.findViewById(R.id.loadingView_hotword);
            this.mHotwordGridView = (SimpleGridView) this.mRootView.findViewById(R.id.hot_words);
            this.mHotwordStateView.setOnRetryRequestListener(new StateView.InterfaceC2247a() { // from class: com.sds.android.ttpod.fragment.main.SearchFragment.2
                @Override // com.sds.android.ttpod.widget.StateView.InterfaceC2247a
                /* renamed from: a */
                public void mo1450a() {
                    SearchFragment.this.requestHotWord();
                }
            });
            initHotwordGridView();
            if (!StringUtils.isEmpty(this.mSearchWord)) {
                startSearch(this.mSearchWord);
            }
        }
        getActionBarController().m7189b(R.string.search);
        return this.mRootView;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        if (!isViewAccessAble()) {
            this.mHotwordStateView = null;
            this.mHotwordGridView = null;
            this.mRootView = null;
            this.mReloadTheme = true;
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        LogUtils.error(TAG, "setUserVisibleHint:" + z);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (isViewAccessAble() && this.mReloadTheme) {
            this.mReloadTheme = false;
            ThemeManager.m3269a(this.mRootView, ThemeElement.BACKGROUND_MASK);
            this.mHotwordStateView.onThemeLoaded();
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onThemeChanged() {
        this.mReloadTheme = true;
        super.onThemeChanged();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public boolean isSupportOfflineMode() {
        return true;
    }

    private void initHotwordGridView() {
        this.mHotwordGridView.removeAllViews();
        this.mHotwordGridView.setNumColumns(3);
        LayoutInflater from = LayoutInflater.from(getActivity());
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.SearchFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str = (String) view.getTag();
                if (!StringUtils.isEmpty(str)) {
                    SearchFragment.this.startSearch(str);
                }
            }
        };
        for (int i = 0; i < 8; i++) {
            View generateHotWordItemView = generateHotWordItemView(from);
            this.mCachedHotwordViews.add(generateHotWordItemView);
            this.mHotwordGridView.addView(generateHotWordItemView);
            generateHotWordItemView.setOnClickListener(onClickListener);
        }
        View generateHotWordItemView2 = generateHotWordItemView(from);
        ThemeManager.m3270a(generateHotWordItemView2, new ColorDrawable(Color.parseColor("#148bbd")));
        final ImageView imageView = (ImageView) generateHotWordItemView2.findViewById(R.id.channel_img);
        generateHotWordItemView2.findViewById(R.id.online_search_item_shadow).setVisibility(View.GONE);
        imageView.setImageResource(R.drawable.img_imageview_hotword_refresh);
        generateHotWordItemView2.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.SearchFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                //SearchStatistic.m4928k();
                imageView.startAnimation(SearchFragment.this.mRotateAnimation);
                SearchFragment.this.refreshDisplayHotWord();
            }
        });
        this.mHotwordGridView.addView(generateHotWordItemView2);
    }

    private View generateHotWordItemView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.online_search_hotword_item, (ViewGroup) null, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_SEARCH_HOT_WORDS_FINISHED, ReflectUtils.m8375a(SearchFragment.class, "updateSearchHotWords", List.class));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        requestHotWord();
    }

    public void updateSearchHotWords(List list) {
        if (isViewAccessAble()) {
            if (list.size() >= 8) {
                if (this.mHotwords.size() < 8) {
                    this.mHotwords = list;
                    refreshDisplayHotWord();
                }
                this.mHotwords = list;
                this.mHotwordStateView.setState(StateView.EnumC2248b.SUCCESS);
                Cache.getInstance().m3199a(list);
            } else if (this.mHotwords.size() < 8) {
                this.mHotwordStateView.setState(StateView.EnumC2248b.FAILED);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshDisplayHotWord() {
        Collections.shuffle(this.mHotwords);
        int size = this.mCachedHotwordViews.size();
        for (int i = 0; i < size; i++) {
            setHotwordItem(i, this.mCachedHotwordViews.get(i));
        }
    }

    private void setHotwordItem(int i, View view) {
        TextView textView = (TextView) view.findViewById(R.id.channel_name);
        HotWords hotWords = this.mHotwords.get(i);
        textView.setText(hotWords.getWord());
        view.setTag(hotWords.getWord());
        ImageCacheUtils.m4751a((ImageView) view.findViewById(R.id.channel_img), hotWords.getPictureUrl(), DisplayUtils.m7225c() / 4, DisplayUtils.m7225c() / 4, (int) R.drawable.img_imageview_hotword_default_icon, (int) R.anim.scale_in);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestHotWord() {
        if (this.mHotwords.size() >= 8) {
            this.mHotwordStateView.setState(StateView.EnumC2248b.SUCCESS);
            return;
        }
        LogUtils.debug(TAG, "request hotwords");
        List<HotWords> m3157j = Cache.getInstance().m3157j();
        if (m3157j.size() >= 8) {
            this.mHotwords = m3157j;
            refreshDisplayHotWord();
            this.mHotwordStateView.setState(StateView.EnumC2248b.SUCCESS);
        } else {
            this.mHotwordStateView.setState(StateView.EnumC2248b.LOADING);
        }
        CommandCenter.getInstance().execute(new Command(CommandID.START_SEARCH_HOT_WORDS, new Object[0]));
    }

    public void startSearch(String str) {
        LogUtils.debug(TAG, "startSearch word=" + str);
        String replace = str.trim().replace("_", "");
        if (this.mSearchResultFragment != null) {
            this.mSearchResultFragment.finish();
        }
        this.mSearchResultFragment = new SearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SearchResultFragment.KEY_SEARCH_WORD, replace);
        this.mSearchResultFragment.setArguments(bundle);
        launchFragment(this.mSearchResultFragment);
    }
}
