package com.sds.android.ttpod.activities.local;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.adapter.p072d.BaseSearchAdapter;
import com.sds.android.ttpod.component.p085b.MediaItemViewHolder;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.OnlineMediaStatistic;
import com.sds.android.ttpod.utils.ListViewUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.util.Locale;

/* loaded from: classes.dex */
public abstract class BaseSearchActivity extends SlidingClosableActivity implements AdapterView.OnItemClickListener, ThemeManager.InterfaceC2019b {
    private static final String LOG_TAG = "BaseSearchActivity";
    private BaseSearchAdapter mAdapter;
    private EditText mEdtPrompt;
    private InputMethodManager mInputMgr;
    private String mOrigin;
    protected ActionExpandableListView mSearchResultListView;
    private boolean mSwitchToSystemKeyboard;
    private TextWatcher mTextWatcher = new TextWatcher() { // from class: com.sds.android.ttpod.activities.local.BaseSearchActivity.1
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            BaseSearchActivity.this.search(editable.toString());
        }
    };
    private TextView mTvPrompt;

    protected abstract BaseSearchAdapter onCreateAdapter();

    public ActionExpandableListView getListView() {
        return this.mSearchResultListView;
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        getActionBarController().onThemeLoaded();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mInputMgr = (InputMethodManager) getSystemService("input_method");
        setContentView(R.layout.activity_local_search);
        getActionBarController().m7189b(R.string.menu_find);
        this.mTvPrompt = (TextView) findViewById(R.id.tv_no_result);
        ThemeManager.m3269a(this.mTvPrompt, ThemeElement.SONG_LIST_ITEM_TEXT);
        this.mEdtPrompt = (EditText) findViewById(R.id.search_input_edittext);
        this.mSearchResultListView = (ActionExpandableListView) findViewById(R.id.search_result_listview);
        this.mAdapter = onCreateAdapter();
        this.mSearchResultListView.mo1261a(this.mAdapter, R.id.menu_view, R.id.expandable);
        this.mSearchResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.activities.local.BaseSearchActivity.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int m8266a;
                if (BaseSearchActivity.this.mAdapter != null && BaseSearchActivity.this.mSearchResultListView != null && (m8266a = ListViewUtils.m8266a(BaseSearchActivity.this.mSearchResultListView.getHeaderViewsCount(), i, BaseSearchActivity.this.mAdapter.getCount())) > -1) {
                    BaseSearchActivity.this.onItemClicked(adapterView, view, m8266a, j);
                }
            }
        });
        this.mSearchResultListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.sds.android.ttpod.activities.local.BaseSearchActivity.3
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                int m8266a = ListViewUtils.m8266a(BaseSearchActivity.this.mSearchResultListView.getHeaderViewsCount(), i, BaseSearchActivity.this.mAdapter.getCount());
                if (m8266a > -1) {
                    OnlineMediaStatistic.m5053a(m8266a + 1);
                    BaseSearchActivity.this.onMediaItemLongClicked(adapterView, view, m8266a, j);
                    return true;
                }
                return true;
            }
        });
        this.mSearchResultListView.setItemExpandCollapseListener(new AbstractExpandableListAdapter.InterfaceC2279a() { // from class: com.sds.android.ttpod.activities.local.BaseSearchActivity.4
            @Override // com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.InterfaceC2279a
            public void onExpand(View view, int i) {
                m8084a((MediaItemViewHolder) ((View) view.getParent()).getTag(), true);
            }

            @Override // com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.InterfaceC2279a
            public void onCollapse(View view, int i) {
                m8084a((MediaItemViewHolder) ((View) view.getParent()).getTag(), false);
            }

            /* renamed from: a */
            private void m8084a(MediaItemViewHolder mediaItemViewHolder, boolean z) {
                mediaItemViewHolder.m6961d().setText(z ? R.string.icon_arrow_top : R.string.icon_arrow_down);
            }
        });
        ThemeManager.m3269a(this.mSearchResultListView, ThemeElement.BACKGROUND_MASK);
        ThemeManager.m3269a(this.mSearchResultListView, ThemeElement.COMMON_SEPARATOR);
        ThemeManager.m3269a(findViewById(R.id.search_screen_layout), ThemeElement.BACKGROUND_MASK);
        getRootView().setBackgroundDrawable(ThemeUtils.m8182a());
        this.mSearchResultListView.setOnTouchListener(new View.OnTouchListener() { // from class: com.sds.android.ttpod.activities.local.BaseSearchActivity.5
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                BaseSearchActivity.this.mInputMgr.hideSoftInputFromWindow(BaseSearchActivity.this.mEdtPrompt.getWindowToken(), 0);
                return false;
            }
        });
        this.mEdtPrompt.addTextChangedListener(this.mTextWatcher);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mOrigin = intent.getStringExtra("origin");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mInputMgr.hideSoftInputFromWindow(this.mEdtPrompt.getWindowToken(), 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onLoadDataFinished() {
        if (!this.mSwitchToSystemKeyboard) {
            this.mSwitchToSystemKeyboard = true;
            this.mEdtPrompt.postDelayed(new Runnable() { // from class: com.sds.android.ttpod.activities.local.BaseSearchActivity.6
                @Override // java.lang.Runnable
                public void run() {
                    BaseSearchActivity.this.switchToSystemKeyboard();
                }
            }, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchToSystemKeyboard() {
        this.mEdtPrompt.setVisibility(View.VISIBLE);
        this.mEdtPrompt.requestFocus();
        this.mInputMgr.showSoftInput(this.mEdtPrompt, 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void search(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mTvPrompt.setVisibility(View.GONE);
            this.mAdapter.m7584e();
        } else if (this.mAdapter.m7588a(str.toUpperCase(Locale.US)) > 0) {
            this.mTvPrompt.setVisibility(View.GONE);
        } else {
            this.mTvPrompt.setVisibility(View.VISIBLE);
            this.mTvPrompt.setText(getString(R.string.search_result_no_result, new Object[]{this.mOrigin}));
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
    }

    public void onItemClicked(AdapterView<?> adapterView, View view, int i, long j) {
    }

    public void onMediaItemLongClicked(AdapterView<?> adapterView, View view, int i, long j) {
    }
}
