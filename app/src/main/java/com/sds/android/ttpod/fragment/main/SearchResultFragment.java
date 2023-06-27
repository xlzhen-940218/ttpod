package com.sds.android.ttpod.fragment.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.Billboards;
import com.sds.android.cloudapi.ttpod.data.PlaylistResult;
import com.sds.android.cloudapi.ttpod.data.SearchType;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy;
import com.sds.android.cloudapi.ttpod.result.AlbumItemsResult;
import com.sds.android.cloudapi.ttpod.result.SearchTypeResult;

import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.adapter.p076g.SearchHistoryAdapter;
import com.sds.android.ttpod.common.p083b.DisplayUtils;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p089e.SearchHistory;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment;
import com.sds.android.ttpod.fragment.search.AlbumSearchFragment;
import com.sds.android.ttpod.fragment.search.PlaylistSearchFragment;
import com.sds.android.ttpod.fragment.search.SongSearchFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.Pager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.AutoCompleteView;
import com.sds.android.ttpod.widget.HotwordView;
import com.sds.android.ttpod.widget.SlidingTabHost;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes.dex */
public class SearchResultFragment extends SlidingClosableFragment implements ViewPager.OnPageChangeListener {
    private static final int BILLBOARD_MAX_SIZE = 20;
    private static final int DEFAULT_SEARCH_PAGE = 1;
    private static final long ID_FRAGMENT_ALBUM = 8;
    private static final long ID_FRAGMENT_SONG = 7;
    private static final long ID_FRAGMENT_SONG_LIST = 9;
    public static final String KEY_SEARCH_WORD = "key_search_word";
    private static final int SHOW_INPUT_METHOD_DELAY = 500;
    private static final String TAG = "SearchResultFragment";
    private static final int TEXT_PADDING_SIZE = DisplayUtils.m7229a(8);
    public static final String THIRD_SEACH_URL = "http://so.bq.yymommy.com/appsearch/#a=ss&q=";
    private static boolean sGotBillboard;
    private AlbumSearchFragment mAlbumSearchFragment;
    private AutoCompleteView mAssociateView;
    private Button mClearHistoryButton;
    private IconTextView mClearImageView;
    private int mCurrentItem;
    private View mHeaderView;
    private SearchHistoryAdapter mHistoryListAdapter;
    private ListView mHistoryListView;
    private HotwordView mHotwordView;
    private EditText mInputEditText;
    private InputMethodManager mInputMethodManager;
    private String mModule;
    private SlidingTabFragmentPagerAdapter mPagerAdapter;
    private View mRootView;
    private ActionBarController.C1070a mSearchAction;
    private SearchHistory mSearchHistory;
    private View mSearchInputLayout;
    private SlidingTabHost mSlidingTabHost;
    private PlaylistSearchFragment mSongListSearchFragment;
    private SongSearchFragment mSongSearchFragment;
    private TextView mTvHistoryHint;
    private TextView mTvHotwordHint;
    private String mUserInput;
    private ViewPager mViewPager;
    private String mWord;
    private boolean mIsSearching = false;
    private boolean mIsShowAssociate = false;
    private Pager mPager = new Pager();
    private String mOrigin = "search-hotword";
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.SearchResultFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageview_search_clear /* 2131230776 */:
                    SearchResultFragment.this.mInputEditText.setText("");
                    SearchResultFragment.this.mClearImageView.setVisibility(View.GONE);
                    return;
                case R.id.menu_view /* 2131231121 */:
                    new DownloadMenuHandler(SearchResultFragment.this.getActivity()).m6927a((MediaItem) view.getTag()
                            ,null);
                    return;
                case R.id.button_search_clear_history /* 2131232071 */:
                    SearchResultFragment.this.mSearchHistory.m4100a();
                    SearchResultFragment.this.showHistory();
                    return;
                default:
                    return;
            }
        }
    };
    private TextWatcher mTextWatcher = new TextWatcher() { // from class: com.sds.android.ttpod.fragment.main.SearchResultFragment.3
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (StringUtils.isEmpty(charSequence.toString())) {
                SearchResultFragment.this.mClearImageView.setVisibility(View.GONE);
                if (SearchResultFragment.this.mAssociateView != null) {
                    SearchResultFragment.this.mAssociateView.m1431c();
                    return;
                }
                return;
            }
            SearchResultFragment.this.mClearImageView.setVisibility(View.VISIBLE);
            SearchResultFragment.this.searchAssociate(charSequence.toString());
            if (SearchResultFragment.this.mAssociateView != null) {
                SearchResultFragment.this.mAssociateView.m1433b();
            }
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }
    };
    private TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() { // from class: com.sds.android.ttpod.fragment.main.SearchResultFragment.4
        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 3 || i == 2 || i == 0 || i == 4) {
                SearchResultFragment.this.searchKeyword();
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_SEARCH_BUTTON.getValue(), SPage.PAGE_SEARCH_HOME.getValue(), 0).append("keyword", SearchResultFragment.this.mWord).post();
                return true;
            }
            return false;
        }
    };
    private HotwordView.InterfaceC2194a mOnHotwordClickListener = new HotwordView.InterfaceC2194a() { // from class: com.sds.android.ttpod.fragment.main.SearchResultFragment.10
        @Override // com.sds.android.ttpod.widget.HotwordView.InterfaceC2194a
        /* renamed from: a */
        public void mo1726a(String str) {
            SearchResultFragment.this.searchHotword(str);
        }
    };

    /* renamed from: com.sds.android.ttpod.fragment.main.SearchResultFragment$a */
    /* loaded from: classes.dex */
    public interface InterfaceC1501a {
        void onFragmentSelected(String str, String str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchKeyword() {
        updateOrigin("search-button");
        updateAlbumOriginPrefix("search-button");
        updateSongListOriginPrefix("search-button");
        this.mUserInput = null;
        requestSongList(this.mInputEditText.getText().toString());
        this.mAssociateView.m1431c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchHotword(String str) {
        updateOrigin("search-hotword");
        updateAlbumOriginPrefix("search-hotword");
        updateSongListOriginPrefix("search-hotword");
        this.mUserInput = null;
        requestSongList(str);
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_HOT_SEARCH_WORD.getValue(), SPage.PAGE_SEARCH_HOME.getValue(), 0).append("keyword", this.mWord).post();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mWord = getArguments().getString(KEY_SEARCH_WORD);
            //SearchStatistic.m4937c(this.mWord);
        }
        String uuid = UUID.randomUUID().toString();
        //SearchStatistic.m4944a(uuid);
        //ListStatistic.m5202b(uuid);
        //ListStatistic.m5205a(this.mWord);
        //ListStatistic.m5211a(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        super.onBackPressed();
        if (this.mAssociateView != null) {
            this.mAssociateView.m1431c();
        }
        hideSoftInputFromWindow();
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, androidx.fragment.app.Fragment
    public void onStop() {
        PopupsUtils.m6723a(this.mAssociateView);
        super.onStop();
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.m7428a();
        }
        this.mAssociateView.m1437a((AutoCompleteView.InterfaceC2253a) null);
        super.onDestroyView();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        //ListStatistic.m5202b(null);
        //ListStatistic.m5205a((String) null);
        //ListStatistic.m5211a(-1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ActionBarController actionBarController = getActionBarController();
        this.mSearchAction = actionBarController.m7199a((Drawable) null);
        this.mSearchAction.m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.fragment.main.SearchResultFragment.5
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                SearchResultFragment.this.searchKeyword();
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_SEARCH_BUTTON.getValue(), SPage.PAGE_SEARCH_HOME.getValue(), 0).append("keyword", SearchResultFragment.this.mWord).post();
            }
        });
        this.mSearchInputLayout = actionBarController.m7201a();
        this.mRootView = layoutInflater.inflate(R.layout.fragment_search_result, viewGroup, false);
        this.mClearHistoryButton = (Button) layoutInflater.inflate(R.layout.search_result_clear_history, (ViewGroup) null);
        this.mInputEditText = (EditText) this.mSearchInputLayout.findViewById(R.id.edittext_search_input);
        this.mClearImageView = (IconTextView) this.mSearchInputLayout.findViewById(R.id.imageview_search_clear);
        this.mClearImageView.setVisibility(View.GONE);
        this.mClearImageView.setOnClickListener(this.mOnClickListener);
        this.mHistoryListView = (ListView) this.mRootView.findViewById(R.id.listview_search_history);
        this.mHeaderView = layoutInflater.inflate(R.layout.fragment_search_result_header, (ViewGroup) null);
        this.mTvHotwordHint = (TextView) this.mHeaderView.findViewById(R.id.tv_hotword_hint);
        this.mTvHistoryHint = (TextView) this.mHeaderView.findViewById(R.id.tv_history_hint);
        this.mHotwordView = (HotwordView) this.mHeaderView.findViewById(R.id.hotwordView);
        flushHotwordView();
        this.mHotwordView.setListener(this.mOnHotwordClickListener);
        this.mHistoryListView.addHeaderView(this.mHeaderView, null, false);
        addClearHistoryButton();
        this.mSlidingTabHost = (SlidingTabHost) this.mRootView.findViewById(R.id.slidingtabhost_localmusic);
        this.mSlidingTabHost.setTabLayoutAverageSpace(true);
        this.mViewPager = (ViewPager) this.mRootView.findViewById(R.id.pager_content);
        this.mViewPager.setCurrentItem(this.mCurrentItem);
        this.mPagerAdapter = new SlidingTabFragmentPagerAdapter(getActivity(), getChildFragmentManager(), buildFragmentBinders());
        updateOrigin("search-hotword");
        updateAlbumOriginPrefix("search-hotword");
        updateSongListOriginPrefix("search-hotword");
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mSlidingTabHost.setViewPager(this.mViewPager);
        this.mSlidingTabHost.setOnPageChangeListener(this);
        this.mViewPager.setOffscreenPageLimit(this.mPagerAdapter.getCount());
        setCurrentPosition(0);
        this.mAssociateView = new AutoCompleteView(layoutInflater.getContext());
        this.mAssociateView.m1439a(getResources().getDrawable(R.color.listview_divider_line));
        this.mAssociateView.m1441a(DisplayUtils.m7229a(1));
        this.mAssociateView.m1440a(0, 0);
        this.mAssociateView.m1432b(TEXT_PADDING_SIZE);
        this.mAssociateView.m1437a(new AutoCompleteView.InterfaceC2253a() { // from class: com.sds.android.ttpod.fragment.main.SearchResultFragment.6
            @Override // com.sds.android.ttpod.widget.AutoCompleteView.InterfaceC2253a
            /* renamed from: a */
            public void mo1426a(String str) {
                SearchResultFragment.this.updateOrigin("search-associativeWord");
                SearchResultFragment.this.updateAlbumOriginPrefix("search-associativeWord");
                SearchResultFragment.this.updateSongListOriginPrefix("search-associativeWord");
                SearchResultFragment.this.requestSongList(str);
                SearchResultFragment.this.mAssociateView.m1431c();
                //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_RELATED_WORD.getValue(), SPage.PAGE_SEARCH_HOME.getValue(), 0).append("keyword", SearchResultFragment.this.mWord).post();
            }
        });
        return this.mRootView;
    }

    private void flushHotwordView() {
        List<Billboards> m3140x = Cache.getInstance().m3140x();
        this.mTvHotwordHint.setVisibility((m3140x == null || m3140x.isEmpty()) ? View.GONE : View.VISIBLE);
        this.mHotwordView.setRows(2);
        this.mHotwordView.setContent(m3140x);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mPager.m4665b(VIPPolicy.Entry.MAX_LIMIT);
        this.mSearchHistory = new SearchHistory();
        this.mInputEditText.addTextChangedListener(this.mTextWatcher);
        this.mInputEditText.setOnEditorActionListener(this.mOnEditorActionListener);
        this.mHistoryListAdapter = new SearchHistoryAdapter(this.mSearchHistory);
        this.mHistoryListAdapter.m7390a(new SearchHistoryAdapter.InterfaceC1006a() { // from class: com.sds.android.ttpod.fragment.main.SearchResultFragment.7
            @Override // com.sds.android.ttpod.adapter.p076g.SearchHistoryAdapter.InterfaceC1006a
            /* renamed from: a */
            public void mo5679a(String str) {
                SearchResultFragment.this.mSearchHistory.m4091b( str);
                SearchResultFragment.this.showHistory();
            }
        });
        flushHistoryHintView();
        this.mHistoryListView.setAdapter((ListAdapter) this.mHistoryListAdapter);
        this.mHistoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.fragment.main.SearchResultFragment.8
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view2, int i, long j) {
                int m8266a = ListViewUtils.m8266a(SearchResultFragment.this.mHistoryListView.getHeaderViewsCount(), i, SearchResultFragment.this.mHistoryListAdapter.getCount());
                if (m8266a >= 0) {
                    SearchResultFragment.this.updateOrigin("search-historyWord");
                    SearchResultFragment.this.updateAlbumOriginPrefix("search-historyWord");
                    SearchResultFragment.this.updateSongListOriginPrefix("search-historyWord");
                    SearchResultFragment.this.mUserInput = null;
                    SearchResultFragment.this.requestSongList(SearchResultFragment.this.mHistoryListAdapter.getItem(m8266a));
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_HISTORY_WORD.getValue(), SPage.PAGE_SEARCH_HOME.getValue(), 0).append("keyword", SearchResultFragment.this.mWord).post();
                }
            }
        });
        this.mClearHistoryButton.setOnClickListener(this.mOnClickListener);
        this.mSlidingTabHost.setTabLayoutAverageSpace(true);
        this.mSlidingTabHost.setShouldExpand(true);
        this.mSlidingTabHost.setBackgroundResource(R.color.dialog_background);
        this.mSlidingTabHost.setTextColorResource(R.color.xml_local_media_search_tab_text);
        this.mSlidingTabHost.setIndicatorColorResource(R.color.listview_item_title_selected);
        getSongFragment().setOnNextPageListener(new OnlineMediaListFragment.InterfaceC1668c() { // from class: com.sds.android.ttpod.fragment.main.SearchResultFragment.9
            @Override // com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.InterfaceC1668c
            /* renamed from: a */
            public void mo5452a(int i) {
                SearchResultFragment.this.getSongFragment().requestSongList(SearchResultFragment.this.mWord, i);
            }
        });
        if (StringUtils.isEmpty(this.mWord)) {
            showSearchResult(false);
            showHistory();
        }
        if (!sGotBillboard) {
            sGotBillboard = true;
            CommandCenter.getInstance().execute(new Command(CommandID.START_SEARCH_BILLBOARD, 20));
        }
        //new SUserEvent("PAGE_CLICK", SAction.ACTION_ACCESS_SEARCH_PAGE.getValue(), 0, SPage.PAGE_SEARCH_HOME.getValue()).post();
    }

    private void flushHistoryHintView() {
        this.mTvHistoryHint.setVisibility(this.mHistoryListAdapter.isEmpty() ? View.GONE : View.VISIBLE);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeManager.m3269a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        ThemeManager.m3269a(this.mHistoryListView, ThemeElement.BACKGROUND_MASK);
        ThemeManager.m3269a(this.mHistoryListView, ThemeElement.COMMON_SEPARATOR);
        ThemeManager.m3269a(this.mSlidingTabHost, ThemeElement.SUB_BAR_BACKGROUND);
        ThemeManager.m3269a(this.mHeaderView, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        ThemeManager.m3269a(this.mClearHistoryButton, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        ThemeManager.m3269a(this.mClearHistoryButton, ThemeElement.SONG_LIST_ITEM_TEXT);
        ThemeManager.m3269a(this.mTvHotwordHint, ThemeElement.TILE_SUB_TEXT);
        ThemeManager.m3269a(this.mTvHistoryHint, ThemeElement.TILE_SUB_TEXT);
        ThemeUtils.m8168a(this.mSearchAction, ThemeElement.TOP_BAR_SEARCH_IMAGE, (int) R.string.icon_search, ThemeElement.TOP_BAR_TEXT);
        ThemeUtils.m8166a(this.mSlidingTabHost);
        if (this.mHistoryListAdapter != null) {
            flushHistoryHintView();
            this.mHistoryListAdapter.notifyDataSetChanged();
        }
        this.mHotwordView.m1732a();
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_SEARCH_SONG_FINISHED, ReflectUtils.m8375a(cls, "updateSongSearchFinished", Integer.class, Integer.class, List.class, String.class));
        map.put(CommandID.UPDATE_SEARCH_ASSOCIATE_FINISHED, ReflectUtils.m8375a(cls, "updateAssociateSearchFinished", List.class));
        map.put(CommandID.UPDATE_SEARCH_ALBUM_FINISHED, ReflectUtils.m8375a(cls, "updateSearchAlbum", AlbumItemsResult.class));
        map.put(CommandID.UPDATE_SEARCH_PLAY_LIST_RESULT, ReflectUtils.m8375a(cls, "updatePlaylistResult", PlaylistResult.class));
        map.put(CommandID.UPDATE_SEARCH_BILLBOARD_FINISHED, ReflectUtils.m8375a(cls, "updateBillboardSearchFinished", List.class));
        map.put(CommandID.UPDATE_SEARCH_TYPES, ReflectUtils.m8375a(cls, "updateSearchTypes", SearchTypeResult.class));
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadFinished() {
        super.onLoadFinished();
        if (StringUtils.isEmpty(this.mWord)) {
            this.mInputEditText.setText(this.mWord);
        } else {
            this.mSearchHistory.m4095a( this.mWord);
            this.mInputEditText.removeTextChangedListener(this.mTextWatcher);
            this.mInputEditText.setText(this.mWord);
            this.mInputEditText.addTextChangedListener(this.mTextWatcher);
            requestSongList(this.mWord);
        }
        this.mHistoryListAdapter.notifyDataSetChanged();
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        hideSoftInputFromWindow();
    }

    public void updateSongSearchFinished(Integer num, Integer num2, List list, String str) {
        LogUtils.debug(TAG, "loadPictureAfterSearchFinished " + list.size());
        if (isAdded()) {
            this.mIsSearching = false;
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    public void updateSearchAlbum(AlbumItemsResult albumItemsResult) {
        this.mIsSearching = false;
    }

    public void updatePlaylistResult(PlaylistResult playlistResult) {
        this.mIsSearching = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOrigin(String str) {
        this.mOrigin = str;
        getSongFragment().setOrigin(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAlbumOriginPrefix(String str) {
        //SearchStatistic.m4939b(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSongListOriginPrefix(String str) {
        //SearchStatistic.m4939b(str);
    }

    public void updateAssociateSearchFinished(List list) {
        if (isAdded()) {
            showAssociate(list);
        }
    }

    public void updateBillboardSearchFinished(List list) {
        if (list != null && !list.isEmpty()) {
            Cache.getInstance().m3178c(list);
            flushHotwordView();
            return;
        }
        sGotBillboard = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showHistory() {
        showSearchResult(false);
        this.mHistoryListView.setVisibility(View.VISIBLE);
        flushHistoryHintView();
        this.mHistoryListAdapter.notifyDataSetChanged();
        this.mHistoryListView.setSelection(0);
        if (this.mSearchHistory.m4089c() > 0) {
            addClearHistoryButton();
        } else {
            removeClearHistoryButton();
        }
        this.mInputEditText.requestFocus();
        this.mInputEditText.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.fragment.main.SearchResultFragment.2
            @Override // java.lang.Runnable
            public void run() {
                SearchResultFragment.this.showSoftInputFromWindow();
            }
        }, 500L);
    }

    @SuppressLint("WrongConstant")
    private void showSearchResult(boolean z) {
        int i = z ? 0 : 4;
        this.mSlidingTabHost.setVisibility(i);
        this.mViewPager.setVisibility(i);
    }

    private void addClearHistoryButton() {
        if (this.mHistoryListView.getFooterViewsCount() == 0) {
            this.mHistoryListView.addFooterView(this.mClearHistoryButton);
        }
    }

    private void removeClearHistoryButton() {
        this.mHistoryListView.removeFooterView(this.mClearHistoryButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchAssociate(String str) {
        if (!this.mIsSearching) {
            this.mUserInput = str;
            LogUtils.debug(TAG, "set mUserInput=" + str);
            this.mIsShowAssociate = true;
            CommandCenter.getInstance().execute(new Command(CommandID.START_SEARCH_ASSOCIATE, str));
        }
    }

    private void showAssociate(List<String> list) {
        if (!this.mIsSearching && this.mIsShowAssociate && !StringUtils.isEmpty(this.mInputEditText.getText().toString())) {
            boolean z = (list == null || list.isEmpty()) ? false : true;
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(z);
            objArr[1] = Integer.valueOf(list != null ? list.size() : 0);
            LogUtils.debug(TAG, "showAssociate isEmpty=%b %d", objArr);
            if (z) {
                this.mAssociateView.m1434a(list, false);
                if (!this.mAssociateView.isShowing()) {
                    this.mAssociateView.m1438a(this.mSearchInputLayout);
                    return;
                }
                return;
            }
            this.mAssociateView.m1431c();
        }
    }

    private void requestSongList(String str, int i) {
        LogUtils.debug(TAG, "requestSongList word: " + str + ",page: " + i);
        String trim = str.trim();
        if (!this.mIsSearching && !StringUtils.isEmpty(trim)) {
            this.mIsSearching = true;
            this.mIsShowAssociate = false;
            this.mWord = trim;
            //SearchStatistic.m4937c(trim);
            String uuid = UUID.randomUUID().toString();
            //SearchStatistic.m4944a(uuid);
            //ListStatistic.m5202b(uuid);
            this.mPager.m4663c(i);
            this.mInputEditText.setText(trim);
            this.mInputEditText.setSelection(trim.length());
            showSearchResult(true);
            this.mHistoryListView.setVisibility(View.GONE);
            this.mSearchHistory.m4095a(this.mWord);
            this.mHistoryListAdapter.notifyDataSetChanged();
            hideSoftInputFromWindow();
            CommandCenter.getInstance().execute(new Command(CommandID.GET_SEARCH_TYPES, new Object[0]));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestSongList(String str) {
        requestSongList(str, 1);
    }

    private InputMethodManager getInputMethodManager() {
        if (this.mInputMethodManager == null) {
            this.mInputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        return this.mInputMethodManager;
    }

    private void hideSoftInputFromWindow() {
        if (this.mInputEditText != null) {
            getInputMethodManager().hideSoftInputFromWindow(this.mInputEditText.getWindowToken(), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSoftInputFromWindow() {
        if (this.mInputEditText != null) {
            getInputMethodManager().showSoftInput(this.mInputEditText, 2);
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onKeyPressed(int i, KeyEvent keyEvent) {
        super.onKeyPressed(i, keyEvent);
        if (i == 82) {
            hideSoftInputFromWindow();
        }
    }

    private void setCurrentPosition(int i) {
        int i2 = 0;
        if (isSlidingAtTheLeftEdge(i)) {
            i2 = 2;
        } else if (isSlidingAtTheRightEdge(i)) {
            i2 = 1;
        }
        setSlidingCloseMode(i2);
    }

    private boolean isSlidingAtTheLeftEdge(int i) {
        return i == 0;
    }

    private boolean isSlidingAtTheRightEdge(int i) {
        return this.mPagerAdapter != null && i == this.mPagerAdapter.getCount() + (-1);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getView().setKeepScreenOn(Preferences.m2813y());
        //OnlineMediaStatistic.m5045a(getOrigin());
        //OnlineMediaStatistic.m5054a();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        this.mCurrentItem = i;
        setCurrentPosition(i);
        ((InterfaceC1501a) currentFragment()).onFragmentSelected(this.mWord, this.mUserInput);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    protected List<SlidingTabFragmentPagerAdapter.C0998a> buildFragmentBinders() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a((long) ID_FRAGMENT_SONG, (int) R.string.tab_song, 0, getSongFragment()));
        arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a((long) ID_FRAGMENT_ALBUM, (int) R.string.tab_album, 0, getAlbumFragment()));
        arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a((long) ID_FRAGMENT_SONG_LIST, (int) R.string.tab_song_list, 0, getSongListSearchFragment()));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SongSearchFragment getSongFragment() {
        if (this.mSongSearchFragment == null) {
            this.mSongSearchFragment = new SongSearchFragment();
            Bundle bundle = new Bundle();
            bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
            this.mSongSearchFragment.setArguments(bundle);
        }
        return this.mSongSearchFragment;
    }

    private AlbumSearchFragment getAlbumFragment() {
        if (this.mAlbumSearchFragment == null) {
            this.mAlbumSearchFragment = new AlbumSearchFragment();
            this.mAlbumSearchFragment.setArguments(getArguments());
        }
        return this.mAlbumSearchFragment;
    }

    private PlaylistSearchFragment getSongListSearchFragment() {
        if (this.mSongListSearchFragment == null) {
            this.mSongListSearchFragment = new PlaylistSearchFragment();
            this.mSongListSearchFragment.setArguments(getArguments());
        }
        return this.mSongListSearchFragment;
    }

    public void hide() {
        getRootView().setVisibility(View.GONE);
    }

    public void show() {
        getRootView().setVisibility(View.VISIBLE);
    }

    public Fragment currentFragment() {
        return this.mPagerAdapter.getItem(this.mViewPager.getCurrentItem());
    }

    private void tabFragmentSearch(String str) {
        //SearchStatistic.m4937c(str);
        //ListStatistic.m5205a(str);
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof SearchFragment.InterfaceC1490a) {
            ((SearchFragment.InterfaceC1490a) currentFragment).search(str, this.mUserInput);
        }
    }

    public void updateSearchTypes(SearchTypeResult searchTypeResult) {
        int currentItem = this.mViewPager.getCurrentItem();
        int appreciatedSearchTab = getAppreciatedSearchTab(searchTypeResult, currentItem);
        if (appreciatedSearchTab != currentItem) {
            this.mViewPager.setCurrentItem(appreciatedSearchTab);
        } else {
            tabFragmentSearch(this.mWord);
        }
    }

    private int getAppreciatedSearchTab(SearchTypeResult searchTypeResult, int i) {
        ArrayList<String> arrayList;
        ArrayList<String> arrayList2 = new ArrayList<>();
        ArrayList<String> arrayList3 = new ArrayList<>();
        Iterator<SearchType> it = searchTypeResult.getDataList().iterator();
        ArrayList<String> arrayList4 = arrayList2;
        while (true) {
            arrayList = arrayList3;
            if (!it.hasNext()) {
                break;
            }
            SearchType next = it.next();
            if ("album".equals(next.getId())) {
                arrayList4 = next.getWords();
            }
            arrayList3 = "playlist".equals(next.getId()) ? next.getWords() : arrayList;
        }
        if (arrayList4.contains(this.mWord)) {
            return 1;
        }
        if (arrayList.contains(this.mWord)) {
            return 2;
        }
        return i;
    }

    public String getModule() {
        return this.mModule;
    }

    public String getOrigin() {
        return this.mOrigin;
    }

    public void setModule(String str) {
        this.mModule = str;
    }

    public void setOrigin(String str) {
        this.mOrigin = str;
    }
}
