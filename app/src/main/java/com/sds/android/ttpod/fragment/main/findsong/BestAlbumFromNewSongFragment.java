package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.MusicCircleFirstPublish;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewSongCategoryResult;

import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.PostDetailFragment;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.SlidingTabHost;
import com.sds.android.ttpod.widget.StateView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class BestAlbumFromNewSongFragment extends SlidingClosableFragment {
    private SlidingTabFragmentPagerAdapter mMainFragmentPagerAdapter;
    private FirstPublishNewSongCategoryResult mResult;
    private View mRootView;
    private SlidingTabHost mSlidingTabHost;
    private StateView mStateLoadingView;
    private ViewPager mViewPager;

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(SPage.PAGE_NONE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_best_album_from_new_song, viewGroup, false);
        this.mViewPager = (ViewPager) this.mRootView.findViewById(R.id.viewpager);
        this.mSlidingTabHost = (SlidingTabHost) this.mRootView.findViewById(R.id.sliding_tabs_host_best_album);
        this.mStateLoadingView = (StateView) this.mRootView.findViewById(R.id.best_album_load_view);
        this.mStateLoadingView.setOnRetryRequestListener(new StateView.InterfaceC2247a() { // from class: com.sds.android.ttpod.fragment.main.findsong.BestAlbumFromNewSongFragment.1
            @Override // com.sds.android.ttpod.widget.StateView.InterfaceC2247a
            /* renamed from: a */
            public void mo1450a() {
                BestAlbumFromNewSongFragment.this.loadData();
            }
        });
        getActionBarController().m7189b(R.string.best_album);
        loadData();
        return this.mRootView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadData() {
        this.mStateLoadingView.setState(StateView.EnumC2248b.LOADING);
        CommandCenter.getInstance().m4606a(new Command(CommandID.REQUEST_NEW_SONG_CATEGORY_PUBLISH_LIST, new Object[0]));
    }

    private void bindView() {
        this.mMainFragmentPagerAdapter = new SlidingTabFragmentPagerAdapter(getActivity(), getChildFragmentManager(), buildFragmentBinders());
        this.mViewPager.setAdapter(this.mMainFragmentPagerAdapter);
        attachSlidingTabHost(this.mSlidingTabHost, this.mViewPager);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_NEW_SONG_CATEGORY_PUBLISH_LIST, ReflectUtils.m8375a(getClass(), "updateNewSongCategory", FirstPublishNewSongCategoryResult.class));
    }

    public void updateNewSongCategory(FirstPublishNewSongCategoryResult firstPublishNewSongCategoryResult) {
        if (firstPublishNewSongCategoryResult.isSuccess()) {
            this.mResult = firstPublishNewSongCategoryResult;
            bindView();
            this.mStateLoadingView.setState(StateView.EnumC2248b.SUCCESS);
            this.mStateLoadingView.setVisibility(View.GONE);
            return;
        }
        this.mStateLoadingView.setState(StateView.EnumC2248b.FAILED);
    }

    private void attachSlidingTabHost(SlidingTabHost slidingTabHost, ViewPager viewPager) {
        slidingTabHost.setTabLayoutAverageSpace(true);
        slidingTabHost.setViewPager(viewPager);
        slidingTabHost.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sds.android.ttpod.fragment.main.findsong.BestAlbumFromNewSongFragment.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                //BestAlbumFromNewSongFragment.this.doStatisticWhenPageSelected(i);
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (this.mMainFragmentPagerAdapter != null) {
            this.mMainFragmentPagerAdapter.m7428a();
        }
        super.onDestroyView();
        if (this.mResult != null && this.mResult.getSingleList() == null) {
        }
    }

    private List<SlidingTabFragmentPagerAdapter.C0998a> buildFragmentBinders() {
        ArrayList arrayList = new ArrayList();
        ArrayList<MusicCircleFirstPublish> singleList = this.mResult.getSingleList();
        Iterator<MusicCircleFirstPublish> it = singleList.iterator();
        while (it.hasNext()) {
            MusicCircleFirstPublish next = it.next();
            PostDetailFragment createById = PostDetailFragment.createById(next.getMsgId(), "BestAlbum");
            createById.initBundle(next.getTitle(), String.valueOf(next.getMsgId()));
            createById.setPage(next.getTitle());
            arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a(0L, next.getTitle(), 0, createById));
        }
        this.mViewPager.setOffscreenPageLimit(singleList.size());
        return arrayList;
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeUtils.m8166a(this.mSlidingTabHost);
        ThemeManager.m3269a(this.mRootView.findViewById(R.id.best_album_background), ThemeElement.BACKGROUND_MASK);
    }
}
