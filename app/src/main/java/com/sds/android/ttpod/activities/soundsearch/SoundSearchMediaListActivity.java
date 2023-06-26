package com.sds.android.ttpod.activities.soundsearch;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.ReflectUtils;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.component.p086c.DownloadMenuHandler;
import com.sds.android.ttpod.component.soundsearch.SoundSearchInfo;
import com.sds.android.ttpod.framework.base.p108a.Command;
import com.sds.android.ttpod.framework.base.p108a.CommandCenter;
import com.sds.android.ttpod.framework.modules.CommandID;
import com.sds.android.ttpod.framework.storage.environment.Preferences;
import com.sds.android.ttpod.framework.storage.p133a.Cache;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.sds.android.ttpod.utils.EntryUtils;
import com.sds.android.ttpod.utils.FavoriteUtils;
import com.sds.android.ttpod.utils.ListViewUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class SoundSearchMediaListActivity extends SlidingClosableActivity {
    private static final int MAX_RATING = 100;
    private C0896a mAdapter;
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            int m8266a = ListViewUtils.m8266a(SoundSearchMediaListActivity.this.mRecognizeResultListView.getHeaderViewsCount(), i, SoundSearchMediaListActivity.this.mAdapter.getCount());
            if (m8266a >= 0) {
                ArrayList arrayList = new ArrayList();
                for (SoundSearchInfo soundSearchInfo : SoundSearchMediaListActivity.this.mAdapter.f3027b) {
                    arrayList.add(soundSearchInfo.m5823f());
                }
                Preferences.m3063I(false);
                CommandCenter.getInstance().execute(new Command(CommandID.SYNC_NET_TEMPORARY_GROUP, arrayList));
                CommandCenter.getInstance().execute(new Command(CommandID.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, arrayList.get(m8266a)));
            }
        }
    };
    private AdapterView.OnItemLongClickListener mOnItemLongClickListener = new AdapterView.OnItemLongClickListener() { // from class: com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity.2
        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            SoundSearchMediaListActivity.this.showContextMenu(SoundSearchMediaListActivity.this.mAdapter.getItem(i).m5823f());
            return true;
        }
    };
    private long mPlayedSongId;
    private ListView mRecognizeResultListView;

    protected abstract void onAddHeaderView(ListView listView);

    protected abstract void onBindData(C0896a c0896a);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.SlidingClosableActivity, com.sds.android.ttpod.activities.base.ActionBarActivity, com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_soundsearch_media_list);
        MediaItem m3225N = Cache.getInstance().getCurrentPlayMediaItem();
        if (!m3225N.isNull()) {
            this.mPlayedSongId = m3225N.getSongID().longValue();
        }
        this.mRecognizeResultListView = (ListView) findViewById(R.id.soundsearch_list);
        this.mRecognizeResultListView.setOnItemClickListener(this.mOnItemClickListener);
        this.mRecognizeResultListView.setOnItemLongClickListener(this.mOnItemLongClickListener);
        onAddHeaderView(this.mRecognizeResultListView);
        this.mAdapter = new C0896a();
        this.mRecognizeResultListView.setAdapter((ListAdapter) this.mAdapter);
        onBindData(this.mAdapter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.framework.base.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sds.android.ttpod.activities.base.ThemeActivity, com.sds.android.ttpod.framework.base.BaseActivity
    public void onLoadCommandMap(Map<CommandID, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(CommandID.UPDATE_PLAYING_MEDIA_INFO, ReflectUtils.m8375a(getClass(), "updatePlayMediaInfo", new Class[0]));
    }

    public void updatePlayMediaInfo() {
        if (!Cache.getInstance().getCurrentPlayMediaItem().isNull()) {
            this.mPlayedSongId = Cache.getInstance().getCurrentPlayMediaItem().getSongID().longValue();
            this.mAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity$a */
    /* loaded from: classes.dex */
    public class C0896a extends BaseAdapter {

        /* renamed from: b */
        private SoundSearchInfo[] f3027b;

        C0896a() {
        }

        /* renamed from: a */
        public void m7771a(SoundSearchInfo[] soundSearchInfoArr) {
            this.f3027b = soundSearchInfoArr;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            if (this.f3027b != null) {
                return this.f3027b.length;
            }
            return 0;
        }

        @Override // android.widget.Adapter
        /* renamed from: a */
        public SoundSearchInfo getItem(int i) {
            return (this.f3027b == null || i < 0 || i >= this.f3027b.length) ? SoundSearchInfo.soundSearchInfo : this.f3027b[i];
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(SoundSearchMediaListActivity.this, R.layout.soundsearch_listview_item, null);
                view.setTag(new C0897a(view));
            }
            m7773a(i, view);
            return view;
        }

        /* renamed from: a */
        private void m7773a(int i, View view) {
            ((C0897a) view.getTag()).m7769a(this.f3027b[i]);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity$a$a */
        /* loaded from: classes.dex */
        public class C0897a {

            /* renamed from: b */
            private View f3029b;

            /* renamed from: c */
            private View f3030c;

            /* renamed from: d */
            private ImageView f3031d;

            /* renamed from: e */
            private View f3032e;

            /* renamed from: f */
            private TextView f3033f;

            /* renamed from: g */
            private TextView f3034g;

            /* renamed from: h */
            private TextView f3035h;

            /* renamed from: i */
            private TextView f3036i;

            public C0897a(View view) {
                this.f3029b = view.findViewById(R.id.recognizer_item);
                this.f3030c = view.findViewById(R.id.active_flag);
                this.f3031d = (ImageView) view.findViewById(R.id.toggle_favorite_imageview);
                this.f3032e = view.findViewById(R.id.context_menu);
                this.f3033f = (TextView) view.findViewById(R.id.textview_soundsearch_title);
                this.f3034g = (TextView) view.findViewById(R.id.textview_soundsearch_album);
                this.f3035h = (TextView) view.findViewById(R.id.textview_soundsearch_favorite_count);
                this.f3036i = (TextView) view.findViewById(R.id.media_similarity);
            }

            /* renamed from: a */
            public void m7769a(SoundSearchInfo soundSearchInfo) {
                String album = null;
                final MediaItem m5823f = soundSearchInfo.m5823f();
                this.f3033f.setText(m5823f.getTitle());
                if (m5823f.getSongID().longValue() == SoundSearchMediaListActivity.this.mPlayedSongId) {
                    this.f3030c.setVisibility(View.VISIBLE);
                    this.f3029b.setEnabled(false);
                    this.f3033f.setEnabled(false);
                } else {
                    this.f3030c.setVisibility(View.INVISIBLE);
                    this.f3029b.setEnabled(true);
                    this.f3033f.setEnabled(true);
                }
                String artist = m5823f.getArtist();
                if (!TTTextUtils.isValidateMediaString(artist)) {
                    artist = "";
                }
                if (TTTextUtils.isValidateMediaString(m5823f.getAlbum())) {
                    artist = artist + (artist.length() > 0 ? "-" + album : album);
                }
                this.f3034g.setText(artist);
                double m5827b = soundSearchInfo.m5827b();
                if (m5827b < 0.0d) {
                    m5827b = 100.0d;
                }
                this.f3035h.setVisibility(View.GONE);
                this.f3036i.setText(SoundSearchMediaListActivity.this.getString(R.string.soundsearch_similarity) + " " + m5827b + "%");
                this.f3031d.setTag(Boolean.valueOf(m5823f.getFav()));
                this.f3031d.setImageResource(m5823f.getFav() ? R.drawable.img_favourite_selected : R.drawable.img_favourite_normal);
                this.f3031d.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity.a.a.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        Boolean valueOf = Boolean.valueOf(!((Boolean) C0897a.this.f3031d.getTag()).booleanValue());
                        if (Preferences.m2954aq() == null) {
                            C0897a.this.f3031d.setTag(false);
                            EntryUtils.m8297a(true);
                            return;
                        }
                        m5823f.setFav(valueOf.booleanValue());
                        C0897a.this.f3031d.setTag(valueOf);
                        C0897a.this.f3031d.setImageResource(valueOf.booleanValue() ? R.drawable.img_favourite_selected : R.drawable.img_favourite_normal);
                        if (valueOf.booleanValue()) {
                            FavoriteUtils.m8283a(m5823f, true);
                        } else {
                            FavoriteUtils.m8282b(m5823f, false);
                        }
                    }
                });
                this.f3032e.setOnClickListener(new View.OnClickListener() { // from class: com.sds.android.ttpod.activities.soundsearch.SoundSearchMediaListActivity.a.a.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        SoundSearchMediaListActivity.this.showContextMenu(m5823f);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showContextMenu(MediaItem mediaItem) {
        new DownloadMenuHandler(this).m6927a(mediaItem, "search");
    }
}
