package com.sds.android.ttpod.activities.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.fragment.main.SearchResultFragment;

/* loaded from: classes.dex */
public class OnlineSearchEntryActivity extends SlidingClosableActivity {
    public static final String KEY_FROM_THIRD = "from_third";
    public static final String KEY_THIRD_APP_IDENTITY = "app";
    public static final String KEY_THIRD_ONLINE_SEARCH_KEYWORD = "keyword";
    private static final String LOG_TAG = "OnlineSearchEntryActivity";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(KEY_THIRD_ONLINE_SEARCH_KEYWORD);
        int intExtra = intent.getIntExtra(KEY_THIRD_APP_IDENTITY, 0);
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtils.m8384b(LOG_TAG, "keyword or appName is empty, will exit, keyword=" + stringExtra);
            finish();
            return;
        }
        getActionBarController().m7180c(false);
        setContentView(R.layout.activity_online_search_entry);
        SearchResultFragment searchResultFragment = new SearchResultFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString(SearchResultFragment.KEY_SEARCH_WORD, stringExtra);
        bundle2.putBoolean(KEY_FROM_THIRD, true);
        bundle2.putInt(KEY_THIRD_APP_IDENTITY, intExtra);
        searchResultFragment.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, searchResultFragment).commitAllowingStateLoss();
    }
}
