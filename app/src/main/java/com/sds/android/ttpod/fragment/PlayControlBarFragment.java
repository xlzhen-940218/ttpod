package com.sds.android.ttpod.fragment;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.sds.android.sdk.lib.util.DateUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.MainActivity;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.fragment.main.BasePlayerFragment;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.view.AnimTransView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.ThemeFramework;
import com.sds.android.ttpod.framework.modules.theme.ThemeManager;
import com.sds.android.ttpod.framework.p106a.C1780b;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.utils.ThemeUtils;
import com.sds.android.ttpod.widget.ArtistFrameView;
import com.sds.android.ttpod.widget.MarqueeTextView;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class PlayControlBarFragment extends BasePlayerFragment {
    private static final String TAG = "PlayControlBarFragment";
    private ArtistFrameView mArtistFrameView;
    private MarqueeTextView mArtistName;
    private int mDuration;
    private IconTextView mItvMenu;
    private IconTextView mItvNext;
    private IconTextView mItvPause;
    private IconTextView mItvPlay;
    private View mPlayPanel;
    private TextView mPlayPosition;
    private View mRootView;
    private SeekBar mSeekBar;
    private AnimTransView mSongImage;
    private MarqueeTextView mSongName;
    private Integer mTrySeekPosition;
    private int mUpdatePlayPositionCount;
    private Drawable mThemeArtsDrawable = null;
    private View.OnClickListener mClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.fragment.PlayControlBarFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((BaseActivity) PlayControlBarFragment.this.getActivity()).acquireFastClickSupport();
            switch (view.getId()) {
                case R.id.itv_playcontrolbar_menu /* 2131231545 */:
                    FragmentActivity activity = PlayControlBarFragment.this.getActivity();
                    if (activity instanceof MainActivity) {
                        ((MainActivity) activity).toggleMenu();
                        return;
                    }
                    return;
                case R.id.itv_playcontrolbar_next /* 2131231546 */:
                    CommandCenter.getInstance().execute(new Command(CommandID.NEXT, new Object[0]));
                    //LocalStatistic.m5180C();
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PLAYBAR_NEXT.getValue(), SPage.PAGE_PLAY_BAR.getValue(), SPage.PAGE_NONE.getValue()).post();
                    return;
                case R.id.itv_playcontrolbar_play /* 2131231547 */:
                    if (SupportFactory.getInstance(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PAUSED) {
                        CommandCenter.getInstance().execute(new Command(CommandID.RESUME, new Object[0]));
                    } else if (SupportFactory.getInstance(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_STOPPED) {
                        CommandCenter.getInstance().execute(new Command(CommandID.START, new Object[0]));
                    }
                    //LocalStatistic.m5182A();
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PLAYBAR_PLAY.getValue(), SPage.PAGE_PLAY_BAR.getValue(), SPage.PAGE_NONE.getValue()).post();
                    return;
                case R.id.itv_playcontrolbar_pause /* 2131231548 */:
                    CommandCenter.getInstance().execute(new Command(CommandID.PAUSE, new Object[0]));
                    //LocalStatistic.m5181B();
                    //new SUserEvent("PAGE_CLICK", SAction.ACTION_CLICK_PLAYBAR_PAUSE.getValue(), SPage.PAGE_PLAY_BAR.getValue(), SPage.PAGE_NONE.getValue()).post();
                    return;
                default:
                    return;
            }
        }
    };

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment, com.sds.android.ttpod.framework.base.BaseFragment
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_PLAY_POSITION, ReflectUtils.m8375a(cls, "updatePlayPosition", Integer.class));
        map.put(CommandID.UPDATE_PICTURE_DELETED, ReflectUtils.m8375a(cls, "pictureDeleted", MediaItem.class));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_playcontrolbar, viewGroup, false);
        this.mItvPlay = (IconTextView) this.mRootView.findViewById(R.id.itv_playcontrolbar_play);
        this.mItvPause = (IconTextView) this.mRootView.findViewById(R.id.itv_playcontrolbar_pause);
        this.mItvNext = (IconTextView) this.mRootView.findViewById(R.id.itv_playcontrolbar_next);
        this.mItvMenu = (IconTextView) this.mRootView.findViewById(R.id.itv_playcontrolbar_menu);
        this.mArtistName = (MarqueeTextView) this.mRootView.findViewById(R.id.textview_playcontrolbar_artist);
        this.mSongName = (MarqueeTextView) this.mRootView.findViewById(R.id.textview_playcontrolbar_title);
        this.mPlayPosition = (TextView) this.mRootView.findViewById(R.id.textview_playcontrolbar_playposition);
        this.mArtistFrameView = (ArtistFrameView) this.mRootView.findViewById(R.id.view_playcontrol_bar_artist);
        this.mSongImage = this.mArtistFrameView.getAnimTransView();
        this.mSeekBar = (SeekBar) this.mRootView.findViewById(R.id.seekbar_playcontrolbar_progress);
        this.mPlayPanel = this.mRootView.findViewById(R.id.view_playcontrolbar_attach_playerpanel);
        this.mItvPlay.setOnClickListener(this.mClickListener);
        this.mItvPause.setOnClickListener(this.mClickListener);
        this.mItvMenu.setOnClickListener(this.mClickListener);
        this.mItvNext.setOnClickListener(this.mClickListener);
        return this.mRootView;
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updatePlayMediaInfo();
        updatePlayStatus(SupportFactory.getInstance(BaseApplication.getApplication()).m2463m());
        updatePlayPosition(SupportFactory.getInstance(BaseApplication.getApplication()).m2465k());
        if (!StringUtils.isEmpty(Cache.getInstance().m3164g())) {
            artistBitmapLoadFinished();
        }
    }

    @Override // com.sds.android.ttpod.framework.base.BaseFragment, com.sds.android.ttpod.framework.modules.theme.ThemeManager.InterfaceC2019b
    public void onThemeLoaded() {
        super.onThemeLoaded();
        LogUtils.debug(TAG, "PlayControlBarFragment onThemeLoaded");
        ThemeUtils.m8172a(this.mItvPlay, ThemeElement.PLAY_BAR_PLAY_IMAGE, (int) R.string.icon_playbar_play, ThemeElement.PLAY_BAR_TEXT);
        ThemeUtils.m8172a(this.mItvPause, ThemeElement.PLAY_BAR_PAUSE_IMAGE, (int) R.string.icon_playbar_pause, ThemeElement.PLAY_BAR_TEXT);
        ThemeUtils.m8172a(this.mItvNext, ThemeElement.PLAY_BAR_NEXT_IMAGE, (int) R.string.icon_playbar_next, ThemeElement.PLAY_BAR_TEXT);
        ThemeUtils.m8172a(this.mItvMenu, ThemeElement.PLAY_BAR_SIDEBAR_IMAGE, (int) R.string.icon_menu_with_four_point, ThemeElement.PLAY_BAR_TEXT);
        setBackgroundHeight(this.mRootView);
        ThemeManager.m3269a(this.mRootView, ThemeElement.PLAY_BAR_BACKGROUND);
        ThemeManager.m3269a(this.mSongImage, ThemeElement.PLAY_BAR_DEF_ARTIST_IMAGE);
        ThemeManager.m3269a(this.mSongName, ThemeElement.PLAY_BAR_TEXT);
        ThemeManager.m3269a(this.mArtistName, ThemeElement.PLAY_BAR_SUB_TEXT);
        ThemeUtils.m8177a(this.mSeekBar);
        this.mThemeArtsDrawable = null;
        Bitmap currentArtistBitmap = getCurrentArtistBitmap();
        if (currentArtistBitmap == null) {
            loadArtsThemeImage();
        } else {
            this.mSongImage.setImageBitmap(currentArtistBitmap);
        }
        this.mArtistFrameView.m1904a();
    }

    private void setBackgroundHeight(View view) {
        int i;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.playcontrol_bar_height);
        ThemeFramework.AbstractC2016e m3258b = ThemeManager.m3258b(ThemeElement.PLAY_BAR_BACKGROUND);
        if (m3258b != null) {
            i = m3258b.m3286f();
            if (i <= dimensionPixelSize) {
                i = dimensionPixelSize;
            }
        } else {
            i = dimensionPixelSize;
        }
        view.getLayoutParams().height = i;
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void updatePlayMediaInfo() {
        if (isViewAccessAble()) {
            MediaItem mediaItem = Cache.getInstance().getCurrentPlayMediaItem();
            boolean isNull = mediaItem.isNull();
            this.mSongName.setText(isNull ? getString(R.string.lyric_ttpod) : mediaItem.getTitle());
            this.mArtistName.setText(isNull ? "" : TTTextUtils.validateString(this.mArtistName.getContext(), mediaItem.getArtist()));
            this.mDuration = isNull ? 0 : mediaItem.getDuration().intValue();
            this.mSeekBar.setMax(this.mDuration);
            updatePlayPosition(0);
            if (isNull) {
                setArtistBitmap(null, null);
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void updatePlayStatus(PlayStatus playStatus) {
        super.updatePlayStatus(playStatus);
        if (isViewAccessAble()) {
            if (playStatus == PlayStatus.STATUS_PLAYING) {
                this.mItvPlay.setVisibility(View.INVISIBLE);
                this.mItvPause.setVisibility(View.VISIBLE);
                return;
            }
            this.mItvPlay.setVisibility(View.VISIBLE);
            this.mItvPause.setVisibility(View.INVISIBLE);
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void playMediaChanged() {
        super.playMediaChanged();
        if (isViewAccessAble()) {
            LogUtils.debug(TAG, "PlayControlBarFragment playMediaChanged");
            if (getCurrentArtistBitmap() == null) {
                loadArtsThemeImage();
            }
        }
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        startUpdatePlayPosition();
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment, com.sds.android.ttpod.framework.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        stopUpdatePlayPosition();
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void updatePlayPosition() {
        int i = this.mUpdatePlayPositionCount;
        this.mUpdatePlayPositionCount = i + 1;
        if (i >= 20) {
            this.mUpdatePlayPositionCount = 0;
            super.updatePlayPosition();
            updatePlayPosition(SupportFactory.getInstance(BaseApplication.getApplication()).m2465k());
        }
    }

    public void updatePlayPosition(Integer num) {
        if (isViewAccessAble()) {
            if (this.mTrySeekPosition != null) {
                num = this.mTrySeekPosition;
            }
            if (!Cache.getInstance().getCurrentPlayMediaItem().isNull()) {
                if (this.mPlayPosition.getVisibility() == View.VISIBLE) {
                    this.mPlayPosition.setText(DateUtils.m8430a(num.intValue()) + "-" + DateUtils.m8430a(this.mDuration));
                }
                this.mSeekBar.setProgress(num.intValue());
                this.mSeekBar.setSecondaryProgress(Cache.getInstance().getCurrentPlayMediaItem().isOnline() ? (int) (SupportFactory.getInstance(BaseApplication.getApplication()).m2464l() * this.mSeekBar.getMax()) : 0);
                return;
            }
            this.mPlayPosition.setText("");
            this.mSeekBar.setProgress(0);
            this.mSeekBar.setSecondaryProgress(0);
        }
    }

    public Rect getPlayerPanelAttachRawRect() {
        int[] iArr = new int[2];
        this.mPlayPanel.getLocationOnScreen(iArr);
        return new Rect(iArr[0], iArr[1], iArr[0] + this.mPlayPanel.getMeasuredWidth(), iArr[1] + this.mPlayPanel.getMeasuredHeight());
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void artistBitmapDownloadStarted() {
        super.artistBitmapDownloadStarted();
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void artistBitmapSearchStarted() {
        super.artistBitmapSearchStarted();
    }

    @Override // com.sds.android.ttpod.fragment.main.BasePlayerFragment
    public void setArtistBitmap(Bitmap bitmap, String str) {
        if (!StringUtils.equals(getCurrentArtistBitmapPath(), str)) {
            if (bitmap != null) {
                bitmap = cropRoundedBitmap(bitmap);
            }
            super.setArtistBitmap(bitmap, str);
            if (isViewAccessAble()) {
                if (bitmap == null) {
                    loadArtsThemeImage();
                } else {
                    this.mSongImage.setImageBitmap(bitmap);
                }
            }
        }
    }

    private Bitmap cropRoundedBitmap(Bitmap bitmap) {
        int cornerRadius = this.mArtistFrameView.getCornerRadius();
        if (cornerRadius != 0) {
            AnimTransView animTransView = this.mArtistFrameView.getAnimTransView();
            int dimension = ((int) getResources().getDimension(R.dimen.playcontrol_bar_background_height)) - (animTransView.getPaddingRight() + animTransView.getPaddingLeft());
            return C1780b.m4785a(C1780b.m4784a(C1780b.m4786a(bitmap, dimension, dimension, false), true), cornerRadius, true);
        }
        return bitmap;
    }

    private void loadArtsThemeImage() {
        if (this.mThemeArtsDrawable == null) {
            this.mThemeArtsDrawable = ThemeManager.m3265a(ThemeElement.PLAY_BAR_DEF_ARTIST_IMAGE);
        }
        if (this.mThemeArtsDrawable == null) {
            this.mThemeArtsDrawable = getResources().getDrawable(R.drawable.img_artist_default);
        }
        int dimension = (int) getResources().getDimension(R.dimen.playcontrol_bar_height);
        this.mSongImage.setImageBitmap(cropRoundedBitmap(C1780b.m4781a(this.mThemeArtsDrawable, dimension, dimension)));
    }

    public void pictureDeleted(MediaItem mediaItem) {
        if (isViewAccessAble() && mediaItem.equals(Cache.getInstance().getCurrentPlayMediaItem())) {
            setArtistBitmap(null, null);
        }
    }
}
