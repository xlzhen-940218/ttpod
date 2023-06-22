package com.sds.android.ttpod.fragment.main;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.lockscreen.p101a.p102a.Animator;
import com.sds.android.ttpod.component.lockscreen.p101a.p102a.ObjectAnimator;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p085b.CheckableActionItem;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.p087d.p088a.EditTextDialog;
import com.sds.android.ttpod.component.p087d.p088a.MultiChoiceListDialog;
import com.sds.android.ttpod.fragment.base.ActionBarFragment;
import com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener;
import com.sds.android.ttpod.fragment.downloadmanager.DownloadManagerFragment;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.GroupListFragment;
import com.sds.android.ttpod.fragment.main.list.LocalMediaEntryFragment;
import com.sds.android.ttpod.fragment.main.list.SubMediaListFragment;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.p126h.UnicomFlowUtil;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.p107a.LocalStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.OnlineMediaStatistic;
import com.sds.android.ttpod.framework.p106a.p107a.SAction;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.p106a.p107a.SUserUtils;
import com.sds.android.ttpod.framework.p106a.p107a.StatisticUtils;
import com.sds.android.ttpod.framework.p106a.p107a.UnicomFlowStatistic;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.environment.PreferencesID;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.ListPopupWindow;
import com.sds.android.ttpod.widget.SimpleGridView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class MyFragment extends BaseFragment {
    private static final int GRIDDLE_ANIMATION_DURATION = 500;
    private static final float GRIDDLE_END_ANGLE = 30.0f;
    private static final float GRIDDLE_START_ANGLE = -30.0f;
    private static final int ID_CREATE_SONGLIST = 6;
    private static final int ID_CUSTOMIZED_HOMEPAGE = 8;
    private static final int ID_MY_DOWNLOAD = 1;
    private static final int ID_MY_FAVORITE = 0;
    private static final int ID_MY_SONGLIST = 2;
    private static final int ID_RECENT_ADDED = 4;
    private static final int ID_RECENT_PLAY = 3;
    private static final int ID_SCAN_MEDIA = 7;
    private static final int ID_USER_CREATED = 5;
    private ListPopupWindow mDropdownMenu;
    private View mHeaderView;
    private CheckableActionItem[] mItems;
    private IconTextView mItvLocalMusic;
    private IconTextView mItvMenu;
    private IconTextView mItvPlay;
    private View mLayoutLocalMusic;
    private C1474a mMyEntryManager;
    private View mRootView;
    private SimpleGridView mSgvEntry;
    private TextView mTvLocalMusicCount;
    private TextView mTvLocalMusicTitle;
    private ImageButton mUnicomCloseButton;
    private View mUnicomLayout;
    private TextView mUnicomText;
    private static final String[] THEME_ID_RESOURCE_MAP = {ThemeElement.HOME_FAVORITE_IMAGE, ThemeElement.HOME_DOWNLOAD_IMAGE, ThemeElement.HOME_SONG_LIST_IMAGE, ThemeElement.HOME_RECENT_PLAY_IMAGE, ThemeElement.HOME_RECENT_ADD_IMAGE, ThemeElement.HOME_ARTIST_IMAGE, ThemeElement.HOME_ALBUM_IMAGE, ThemeElement.HOME_FOLDER_IMAGE};
    private static final String[] HOME_ELEMENT_ID = {ThemeElement.HOME_IMG_FAVORITE, ThemeElement.HOME_IMG_DOWNLOAD, ThemeElement.HOME_IMG_SONG_LIST, ThemeElement.HOME_IMG_RECENT_PLAY, ThemeElement.HOME_IMG_RECENT_ADD, ThemeElement.HOME_IMG_ARTIST, ThemeElement.HOME_IMG_ALBUM, ThemeElement.HOME_IMG_FOLDER};
    private boolean mReloadTheme = true;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.main.MyFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Boolean valueOf = Boolean.valueOf(MyFragment.getMediaCount(MediaStorage.GROUP_ID_ALL_LOCAL) > 0);
            if (view != MyFragment.this.mItvPlay) {
                if (view != MyFragment.this.mLayoutLocalMusic) {
                    if (view != MyFragment.this.mItvMenu) {
                        if (view != MyFragment.this.mUnicomText) {
                            if (view == MyFragment.this.mUnicomCloseButton) {
                                MyFragment.this.mUnicomLayout.setVisibility(View.GONE);
                                UnicomFlowStatistic.m4815l();
                                new SUserEvent("PAGE_CLICK", 1121, 0).post();
                                Cache.m3218a().m3162g(false);
                                return;
                            }
                            Object tag = view.getTag();
                            if (tag instanceof ActionItem) {
                                MyFragment.this.doClickActionItem((ActionItem) tag, false);
                                return;
                            }
                            return;
                        }
                        UnicomFlowStatistic.m4816k();
                        new SUserEvent("PAGE_CLICK", 1122, 0).post();
                        EntryUtils.m8295b((Activity) MyFragment.this.getActivity());
                        return;
                    }
                    LocalStatistic.m5131ab();
                    MyFragment.this.popupDropdownMenu(view);
                    return;
                }
                SUserUtils.m4956a(SAction.ACTION_LOCAL_MUSIC, SPage.PAGE_LOCAL_SONG);
                MyFragment.this.launchFragment((BaseFragment) Fragment.instantiate(MyFragment.this.getActivity(), LocalMediaEntryFragment.class.getName()));
                LocalStatistic.m5178E();
                LocalStatistic.m5166Q();
                OnlineMediaStatistic.m5045a("local");
                return;
            }
            LocalStatistic.m5132aa();
            MyFragment.this.tryRandomPlay(view, valueOf.booleanValue());
            SUserUtils.m4956a(SAction.ACTION_RANDOM_PLAY, SPage.PAGE_NONE);
        }
    };
    private Preferences.InterfaceC2031a mPreferencesChangeListener = new Preferences.InterfaceC2031a() { // from class: com.sds.android.ttpod.fragment.main.MyFragment.2
        @Override // com.sds.android.ttpod.framework.storage.environment.Preferences.InterfaceC2031a
        /* renamed from: a */
        public void mo2553a(PreferencesID preferencesID) {
            if (preferencesID != PreferencesID.IS_SHOW_DOWNLOAD_HIGHLIGHT) {
                return;
            }
            MyFragment.this.mMyEntryManager.m5684a(MyFragment.this.mItems);
        }
    };
    private OnDropdownMenuClickListener mMenuClickListener = new OnDropdownMenuClickListener() { // from class: com.sds.android.ttpod.fragment.main.MyFragment.3
        @Override // com.sds.android.ttpod.fragment.base.OnDropdownMenuClickListener
        public void onDropDownMenuClicked(int i, ActionItem actionItem) {
            switch (i) {
                case 7:
                    EntryUtils.m8289f(MyFragment.this.getActivity());
                    LocalStatistic.m5130ac();
                    SUserUtils.m4956a(SAction.ACTION_MY_DROP_MENU_SCAN_MUSIC, SPage.PAGE_SCAN_MUSIC);
                    return;
                case 8:
                    StatisticUtils.m4910a("local", "click", "local-customized_homepage");
                    MyFragment.this.displayCustomizedHomepage();
                    LocalStatistic.m5129ad();
                    SUserUtils.m4956a(SAction.ACTION_MY_DROP_MENU_CUSTOM_MY, SPage.PAGE_NONE);
                    return;
                default:
                    MyFragment.this.doClickActionItem(actionItem, true);
                    return;
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void popupDropdownMenu(View view) {
        CheckableActionItem[] checkableActionItemArr;
        SUserUtils.m4956a(SAction.ACTION_MY_DROP_MENU, SPage.PAGE_NONE);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ActionItem(7, (int) R.drawable.img_actionitem_add_media, (int) R.string.menu_scan_media));
        for (CheckableActionItem checkableActionItem : this.mItems) {
            if (!checkableActionItem.isChecked()) {
                arrayList.add(checkableActionItem);
            }
        }
        arrayList.add(new ActionItem(8, 0, (int) R.string.menu_customized_homepage));
        this.mDropdownMenu = ActionBarFragment.createDropDownMenu(getActivity(), arrayList, this.mMenuClickListener);
        this.mDropdownMenu.setWidth((int) getResources().getDimension(R.dimen.drop_down_top_right_menu_width));
        this.mDropdownMenu.showAsDropDown(view, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayCustomizedHomepage() {
        MultiChoiceListDialog multiChoiceListDialog = new MultiChoiceListDialog(getActivity(), new ArrayList(Arrays.asList(this.mItems)), new BaseDialog.InterfaceC1064a<MultiChoiceListDialog>() { // from class: com.sds.android.ttpod.fragment.main.MyFragment.4
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MultiChoiceListDialog multiChoiceListDialog2) {
                MyFragment.this.saveCustomizedHomepage();
                MyFragment.this.mMyEntryManager.m5684a(MyFragment.this.mItems);
            }
        }, new BaseDialog.InterfaceC1064a<MultiChoiceListDialog>() { // from class: com.sds.android.ttpod.fragment.main.MyFragment.5
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo2038a(MultiChoiceListDialog multiChoiceListDialog2) {
                MyFragment.this.loadCustomizedHomepage();
            }
        });
        multiChoiceListDialog.setTitle(R.string.choose_homepage_content);
        multiChoiceListDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadCustomizedHomepage() {
        String m2922bg = Preferences.m2922bg();
        if (StringUtils.m8346a(m2922bg)) {
            initHomepageSetting();
            return;
        }
        for (int i = 0; i < this.mItems.length; i++) {
            this.mItems[i].setChecked(m2922bg.contains(HOME_ELEMENT_ID[i]));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveCustomizedHomepage() {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", SAction.ACTION_MY_DROP_MENU_CUSTOM_MY_OK.getValue(), 0, 0);
        sUserEvent.setPageParameter(true);
        StringBuilder sb = new StringBuilder(PreferencesID.HOMEPAGE_ELEMENT_SETTING.name());
        for (int i = 0; i < this.mItems.length; i++) {
            if (this.mItems[i].isChecked()) {
                sb.append(HOME_ELEMENT_ID[i]);
            }
        }
        sUserEvent.append(PreferencesID.HOMEPAGE_ELEMENT_SETTING.name(), sb.toString());
        sUserEvent.post();
        Preferences.m2824u(sb.toString());
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(SPage.PAGE_MY);
        initHomepageSetting();
        loadCustomizedHomepage();
    }

    private void initHomepageSetting() {
        this.mItems = new CheckableActionItem[]{new CheckableActionItem(0, 0, R.string.mine_my_favorite, R.string.icon_my_favorite, true), new CheckableActionItem(1, 0, R.string.mine_my_download, R.string.icon_my_download, true), new CheckableActionItem(2, 0, R.string.my_playlist, R.string.icon_my_songlist, true), new CheckableActionItem(3, 0, R.string.mine_recent_play, R.string.icon_my_recent_play, true), new CheckableActionItem(4, 0, R.string.local_music_recent_add, R.string.icon_my_recent_add, false)};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_MEDIA_LIBRARY_CHANGED, ReflectUtils.m8375a(cls, "updateMediaLibraryChanged", String.class));
        map.put(CommandID.PUSH_FAVORITE_ONLINE_MEDIA_LIST_COMPLETE, ReflectUtils.m8375a(cls, "pushFavoriteOnlineMediaListComplete", new Class[0]));
        map.put(CommandID.UPDATE_ADD_DOWNLOAD_TASK_DATABASE, ReflectUtils.m8375a(cls, "updateAddDownloadTaskDatabase", DownloadTaskInfo.class));
        map.put(CommandID.UPDATE_ADD_DOWNLOAD_TASK_LIST_DATABASE, ReflectUtils.m8375a(cls, "updateAddDownloadTaskListDatabase", List.class));
        map.put(CommandID.USER_ADDED_FAVORITE_MEDIA, ReflectUtils.m8375a(cls, "userAddedFavoriteMedia", MediaItem.class));
    }

    public void updateMediaLibraryChanged(String str) {
        loadCount();
        this.mMyEntryManager.m5684a(this.mItems);
    }

    public void pushFavoriteOnlineMediaListComplete() {
        this.mMyEntryManager.m5684a(this.mItems);
    }

    public void userAddedFavoriteMedia(MediaItem mediaItem) {
        Preferences.m3035W(true);
        this.mMyEntryManager.m5684a(this.mItems);
    }

    public void updateAddDownloadTaskDatabase(DownloadTaskInfo downloadTaskInfo) {
        if (!Preferences.m2994aM() && downloadTaskInfo.getType() == DownloadTaskInfo.TYPE_AUDIO) {
            Preferences.m3043S(true);
        }
    }

    public void updateAddDownloadTaskListDatabase(List<DownloadTaskInfo> list) {
        if (!Preferences.m2994aM() && !list.isEmpty() && list.get(0).getType() == DownloadTaskInfo.TYPE_AUDIO) {
            Preferences.m3043S(true);
        }
    }

    private void loadCount() {
        this.mTvLocalMusicCount.setText(getString(R.string.count_of_media, Integer.valueOf(getMediaCount(MediaStorage.GROUP_ID_ALL_LOCAL))));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_main_my_header_all, (ViewGroup) null, false);
            this.mHeaderView = this.mRootView;
            this.mLayoutLocalMusic = this.mHeaderView.findViewById(R.id.layout_local_music);
            this.mUnicomText = (TextView) this.mHeaderView.findViewById(R.id.text_unicom);
            this.mUnicomCloseButton = (ImageButton) this.mHeaderView.findViewById(R.id.image_button_unicom_close);
            this.mUnicomLayout = this.mHeaderView.findViewById(R.id.layout_unicom);
            initHomeUnicomLayout();
            this.mItvLocalMusic = (IconTextView) this.mHeaderView.findViewById(R.id.itv_local_music);
            this.mTvLocalMusicCount = (TextView) this.mHeaderView.findViewById(R.id.text_count);
            this.mItvPlay = (IconTextView) this.mHeaderView.findViewById(R.id.itv_play);
            this.mItvMenu = (IconTextView) this.mHeaderView.findViewById(R.id.itv_menu);
            this.mTvLocalMusicTitle = (TextView) this.mHeaderView.findViewById(R.id.text_title);
            this.mSgvEntry = (SimpleGridView) this.mHeaderView.findViewById(R.id.sgv_entry);
            this.mSgvEntry.setNumColumns(2);
            this.mMyEntryManager = new C1474a(this.mSgvEntry);
            this.mMyEntryManager.m5684a(this.mItems);
        }
        setClickListener(this.mOnClickListener);
        loadCount();
        return this.mRootView;
    }

    private void setClickListener(View.OnClickListener onClickListener) {
        this.mLayoutLocalMusic.setOnClickListener(onClickListener);
        this.mUnicomText.setOnClickListener(onClickListener);
        this.mUnicomCloseButton.setOnClickListener(onClickListener);
        this.mItvPlay.setOnClickListener(onClickListener);
        this.mItvMenu.setOnClickListener(onClickListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        PopupsUtils.m6723a(this.mDropdownMenu);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Preferences.m2938b(PreferencesID.IS_ONLY_USE_WIFI, this.mPreferencesChangeListener);
        Preferences.m2938b(PreferencesID.IS_SHOW_DOWNLOAD_HIGHLIGHT, this.mPreferencesChangeListener);
        setClickListener(null);
    }

    private boolean isShowUnicomLayout() {
        return UnicomFlowUtil.m3951b() && Cache.m3218a().m3226M();
    }

    private void initHomeUnicomLayout() {
        if (isShowUnicomLayout()) {
            this.mUnicomLayout.setVisibility(View.VISIBLE);
            UnicomFlowStatistic.m4814m();
            new SUserEvent("PAGE_CLICK", 1120, 0).post();
            return;
        }
        this.mUnicomLayout.setVisibility(View.GONE);
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Preferences.m3019a(PreferencesID.IS_ONLY_USE_WIFI, this.mPreferencesChangeListener);
        Preferences.m3019a(PreferencesID.IS_SHOW_DOWNLOAD_HIGHLIGHT, this.mPreferencesChangeListener);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        if (!isViewAccessAble()) {
            this.mHeaderView = null;
            this.mMyEntryManager = null;
            this.mRootView = null;
            this.mReloadTheme = true;
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        if (isViewAccessAble() && this.mReloadTheme) {
            this.mReloadTheme = false;
            setActionItemIcon(this.mItems[0], ThemeElement.HOME_FAVORITE_IMAGE);
            setActionItemIcon(this.mItems[1], ThemeElement.HOME_DOWNLOAD_IMAGE);
            setActionItemIcon(this.mItems[3], ThemeElement.HOME_RECENT_PLAY_IMAGE);
            ThemeManager.m3269a(this.mLayoutLocalMusic, ThemeElement.HOME_BACKGROUND);
            ThemeManager.m3269a(this.mTvLocalMusicTitle, ThemeElement.HOME_TEXT);
            ThemeManager.m3269a(this.mTvLocalMusicCount, ThemeElement.HOME_TEXT);
            ThemeUtils.m8172a(this.mItvMenu, ThemeElement.HOME_MENU_IMAGE, (int) R.string.icon_my_music_menu, ThemeElement.HOME_TEXT);
            ThemeUtils.m8172a(this.mItvPlay, ThemeElement.HOME_RANDOM_PLAY_IMAGE, (int) R.string.icon_my_luck_play, ThemeElement.HOME_TEXT);
            ThemeUtils.m8172a(this.mItvLocalMusic, ThemeElement.HOME_MUSIC_IMAGE, (int) R.string.icon_my_music, ThemeElement.HOME_TEXT);
        }
    }

    private void setActionItemIcon(ActionItem actionItem, String str) {
        Drawable m3265a = ThemeManager.m3265a(str);
        if (m3265a != null) {
            actionItem.m7007b(m3265a);
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment
    public void onThemeChanged() {
        this.mReloadTheme = true;
        super.onThemeChanged();
        refreshListViewTheme();
    }

    private void refreshListViewTheme() {
        if (this.mMyEntryManager == null) {
            return;
        }
        this.mMyEntryManager.m5684a(this.mItems);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryRandomPlay(final View view, final boolean z) {
        view.clearAnimation();
        ObjectAnimator mo5991b = ObjectAnimator.m6059a(view, "rotation", 0.0f, GRIDDLE_START_ANGLE, 0.0f, GRIDDLE_END_ANGLE, 0.0f).mo5991b(500L);
        mo5991b.m6088a(new Animator.InterfaceC1283a() { // from class: com.sds.android.ttpod.fragment.main.MyFragment.6
            @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.Animator.InterfaceC1283a
            /* renamed from: a */
            public void mo5692a(Animator animator) {
            }

            @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.Animator.InterfaceC1283a
            /* renamed from: b */
            public void mo5691b(Animator animator) {
                view.post(new Runnable() { // from class: com.sds.android.ttpod.fragment.main.MyFragment.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        String string;
                        if (!(z ? ((Boolean) CommandCenter.m4607a().m4602a(new Command(CommandID.PLAY_LOCAL_RANDOM, new Object[0]), Boolean.class)).booleanValue() : false)) {
                            PopupsUtils.m6721a("没有歌曲，臣妾做不到啊");
                            return;
                        }
                        MediaItem mediaItem = (MediaItem) CommandCenter.m4607a().m4602a(new Command(CommandID.QUERY_MEDIA_ITEM, Preferences.m2858m(), Preferences.m2854n()), MediaItem.class);
                        if (mediaItem != null && TTTextUtils.isValidateMediaString(mediaItem.getArtist())) {
                            string = MyFragment.this.getString(R.string.random_song_info, mediaItem.getArtist());
                        } else {
                            string = MyFragment.this.getString(R.string.unknown_artist);
                        }
                        PopupsUtils.m6721a(string);
                    }
                });
            }

            @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.Animator.InterfaceC1283a
            /* renamed from: c */
            public void mo5690c(Animator animator) {
            }

            @Override // com.sds.android.ttpod.component.lockscreen.p101a.p102a.Animator.InterfaceC1283a
            /* renamed from: d */
            public void mo5689d(Animator animator) {
            }
        });
        mo5991b.mo6004a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doClickActionItem(ActionItem actionItem, boolean z) {
        FragmentActivity activity = getActivity();
        int m7005e = actionItem.m7005e();
        if (m7005e == 0) {
            launchSubMediaListFragment(MediaStorage.GROUP_ID_FAV, getString(R.string.mine_my_favorite));
            Preferences.m3035W(false);
            this.mMyEntryManager.m5684a(this.mItems);
            LocalStatistic.m5177F();
            SUserUtils.m4956a(SAction.ACTION_MY_FAVORITE, SPage.PAGE_MY_FAVORITE);
            OnlineMediaStatistic.m5045a("favorite");
        } else if (1 == m7005e) {
            launchFragment((BaseFragment) Fragment.instantiate(activity, DownloadManagerFragment.class.getName()));
            LocalStatistic.m5170M();
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", SAction.ACTION_MY_DOWNLOAD.getValue(), 0, SPage.PAGE_MY_DOWNLOAD_DOWNLOADED.getValue());
            sUserEvent.append(DownloadManagerFragment.DOWNLOAD_TYPE, DownloadTaskInfo.TYPE_AUDIO);
            sUserEvent.setPageParameter(true);
            sUserEvent.post();
        } else if (2 == m7005e) {
            Bundle bundle = new Bundle();
            bundle.putString(GroupListFragment.KEY_GROUP_TYPE, GroupType.CUSTOM_LOCAL.name());
            bundle.putString("key_list_title", this.mItems[2].m7006d().toString());
            bundle.putBoolean("key_list_creatable", true);
            bundle.putBoolean("key_list_draggable", true);
            BaseFragment baseFragment = (BaseFragment) Fragment.instantiate(activity, SubCustomGroupListFragment.class.getName(), bundle);
            baseFragment.setPage(SPage.PAGE_MY_SONGLIST);
            launchFragment(baseFragment);
            LocalStatistic.m5176G();
            SUserUtils.m4956a(SAction.ACTION_MY_SONGLIST, SPage.PAGE_MY_SONGLIST);
        } else if (3 == m7005e) {
            launchSubMediaListFragment(MediaStorage.GROUP_ID_RECENTLY_PLAY, getString(R.string.mine_recent_play));
            LocalStatistic.m5158Y();
            SUserUtils.m4956a(SAction.ACTION_RECENT_PLAY, SPage.PAGE_RECENT_PLAY);
        } else if (4 == m7005e) {
            launchSubMediaListFragment(MediaStorage.GROUP_ID_RECENTLY_ADD, getString(R.string.local_music_recent_add));
            LocalStatistic.m5157Z();
            SUserUtils.m4956a(SAction.ACTION_RECENT_ADDED, SPage.PAGE_RECENT_ADDED);
        } else if (6 == m7005e) {
            PopupsUtils.m6710b(getActivity(), (String) null, new BaseDialog.InterfaceC1064a<EditTextDialog>() { // from class: com.sds.android.ttpod.fragment.main.MyFragment.7
                @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo2038a(EditTextDialog editTextDialog) {
                    CommandCenter.m4607a().m4602a(new Command(CommandID.ADD_GROUP, editTextDialog.m6902c(0).m6896d().toString()), String.class);
                }
            });
        }
    }

    protected void launchSubMediaListFragment(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, str);
        bundle.putString(SubMediaListFragment.KEY_GROUP_NAME, str2);
        launchFragment((BaseFragment) instantiate(getActivity(), SubMediaListFragment.selectSubMediaListFragmentClassName(str), bundle));
    }

    public static void displayHighlightFlag(View view, ActionItem actionItem) {
        boolean m2994aM;
        if (actionItem.m7005e() == 0) {
            m2994aM = Preferences.m2991aP();
        } else {
            m2994aM = actionItem.m7005e() == 1 ? Preferences.m2994aM() : false;
        }
        view.setVisibility(m2994aM ? View.VISIBLE : View.INVISIBLE);
    }

    public static int getMediaCount(String str) {
        return ((Integer) CommandCenter.m4607a().m4602a(new Command(CommandID.QUERY_MEDIA_COUNT, str), Integer.class)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.fragment.main.MyFragment$a */
    /* loaded from: classes.dex */
    public final class C1474a {

        /* renamed from: b */
        private SimpleGridView sgvEntryGridView;

        private C1474a(SimpleGridView simpleGridView) {
            this.sgvEntryGridView = simpleGridView;
        }

        /* renamed from: a */
        private void m5687a(int index) {
            int childCount = this.sgvEntryGridView.getChildCount();
            if (childCount > index) {
                for (int i2 = childCount - 1; i2 >= index; i2--) {
                    this.sgvEntryGridView.removeViewAt(i2);
                }
            } else if (childCount < index) {
                while (childCount < index) {
                    this.sgvEntryGridView.addView(m5683b(childCount));
                    childCount++;
                }
            }
        }

        /* renamed from: b */
        private View m5683b(int i) {
            View inflate = View.inflate(MyFragment.this.getActivity(), R.layout.fragment_main_mine_item, null);
            inflate.setOnClickListener(MyFragment.this.mOnClickListener);
            return inflate;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m5684a(CheckableActionItem[] checkableActionItemArr) {
            int i = 0;
            for (CheckableActionItem checkableActionItem : checkableActionItemArr) {
                if (checkableActionItem.isChecked()) {
                    i++;
                }
            }
            m5687a(i);
            int i2 = 0;
            for (CheckableActionItem checkableActionItem2 : checkableActionItemArr) {
                if (checkableActionItem2.isChecked()) {
                    m5686a(this.sgvEntryGridView.getChildAt(i2), checkableActionItem2);
                    i2++;
                }
            }
        }

        /* renamed from: a */
        private void m5686a(View view, ActionItem actionItem) {
            view.setTag(actionItem);
            TextView textView = (TextView) view.findViewById(R.id.text_title);
            textView.setText(actionItem.m7006d());
            IconTextView iconTextView = (IconTextView) view.findViewById(R.id.new_flag);
            MyFragment.displayHighlightFlag(iconTextView, actionItem);
            ThemeUtils.m8172a((IconTextView) view.findViewById(R.id.itv_icon), MyFragment.THEME_ID_RESOURCE_MAP[actionItem.m7005e()], actionItem.m7001i(), ThemeElement.HOME_TEXT);
            ThemeUtils.m8172a(iconTextView, ThemeElement.HOME_NEW_IMAGE, (int) R.string.icon_dot, ThemeElement.HOME_TEXT);
            //ThemeManager.m3269a(view, ThemeElement.HOME_BACKGROUND);
            ThemeManager.m3269a(textView, ThemeElement.HOME_TEXT);
        }
    }
}
