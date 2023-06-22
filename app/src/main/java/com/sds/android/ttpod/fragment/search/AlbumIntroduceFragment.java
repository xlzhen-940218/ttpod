package com.sds.android.ttpod.fragment.search;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.main.SearchResultFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;

/* loaded from: classes.dex */
public class AlbumIntroduceFragment extends SlidingClosableFragment {
    private View mRootView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_album_indroduce, (ViewGroup) null);
            initActionBar();
            initView();
        }
        return this.mRootView;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeManager.m3269a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        ThemeManager.m3269a(this.mRootView.findViewById(R.id.album_name), ThemeElement.CARD_TEXT);
        ThemeManager.m3269a(this.mRootView.findViewById(R.id.album_artist), ThemeElement.CARD_SUB_TEXT);
        ThemeManager.m3269a(this.mRootView.findViewById(R.id.title_area), ThemeElement.CARD_BACKGROUND);
        ThemeManager.m3269a(this.mRootView.findViewById(R.id.album_lang), ThemeElement.CARD_SUB_TEXT);
        ThemeManager.m3269a(this.mRootView.findViewById(R.id.album_publish_time), ThemeElement.CARD_SUB_TEXT);
        ThemeManager.m3269a(this.mRootView.findViewById(R.id.album_introduce), ThemeElement.CARD_TEXT);
    }

    private void initView() {
        AlbumItem albumItem = (AlbumItem) getArguments().getSerializable(SearchResultFragment.KEY_SEARCH_WORD);
        TextView textView = (TextView) this.mRootView.findViewById(R.id.album_introduce);
        ((TextView) this.mRootView.findViewById(R.id.album_name)).setText(albumItem.getName());
        ((TextView) this.mRootView.findViewById(R.id.album_artist)).setText(albumItem.getSingerName());
        ((TextView) this.mRootView.findViewById(R.id.album_lang)).setText(getString(R.string.album_lang, albumItem.getLang()));
        ((TextView) this.mRootView.findViewById(R.id.album_publish_time)).setText(getString(R.string.album_publish_time, albumItem.getPublishTime()));
        if (albumItem.getDesc() != null) {
            textView.setText(Html.fromHtml(albumItem.getDesc()));
        }
    }

    private void initActionBar() {
        getActionBarController().m7189b(R.string.album_introduce);
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needSearchAction() {
        return false;
    }
}
