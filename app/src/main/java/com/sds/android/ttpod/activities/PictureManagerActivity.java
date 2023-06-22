package com.sds.android.ttpod.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.PictureManagerAdapter;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.modules.search.SearchMediaLinkInfo;
import com.sds.android.ttpod.framework.storage.database.SearchSqliteDb;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.utils.OfflineModeUtils;
import com.sds.android.ttpod.widget.NetworkLoadView;

/* loaded from: classes.dex */
public class PictureManagerActivity extends BaseActivity implements View.OnClickListener {
    private static final int ARTIST_RATIO_HEIGHT = 128;
    private static final int ARTIST_RATIO_WIDTH = 96;
    private PictureManagerAdapter mAdapter;
    private EditText mArtistEditText;
    private View mClearTextView;
    private View mContentView;
    private InputMethodManager mInputMgr;
    private ListView mListView;
    private NetworkLoadView mLoadView;
    private View mRootView;
    private View mSearchView;

    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MediaItem m3225N = Cache.m3218a().m3225N();
        if (m3225N.isNull()) {
            PopupsUtils.m6721a("没有播放的歌曲信息");
            finish();
            return;
        }
        setContentView(R.layout.picture_manager);
        this.mArtistEditText = (EditText) findViewById(R.id.edittext_search_input);
        this.mArtistEditText.setFocusable(false);
        this.mArtistEditText.setFocusableInTouchMode(false);
        this.mSearchView = findViewById(R.id.imageview_search);
        this.mClearTextView = findViewById(R.id.imageview_search_clear);
        this.mListView = (ListView) findViewById(R.id.listview);
        this.mContentView = findViewById(R.id.layout_content);
        this.mSearchView.setOnClickListener(this);
        this.mClearTextView.setOnClickListener(this);
        this.mRootView = findViewById(R.id.layout_root);
        this.mRootView.setOnClickListener(this);
        this.mLoadView = (NetworkLoadView) findViewById(R.id.layout_loading);
        this.mLoadView.setLoadState(NetworkLoadView.EnumC2205a.IDLE);
        this.mLoadView.setOnStartLoadingListener(new NetworkLoadView.InterfaceC2206b() { // from class: com.sds.android.ttpod.activities.PictureManagerActivity.1
            @Override // com.sds.android.ttpod.widget.NetworkLoadView.InterfaceC2206b
            /* renamed from: a */
            public void mo1678a() {
                if (!PictureManagerActivity.this.mAdapter.m7687c()) {
                    PictureManagerActivity.this.doSearchArtistPicture();
                }
            }
        });
        this.mAdapter = new PictureManagerAdapter(this, this.mLoadView);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        String artist = m3225N.getArtist();
        SearchMediaLinkInfo m3135a = SearchSqliteDb.m3135a(getContentResolver(), m3225N.getID());
        if (m3135a != null && !StringUtils.m8346a(m3135a.getArtist())) {
            artist = m3135a.getArtist();
        }
        String m8397o = FileUtils.m8397o(artist);
        this.mArtistEditText.setText(m8397o);
        this.mArtistEditText.setHint("请输入歌手名");
        this.mAdapter.m7694a(m3225N, m8397o);
        this.mArtistEditText.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.activities.PictureManagerActivity.2
            @Override // java.lang.Runnable
            public void run() {
                PictureManagerActivity.this.mArtistEditText.clearFocus();
                PictureManagerActivity.this.mArtistEditText.setFocusable(true);
                PictureManagerActivity.this.mArtistEditText.setFocusableInTouchMode(true);
            }
        }, 200L);
        this.mArtistEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.sds.android.ttpod.activities.PictureManagerActivity.3
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 3 || i == 2 || i == 0 || i == 4) {
                    PictureManagerActivity.this.doSearchArtistPicture();
                    return true;
                }
                return false;
            }
        });
        this.mArtistEditText.addTextChangedListener(new TextWatcher() { // from class: com.sds.android.ttpod.activities.PictureManagerActivity.4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                PictureManagerActivity.this.mClearTextView.setVisibility(editable.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
        int dimension = (int) getResources().getDimension(R.dimen.artist_manager_margin);
        View findViewById = findViewById(R.id.layout_list);
        ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
        layoutParams.height = (dimension + ((((getResources().getDisplayMetrics().widthPixels - (dimension << 2)) / 3) * 128) / ARTIST_RATIO_WIDTH)) << 1;
        findViewById.setLayoutParams(layoutParams);
        this.mInputMgr = (InputMethodManager) getSystemService("input_method");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.mAdapter != null && this.mAdapter.m7701a() && StringUtils.m8344a(this.mAdapter.m7691b().getID(), Cache.m3218a().m3225N().getID())) {
            localSearchArtist();
        }
        super.onDestroy();
    }

    private void localSearchArtist() {
        startService(new Intent(this, SupportService.class).putExtra("command", "search_lyric_pic_command").putExtra("type", "picture_type").putExtra("only_local_search_parameter", true));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mClearTextView) {
            this.mArtistEditText.setText("");
            this.mClearTextView.setVisibility(View.GONE);
        } else if (view == this.mSearchView) {
            doSearchArtistPicture();
        } else {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doSearchArtistPicture() {
        final String obj = this.mArtistEditText.getText().toString();
        if (StringUtils.m8346a(obj)) {
            PopupsUtils.m6721a("歌手名不能为空");
            return;
        }
        this.mInputMgr.hideSoftInputFromWindow(this.mArtistEditText.getWindowToken(), 0);
        OfflineModeUtils.m8255a(this, new DialogInterface.OnClickListener() { // from class: com.sds.android.ttpod.activities.PictureManagerActivity.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    PictureManagerActivity.this.mAdapter.m7693a(obj);
                }
            }
        });
    }
}
