package com.sds.android.ttpod.fragment.musiccircle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.modules.skin.view.ModifyFitCenterImageView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.ImageCacheUtils;
import com.sds.android.ttpod.framework.p106a.ViewUtils;
import com.sds.android.ttpod.widget.HotwordView;

import java.util.Arrays;

/* loaded from: classes.dex */
public class IntroductionFragment extends SlidingClosableFragment {
    private View mContent;
    private View mHotWordLayout;
    private HotwordView mHotwordView;
    private View mIntroTitleView;
    private View mTagTitleView;
    private View mTweetLayout;
    private TextView mTweetTextView;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateContentView(layoutInflater, viewGroup, bundle);
        this.mContent = layoutInflater.inflate(R.layout.fragment_post_introduction, (ViewGroup) null);
        getActionBarController().m7193a((CharSequence) getArguments().getString("name"));
        initTweetView(getArguments().getString("desc"));
        initHeaderImage(getArguments().getString(User.KEY_AVATAR));
        initTagView(getArguments().getString("tags"));
        return this.mContent;
    }

    private void initHeaderImage(String str) {
        ModifyFitCenterImageView modifyFitCenterImageView = (ModifyFitCenterImageView) this.mContent.findViewById(R.id.tv_post_introduction_pic);
        if (str != null) {
            ImageCacheUtils.m4752a(modifyFitCenterImageView, str, (int) VIPPolicy.Entry.MAX_LIMIT, (int) VIPPolicy.Entry.MAX_LIMIT, (int) R.drawable.img_musiccircle_post_pic_default);
        }
    }

    private void initTweetView(String str) {
        this.mTweetTextView = (TextView) this.mContent.findViewById(R.id.tv_post_introduction_tweet);
        this.mIntroTitleView = this.mContent.findViewById(R.id.tv_title_intro);
        this.mTweetLayout = this.mContent.findViewById(R.id.layout_introduction);
        if (StringUtils.isEmpty(str)) {
            this.mTweetTextView.setText(getString(R.string.post_no_description));
        } else {
            this.mTweetTextView.setText(str);
        }
    }

    private void initTagView(String str) {
        this.mHotwordView = (HotwordView) this.mContent.findViewById(R.id.hotword_view);
        this.mHotWordLayout = this.mContent.findViewById(R.id.layout_tags);
        this.mTagTitleView = this.mContent.findViewById(R.id.tv_title_tags);
        TextView textView = (TextView) this.mContent.findViewById(R.id.tv_no_tag);
        if (StringUtils.isEmpty(str)) {
            this.mHotwordView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            textView.setText(R.string.post_no_tag);
            return;
        }
        this.mHotwordView.setStringList(Arrays.asList(str.split(",")));
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeManager.m3269a(this.mContent, ThemeElement.BACKGROUND_MASK);
        ThemeManager.m3269a(this.mTweetTextView, ThemeElement.SONG_LIST_ITEM_TEXT);
        ThemeManager.m3269a(this.mContent.findViewById(R.id.v_post_introduction_indicator1), ThemeElement.SONG_LIST_ITEM_INDICATOR);
        ThemeManager.m3269a(this.mContent.findViewById(R.id.v_post_introduction_indicator2), ThemeElement.SONG_LIST_ITEM_INDICATOR);
        ThemeManager.m3269a(this.mTagTitleView, ThemeElement.TILE_TEXT);
        ThemeManager.m3269a(this.mIntroTitleView, ThemeElement.TILE_TEXT);
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        ViewUtils.m4639a(this.mContent);
        ViewUtils.m4639a(getRootView());
    }
}
