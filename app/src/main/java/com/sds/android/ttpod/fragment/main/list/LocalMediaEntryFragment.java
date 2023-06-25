package com.sds.android.ttpod.fragment.main.list;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.util.LongSparseArray;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.mediascan.MediaScanActivity;
import com.sds.android.ttpod.adapter.SlidingTabFragmentPagerAdapter;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.MessageDialog;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.ActionPage;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.SlidingTabHost;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public class LocalMediaEntryFragment extends SlidingClosableFragment implements IEditAble.InterfaceC1677a {
    private static final LongSparseArray<ActionPage> ACTION_PAGE_MAP = new LongSparseArray<>();
    public static final long ID_FRAGMENT_ALBUM = 2;
    public static final long ID_FRAGMENT_ALL = 0;
    public static final long ID_FRAGMENT_ARTIST = 1;
    public static final long ID_FRAGMENT_FOLDER = 3;
    private static final String TAG = "LocalMediaEntryFragment";
    private int mCurrentItem;
    private int mFailedCount;
    private ImageView mIvAction;
    private View mLayoutMatcher;
    private View mLayoutOuterMatcher;
    private boolean mMatching;
    protected SlidingTabFragmentPagerAdapter mPagerAdapter;
    private ProgressBar mProgressBar;
    private boolean mRegisteredBroadcast;
    private View mRootView;
    private ActionBarController.C1070a mSelectAction;
    private int mSkipCount;
    protected SlidingTabHost mSlidingTabHost;
    private int mSuccessCount;
    private int mTotalCount;
    private TextView mTvSubtitle;
    private TextView mTvTitle;
    protected ViewPager mViewPager;
    private ActionBarController.InterfaceC1072b mOnActionClickListener = new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.fragment.main.list.LocalMediaEntryFragment.1
        @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
        /* renamed from: a */
        public void mo5433a(ActionBarController.C1070a c1070a) {
            if (c1070a == LocalMediaEntryFragment.this.mSelectAction) {
                if (c1070a.m7150f() == null) {
                    LocalMediaEntryFragment.this.selectAll();
                } else {
                    LocalMediaEntryFragment.this.selectNone();
                }
            }
        }
    };
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() { // from class: com.sds.android.ttpod.fragment.main.list.LocalMediaEntryFragment.2
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            LocalMediaEntryFragment.this.doStatistic(i);
            LocalMediaEntryFragment.this.mCurrentItem = i;
            LocalMediaEntryFragment.this.setCurrentPosition(i);
            if (LocalMediaEntryFragment.this.isViewAccessAble()) {
                if (PopupsUtils.m6716b()) {
                    LocalMediaEntryFragment.this.stopEdit();
                } else {
                    LocalMediaEntryFragment.this.resetActionBar(LocalMediaEntryFragment.this.mViewPager.getCurrentItem());
                }
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
        }
    };
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.sds.android.ttpod.fragment.main.list.LocalMediaEntryFragment.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (Action.LYRIC_PIC_BATCH_OPERATE_RESULT.equals(intent != null ? intent.getAction() : null)) {
                LocalMediaEntryFragment.this.batchMatchStateChanged(intent);
            }
        }
    };
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.list.LocalMediaEntryFragment.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view != LocalMediaEntryFragment.this.mLayoutMatcher || LocalMediaEntryFragment.this.mMatching) {
                if (view == LocalMediaEntryFragment.this.mIvAction) {
                    if (LocalMediaEntryFragment.this.mMatching) {
                        //SUserUtils.m4956a(SAction.ACTION_ONE_KEY_MATCH_LRC_PIC_STOP, SPage.PAGE_NONE);
                        MessageDialog messageDialog = new MessageDialog(LocalMediaEntryFragment.this.getActivity(), (int) R.string.prompt_match_not_complete, (int) R.string.prompt_stop, new BaseDialog.InterfaceC1064a<MessageDialog>() { // from class: com.sds.android.ttpod.fragment.main.list.LocalMediaEntryFragment.5.1
                            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                            /* renamed from: a  reason: avoid collision after fix types in other method */
                            public void mo2038a(MessageDialog messageDialog2) {
                                if (LocalMediaEntryFragment.this.mRegisteredBroadcast) {
                                    LocalMediaEntryFragment.this.getActivity().unregisterReceiver(LocalMediaEntryFragment.this.mBroadcastReceiver);
                                    LocalMediaEntryFragment.this.mRegisteredBroadcast = false;
                                }
                                //SUserUtils.m4956a(SAction.ACTION_ONE_KEY_MATCH_LRC_PIC_STOP_SURE, SPage.PAGE_NONE);
                                LocalMediaEntryFragment.this.doBatchLyricPicOperate("stop");
                                LocalMediaEntryFragment.this.mMatching = false;
                                LocalMediaEntryFragment.this.flushHeaderView();
                            }
                        }, (int) R.string.cancel, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
                        messageDialog.setTitle(R.string.message_prompt);
                        messageDialog.show();
                        return;
                    }
                    //SUserUtils.m4956a(SAction.ACTION_ONE_KEY_MATCH_LRC_PIC_BANNER_CLOSE, SPage.PAGE_NONE);
                    LocalMediaEntryFragment.this.hideMatchBanner();
                    return;
                }
                return;
            }
            //LocalStatistic.m5160W();
            LocalMediaEntryFragment.this.doMatchLyricPic();
        }
    };

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected void onSearchActionClicked() {
        search();
        //LocalStatistic.m5127af();
        //SUserUtils.m4956a(SAction.ACTION_LOCAL_SEARCH, SPage.PAGE_NONE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_localmusic, viewGroup, false);
        this.mRootView = inflate;
        prepareMatchPicLrcView(this.mRootView);
        this.mSlidingTabHost = (SlidingTabHost) inflate.findViewById(R.id.slidingtabhost_localmusic);
        this.mSlidingTabHost.setTabLayoutAverageSpace(true);
        this.mViewPager = (ViewPager) inflate.findViewById(R.id.pager_content);
        this.mViewPager.setCurrentItem(this.mCurrentItem);
        ActionBarController actionBarController = getActionBarController();
        actionBarController.m7189b(R.string.local_music);
        this.mSelectAction = actionBarController.m7199a((Drawable) null);
        setSelectAllAction();
        this.mSelectAction.m7172a();
        this.mSelectAction.m7167a(this.mOnActionClickListener);
        this.mPagerAdapter = new SlidingTabFragmentPagerAdapter(getActivity(), getChildFragmentManager(), buildFragmentBinders());
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mSlidingTabHost.setViewPager(this.mViewPager);
        this.mSlidingTabHost.setOnPageChangeListener(this.mOnPageChangeListener);
        this.mViewPager.setOffscreenPageLimit(this.mPagerAdapter.getCount());
        resetActionBar(0);
        setCurrentPosition(0);
        return inflate;
    }

    static {
        ACTION_PAGE_MAP.append(0L, new ActionPage(SAction.ACTION_LOCAL_SONG, SPage.PAGE_LOCAL_SONG));
        ACTION_PAGE_MAP.append(1L, new ActionPage(SAction.ACTION_LOCAL_ARTIST, SPage.PAGE_LOCAL_ARTIST));
        ACTION_PAGE_MAP.append(2L, new ActionPage(SAction.ACTION_LOCAL_ALBUM, SPage.PAGE_LOCAL_ALBUM));
        ACTION_PAGE_MAP.append(3L, new ActionPage(SAction.ACTION_LOCAL_FOLDER, SPage.PAGE_LOCAL_FOLDER));
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected Collection<ActionItem> onCreateDropDownMenu() {
        Fragment currentFragment = currentFragment();
        boolean isEditing = currentFragment instanceof IEditAble ? ((IEditAble) currentFragment).isEditing() : false;
        if (isEditing) {
            return null;
        }
        //SUserUtils.m4956a(SAction.ACTION_OPEN_LOCAL_DROP_MENU, SPage.PAGE_NONE);
        ArrayList<ActionItem> arrayList = new ArrayList<>();
        if (!isEditing) {
            arrayList.add(new ActionItem(4, 0, (int) R.string.menu_scan_media));
        }
        if (currentFragment instanceof MediaListFragment) {
            if (!((MediaListFragment) currentFragment).isEmpty()) {
                doAddCommonMenuItem(arrayList, isEditing);
                arrayList.add(new ActionItem(6, 0, (int) R.string.menu_sort_by_title).m7009a((Object) 7));
                arrayList.add(new ActionItem(6, 0, (int) R.string.menu_sort_by_artist).m7009a((Object) 8));
                arrayList.add(new ActionItem(6, 0, (int) R.string.menu_sort_by_add_time).m7009a((Object) 10));
                if (!isEditing) {
                    arrayList.add(new ActionItem(15, 0, (int) R.string.menu_batch_operate));
                    arrayList.add(new ActionItem(29, 0, (int) R.string.menu_match_lyric_pic));
                    return arrayList;
                }
                return arrayList;
            }
            return arrayList;
        } else if (currentFragment instanceof GroupListFragment) {
            GroupListFragment groupListFragment = (GroupListFragment) currentFragment;
            if (!groupListFragment.isEmpty()) {
                doAddCommonMenuItem(arrayList, isEditing);
                GroupType groupType = groupListFragment.getGroupType();
                if (groupType == GroupType.DEFAULT_ARTIST) {
                    arrayList.add(new ActionItem(6, 0, (int) R.string.menu_sort_by_artist).m7009a((Object) 8));
                } else if (groupType == GroupType.DEFAULT_ALBUM) {
                    arrayList.add(new ActionItem(6, 0, (int) R.string.menu_sort_by_album).m7009a((Object) 9));
                } else if (groupType == GroupType.DEFAULT_FOLDER) {
                    arrayList.add(new ActionItem(6, 0, (int) R.string.menu_sort_by_file_name).m7009a((Object) 11));
                }
                arrayList.add(new ActionItem(6, 0, (int) R.string.menu_sort_by_amount).m7009a((Object) 12));
                return arrayList;
            }
            return arrayList;
        } else {
            return arrayList;
        }
    }

    private void doAddCommonMenuItem(ArrayList<ActionItem> arrayList, boolean z) {
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment
    protected boolean needMenuAction() {
        return true;
    }

    @Override // com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener
    public void onDropDownMenuClicked(int i, ActionItem actionItem) {
        super.onDropDownMenuClicked(i, actionItem);
        switch (i) {
            case 4:
                if (getActivity() != null) {
                    startActivity(new Intent(getActivity(), MediaScanActivity.class));
                    //LocalStatistic.m5125ah();
                    //SUserUtils.m4956a(SAction.ACTION_MENU_SCAN_MUSIC, SPage.PAGE_SCAN_MUSIC);
                    return;
                }
                return;
            case 5:
                search();
                return;
            case 6:
                order(((Number) actionItem.m7004f()).intValue());
                //LocalStatistic.m5123aj();
                return;
            case 15:
                if (getActivity() != null) {
                    startEdit();
                    //LocalStatistic.m5124ai();
                    //SUserUtils.m4956a(SAction.ACTION_MENU_BATCH_OPERATE, SPage.PAGE_NONE);
                    return;
                }
                return;
            case 16:
            default:
                return;
            case 29:
                if (getActivity() != null) {
                    clickOneKeyMatch();
                    //LocalStatistic.m5093m();
                    //SUserUtils.m4956a(SAction.ACTION_MENU_ONE_KEY_MATCH_LRC_PIC, SPage.PAGE_NONE);
                    return;
                }
                return;
        }
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        ThemeUtils.m8166a(this.mSlidingTabHost);
        ThemeManager.m3269a(this.mLayoutOuterMatcher, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getView().setKeepScreenOn(Preferences.m2813y());
        //OnlineMediaStatistic.m5045a("local");
        //OnlineMediaStatistic.m5054a();
    }

    protected List<SlidingTabFragmentPagerAdapter.C0998a> buildFragmentBinders() {
        FragmentActivity activity = getActivity();
        ArrayList arrayList = new ArrayList();
        MediaListFragment mediaListFragment = (MediaListFragment) Fragment.instantiate(activity, MediaListFragment.class.getName(), buildMediaListFragmentBundle(MediaStorage.GROUP_ID_ALL_LOCAL));
        mediaListFragment.setNeedCountStastic();
        mediaListFragment.setPage(SPage.PAGE_LOCAL_SONG);
        arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a(0L, (int) R.string.local_music_all, 0, mediaListFragment));
        arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a(1L, (int) R.string.local_music_artist, 0, Fragment.instantiate(activity, GroupListFragment.class.getName(), buildGroupListFragmentBundle(GroupType.DEFAULT_ARTIST))));
        arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a(2L, (int) R.string.local_music_album, 0, Fragment.instantiate(activity, GroupListFragment.class.getName(), buildGroupListFragmentBundle(GroupType.DEFAULT_ALBUM))));
        arrayList.add(new SlidingTabFragmentPagerAdapter.C0998a(3L, (int) R.string.folder, 0, Fragment.instantiate(activity, GroupListFragment.class.getName(), buildGroupListFragmentBundle(GroupType.DEFAULT_FOLDER))));
        return arrayList;
    }

    protected Bundle buildMediaListFragmentBundle(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, str);
        return bundle;
    }

    private Bundle buildGroupListFragmentBundle(GroupType groupType) {
        Bundle bundle = new Bundle();
        bundle.putString(GroupListFragment.KEY_GROUP_TYPE, groupType.name());
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentPosition(int i) {
        int i2 = 0;
        if (isSlidingAtTheLeftEdge(i)) {
            i2 = 2;
        } else if (isSlidingAtTheRightEdge(i)) {
            i2 = 1;
        }
        setSlidingCloseMode(i2);
    }

    protected int getCurrentFragmentId() {
        return (int) this.mPagerAdapter.getItemId(this.mCurrentItem);
    }

    protected void doStatistic(int i) {
        switch ((int) this.mPagerAdapter.getItemId(i)) {
            case 0:
                //LocalStatistic.m5166Q();
                break;
            case 1:
                //LocalStatistic.m5165R();
                break;
            case 2:
                //LocalStatistic.m5164S();
                break;
            case 3:
                //LocalStatistic.m5163T();
                break;
        }
        ActionPage actionPage = ACTION_PAGE_MAP.get(i);
        //SUserUtils.m4956a(actionPage.m5275a(), actionPage.m5274b());
    }

    protected void resetActionBar(int i) {
    }

    protected Fragment currentFragment() {
        return this.mPagerAdapter.getItem(this.mViewPager.getCurrentItem());
    }

    protected void startEdit() {
        getActionBarController().m7193a((CharSequence) getString(R.string.select_media_with_count, 0));
        this.mSelectAction.m7163b();
        hideFixedAction();
        PopupsUtils.m6742a(getActivity(), getView(), this);
        if (currentFragment() instanceof MediaListFragment) {
            ((MediaListFragment) currentFragment()).setEditRequestListener(this);
        }
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof IEditAble) {
            ((IEditAble) currentFragment).startEdit();
        }
    }

    protected void stopEdit() {
        if (isViewAccessAble()) {
            getActionBarController().m7193a((CharSequence) getString(R.string.local_music));
            this.mSelectAction.m7172a();
            showFixedAction();
            PopupsUtils.m6706c();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < this.mPagerAdapter.getCount()) {
                    Fragment item = this.mPagerAdapter.getItem(i2);
                    if ((item instanceof IEditAble) && ((IEditAble) item).isEditing()) {
                        ((IEditAble) item).stopEdit();
                    }
                    i = i2 + 1;
                } else {
                    resetActionBar(this.mViewPager.getCurrentItem());
                    setSelectAllAction();
                    return;
                }
            }
        }
    }

    private void addTo() {
        Fragment currentFragment = currentFragment();
        if ((currentFragment instanceof IEditAble) && ((IEditAble) currentFragment).selectedCount() > 0) {
            ((IEditAble) currentFragment).addTo();
        }
    }

    private void sendTo() {
        Fragment currentFragment = currentFragment();
        if ((currentFragment instanceof IEditAble) && ((IEditAble) currentFragment).selectedCount() > 0) {
            ((IEditAble) currentFragment).sendTo();
        }
    }

    private void remove() {
        Fragment currentFragment = currentFragment();
        if ((currentFragment instanceof IEditAble) && ((IEditAble) currentFragment).selectedCount() > 0) {
            ((IEditAble) currentFragment).remove();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectNone() {
        setSelectAllAction();
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof IEditAble) {
            ((IEditAble) currentFragment).selectNone();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectAll() {
        setUnSelectAllAction();
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof IEditAble) {
            ((IEditAble) currentFragment).selectAll();
        }
    }

    private void setUnSelectAllAction() {
        this.mSelectAction.m7166a((Object) this.mSelectAction);
        ThemeUtils.m8170a(this.mSelectAction, (int) R.string.icon_checked, ThemeElement.TOP_BAR_TEXT);
    }

    private void setSelectAllAction() {
        this.mSelectAction.m7166a((Object) null);
        ThemeUtils.m8170a(this.mSelectAction, (int) R.string.icon_unchecked, ThemeElement.TOP_BAR_TEXT);
    }

    private void search() {
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof ISearchAble) {
            ((ISearchAble) currentFragment).search();
        }
    }

    private void order(int i) {
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof IOrderAble) {
            ((IOrderAble) currentFragment).order(i);
        }
    }

    private boolean isSlidingAtTheLeftEdge(int i) {
        return i == 0;
    }

    private boolean isSlidingAtTheRightEdge(int i) {
        return i == this.mPagerAdapter.getCount() + (-1);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onBackPressed() {
        if (PopupsUtils.m6716b()) {
            stopEdit();
        } else {
            super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment
    public void onSlidingClosed() {
        if (PopupsUtils.m6716b()) {
            new Handler().post(new Runnable() { // from class: com.sds.android.ttpod.fragment.main.list.LocalMediaEntryFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    LocalMediaEntryFragment.this.stopEdit();
                }
            });
        }
        super.onSlidingClosed();
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
    public void onSelectedCountChanged() {
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof IEditAble) {
            if (((IEditAble) currentFragment).totalCount() != ((IEditAble) currentFragment).selectedCount()) {
                setSelectAllAction();
            } else {
                setUnSelectAllAction();
            }
            getActionBarController().m7193a((CharSequence) getResources().getString(R.string.select_media_with_count, Integer.valueOf(((IEditAble) currentFragment).selectedCount())));
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
    public void onRemoveRequested() {
        remove();
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
    public void onAddToRequested() {
        addTo();
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
    public void onSendToRequested() {
        sendTo();
    }

    @Override // com.sds.android.ttpod.fragment.main.list.IEditAble.InterfaceC1677a
    public void onStopEditRequested() {
        stopEdit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void batchMatchStateChanged(Intent intent) {
        this.mMatching = intent.getBooleanExtra("state", false);
        this.mSuccessCount = intent.getIntExtra("success_count", 0);
        this.mFailedCount = intent.getIntExtra("failed_count", 0);
        this.mSkipCount = intent.getIntExtra("skip_count", 0);
        this.mTotalCount = intent.getIntExtra("total_count", 0);
        flushHeaderView();
        if (!this.mMatching && this.mTotalCount > 0 && this.mSuccessCount + this.mFailedCount + this.mSkipCount >= this.mTotalCount) {
            showMatchComplete();
        }
    }

    private void showMatchComplete() {
        hideMatchBanner();
        MessageDialog messageDialog = new MessageDialog(getActivity(), getString(R.string.prompt_match_detail, Integer.valueOf(this.mSuccessCount + this.mSkipCount), Integer.valueOf(this.mFailedCount)), (int) R.string.iknown, (BaseDialog.InterfaceC1064a<MessageDialog>) null);
        messageDialog.setTitle(R.string.prompt_match_result_title);
        messageDialog.show();
        //SUserUtils.m4956a(SAction.ACTION_ONE_KEY_MATCH_LRC_PIC_SUCCESS, SPage.PAGE_NONE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doBatchLyricPicOperate(String str) {
        BaseApplication.getApplication().startService(new Intent(BaseApplication.getApplication(), SupportService.class).putExtra("command", "batch_search_lyric_pic_command").putExtra("type", str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flushHeaderView() {
        this.mIvAction.setImageResource(this.mMatching ? R.drawable.img_match_piclrc_stop : R.drawable.img_match_piclrc_close);
        if (this.mMatching) {
            this.mLayoutMatcher.setVisibility(View.VISIBLE);
            this.mLayoutMatcher.setEnabled(false);
            this.mTvSubtitle.setVisibility(View.VISIBLE);
            this.mProgressBar.setVisibility(View.VISIBLE);
            this.mTvTitle.setText(R.string.prompt_matching_lyric_pic);
            int i = this.mSuccessCount + this.mFailedCount + this.mSkipCount;
            this.mTvSubtitle.setText(String.format("%d/%d", Integer.valueOf(i), Integer.valueOf(this.mTotalCount)));
            this.mProgressBar.setMax(this.mTotalCount);
            this.mProgressBar.setProgress(i);
            return;
        }
        this.mLayoutMatcher.setEnabled(true);
        this.mTvTitle.setText(R.string.prompt_match_lyric_pic);
        this.mTvSubtitle.setVisibility(View.GONE);
        this.mProgressBar.setVisibility(View.GONE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideMatchBanner() {
        this.mLayoutMatcher.setVisibility(View.GONE);
        Preferences.m2929b(false);
    }

    private void prepareMatchPicLrcView(View view) {
        View inflate = ((ViewStub) view.findViewById(R.id.vs_match_pic_lrc)).inflate();
        this.mLayoutOuterMatcher = inflate.findViewById(R.id.layout_outer_matcher);
        this.mLayoutMatcher = inflate.findViewById(R.id.layout_matcher);
        this.mLayoutMatcher.setVisibility(Preferences.m2886f() ? View.VISIBLE : View.GONE);
        this.mLayoutMatcher.setOnClickListener(this.mOnClickListener);
        this.mProgressBar = (ProgressBar) this.mLayoutMatcher.findViewById(R.id.progress_bar);
        this.mIvAction = (ImageView) this.mLayoutMatcher.findViewById(R.id.iv_action);
        this.mTvTitle = (TextView) this.mLayoutMatcher.findViewById(R.id.tv_title);
        this.mTvSubtitle = (TextView) this.mLayoutMatcher.findViewById(R.id.tv_subtitle);
        flushHeaderView();
        this.mIvAction.setOnClickListener(this.mOnClickListener);
        getActivity().registerReceiver(this.mBroadcastReceiver, new IntentFilter(Action.LYRIC_PIC_BATCH_OPERATE_RESULT));
        this.mRegisteredBroadcast = true;
        doBatchLyricPicOperate("query");
    }

    @Override // com.sds.android.ttpod.fragment.base.SlidingClosableFragment, com.sds.android.ttpod.fragment.base.ActionBarFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.m7428a();
        }
        if (this.mRegisteredBroadcast) {
            getActivity().unregisterReceiver(this.mBroadcastReceiver);
            this.mRegisteredBroadcast = false;
        }
    }

    public void clickOneKeyMatch() {
        //LocalStatistic.m5161V();
        //SUserUtils.m4956a(SAction.ACTION_ONE_KEY_MATCH_LRC_PIC_BANNER, SPage.PAGE_NONE);
        doMatchLyricPic();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doMatchLyricPic() {
        if (!this.mMatching) {
            if (EnvironmentUtils.C0604c.m8476d() == -1) {
                PopupsUtils.m6721a("没有网络，臣妾难为无米之炊( ⊙o⊙ )哇。");
                return;
            }
            if (!this.mRegisteredBroadcast) {
                getActivity().registerReceiver(this.mBroadcastReceiver, new IntentFilter(Action.LYRIC_PIC_BATCH_OPERATE_RESULT));
                this.mRegisteredBroadcast = true;
            }
            Preferences.m2929b(true);
            doBatchLyricPicOperate("search");
            this.mMatching = true;
            this.mSuccessCount = 0;
            this.mFailedCount = 0;
            this.mSkipCount = 0;
            flushHeaderView();
        }
    }
}
