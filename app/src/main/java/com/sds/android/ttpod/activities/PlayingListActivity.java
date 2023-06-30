package com.sds.android.ttpod.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.p085b.MediaItemViewHolder;
import com.sds.android.ttpod.component.p091g.p093b.AbsolutePlayerViewController;
import com.sds.android.ttpod.fragment.main.DefaultSkinEventHandler;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.PlayingFragment;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.SkinCache;
import com.sds.android.ttpod.framework.modules.skin.serialskin.SPlaylistView;
import com.sds.android.ttpod.framework.modules.skin.lyric.Lyric;
import com.sds.android.ttpod.framework.modules.skin.view.SkinAbsoluteLayout;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.p134a.PlayMode;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.utils.ThemeUtils;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class PlayingListActivity extends SlidingClosableActivity implements ThemeManager.InterfaceC2019b {
    private static final String TAG = "PlayingListActivity";
    private View mCloseView;
    private ImageView mImageViewPlayMode;
    private View mPlayModeView;
    private TextView mPlayingListTitle;
    private View mPlayingListTitleArea;
    private AbsolutePlayerViewController mViewController;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getSlidingContainer().setEnableScrollingMask(false);
        getSlidingContainer().setShadowWidth(0);
        hideActionBar();
        initContent();
        this.mPlayingListTitleArea = findViewById(R.id.palying_list_title_area);
        if (this.mPlayingListTitleArea == null) {
            this.mPlayingListTitle = (TextView) findViewById(R.id.textview_playing);
        } else {
            this.mPlayingListTitle = (TextView) this.mPlayingListTitleArea.findViewById(R.id.textview_playing);
        }
        this.mImageViewPlayMode = (ImageView) findViewById(R.id.imagebutton_playmode);
        if (this.mImageViewPlayMode != null) {
            this.mPlayModeView = this.mImageViewPlayMode.findViewById(R.id.imagebutton_playmode);
            this.mPlayModeView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.PlayingListActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CommandCenter.getInstance().execute(new Command(CommandID.SWITCH_PLAY_MODE, new Object[0]));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ActionBarActivity, android.app.Activity
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        initViewController();
    }

    private void initViewController() {
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (this.mViewController != null) {
            if (!m3225N.isNull()) {
                this.mViewController.mo6447a(m3225N, (Bitmap) null, (Lyric) null);
                updatePlayMediaInfo();
            } else if (!this.mViewController.m6543J()) {
                this.mViewController.mo6447a(m3225N, (Bitmap) null, (Lyric) null);
            }
        }
        updatePlayMode();
        updateSleepMode();
        updatePlayStatus(SupportFactory.getInstance(BaseApplication.getApplication()).m2463m());
    }

    @SuppressLint("ResourceType")
    private void initContent() {
        SPlaylistView m3842b;
        final View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.PlayingListActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PlayingListActivity.this.finish();
            }
        };
        SkinAbsoluteLayout skinAbsoluteLayout = null;
        SkinCache m3151m = Cache.getInstance().getSkinCache();
        if (m3151m != null && m3151m.serializableSkinNotNull() && (m3842b = m3151m.getSerializableSkin().getPlayerListViewByTransForm(0)) != null) {
            SkinAbsoluteLayout skinAbsoluteLayout2 = new SkinAbsoluteLayout(this);
            this.mViewController = new AbsolutePlayerViewController(this, m3151m, m3842b) { // from class: com.sds.android.ttpod.activities.PlayingListActivity.3
                @Override // com.sds.android.ttpod.component.p091g.p093b.p094a.ViewEventController, com.sds.android.ttpod.component.p091g.p093b.p094a.ViewController
                /* renamed from: a */
                public void mo6456a(View view) {
                    super.mo6456a(view);
                    if (view != null && "CloseButton".equals(view.getTag())) {
                        onClickListener.onClick(view);
                    }
                }
            };
            this.mViewController.mo6452a(new DefaultSkinEventHandler(this, this.mViewController));
            View mo6461a = this.mViewController.getMultiScreenLayout();
            View findViewWithTag = mo6461a.findViewWithTag("WindowTitle");
            if (findViewWithTag instanceof TextView) {
                findViewWithTag.setId(R.id.textview_playing);
            }
            if (mo6461a.findViewWithTag("CloseButton") == null) {
                this.mCloseView = mo6461a.findViewById(R.id.main_content);
                this.mCloseView.setOnClickListener(onClickListener);
            }
            View findViewWithTag2 = mo6461a.findViewWithTag("List");
            if (findViewWithTag2 != null) {
                findViewWithTag2.setId(R.id.playing_list);
            } else {
                mo6461a.setId(R.id.playing_list);
            }
            View findViewWithTag3 = mo6461a.findViewWithTag("Title");
            if (findViewWithTag3 instanceof ViewGroup) {
                findViewWithTag3.setId(R.id.palying_list_title_area);
                getLayoutInflater().inflate(R.layout.activity_playinglist_header, (ViewGroup) findViewWithTag3);
            }
            skinAbsoluteLayout2.setId(R.id.main_content);
            skinAbsoluteLayout2.addView(mo6461a);
            setContentView(skinAbsoluteLayout2);
            skinAbsoluteLayout = skinAbsoluteLayout2;
        }
        if (skinAbsoluteLayout == null) {
            getWindow().setWindowAnimations(16973827);
            setContentView(R.layout.activity_playinglist);
            this.mCloseView = findViewById(R.id.main_content).findViewById(R.id.main_content);
            this.mCloseView.setOnClickListener(onClickListener);
        }
        Bundle bundle = new Bundle();
        bundle.putString("title", getString(R.string.playing));
        bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, Preferences.getLocalGroupId());
        getSupportFragmentManager().beginTransaction().replace(R.id.playing_list, Fragment.instantiate(this, MyPlayingFragment.class.getName(), bundle)).commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.mViewController != null) {
            this.mViewController.mo6441b();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.mViewController != null) {
            this.mViewController.onPanelShow();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.mViewController != null) {
            this.mViewController.onPanelDisappear();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.mViewController != null) {
            if (z) {
                this.mViewController.m6550y();
            } else {
                this.mViewController.m6551x();
            }
        }
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.app.Activity
    public void finish() {
        SPlaylistView m3842b;
        super.finish();
        SkinCache m3151m = Cache.getInstance().getSkinCache();
        if (m3151m != null && m3151m.serializableSkinNotNull() && (m3842b = m3151m.getSerializableSkin().getPlayerListViewByTransForm(0)) != null) {
            overrideActivityOutAnimation(this, m3842b.m3785e());
        }
    }

    private void updatePlayMode(PlayMode playMode) {
        int i;
        if (this.mImageViewPlayMode != null) {
            switch (playMode) {
                case REPEAT:
                    i = R.drawable.img_playmode_repeat_playinglist;
                    break;
                case REPEAT_ONE:
                    i = R.drawable.img_playmode_repeatone_playinglist;
                    break;
                case SHUFFLE:
                    i = R.drawable.img_playmode_shuffle_playinglist;
                    break;
                default:
                    i = R.drawable.img_playmode_sequence_playinglist;
                    break;
            }
            this.mImageViewPlayMode.setImageResource(i);
        }
        if (this.mViewController != null) {
            this.mViewController.onPlayModeChange(playMode);
        }
    }

    @Override // com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        if (this.mPlayingListTitleArea != null) {
            ThemeManager.m3269a(this.mPlayingListTitleArea, ThemeElement.SUB_BAR_BACKGROUND);
            if (this.mPlayingListTitle != null) {
                ThemeManager.m3269a(this.mPlayingListTitle, ThemeElement.SONG_LIST_ITEM_TEXT);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_PLAY_MODE, ReflectUtils.loadMethod(cls, "updatePlayMode", new Class[0]));
        map.put(CommandID.UPDATE_PLAYING_MEDIA_INFO, ReflectUtils.loadMethod(cls, "updatePlayMediaInfo", new Class[0]));
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.loadMethod(cls, "updatePlayStatus", PlayStatus.class));
    }

    public void updatePlayMode() {
        PlayMode m2862l = Preferences.m2862l();
        updatePlayMode(m2862l);
        if (this.mViewController != null) {
            this.mViewController.onPlayModeChange(m2862l);
        }
    }

    public void updateSleepMode() {
        if (this.mViewController != null) {
            this.mViewController.m6525d(((Boolean) CommandCenter.getInstance().m4602a(new Command(CommandID.IS_SLEEP_MODE_ENABLED, new Object[0]), Boolean.class)).booleanValue());
        }
    }

    public void updatePlayMediaInfo() {
        if (this.mViewController != null) {
            this.mViewController.onMetaChange(Cache.getInstance().getCurrentPlayMediaItem());
            this.mViewController.mo6459a(SupportFactory.getInstance(BaseApplication.getApplication()).m2465k().intValue(), SupportFactory.getInstance(BaseApplication.getApplication()).m2464l());
        }
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        if (this.mViewController != null) {
            this.mViewController.mo6446a(playStatus);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTitleText(CharSequence charSequence) {
        if (this.mPlayingListTitle != null) {
            this.mPlayingListTitle.setText(charSequence);
        }
    }

    /* loaded from: classes.dex */
    public static class MyPlayingFragment extends PlayingFragment {
        @Override // com.sds.android.ttpod.fragment.main.list.PlayingFragment
        protected void onFlushMediaItemCountView() {
            FragmentActivity activity = getActivity();
            if (activity instanceof PlayingListActivity) {
                List<MediaItem> mediaItemList = getMediaItemList();
                PlayingListActivity playingListActivity = (PlayingListActivity) activity;
                Object[] objArr = new Object[1];
                objArr[0] = Integer.valueOf(mediaItemList != null ? mediaItemList.size() : 0);
                playingListActivity.updateTitleText(getString(R.string.playlist_with_count, objArr));
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sds.android.ttpod.fragment.main.list.MediaListFragment, com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment
        public View getMediaItemView(MediaItem mediaItem, View view, ViewGroup viewGroup, int i) {
            View mediaItemView = super.getMediaItemView(mediaItem, view, viewGroup, i);
            MediaItemViewHolder mediaItemViewHolder = (MediaItemViewHolder) mediaItemView.getTag();
            mediaItemViewHolder.m6964b(true);
            mediaItemViewHolder.mo3251a();
            return mediaItemView;
        }

        @Override // com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment, com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
        public void onThemeLoaded() {
            super.onThemeLoaded();
            ThemeUtils.m8178a(this.mListView, ThemeElement.PLAYER_MUSIC_LIST_SEPARATOR, ThemeElement.COMMON_SEPARATOR);
            ThemeUtils.m8178a(this.mListFooterView, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SUB_BAR_TEXT);
            ThemeUtils.m8178a(this.mListFooterView, ThemeElement.PLAYER_MUSIC_LIST_BACKGROUND, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        }
    }

    public static void overrideActivityInAnimation(Activity activity, int i) {
        switch (i) {
            case 1:
                activity.overridePendingTransition(R.anim.push_left_in, 0);
                return;
            case 2:
                activity.overridePendingTransition(R.anim.push_right_in, 0);
                return;
            case 3:
                activity.overridePendingTransition(R.anim.push_top_in, 0);
                return;
            case 4:
                activity.overridePendingTransition(R.anim.push_bottom_in, 0);
                return;
            case 5:
                activity.overridePendingTransition(R.anim.fade_in, 0);
                return;
            case 6:
                activity.overridePendingTransition(R.anim.scale_in, 0);
                return;
            default:
                return;
        }
    }

    public static void overrideActivityOutAnimation(Activity activity, int i) {
        switch (i) {
            case 1:
                activity.overridePendingTransition(0, R.anim.push_left_out);
                return;
            case 2:
                activity.overridePendingTransition(0, R.anim.push_right_out);
                return;
            case 3:
                activity.overridePendingTransition(0, R.anim.push_top_out);
                return;
            case 4:
                activity.overridePendingTransition(0, R.anim.push_bottom_out);
                return;
            case 5:
                activity.overridePendingTransition(0, R.anim.fade_out);
                return;
            case 6:
                activity.overridePendingTransition(0, R.anim.scale_out);
                return;
            default:
                return;
        }
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity
    protected boolean needFinishAnimation() {
        return false;
    }
}
