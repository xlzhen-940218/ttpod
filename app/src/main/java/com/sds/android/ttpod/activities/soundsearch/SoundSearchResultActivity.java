package com.sds.android.ttpod.activities.soundsearch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.sdk.lib.util.StringUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.SoundSearchActivity;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.component.p087d.PopupsUtils;
import com.sds.android.ttpod.component.soundsearch.SoundSearchInfo;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.skin.p132d.Lyric;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.search.SearchStatus;
import com.sds.android.ttpod.framework.support.search.task.ResultData;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.FavoriteUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SoundSearchResultActivity extends SlidingClosableActivity {
    private static final int ARG_REFRESH_CANCEL = -1;
    private static final int ARG_REFRESH_REPEAT = 1;
    private static final int HIGH_LIGHT_COLOR = -14256489;
    private static final int HIGH_LIGHT_SIZE = 18;
    private static final int NORMAL_COLOR = -4600363;
    private static final int NORMAL_SIZE = 14;
    private static final long REFRESH_DELAY = 50;
    private static final int WHAT_REFRESH_LYRIC = 1;
    private static final int WHAT_REFRESH_LYRIC_WHILE_NO_PLAY = 2;
    private TextView mAlbum;
    private ImageView mCover;
    private TextView mFavoriteCount;
    private ImageView mImageViewAddFavor;
    private ImageView mImageViewDownload;
    private ImageView mImageViewPlay;
    private ImageView mImageViewShare;
    private long mLyricOffset;
    private LyricView mLyricView;
    private MediaItem mMediaItem;
    private SoundSearchInfo mSoundSearchInfo;
    private long mStartTime;
    private TextView mTitle;
    private boolean mHasBeenPlayed = false;
    private Handler mHandler = new Handler() { // from class: com.sds.android.ttpod.activities.soundsearch.SoundSearchResultActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                if (SoundSearchResultActivity.this.mLyricView != null) {
                    SoundSearchResultActivity.this.mLyricView.setPlayingTime(SupportFactory.m2397a(BaseApplication.getApplication()).m2465k().intValue());
                    if (message.arg1 == 1) {
                        SoundSearchResultActivity.this.refreshLyric(1);
                    }
                }
            } else if (message.what == 2) {
                SoundSearchResultActivity.this.tryToRefreshLyricWhileNoPlay();
            }
        }
    };
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.soundsearch.SoundSearchResultActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imageview_soundsearch_download /* 2131230980 */:
                    new DownloadMenuHandler(SoundSearchResultActivity.this).m6927a(SoundSearchResultActivity.this.mMediaItem, "search");
                    return;
                case R.id.imageview_soundsearch_play /* 2131230981 */:
                    if (Cache.getInstance().getCurrentPlayMediaItem().getID().equals(SoundSearchResultActivity.this.mMediaItem.getID())) {
                        if (SupportFactory.m2397a(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PAUSED) {
                            CommandCenter.getInstance().m4606a(new Command(CommandID.RESUME, new Object[0]));
                        } else if (SupportFactory.m2397a(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PLAYING) {
                            CommandCenter.getInstance().m4606a(new Command(CommandID.PAUSE, new Object[0]));
                        }
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(SoundSearchResultActivity.this.mMediaItem);
                        Preferences.m3063I(false);
                        CommandCenter.getInstance().m4606a(new Command(CommandID.SYNC_NET_TEMPORARY_GROUP, arrayList));
                        CommandCenter.getInstance().m4606a(new Command(CommandID.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, SoundSearchResultActivity.this.mMediaItem));
                    }
                    SoundSearchResultActivity.this.mHasBeenPlayed = true;
                    return;
                case R.id.imageview_soundsearch_share /* 2131230982 */:
                    PopupsUtils.m6756a((Activity) SoundSearchResultActivity.this, SoundSearchResultActivity.this.mMediaItem);
                    return;
                case R.id.lyricview_soundsearch /* 2131230983 */:
                case R.id.imageview_soundsearch_cover /* 2131230984 */:
                default:
                    return;
                case R.id.imageview_soundsearch_favorite /* 2131230985 */:
                    Boolean valueOf = Boolean.valueOf(!((Boolean) SoundSearchResultActivity.this.mImageViewAddFavor.getTag()).booleanValue());
                    if (Preferences.m2954aq() == null) {
                        SoundSearchResultActivity.this.mImageViewAddFavor.setTag(false);
                        EntryUtils.m8297a(true);
                        return;
                    }
                    SoundSearchResultActivity.this.mMediaItem.setFav(valueOf.booleanValue());
                    SoundSearchResultActivity.this.mImageViewAddFavor.setTag(valueOf);
                    SoundSearchResultActivity.this.mImageViewAddFavor.setImageResource(valueOf.booleanValue() ? R.drawable.img_favourite_selected : R.drawable.img_favourite_normal);
                    if (valueOf.booleanValue()) {
                        FavoriteUtils.m8283a(SoundSearchResultActivity.this.mMediaItem, true);
                        return;
                    } else {
                        FavoriteUtils.m8282b(SoundSearchResultActivity.this.mMediaItem, false);
                        return;
                    }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(R.string.search_sound_search);
        ActionBarController actionBarController = getActionBarController();
        actionBarController.m7179d();
        actionBarController.m7178d(R.drawable.img_actionitem_history).m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.soundsearch.SoundSearchResultActivity.3
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                SoundSearchResultActivity.this.startActivity(new Intent(SoundSearchResultActivity.this, SoundSearchHistoryActivity.class));
            }
        });
        setContentView(R.layout.activity_soundsearch_result);
        initView();
        initData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        initFav();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Cache.getInstance().m3149o();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.UPDATE_PLAY_STATUS, ReflectUtils.m8375a(cls, "updatePlayStatus", PlayStatus.class));
        map.put(CommandID.UPDATE_SEARCH_LYRIC_STATE, ReflectUtils.m8375a(cls, "updateSearchLyricState", SearchStatus.class, List.class, String.class, Lyric.class));
        map.put(CommandID.UPDATE_SEARCH_PICTURE_STATE, ReflectUtils.m8375a(cls, "updateSearchPictureState", SearchStatus.class, List.class, String.class, Bitmap.class));
    }

    public void updateSearchPictureState(SearchStatus searchStatus, List<ResultData> list, String str, Bitmap bitmap) {
        switch (searchStatus) {
            case SEARCH_LOCAL_FINISHED:
            case SEARCH_DOWNLOAD_FINISHED:
                this.mCover.setImageBitmap(bitmap);
                return;
            case SEARCH_ONLINE_FINISHED:
                if (list != null && list.size() > 0) {
                    ResultData.Item item = list.get(0).m2179c()[0];
                    CommandCenter.getInstance().m4596b(new Command(CommandID.START_DOWNLOAD_SEARCH_PICTURE, item.m2173c(), item.m2172d(), this.mMediaItem));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void updateSearchLyricState(SearchStatus searchStatus, List<ResultData> list, String str, Lyric lyric) {
        if (StringUtils.m8344a(str, this.mMediaItem.getID())) {
            switch (searchStatus) {
                case SEARCH_LOCAL_FINISHED:
                case SEARCH_DOWNLOAD_FINISHED:
                    this.mLyricView.setLyric(lyric);
                    this.mLyricOffset = this.mSoundSearchInfo != null ? this.mSoundSearchInfo.m5825d() : 0L;
                    this.mLyricView.setPlayingTime(this.mLyricOffset);
                    tryToRefreshLyricWhileNoPlay();
                    return;
                case SEARCH_ONLINE_FINISHED:
                    if (list != null && list.size() > 0) {
                        CommandCenter.getInstance().m4596b(new Command(CommandID.START_DOWNLOAD_SEARCH_LYRIC, list.get(0).m2179c()[0], this.mMediaItem));
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void updatePlayStatus(PlayStatus playStatus) {
        this.mImageViewPlay.setImageResource(playStatus == PlayStatus.STATUS_PLAYING ? R.drawable.xml_imageview_soundsearch_result_pause : R.drawable.xml_imageview_soundsearch_result_play);
        refreshLyric(playStatus == PlayStatus.STATUS_PLAYING ? 1 : -1);
    }

    private void initFav() {
        this.mMediaItem.setFav(MediaItemUtils.m4715a(this.mMediaItem));
        this.mImageViewAddFavor.setTag(Boolean.valueOf(this.mMediaItem.getFav()));
        this.mImageViewAddFavor.setImageResource(this.mMediaItem.getFav() ? R.drawable.img_favourite_selected : R.drawable.img_favourite_normal);
    }

    private void initView() {
        this.mCover = (ImageView) findViewById(R.id.imageview_soundsearch_cover);
        this.mImageViewDownload = (ImageView) findViewById(R.id.imageview_soundsearch_download);
        this.mImageViewDownload.setOnClickListener(this.mOnClickListener);
        this.mImageViewPlay = (ImageView) findViewById(R.id.imageview_soundsearch_play);
        this.mImageViewPlay.setOnClickListener(this.mOnClickListener);
        this.mImageViewShare = (ImageView) findViewById(R.id.imageview_soundsearch_share);
        this.mImageViewShare.setOnClickListener(this.mOnClickListener);
        this.mImageViewAddFavor = (ImageView) findViewById(R.id.imageview_soundsearch_favorite);
        this.mImageViewAddFavor.setOnClickListener(this.mOnClickListener);
        this.mTitle = (TextView) findViewById(R.id.textview_soundsearch_title);
        this.mAlbum = (TextView) findViewById(R.id.textview_soundsearch_album);
        this.mLyricView = (LyricView) findViewById(R.id.lyricview_soundsearch);
        this.mFavoriteCount = (TextView) findViewById(R.id.textview_soundsearch_favorite_count);
    }

    private void initData() {
        String album = "";
        this.mSoundSearchInfo = (SoundSearchInfo) getIntent().getParcelableExtra(SoundSearchActivity.EXTRA_RECOGNIZE_RESULT);
        this.mMediaItem = this.mSoundSearchInfo.m5823f();
        if (this.mMediaItem != null) {
            this.mTitle.setText(this.mMediaItem.getTitle());
            String artist = this.mMediaItem.getArtist();
            if (!TTTextUtils.isValidateMediaString(artist)) {
                artist = "";
            }
            if (TTTextUtils.isValidateMediaString(this.mMediaItem.getAlbum())) {
                artist = artist + (artist.length() > 0 ? "-" + album : album);
            }
            this.mAlbum.setText(artist);
            this.mFavoriteCount.setVisibility(View.GONE);
        }
        initCover();
        initLyric();
        startSearchLyricPic();
    }

    private void startSearchLyricPic() {
        startService(new Intent(this, SupportService.class).putExtra("command", "search_lyric_pic_command").putExtra("media", (Parcelable) this.mMediaItem));
    }

    private void initCover() {
    }

    private void initLyric() {
        this.mLyricView.setDefaultColorHighlight(HIGH_LIGHT_COLOR);
        this.mLyricView.setColorHighlight(HIGH_LIGHT_COLOR);
        this.mLyricView.setColorNormal(NORMAL_COLOR);
        this.mLyricView.setAlign(Paint.Align.CENTER);
        this.mLyricView.setDefaultFontSizeHighlight(18.0f);
        this.mLyricView.setDefaultFontSizeNormal(14.0f);
        this.mLyricView.setDisplayMode(LyricView.EnumC1996a.Normal);
        this.mLyricView.setTextSize(14.0f);
        this.mLyricView.setTextSizeHighlight(18.0f);
        this.mLyricView.setKalaOK(false);
        this.mLyricView.setSlowScroll(true);
        this.mStartTime = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryToRefreshLyricWhileNoPlay() {
        if (!this.mHasBeenPlayed) {
            this.mLyricView.setPlayingTime(this.mLyricOffset + (System.currentTimeMillis() - this.mStartTime));
            this.mHandler.removeMessages(2);
            this.mHandler.sendEmptyMessage(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshLyric(int i) {
        this.mHandler.removeMessages(1);
        Message obtainMessage = this.mHandler.obtainMessage(1);
        obtainMessage.arg1 = i;
        this.mHandler.sendMessageDelayed(obtainMessage, REFRESH_DELAY);
    }
}
