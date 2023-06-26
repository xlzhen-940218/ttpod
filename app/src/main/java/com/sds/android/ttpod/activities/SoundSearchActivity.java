package com.sds.android.ttpod.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.voicedragon.musicclient.record.DoresoMusicTrack;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.p055a.OnlineMediaSearchAPI;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.sdk.lib.p065e.TaskScheduler;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.RequestCallback;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.activities.soundsearch.SoundSearchHistoryActivity;
import com.sds.android.ttpod.activities.soundsearch.SoundSearchMultiResultActivity;
import com.sds.android.ttpod.activities.soundsearch.SoundSearchResultActivity;
import com.sds.android.ttpod.component.ActionBarController;
import com.sds.android.ttpod.component.soundsearch.SoundSearchHistory;
import com.sds.android.ttpod.component.soundsearch.SoundSearchInfo;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.modules.search.SoundRecognizer;
import com.sds.android.ttpod.framework.p106a.MediaItemUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SPage;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.SupportFactory;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.SoundSearchAnimView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SoundSearchActivity extends SlidingClosableActivity {
    public static final String EXTRA_RECOGNIZE_RESULT = "extra_recognize_result";
    private static final int MSG_REFRESH = 0;
    private static final int PAGE_SIZE = 50;
    private static final int REFRESH_TIME = 20;
    private static final String TAG = "SoundSearchActivity";
    private static final int TIME_OUT = 15000;
    private ImageView mImageViewState;
    private SoundSearchAnimView mRecognizeAnimView;
    private SoundSearchHistory mRecognizerHistory;
    private TextView mRecognizerSubTitle;
    private TextView mRecognizerTitle;
    private View mSoundSearchView;
    private long mStartTime;
    private Handler mHandler = new Handler() { // from class: com.sds.android.ttpod.activities.SoundSearchActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 0) {
                SoundSearchActivity.this.mRecognizeAnimView.setVolume(((Double) CommandCenter.getInstance().m4602a(new Command(CommandID.GET_SEARCH_RECOGNIZE_VOLUME, new Object[0]), Double.class)).doubleValue());
                SoundSearchActivity.this.mRecognizeAnimView.invalidate();
                sendEmptyMessageDelayed(0, 20L);
            }
        }
    };
    private Runnable mTimeOutRunnable = new Runnable() { // from class: com.sds.android.ttpod.activities.SoundSearchActivity.2
        @Override // java.lang.Runnable
        public void run() {
            SoundSearchActivity.this.stopSoundSearch();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.activities.SoundSearchActivity$a */
    /* loaded from: classes.dex */
    public enum EnumC0680a {
        RECOGNIZE_IDLE,
        RECOGNIZING,
        RECOGNIZE_SUCCESSFUL,
        RECOGNIZE_FAIL,
        RECOGNIZE_NOT_CONNECT
    }

    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPage(SPage.PAGE_RECOGNIZE);
        setContentView(R.layout.activity_soundsearch);
        setTitle(R.string.search_sound_search);
        ActionBarController actionBarController = getActionBarController();
        actionBarController.m7179d();
        actionBarController.m7178d(R.drawable.img_actionitem_history).m7167a(new ActionBarController.InterfaceC1072b() { // from class: com.sds.android.ttpod.activities.SoundSearchActivity.3
            @Override // com.sds.android.ttpod.component.ActionBarController.InterfaceC1072b
            /* renamed from: a */
            public void mo5433a(ActionBarController.C1070a c1070a) {
                SoundSearchActivity.this.startActivity(new Intent(SoundSearchActivity.this, SoundSearchHistoryActivity.class));
                //SUserUtils.m4951b("PAGE_CLICK", SAction.ACTION_SOUND_RECOGNIZE_HISTORY, SPage.PAGE_RECOGNIZE, SPage.PAGE_SOUND_RECOGNIZE_HISTORY);
            }
        });
        this.mRecognizeAnimView = (SoundSearchAnimView) findViewById(R.id.soundSearchAnimView);
        this.mImageViewState = (ImageView) findViewById(R.id.imageview_soundsearch_state);
        this.mRecognizerTitle = (TextView) findViewById(R.id.textview_soundsearch_title);
        this.mRecognizerSubTitle = (TextView) findViewById(R.id.textview_soundsearch_sub_title);
        this.mSoundSearchView = findViewById(R.id.imagview_soundsearch_center);
        this.mSoundSearchView.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.SoundSearchActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SoundSearchActivity.this.mRecognizeAnimView.getVisibility() == View.VISIBLE) {
                    SoundSearchActivity.this.cancelSoundSearch();
                    return;
                }
                //SearchStatistic.m4926m();
                SoundSearchActivity.this.startSoundSearch();
            }
        });
        this.mRecognizerHistory = new SoundSearchHistory(null);
        if (SupportFactory.m2397a(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PLAYING) {
            Preferences.m3063I(true);
            CommandCenter.getInstance().execute(new Command(CommandID.PAUSE, new Object[0]));
        } else {
            Preferences.m3063I(false);
        }
        startSoundSearch();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        cancelSoundSearch();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (SupportFactory.m2397a(BaseApplication.getApplication()).m2463m() == PlayStatus.STATUS_PAUSED && Preferences.m2947ax()) {
            CommandCenter.getInstance().execute(new Command(CommandID.RESUME, new Object[0]));
        }
        this.mHandler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class<?> cls = getClass();
        map.put(CommandID.SEARCH_RECOGNIZE_ERROR, ReflectUtils.m8375a(cls, "searchRecognizeError", SoundRecognizer.EnumC1975a.class));
        map.put(CommandID.SEARCH_RECOGNIZE_SUCCESS, ReflectUtils.m8375a(cls, "searchRecognizeSuccess", List.class));
    }

    public void searchRecognizeError(SoundRecognizer.EnumC1975a enumC1975a) {
        LogUtils.debug(TAG, "searchRecognizeError: " + enumC1975a);
        if (enumC1975a == SoundRecognizer.EnumC1975a.NOT_CONNECT) {
            updateUI(EnumC0680a.RECOGNIZE_NOT_CONNECT);
        } else {
            updateUI(EnumC0680a.RECOGNIZE_FAIL);
        }
    }

    public void searchRecognizeSuccess(List<DoresoMusicTrack> list) {
        if (list != null && !list.isEmpty()) {
            //SearchStatistic.m4927l();
            SoundSearchInfo[] soundSearchInfoArr = new SoundSearchInfo[list.size()];
            int i = 0;
            for (DoresoMusicTrack doresoMusicTrack : list) {
                soundSearchInfoArr[i] = new SoundSearchInfo(doresoMusicTrack);
                i++;
            }
            processResults(soundSearchInfoArr);
            return;
        }
        updateUI(EnumC0680a.RECOGNIZE_FAIL);
    }

    private void processResults(SoundSearchInfo[] soundSearchInfoArr) {
        LogUtils.debug(TAG, "search start ");
        this.mStartTime = System.currentTimeMillis();
        TaskScheduler.m8582a(new TaskScheduler.AbstractAsyncTaskC0595a<SoundSearchInfo[], SoundSearchInfo[]>(soundSearchInfoArr) { // from class: com.sds.android.ttpod.activities.SoundSearchActivity.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public SoundSearchInfo[] mo1981a(SoundSearchInfo[] soundSearchInfoArr2) {
                if (SoundSearchActivity.this.isFinishing()) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                for (SoundSearchInfo soundSearchInfo : soundSearchInfoArr2) {
                    OnlineMediaItemsResult m8531f = OnlineMediaSearchAPI.m8861a(soundSearchInfo.m5824e() + " " + soundSearchInfo.m5826c(), 1, 50).m8531f();
                    if (m8531f != null) {
                        ArrayList<OnlineMediaItem> dataList = m8531f.getDataList();
                        if (dataList != null && !dataList.isEmpty()) {
                            SoundSearchActivity.this.chooseSuitableMediaItem(soundSearchInfo, dataList);
                            arrayList.add(soundSearchInfo);
                        } else {
                            OnlineMediaSearchAPI.m8862a(soundSearchInfo.m5824e() + " " + soundSearchInfo.m5826c()).m8544a((RequestCallback<BaseResult>) null);
                        }
                    } else {
                        OnlineMediaSearchAPI.m8862a(soundSearchInfo.m5824e() + " " + soundSearchInfo.m5826c()).m8544a((RequestCallback<BaseResult>) null);
                    }
                }
                if (arrayList.isEmpty()) {
                    return null;
                }
                SoundSearchInfo[] soundSearchInfoArr3 = new SoundSearchInfo[arrayList.size()];
                arrayList.toArray(soundSearchInfoArr3);
                return soundSearchInfoArr3;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sds.android.sdk.lib.p065e.TaskScheduler.AbstractAsyncTaskC0595a
            /* renamed from: b  reason: avoid collision after fix types in other method */
            public void postExecute(SoundSearchInfo[] soundSearchInfoArr2) {
                LogUtils.debug(SoundSearchActivity.TAG, "search end, cost time: " + (System.currentTimeMillis() - SoundSearchActivity.this.mStartTime) + "ms");
                LogUtils.debug(SoundSearchActivity.TAG, "search end, result: " + Arrays.toString(soundSearchInfoArr2));
                if (soundSearchInfoArr2 == null) {
                    SoundSearchActivity.this.updateUI(EnumC0680a.RECOGNIZE_FAIL);
                    return;
                }
                if (soundSearchInfoArr2.length == 1) {
                    SoundSearchActivity.this.startActivity(new Intent(SoundSearchActivity.this, SoundSearchResultActivity.class).putExtra(SoundSearchActivity.EXTRA_RECOGNIZE_RESULT, (Parcelable) soundSearchInfoArr2[0]));
                } else {
                    SoundSearchActivity.this.startActivity(new Intent(SoundSearchActivity.this, SoundSearchMultiResultActivity.class).putExtra(SoundSearchActivity.EXTRA_RECOGNIZE_RESULT, soundSearchInfoArr2));
                }
                SoundSearchActivity.this.updateUI(EnumC0680a.RECOGNIZE_IDLE);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void chooseSuitableMediaItem(SoundSearchInfo soundSearchInfo, List<OnlineMediaItem> list) {
        OnlineMediaItem onlineMediaItem;
        Iterator<OnlineMediaItem> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                onlineMediaItem = null;
                break;
            }
            onlineMediaItem = it.next();
            if (onlineMediaItem.getTitle().equals(soundSearchInfo.m5824e()) && onlineMediaItem.getArtist().equals(soundSearchInfo.m5826c())) {
                break;
            }
        }
        if (onlineMediaItem == null) {
            onlineMediaItem = list.get(0);
        }
        soundSearchInfo.m5828a(MediaItemUtils.m4716a(onlineMediaItem));
        this.mRecognizerHistory.m4095a(soundSearchInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSoundSearch() {
        CommandCenter.getInstance().execute(new Command(CommandID.START_SEARCH_RECOGNIZE, new Object[0]));
        updateUI(EnumC0680a.RECOGNIZING);
        this.mHandler.postDelayed(this.mTimeOutRunnable, 15000L);
        this.mHandler.sendEmptyMessage(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopSoundSearch() {
        CommandCenter.getInstance().execute(new Command(CommandID.STOP_SEARCH_RECOGNIZE, new Object[0]));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelSoundSearch() {
        CommandCenter.getInstance().execute(new Command(CommandID.CANCEL_SEARCH_RECOGNIZE, new Object[0]));
        updateUI(EnumC0680a.RECOGNIZE_IDLE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUI(EnumC0680a enumC0680a) {
        String obj;
        String obj2;
        int i = R.drawable.img_imageview_soundsearch_state_fail;
        if (!isFinishing()) {
            LogUtils.debug(TAG, "updateUI mState: " + enumC0680a);
            boolean z = enumC0680a == EnumC0680a.RECOGNIZING;
            switch (enumC0680a) {
                case RECOGNIZING:
                    obj = getText(R.string.soundsearch_state_regoznizing_title).toString();
                    obj2 = getText(R.string.soundsearch_state_regoznizing).toString();
                    i = R.drawable.img_imageview_soundsearch_state_regoznizing;
                    break;
                case RECOGNIZE_FAIL:
                    obj = getText(R.string.soundsearch_state_fail_title).toString();
                    obj2 = getText(R.string.soundsearch_state_fail).toString();
                    break;
                case RECOGNIZE_NOT_CONNECT:
                    obj = getText(R.string.soundsearch_state_not_connect_title).toString();
                    obj2 = getText(R.string.soundsearch_state_not_connect).toString();
                    break;
                default:
                    obj = getText(R.string.soundsearch_state_idle_title).toString();
                    obj2 = getText(R.string.soundsearch_state_idle).toString();
                    i = R.drawable.img_imageview_soundsearch_state_idle;
                    break;
            }
            this.mRecognizeAnimView.setVisibility(z ? View.VISIBLE : View.GONE);
            this.mImageViewState.setImageDrawable(getResources().getDrawable(i));
            this.mRecognizerTitle.setText(obj);
            this.mRecognizerSubTitle.setText(obj2);
            if (!z) {
                this.mHandler.removeMessages(0);
            }
        }
    }
}
