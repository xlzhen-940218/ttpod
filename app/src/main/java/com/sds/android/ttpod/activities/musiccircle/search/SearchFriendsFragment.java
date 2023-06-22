package com.sds.android.ttpod.activities.musiccircle.search;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.MusicCircleStatistic;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;

/* loaded from: classes.dex */
public class SearchFriendsFragment extends SlidingClosableFragment {
    private Button mSearchButton;
    private EditText mSearchContentInput;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.musiccircle_search_layout, viewGroup, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View view) {
        getActionBarController().m7189b(R.string.musiccircle_search_title);
        this.mSearchContentInput = (EditText) view.findViewById(R.id.edittext_username);
        this.mSearchContentInput.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.sds.android.ttpod.activities.musiccircle.search.SearchFriendsFragment.1
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                SearchFriendsFragment.this.search();
                return true;
            }
        });
        this.mSearchButton = (Button) view.findViewById(R.id.button_search);
        this.mSearchButton.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.musiccircle.search.SearchFriendsFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SearchFriendsFragment.this.search();
                MusicCircleStatistic.m7960o();
            }
        });
        MusicCircleStatistic.m7961n();
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        super.onBackPressed();
        MusicCircleStatistic.m7959p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void search() {
        if (EnvironmentUtils.C0604c.m8474e()) {
            String obj = this.mSearchContentInput.getText().toString();
            if (StringUtils.m8346a(obj)) {
                PopupsUtils.m6760a((int) R.string.social_search_empty);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("search_content", obj);
            SearchFriendsResultFragment searchFriendsResultFragment = new SearchFriendsResultFragment();
            searchFriendsResultFragment.setArguments(bundle);
            launchFragment(searchFriendsResultFragment);
            hideSoftInput();
            return;
        }
        PopupsUtils.m6760a((int) R.string.network_error);
    }

    private void hideSoftInput() {
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.mSearchContentInput.getWindowToken(), 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment
    public void onSlidingClosed() {
        hideSoftInput();
        super.onSlidingClosed();
    }
}
