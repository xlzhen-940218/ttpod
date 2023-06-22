package com.sds.android.ttpod.activities.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.sdk.lib.util.FileUtils;
import com.sds.android.sdk.lib.util.JSONUtils;
import com.sds.android.sdk.lib.util.LogUtils;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.common.p082a.BaseDialog;
import com.sds.android.ttpod.component.p085b.ActionItem;
import com.sds.android.ttpod.component.p085b.CheckableActionItem;
import com.sds.android.ttpod.component.p087d.p088a.ListDialog;
import com.sds.android.ttpod.component.p087d.p088a.SingleChoiceListDialog;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.p106a.DownloadUtils;
import com.sds.android.ttpod.framework.p106a.OnlineMediaItemUtils;
import com.sds.android.ttpod.framework.p106a.p107a.SearchStatistic;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class AlbumDownloadSelectActivity extends SlidingClosableActivity {
    public static final String KEY_MEDIA_LIST = "key_media_list";
    private static final String TAG = "AlbumDownloadSelectActivity";
    private BaseAdapter mAdapter;
    private Button mButtonStartDownload;
    private CheckBox mCheckBoxAll;
    private List<MediaItem> mData;
    private ListView mListView;
    private AudioQuality mOriginQuality;
    private View mSelectAllView;
    private AudioQuality mSelectedQuality;
    private SparseBooleanArray mSelectedArray = new SparseBooleanArray();
    private View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.search.AlbumDownloadSelectActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.checkbox_select_all /* 2131230789 */:
                case R.id.text_select_all /* 2131230790 */:
                    AlbumDownloadSelectActivity.this.performSelectAllClick();
                    return;
                case R.id.button_start_download /* 2131230791 */:
                    AlbumDownloadSelectActivity.this.performDownloadClick();
                    return;
                default:
                    return;
            }
        }
    };

    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_album_download_select);
        setTitle(R.string.album_download_select_activity_title);
        initViews();
        initDatas();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_DOWNLOAD_TASK_STATE, ReflectUtils.m8375a(getClass(), "updateTaskState", DownloadTaskInfo.class));
    }

    @Override // com.sds.android.ttpod.activities.base.ThemeActivity
    public void updateTaskState(DownloadTaskInfo downloadTaskInfo) {
        super.updateTaskState(downloadTaskInfo);
        Integer type = downloadTaskInfo.getType();
        Integer state = downloadTaskInfo.getState();
        if (type == DownloadTaskInfo.TYPE_AUDIO || type == DownloadTaskInfo.TYPE_VIDEO) {
            if (state.intValue() == 4 || state.intValue() == 1) {
                LogUtils.m8388a(TAG, "updateTaskState " + downloadTaskInfo.getSavePath() + ",state=" + state);
                notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performDownloadClick() {
        if (getSelectItems().size() > 0) {
            showAlbumQualityDialog(this).show();
        }
        SearchStatistic.m4932g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performSelectAllClick() {
        if (this.mCheckBoxAll.getTag() == null) {
            selectAll();
            this.mCheckBoxAll.setTag(this.mCheckBoxAll);
        } else {
            selectNone();
            this.mCheckBoxAll.setTag(null);
        }
        updateAction();
        SearchStatistic.m4931h();
    }

    private void initDatas() {
        Intent intent = getIntent();
        if (intent != null) {
            this.mData = (List) intent.getSerializableExtra(KEY_MEDIA_LIST);
            notifyDataSetChanged();
        }
    }

    private void notifyDataSetChanged() {
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    private void initViews() {
        this.mListView = (ListView) findViewById(R.id.listview);
        this.mAdapter = new C0855a();
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.activities.search.AlbumDownloadSelectActivity.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                AlbumDownloadSelectActivity.this.mSelectedArray.put(i, !AlbumDownloadSelectActivity.this.mSelectedArray.get(i));
                AlbumDownloadSelectActivity.this.mAdapter.notifyDataSetChanged();
                AlbumDownloadSelectActivity.this.updateAction();
            }
        });
        this.mCheckBoxAll = (CheckBox) findViewById(R.id.checkbox_select_all);
        this.mCheckBoxAll.setOnClickListener(this.mOnClickListener);
        this.mSelectAllView = findViewById(R.id.text_select_all);
        this.mSelectAllView.setOnClickListener(this.mOnClickListener);
        this.mButtonStartDownload = (Button) findViewById(R.id.button_start_download);
        this.mButtonStartDownload.setOnClickListener(this.mOnClickListener);
        updateAction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAction() {
        int selectedCount = getSelectedCount();
        this.mCheckBoxAll.setChecked(selectedCount == this.mAdapter.getCount() && selectedCount != 0);
        this.mButtonStartDownload.setText(getString(R.string.download_count, new Object[]{Integer.valueOf(selectedCount)}));
    }

    public void selectAll() {
        int count = this.mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            this.mSelectedArray.put(i, true);
        }
        this.mAdapter.notifyDataSetChanged();
    }

    public void selectNone() {
        this.mSelectedArray.clear();
        this.mAdapter.notifyDataSetChanged();
    }

    public List<MediaItem> getSelectItems() {
        int size = this.mSelectedArray.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            if (this.mSelectedArray.valueAt(i)) {
                arrayList.add(this.mData.get(this.mSelectedArray.keyAt(i)));
            }
        }
        return arrayList;
    }

    public int getSelectedCount() {
        int i = 0;
        int size = this.mSelectedArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.mSelectedArray.valueAt(i2)) {
                i++;
            }
        }
        return i;
    }

    private ListDialog showAlbumQualityDialog(Context context) {
        this.mOriginQuality = Preferences.m3056M();
        this.mSelectedQuality = this.mOriginQuality;
        SingleChoiceListDialog singleChoiceListDialog = new SingleChoiceListDialog(context, buildAudioQualityActionItems(), (BaseDialog.InterfaceC1064a<SingleChoiceListDialog>) null, (BaseDialog.InterfaceC1064a<SingleChoiceListDialog>) null);
        singleChoiceListDialog.setTitle(R.string.album_download_dialog_title);
        singleChoiceListDialog.m6778c(this.mOriginQuality.ordinal());
        singleChoiceListDialog.m6844a(new ActionItem.InterfaceC1135b() { // from class: com.sds.android.ttpod.activities.search.AlbumDownloadSelectActivity.3
            @Override // com.sds.android.ttpod.component.p085b.ActionItem.InterfaceC1135b
            /* renamed from: a */
            public void mo5409a(ActionItem actionItem, int i) {
                AlbumDownloadSelectActivity.this.mSelectedQuality = AudioQuality.values()[actionItem.m7005e()];
                SearchStatistic.m4949a(AlbumDownloadSelectActivity.this.mSelectedQuality);
            }
        });
        singleChoiceListDialog.m7261a(R.string.confirm, new BaseDialog.InterfaceC1064a() { // from class: com.sds.android.ttpod.activities.search.AlbumDownloadSelectActivity.4
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a */
            public void mo2038a(Object obj) {
                if (!AlbumDownloadSelectActivity.this.mOriginQuality.equals(AlbumDownloadSelectActivity.this.mSelectedQuality)) {
                    Preferences.m2889e(AlbumDownloadSelectActivity.this.mSelectedQuality);
                }
                CommandCenter.m4607a().m4605a(new Command(CommandID.ASYN_ADD_DOWNLOAD_TASK_LIST, DownloadUtils.m4758a(AlbumDownloadSelectActivity.this.getSelectItems(), Preferences.m3056M()), Boolean.FALSE), 10);
                SearchStatistic.m4929j();
            }
        }, R.string.cancel, new BaseDialog.InterfaceC1064a() { // from class: com.sds.android.ttpod.activities.search.AlbumDownloadSelectActivity.5
            @Override // com.sds.android.ttpod.common.p082a.BaseDialog.InterfaceC1064a
            /* renamed from: a */
            public void mo2038a(Object obj) {
                Preferences.m2889e(AlbumDownloadSelectActivity.this.mOriginQuality);
                SearchStatistic.m4930i();
            }
        });
        return singleChoiceListDialog;
    }

    private CheckableActionItem[] buildAudioQualityActionItems() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CheckableActionItem(AudioQuality.LOSSLESS.ordinal(), R.string.album_quality_lossless_title));
        arrayList.add(new CheckableActionItem(AudioQuality.SUPER.ordinal(), R.string.album_quality_high_title));
        arrayList.add(new CheckableActionItem(AudioQuality.STANDARD.ordinal(), R.string.album_quality_normal_title));
        arrayList.add(new CheckableActionItem(AudioQuality.COMPRESSED.ordinal(), R.string.album_quality_compress_title));
        return (CheckableActionItem[]) arrayList.toArray(new CheckableActionItem[arrayList.size()]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.sds.android.ttpod.activities.search.AlbumDownloadSelectActivity$a */
    /* loaded from: classes.dex */
    public class C0855a extends BaseAdapter {
        private C0855a() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (AlbumDownloadSelectActivity.this.mData == null) {
                return 0;
            }
            return AlbumDownloadSelectActivity.this.mData.size();
        }

        @Override // android.widget.Adapter
        /* renamed from: a */
        public MediaItem getItem(int i) {
            return (MediaItem) AlbumDownloadSelectActivity.this.mData.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(AlbumDownloadSelectActivity.this, R.layout.album_download_select_item, null);
                view.setTag(new C0857b(view));
            }
            C0857b c0857b = (C0857b) view.getTag();
            MediaItem item = getItem(i);
            c0857b.f2941b.setChecked(AlbumDownloadSelectActivity.this.mSelectedArray.get(i));
            c0857b.f2941b.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.search.AlbumDownloadSelectActivity.a.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AlbumDownloadSelectActivity.this.mSelectedArray.put(i, !AlbumDownloadSelectActivity.this.mSelectedArray.get(i));
                    AlbumDownloadSelectActivity.this.updateAction();
                }
            });
            c0857b.f2942c.setText(item.getTitle());
            c0857b.m7824a(AlbumDownloadSelectActivity.this, item.getArtist(), item.getUseCount().intValue(), true);
            c0857b.f2944e.setVisibility(View.GONE);
            c0857b.m7822a(item);
            c0857b.f2946g.setText(m7825a(item) ? AlbumDownloadSelectActivity.this.getString(R.string.album_song_downloaded) : "");
            return view;
        }

        /* renamed from: a */
        private boolean m7825a(MediaItem mediaItem) {
            OnlineMediaItem onlineMediaItem = (OnlineMediaItem) JSONUtils.fromJson(mediaItem.getExtra(), OnlineMediaItem.class);
            if (onlineMediaItem == null) {
                return false;
            }
            OnlineMediaItem.Url m4682b = OnlineMediaItemUtils.m4682b(onlineMediaItem, Preferences.m3056M());
            return m4682b != null && FileUtils.m8414b(OnlineMediaItemUtils.m4688a(onlineMediaItem, m4682b));
        }
    }

    /* renamed from: com.sds.android.ttpod.activities.search.AlbumDownloadSelectActivity$b */
    /* loaded from: classes.dex */
    private class C0857b {

        /* renamed from: b */
        private CheckBox f2941b;

        /* renamed from: c */
        private TextView f2942c;

        /* renamed from: d */
        private TextView f2943d;

        /* renamed from: e */
        private ImageView f2944e;

        /* renamed from: f */
        private ImageView f2945f;

        /* renamed from: g */
        private TextView f2946g;

        public C0857b(View view) {
            this.f2941b = (CheckBox) view.findViewById(R.id.checked_view);
            this.f2942c = (TextView) view.findViewById(R.id.title_view);
            this.f2943d = (TextView) view.findViewById(R.id.subtitle_view);
            this.f2944e = (ImageView) view.findViewById(R.id.flag_mv_view);
            this.f2945f = (ImageView) view.findViewById(R.id.flag_quality_view);
            this.f2946g = (TextView) view.findViewById(R.id.text_download_state);
        }

        /* renamed from: a */
        public void m7822a(MediaItem mediaItem) {
            if (mediaItem != null) {
                AudioQuality quality = mediaItem.quality();
                if (quality.ordinal() >= AudioQuality.HIGH.ordinal()) {
                    this.f2945f.setVisibility(View.VISIBLE);
                    this.f2945f.setImageResource(quality == AudioQuality.LOSSLESS ? R.drawable.img_flag_lossless : R.drawable.img_flag_hq);
                    return;
                }
                this.f2945f.setVisibility(View.GONE);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10 */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.CharSequence, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.CharSequence] */
        /* JADX WARN: Type inference failed for: r0v7, types: [android.text.SpannableString] */
        /* JADX WARN: Type inference failed for: r1v1, types: [android.widget.TextView] */
        /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.StringBuilder] */
        /* renamed from: a */
        public void m7824a(Context context, CharSequence charSequence, int i, boolean z) {
            if (charSequence != null) {
                CharSequence subSequence = charSequence.length() > 40 ? charSequence.subSequence(0, 40) : charSequence;
                if (z) {
                    int length = subSequence.length();
                    subSequence = new SpannableString(subSequence + "  " + i);
                    ((SpannableString)subSequence).setSpan(new ImageSpan(context, (int) R.drawable.img_favorite_count_selected, 1), length + 1, length + 2, 33);
                }
                this.f2943d.setText(subSequence);
            }
        }
    }
}
